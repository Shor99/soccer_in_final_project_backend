package or.project.soccer_in_final_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferFindByTeamDto {
    private List<TransfersWithCommentsDto> results;
    private String teamName;
}
