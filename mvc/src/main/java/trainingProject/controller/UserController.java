package trainingProject.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import trainingProject.model.Role;
import trainingProject.model.User;
import trainingProject.service.RoleService;
import trainingProject.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value="/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    // /user GET
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> getUsers() {
        logger.debug("Getting all users");
        return userService.getUsers();
    }

    // /user/1 GET
    @RequestMapping(value = "/{Id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable(name="Id") Long id) {
        logger.debug("the user id is" + id);
        return userService.getBy(id);
    }

    // /user/1?name=lily   PATCH
    @RequestMapping(value = "/{Id}", method = RequestMethod.PATCH)
    public User updateUserName(@PathVariable("Id") Long id, @RequestParam("name") String name) {
        logger.debug("change name");
        User u = userService.getBy(id);
        u.setName(name);
        u = userService.update(u);
        return u;
    }

    //user signup
    // /user POST
    @RequestMapping(value = "", method = RequestMethod.POST)
    public User create(@RequestBody User newUser) {
        logger.debug(newUser.toString());
        User user = userService.save(newUser);
        return user;
    }

}
