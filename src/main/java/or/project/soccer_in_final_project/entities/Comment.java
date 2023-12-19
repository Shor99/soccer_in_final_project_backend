package or.project.soccer_in_final_project.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @NotNull
    private String comment;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "transfer_id", nullable = false)
    private Transfer transfer;
    @NotNull
    private int numberOfLikes;
}
