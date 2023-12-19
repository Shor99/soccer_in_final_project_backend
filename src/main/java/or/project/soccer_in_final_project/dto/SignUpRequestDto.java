package or.project.soccer_in_final_project.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import or.project.soccer_in_final_project.validators.UniqueEmail;
import or.project.soccer_in_final_project.validators.UniqueUsername;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 20, message = "username must contain at least 2 letters")
    @UniqueUsername
    private String username;

    @NotNull
    @Email
    @NotEmpty
    @UniqueEmail
    private String email;

    @NotNull
    @NotEmpty
    @Size(min = 8, max = 20)
    @NotNull(message = "password is mandatory")
    @Pattern(
            regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?\\W).{8,20}$",
            message = "password must contain at least 8 characters, one or more lower case letters, uppercase letter, symbol, digits"
    )
    private String password;

    private String profileLogoName;
}