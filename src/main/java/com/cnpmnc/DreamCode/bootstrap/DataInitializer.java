package com.cnpmnc.DreamCode.bootstrap;

import com.cnpmnc.DreamCode.constant.PredefinedRole;
import com.cnpmnc.DreamCode.model.Role;
import com.cnpmnc.DreamCode.model.User;
import com.cnpmnc.DreamCode.repository.RoleRepository;
import com.cnpmnc.DreamCode.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${ADMIN_USERNAME:admin}")
    private String adminUsername;

    @Value("${ADMIN_PASSWORD:admin123}")
    private String adminPassword;

    @Value("${SEED_SAMPLE_USERS:true}")
    private boolean seedSampleUsers;

    public DataInitializer(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // 1. Tạo các role mặc định nếu chưa tồn tại
        Role adminRole = roleRepository.findByName(PredefinedRole.ADMIN_ROLE)
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName(PredefinedRole.ADMIN_ROLE);
                    role.setDescription("Administrator role with full access");
                    return roleRepository.save(role);
                });

        Role userRole = roleRepository.findByName(PredefinedRole.USER_ROLE)
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName(PredefinedRole.USER_ROLE);
                    role.setDescription("Standard user role");
                    return roleRepository.save(role);
                });

        Role assetManagerRole = roleRepository.findByName(PredefinedRole.ASSET_MANAGER_ROLE)
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName(PredefinedRole.ASSET_MANAGER_ROLE);
                    role.setDescription("Asset manager role");
                    return roleRepository.save(role);
                });

        Role departmentManagerRole = roleRepository.findByName(PredefinedRole.DEPT_MANAGER_ROLE)
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName(PredefinedRole.DEPT_MANAGER_ROLE);
                    role.setDescription("Department manager role");
                    return roleRepository.save(role);
                });


        // 2. Tạo admin user với role ADMIN
        if (!userRepository.existsByUserName(adminUsername)) {
            User admin = new User();
            admin.setUserName(adminUsername);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setIsActive(true);

            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);
            admin.setRoles(adminRoles);

            userRepository.save(admin);
            System.out.println("✓ Admin user created: " + adminUsername);
        }

        // 3. Tạo sample users nếu được bật
//        if (seedSampleUsers) {
//            if (!userRepository.existsByUserName("viewer1")) {
//                User viewer = new User();
//                viewer.setUserName("viewer1");
//                viewer.setPassword(passwordEncoder.encode("viewer123"));
//                viewer.setIsActive(true);
//                viewer.setRoles(Set.of(assetManagerRole));
//                userRepository.save(viewer);
//                System.out.println("✓ Sample user created: viewer1");
//            }
//
//            if (!userRepository.existsByUserName("assetmgr1")) {
//                User am = new User();
//                am.setUserName("assetmgr1");
//                am.setPassword(passwordEncoder.encode("asset123"));
//                am.setIsActive(true);
//                am.setRoles(Set.of(departmentManagerRole));
//                userRepository.save(am);
//                System.out.println("✓ Sample user created: assetmgr1");
//            }
//
//            if (!userRepository.existsByUserName("deptmgr1")) {
//                User dm = new User();
//                dm.setUserName("deptmgr1");
//                dm.setPassword(passwordEncoder.encode("dept123"));
//                dm.setIsActive(true);
//                dm.setRoles(Set.of(deptManagerRole));
//                userRepository.save(dm);
//                System.out.println("✓ Sample user created: deptmgr1");
//            }
//
//            if (!userRepository.existsByUserName("user1")) {
//                User u = new User();
//                u.setUserName("user1");
//                u.setPassword(passwordEncoder.encode("user123"));
//                u.setIsActive(true);
//                u.setRoles(Set.of(userRole));
//                userRepository.save(u);
//                System.out.println("✓ Sample user created: user1");
//            }
//        }
    }
}
