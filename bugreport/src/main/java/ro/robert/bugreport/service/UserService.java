package ro.robert.bugreport.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.robert.bugreport.dbo.RegisterRequest;
import ro.robert.bugreport.model.User;
import ro.robert.bugreport.repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void signup(RegisterRequest registerRequest) throws Exception {
        User user = userRepository.findUsersByEmail(registerRequest.getEmail())
                .orElseThrow(() -> new Exception("Nu se poate"));
        userRepository.save(user);
    }
}
