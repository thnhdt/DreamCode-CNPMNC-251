package com.cnpmnc.DreamCode.api.admin;

import com.cnpmnc.DreamCode.constant.PredefinedRole;
import com.cnpmnc.DreamCode.dto.request.UserCreationRequest;
import com.cnpmnc.DreamCode.dto.response.UserResponse;
import com.cnpmnc.DreamCode.model.Role;
import com.cnpmnc.DreamCode.model.User;
import com.cnpmnc.DreamCode.repository.RoleRepository;
import com.cnpmnc.DreamCode.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AdminService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    UserMapper userMapper;

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
}
