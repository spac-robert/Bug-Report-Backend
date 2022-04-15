package ro.robert.bugreport.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ro.robert.bugreport.dbo.AuthResponse;
import ro.robert.bugreport.dbo.LoginRequest;
import ro.robert.bugreport.dbo.RegisterRequest;
import ro.robert.bugreport.model.User;
import ro.robert.bugreport.repository.UserRepository;

import java.util.Collection;
import java.util.Date;

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

    public AuthResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
        GrantedAuthority role = roles.iterator().next();
        String token = Jwts.builder()
                .setId(loginRequest.getEmail())
                .setIssuedAt(new Date())
                .setSubject(loginRequest.getEmail())
                .claim("role", role.getAuthority())
                .setIssuer("bugreport")
                .signWith(
                        SignatureAlgorithm.HS256,
                        TextCodec.BASE64.decode("Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=")
                ).compact();

        return new AuthResponse(token, loginRequest.getEmail());
    }
}
