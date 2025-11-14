package ro.andreialionte.magazinonline.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDto {
    private UUID id;
    @Email
    private String email;
    private String firstName;
    private String lastName;
}
