package ro.andreialionte.magazinonline.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthDto {
    @Email
    private String email;

    // nu expunem parola hash-uită
    private String password;
}
