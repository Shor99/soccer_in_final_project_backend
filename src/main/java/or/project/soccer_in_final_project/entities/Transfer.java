package or.project.soccer_in_final_project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity(name = "transfers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transfer {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String playerLogoUrl;
    @NotNull
    private String playerFullName;
    @NotNull
    private int transferFeeInMillions;
    @NotNull
    private String fromTeam;
    @NotNull
    private String fromTeamLogoUrl;
    @NotNull
    private String toTeam;
    @NotNull
    private String toTeamLogoUrl;
    @NotNull
    private long numberOfLikes;

}
