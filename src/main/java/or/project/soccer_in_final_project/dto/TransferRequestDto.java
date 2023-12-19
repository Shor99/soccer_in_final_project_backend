package or.project.soccer_in_final_project.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferRequestDto {
    private String playerLogoUrl;
    private String playerFullName;
    private int transferFeeInMillions;
    private String fromTeam;
    private String fromTeamLogoUrl;
    private String toTeam;
    private String toTeamLogoUrl;
    private long numberOfLikes;
}
