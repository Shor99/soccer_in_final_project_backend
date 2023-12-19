package or.project.soccer_in_final_project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity(name = "profileLogos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileLogo {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String profileLogoName;

    @NotNull
    private String profileLogoUrl;
}
