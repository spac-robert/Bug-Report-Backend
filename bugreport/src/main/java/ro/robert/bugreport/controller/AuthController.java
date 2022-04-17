package ro.robert.bugreport.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ro.robert.bugreport.dbo.AuthResponse;
import ro.robert.bugreport.dbo.LoginRequest;
import ro.robert.bugreport.dbo.RegisterRequest;
import ro.robert.bugreport.dbo.RegisterResponse;
import ro.robert.bugreport.service.UserService;

@RestController
@AllArgsConstructor
@RequestMapping()
public class AuthController {
    private final UserService userService;

    @PostMapping(path = "/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @PostMapping(path = "/register")
    public RegisterResponse register(@RequestBody RegisterRequest registerRequest) {
        RegisterResponse response = userService.signup(registerRequest);
        return response;
    }

    @GetMapping(path = "/logout")
    public void logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
