package ro.andreialionte.magazinonline.request;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    @Email
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
