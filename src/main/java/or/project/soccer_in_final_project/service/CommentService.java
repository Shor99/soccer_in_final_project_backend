package or.project.soccer_in_final_project.service;

import or.project.soccer_in_final_project.dto.CommentRequestDto;
import or.project.soccer_in_final_project.dto.CommentResponseDto;
import or.project.soccer_in_final_project.dto.TransferResponseDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CommentService {

    CommentResponseDto createComment(long transferId, CommentRequestDto dto, Authentication authentication);
    List<CommentResponseDto> findCommentsByPostId(long transferId);
    List<CommentResponseDto> deleteAlCommentsByTransferId(long transferId);
    CommentResponseDto deleteCommentById(long id);
    CommentResponseDto updateNumberOfLikes(CommentRequestDto commentRequestDto,long id);
}
