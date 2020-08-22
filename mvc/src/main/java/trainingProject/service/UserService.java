package trainingProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import trainingProject.model.Role;
import trainingProject.model.User;
import trainingProject.repository.UserDao;

import java.util.List;


@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleService roleService;

    public User save(User user) {
//        if (roleService.getRoleByName("user") == null) {
//            Role role = new Role();
//            role.setName("user");
//            role.setAllowedResource("/recipes,/comments,/images");
//            role.setAllowedRead(true);
//            role.setAllowedUpdate(true);
//            role.setAllowedCreate(true);
//            role.setAllowedDelete(true);
//            roleService.save(role);
//        }
//        user.addRole(roleService.getRoleByName("user"));
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

    public User update(User u) {
        return userDao.update(u);
    }

    //either username and email works
    public User getUserByCredentials(String email, String password) {
        return userDao.getUserByCredentials(email, password);
    }
}
