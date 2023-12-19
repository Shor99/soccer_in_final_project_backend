package or.project.soccer_in_final_project.service;

import lombok.RequiredArgsConstructor;
import or.project.soccer_in_final_project.dto.CommentRequestDto;
import or.project.soccer_in_final_project.dto.CommentResponseDto;
import or.project.soccer_in_final_project.dto.TransferResponseDto;
import or.project.soccer_in_final_project.entities.Comment;
import or.project.soccer_in_final_project.entities.Transfer;
import or.project.soccer_in_final_project.error.ResourceNotFoundException;
import or.project.soccer_in_final_project.repository.CommentRepository;
import or.project.soccer_in_final_project.repository.TransferRepository;
import or.project.soccer_in_final_project.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    //props:
    private final CommentRepository commentRepository;
    private final TransferRepository transferRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Override
    public CommentResponseDto createComment(long transferId, CommentRequestDto commentRequestDto, Authentication authentication) {
        var transfer = transferRepository.findById(transferId).orElseThrow(
                () -> new ResourceNotFoundException("Transfer", transferId)
        );

        //אם המשתמש הצליח להגיע לפה סימן שיש כזה משתמש
        var user = userRepository.findByUsernameIgnoreCase(authentication.getName()).orElseThrow();
        Comment comment = modelMapper.map(commentRequestDto, Comment.class);
        comment.setTransfer(transfer);
        comment.setUser(user);

        var saved = commentRepository.save(comment);
        return modelMapper.map(saved, CommentResponseDto.class);
    }

    @Override
    public List<CommentResponseDto> findCommentsByPostId(long transferId) {
        //assert that the post exists:
        if (!transferRepository.existsById(transferId)) {
            throw new ResourceNotFoundException("post", transferId);
        }
        //return the comments for the post:
        return commentRepository
                .findCommentsByTransferId(transferId)
                .stream()
                .map(
                        c -> modelMapper.map(c, CommentResponseDto.class)
                ).toList();
    }

    @Override
    public List<CommentResponseDto> deleteAlCommentsByTransferId(long transferId) {
        commentRepository.findCommentsByTransferId(transferId).stream().forEach(c-> {
            commentRepository.deleteById(c.getId());
        });
        return Collections.emptyList();
    }

    @Override
    public CommentResponseDto deleteCommentById(long id) {
        Comment comment = getCommentEntity(id);
        commentRepository.deleteById(id);
        return modelMapper.map(comment, CommentResponseDto.class);
    }

    private Comment getCommentEntity(long id){
        return commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Comment", id)
        );
    }
    @Override
    public CommentResponseDto updateNumberOfLikes(CommentRequestDto commentRequestDto, long id) {
        System.out.println(id);
        Comment commentFromDb = getCommentEntity(id);

        commentFromDb.setNumberOfLikes(commentRequestDto.getNumberOfLikes());
        commentFromDb.setComment(commentFromDb.getComment());

        var saved = commentRepository.save(commentFromDb);

        return modelMapper.map(saved,CommentResponseDto.class);
    }



}
