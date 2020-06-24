package trainingProject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import trainingProject.model.User;
import trainingProject.service.UserService;

import java.util.List;

@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public void getUsers() {
        logger.debug("hahaha");
    }

    @RequestMapping(value = "/user/{Id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable(name="Id") Long id) {
        logger.debug("the user id is" + id);
        return userService.getBy(id);
    }

}
