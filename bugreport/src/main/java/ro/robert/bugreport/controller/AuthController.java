package ro.robert.bugreport.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.robert.bugreport.dbo.LoginRequest;
import ro.robert.bugreport.dbo.RegisterRequest;
import ro.robert.bugreport.service.UserService;

@RestController
@AllArgsConstructor
@RequestMapping()
public class AuthController {
    private final UserService userService;

    @PostMapping(path = "/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        try {
            userService.signup(registerRequest);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>("All gud", HttpStatus.OK);
    }

    @GetMapping(path = "/login")
    public void login(@RequestBody LoginRequest loginRequest) {
        System.out.println("Sa moara franta ca am putut sa trimit ceva");
        //return userService.login(loginRequest);
    }
}
