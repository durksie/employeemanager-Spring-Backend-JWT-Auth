package tech.getarrays.employeemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.getarrays.employeemanager.repo.RoleRepo;
import tech.getarrays.employeemanager.repo.UserRepo;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private PasswordEncoder passwordEncoder;

     @Autowired
    public AuthController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, RoleRepo roleRepo, UserRepo userRepo) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
    }



}
