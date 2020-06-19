package trainingProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainingProject.model.User;
import trainingProject.repository.UserDao;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User save(User user) {
        return userDao.save(user);
    }
    public List<User> getUsers() {
        return userDao.getUsers();
    }
    public User getBy(Long id) {
        return userDao.getBy(id);
    }
    public boolean delete(User user){
        return userDao.delete(user);
    }

    //based on table recipes(user_id)
    public User getUserEagerByRecipe(Long id) {
        return userDao.getUserEagerByRecipe(id);
    }

    //based on table comments(user_id)
    public User getUserEagerByComment(Long id) {
        return userDao.getUserEagerByComment(id);
    }
}
