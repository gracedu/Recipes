package trainingProject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;
import trainingProject.model.User;
import trainingProject.service.JWTService;
import trainingProject.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RequestMapping(value = "/auth")
@RestController
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JWTService jwtService;
    Logger logger = LoggerFactory.getLogger(getClass());
    private String errorMsg = "The email or password is not correct";

    // 1. validate user exist in database and verify password
    // 2. generate JWToken
    // 3. return token
    // /user POST
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Map> authentication(@RequestBody User u) {
        String token;
        Map<String, String> result = new HashMap<>();
        try {
            logger.debug("username is " + u.getEmail() + " password is " + u.getPassword());
            User user = userService.getUserByCredentials(u.getEmail(), u.getPassword());
            if (user == null) { //is email password pair is not in database
                result.put("message", errorMsg);
                return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body(result);
            }
            logger.debug(user.toString());
            token = jwtService.generateToken(user);
            result.put("token", token);
        }
        catch (Exception e) {
            String msg = e.getMessage();
            if (msg == null) {
                msg = "BAD REQUEST";
            }
            logger.error(msg);
            result.put("msg", msg);
            return  ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(result);
        }
        return ResponseEntity.status(HttpServletResponse.SC_OK).body(result);
    }
}
