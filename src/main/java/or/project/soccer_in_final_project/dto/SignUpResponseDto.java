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
public class SignUpResponseDto {
    private Long id;
    private String username;
    private String email;
    private String password;
}