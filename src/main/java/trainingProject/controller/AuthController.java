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
    public String authentication(@RequestParam("username") String username, @RequestParam("password") String password) {
        logger.debug("username is " + username + " password is " + password );
        String token;
        try {
            User u = userService.getUserByCredentials(username, password);
            logger.debug(u.toString());
            token = jwtService.generateToken(u);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
