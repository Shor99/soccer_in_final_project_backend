package or.project.soccer_in_final_project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "role")
public class Role {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;
}
