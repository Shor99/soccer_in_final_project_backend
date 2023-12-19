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
public class TransferFindByRangeFeeDto {
    private List<TransfersWithCommentsDto> results;
    private int rangeFrom;
    private int rangeTo;
}
