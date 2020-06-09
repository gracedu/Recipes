package trainingProject.repository;

import trainingProject.model.User;

import java.util.List;

public interface UserDao {
    User save(User user);
    List<User> getUsers();
    User getBy(Long userId);
    boolean delete(User user);
}
