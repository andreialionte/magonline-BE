package ro.andreialionte.magazinonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ro.andreialionte.magazinonline.dto.UserDto;
import ro.andreialionte.magazinonline.mapper.AuthMapper;
import ro.andreialionte.magazinonline.mapper.UserMapper;
import ro.andreialionte.magazinonline.model.Auth;
import ro.andreialionte.magazinonline.model.User;
import ro.andreialionte.magazinonline.repository.AuthRepository;
import ro.andreialionte.magazinonline.repository.UserRepository;
import ro.andreialionte.magazinonline.request.LoginRequest;
import ro.andreialionte.magazinonline.request.RegisterRequest;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthMapper authMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.secret:default_jwt_secret_change_me}")
    private String jwtSecret;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<UserDto> register(@RequestBody RegisterRequest registerRequest) {

        boolean emailExists = authRepository.findByEmail(registerRequest.getEmail()).isPresent();
        if (emailExists) {
            throw new RuntimeException("Email already registered");
        }

        Auth auth = authMapper.fromRegisterRequest(registerRequest);
        auth.setPasswordHash(passwordEncoder.encode(registerRequest.getPassword()));
        auth = authRepository.save(auth);

        User user = userMapper.fromRegisterRequest(registerRequest);
        user.setAuth(auth);
        user = userRepository.save(user);

        UserDto responseDto = userMapper.toDto(user);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest) {

        Optional<Auth> authOptional = authRepository.findByEmail(loginRequest.getEmail());
        boolean authNotFound = authOptional.isEmpty();

        if (authNotFound) {
            throw new RuntimeException("Invalid credentials");
        }

        Auth auth = authOptional.get();
        boolean passwordMatches = passwordEncoder.matches(loginRequest.getPassword(), auth.getPasswordHash());

        if (!passwordMatches) {
            throw new RuntimeException("Invalid credentials");
        }

        Optional<User> userOptional = userRepository.findByAuth(auth);
        boolean userNotFound = userOptional.isEmpty();

        if (userNotFound) {
            throw new RuntimeException("User not found");
        }

    UserDto responseDto = userMapper.toDto(userOptional.get());

    // create JWT token
    Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
    String token = JWT.create()
        .withSubject(responseDto.getId().toString())
        .withClaim("email", responseDto.getEmail())
        .withIssuedAt(Instant.now())
        .sign(algorithm);

    Map<String, Object> body = new HashMap<>();
    body.put("token", token);
    body.put("user", responseDto);

    return ResponseEntity.ok(body);
    }
}