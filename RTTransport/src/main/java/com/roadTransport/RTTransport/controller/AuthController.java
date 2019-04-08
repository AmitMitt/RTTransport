package com.roadTransport.RTTransport.controller;

import com.roadTransport.RTTransport.entity.Admin;
import com.roadTransport.RTTransport.entity.Role;
import com.roadTransport.RTTransport.entity.RoleName;
import com.roadTransport.RTTransport.exception.AppException;
import com.roadTransport.RTTransport.model.ApiResponse;
import com.roadTransport.RTTransport.model.JwtAuthenticationResponse;
import com.roadTransport.RTTransport.model.LoginRequest;
import com.roadTransport.RTTransport.model.SignUpRequest;
import com.roadTransport.RTTransport.repository.AdminRepository;
import com.roadTransport.RTTransport.repository.RoleRepository;
import com.roadTransport.RTTransport.security.JwtTokenProvider;
import com.roadTransport.RTTransport.service.TransportDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    TransportDetailsService transportDetailsService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AdminRepository adminRepository;

    @PostMapping("/signin/admin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup/admin")
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(adminRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(adminRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(adminRepository.existsByMobile(signUpRequest.getMobile())){
            return new ResponseEntity(new ApiResponse(false, "Mobile already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating admin's account
        Admin admin= new Admin(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword(), signUpRequest.getMobile());

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                .orElseThrow(() -> new AppException("User Role not set."));

        admin.setRoles(Collections.singleton(userRole));

        Admin result = adminRepository.save(admin);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        transportDetailsService.add(signUpRequest);

        long pin = Long.parseLong(signUpRequest.getMobile()) % 10000;

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully and wallet pin is: "+pin+ "  Please update it."));
    }

}
