package or.project.soccer_in_final_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileLogoResponseDto {
    private Long id;
    private String profileLogoName;
    private String profileLogoUrl;
}
