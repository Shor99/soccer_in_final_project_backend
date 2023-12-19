package or.project.soccer_in_final_project.repository;

import or.project.soccer_in_final_project.entities.Comment;
import or.project.soccer_in_final_project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findCommentsByTransferId(long postId);
    List<Comment> findCommentsByUser(User user);
}
