package com.cnpmnc.DreamCode.api.admin;

import com.cnpmnc.DreamCode.dto.request.SupplierCreationRequest;
import com.cnpmnc.DreamCode.dto.request.SupplierUpdateRequest;
import com.cnpmnc.DreamCode.dto.request.UserCreationRequest;
import com.cnpmnc.DreamCode.dto.request.UserUpdateRequest;
import com.cnpmnc.DreamCode.dto.response.SupplierResponse;
import com.cnpmnc.DreamCode.dto.response.UserResponse;
import com.cnpmnc.DreamCode.model.Role;
import com.cnpmnc.DreamCode.model.Supplier;
import com.cnpmnc.DreamCode.model.User;
import com.cnpmnc.DreamCode.repository.RoleRepository;
import com.cnpmnc.DreamCode.repository.SupplierRepository;
import com.cnpmnc.DreamCode.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AdminService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    UserMapper userMapper;
    SupplierMapper supplierMapper;
    private final SupplierRepository supplierRepository;

    public UserResponse createUser(UserCreationRequest request) {
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<Role> roles = new HashSet<>();
        for (String role : request.getRoles()) {
            roleRepository.findByName(role).ifPresent(roles::add);
        }

        user.setRoles(roles);

        user = userRepository.save(user);

        return userMapper.toUserResponse(user);
    }

    public List<UserResponse> getUsers(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size, Sort.by("id").descending())).stream().map(userMapper::toUserResponse).toList();
    }

    public UserResponse getUser(Integer id) {
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id)));
    }

    public UserResponse updateUser(Integer userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        userMapper.updateUser(user, request);
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        if (request.getRoles() != null && !request.getRoles().isEmpty()) {
            HashSet<Role> roles = new HashSet<>();
            for (String role : request.getRoles()) {
                roleRepository.findByName(role).ifPresent(roles::add);
            }
            user.setRoles(roles);
        }

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public SupplierResponse createSupplier(SupplierCreationRequest request) {
        Supplier supplier = supplierMapper.toSupplier(request);

        supplier = supplierRepository.save(supplier);

        return supplierMapper.toSupplierResponse(supplier);
    }

    public List<SupplierResponse> getSuppliers(int page, int size) {
        return supplierRepository.findAll(PageRequest.of(page, size, Sort.by("id").descending())).stream().map(supplierMapper::toSupplierResponse).toList();
    }

    public SupplierResponse getSupplier(Integer id) {
        return supplierMapper.toSupplierResponse(supplierRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Supplier not found with id: " + id)));
    }

    public SupplierResponse updateSupplier(Integer supplierId, SupplierUpdateRequest request) {
        Supplier supplier = supplierRepository.findById(supplierId).orElseThrow(() -> new IllegalArgumentException("Supplier not found with id: " + supplierId));

        supplierMapper.updateSupplier(supplier, request);

        return supplierMapper.toSupplierResponse(supplierRepository.save(supplier));
    }

    public void deleteSupplier(Integer supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new IllegalArgumentException("Supplier not found with id: " + supplierId));

        // Check if supplier has any associated assets using efficient count query
        long assetCount = supplierRepository.countAssetsBySupplierId(supplierId);
        if (assetCount > 0) {
            throw new IllegalStateException("Cannot delete supplier. There are " + assetCount + " asset(s) associated with this supplier.");
        }

        supplierRepository.delete(supplier);
        log.info("Supplier with id {} has been deleted successfully", supplierId);
    }
}
