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
public class TransferPageResponseDto {
    private List<TransfersWithCommentsDto> results;
    private int totalPages;
    private long totalPosts;
    private boolean isLast;
    private boolean isFirst;
    /**
     * current page
     */
    private int pageNo;
    /**
     * current page size
     */
    private int pageSize;
}
