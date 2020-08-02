package trainingProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainingProject.model.Comment;
import trainingProject.model.Recipe;
import trainingProject.model.User;
import trainingProject.repository.CommentDao;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;

    public Comment save(Comment comment) {
        return commentDao.save(comment);
    }
    public List<Comment> getComments() {
        return commentDao.getComments();
    }
    public Comment getBy(Long id) {
        return commentDao.getBy(id);
    }
    public boolean delete(Comment comment) {
        return commentDao.delete(comment);
    }
    public List<Comment> getBy(Recipe recipe) {
        return commentDao.getBy(recipe);
    }
    public List<Comment> getBy(User user) {
        return commentDao.getBy(user);
    }
    public Comment update(Comment comment) {
        return commentDao.update(comment);
    }
}
