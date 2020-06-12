package trainingProject.repository;

import trainingProject.model.Comment;
import trainingProject.model.Recipe;

import java.util.List;

public interface CommentDao {
    Comment save(Comment comment);
    List<Comment> getComments();
    Comment getBy(Long commentId);
    boolean delete(Comment comment);
    Comment getCommentEagerBy(Long id);
}
