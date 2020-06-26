package trainingProject.repository;

import trainingProject.model.Comment;
import trainingProject.model.Recipe;
import trainingProject.model.User;

import java.util.Comparator;
import java.util.List;

public interface CommentDao {
    Comment save(Comment comment);
    List<Comment> getComments();
    Comment getBy(Long id);
    boolean delete(Comment comment);
    List<Comment> getBy(Recipe recipe);
    List<Comment> getBy(User user);
    Comment update(Comment comment);
}
