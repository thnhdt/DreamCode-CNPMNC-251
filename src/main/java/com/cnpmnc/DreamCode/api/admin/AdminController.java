package com.cnpmnc.DreamCode.api.admin;

import com.cnpmnc.DreamCode.dto.request.UserCreationRequest;
import com.cnpmnc.DreamCode.dto.response.UserResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AdminController {

    AdminService adminService;
    @PostMapping("/user")
    UserResponse createUser(@RequestBody @Valid UserCreationRequest request) {
        return adminService.createUser(request);
    }
    @GetMapping("/health")
    public String health() {
        return "assets-ok";
    }
}
