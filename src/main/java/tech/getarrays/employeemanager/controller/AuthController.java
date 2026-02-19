package tech.getarrays.employeemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.employeemanager.dto.AuthResponseDto;
import tech.getarrays.employeemanager.dto.LoginDto;
import tech.getarrays.employeemanager.dto.RegisterDto;
import tech.getarrays.employeemanager.model.Role;
import tech.getarrays.employeemanager.model.UserEntity;
import tech.getarrays.employeemanager.repo.RoleRepo;
import tech.getarrays.employeemanager.repo.UserRepo;
import tech.getarrays.employeemanager.security.JWTGenerator;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;



    @Autowired
    public AuthController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, RoleRepo roleRepo, UserRepo userRepo,JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
        this.jwtGenerator = jwtGenerator;
    }


//fOR lOGIN
    @GetMapping("login")
    public ResponseEntity<AuthResponseDto>login(@RequestBody LoginDto loginDto){
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
                        loginDto.getPassword()));
        //so the user doesn't login all the time
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token= jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDto(token),HttpStatus.OK);
    }

//For Registration
    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
         if (userRepo.existsByusername(registerDto.getUsername())){
             return new ResponseEntity<>("Username is taken", HttpStatus.BAD_REQUEST);
         }

        UserEntity user = new UserEntity();
         user.setUsername(registerDto.getUsername());
         user.setPassword(passwordEncoder.encode(registerDto.getPassword()));


        Role roles = roleRepo.findByName("ADMIN").get();
        user.setRoles(Collections.singletonList(roles));

        userRepo.save(user);

        return  new ResponseEntity<>("User Registration Success",HttpStatus.OK);
    }



}
