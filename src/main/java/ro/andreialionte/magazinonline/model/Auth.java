package ro.andreialionte.magazinonline.model;

import io.lettuce.core.dynamic.annotation.Key;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.springframework.aot.generate.GeneratedTypeReference;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Auth {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;
    @Email
    private String email;
    private String passwordHash;
    @OneToOne(mappedBy = "auth")
    private User user;
}
