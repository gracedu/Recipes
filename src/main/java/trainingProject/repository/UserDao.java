package trainingProject.repository;

import trainingProject.model.User;

import java.util.List;

public interface UserDao {
    User save(User user);
    List<User> getUsers();
    User getBy(Long id);
    boolean delete(User user);
    User getUserEagerByRecipe(Long id);//based on table recipes(user_id)
    User getUserEagerByComment(Long id); //based on table comments(user_id)
    User update(User user);


}
