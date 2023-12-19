package or.project.soccer_in_final_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransfersWithCommentsDto {
    private Long id;
    private String playerLogoUrl;
    private String playerFullName;
    private int transferFeeInMillions;
    private String fromTeam;
    private String fromTeamLogoUrl;
    private String toTeam;
    private String toTeamLogoUrl;
    private long numberOfLikes;

    private List<CommentResponseDto> comments;
}
