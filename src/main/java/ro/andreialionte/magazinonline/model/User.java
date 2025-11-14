package ro.andreialionte.magazinonline.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Email
    private String email;

    private String firstName;
    private String lastName;

    @OneToOne
    @JoinColumn(name = "auth_id", referencedColumnName = "id") //FK
    private Auth auth;
}
