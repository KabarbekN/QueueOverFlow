package io.nurgissa.queueoverflow.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public String get() {
        return "GET:: admin controller";
    }
    @PostMapping
    @Hidden
    public String post() {
        return "POST:: admin controller";
    }
    @PutMapping
    @Hidden
    public String put() {
        return "PUT:: admin controller";
    }
    @DeleteMapping
    @Hidden
    public String delete() {
        return "DELETE:: admin controller";
    }

}




