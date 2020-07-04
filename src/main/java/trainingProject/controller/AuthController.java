package trainingProject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;
import trainingProject.model.User;
import trainingProject.service.JWTService;
import trainingProject.service.UserService;

@RequestMapping(value = "/auth")
@RestController
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JWTService jwtService;
    Logger logger = LoggerFactory.getLogger(getClass());

    // 1. validate user exist in database and verify password
    // 2. generate JWToken
    // 3. return token
    // /user POST
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String authentication(@RequestBody User u) {
        logger.debug("username is " + u.getEmail() + " password is " + u.getPassword());
        try {

            User user = userService.getUserByCredentials(u.getEmail(), u.getPassword());
            logger.debug(user.toString());
            String token = jwtService.generateToken(user);
            return token;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
