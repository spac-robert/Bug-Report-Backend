package ro.robert.bugreport.service;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ro.robert.bugreport.dbo.LoginRequest;
import ro.robert.bugreport.dbo.RegisterRequest;
import ro.robert.bugreport.model.User;
import ro.robert.bugreport.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public void signup(RegisterRequest registerRequest) throws Exception {
        User user = userRepository.findUsersByEmail(registerRequest.getEmail())
                .orElseThrow(() -> new Exception("Nu se poate"));
        userRepository.save(user);
    }

    public void login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //String token = jwtProvider.generateToken(authentication);
        //return new AuthResponse(token, loginRequest.getEmail());
    }
}
