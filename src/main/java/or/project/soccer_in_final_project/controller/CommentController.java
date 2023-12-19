package or.project.soccer_in_final_project.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import or.project.soccer_in_final_project.dto.CommentRequestDto;
import or.project.soccer_in_final_project.dto.CommentResponseDto;
import or.project.soccer_in_final_project.dto.TransferRequestDto;
import or.project.soccer_in_final_project.dto.TransferResponseDto;
import or.project.soccer_in_final_project.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@SecurityRequirement(
        name = "Bearer Authentication"
)
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/transfers/{id}/comments")
    public ResponseEntity<CommentResponseDto> createComment(
            @PathVariable(name = "id") long transfersId,
            @RequestBody CommentRequestDto commentRequestDto,
            UriComponentsBuilder uriBuilder,
            Authentication authentication
    ){
        var saved = commentService.createComment(transfersId, commentRequestDto, authentication);
        var uri = uriBuilder
                .path("/api/v1/transfers/{transfers_id}/{comment_id}")
                .buildAndExpand(transfersId, saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(saved);
    }

    @DeleteMapping("/transfers/{id}/comments/{commentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommentResponseDto> deleteTransferById(
            @Valid @NotNull @PathVariable(name = "id") long id,
            @Valid @NotNull @PathVariable(name = "commentId") long commentId){
        return ResponseEntity.ok(commentService.deleteCommentById(commentId));
    }

    @GetMapping("/transfers/{id}/comments")
    public ResponseEntity<List<CommentResponseDto>> findCommentByPostId(@PathVariable("id") long transferId){
        return ResponseEntity.ok(commentService.findCommentsByPostId(transferId));
    }
    @PutMapping("/transfers/{transferId}/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> updateNumberOfLikes(
            @Valid @NotNull @PathVariable long transferId,
            @Valid @NotNull @PathVariable long commentId,
            @Valid @RequestBody CommentRequestDto commentRequestDto
    ){
        return ResponseEntity.ok(commentService.updateNumberOfLikes(commentRequestDto, commentId));
    }
}
