package trainingProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import trainingProject.model.Role;
import trainingProject.model.User;
import trainingProject.service.RoleService;
import trainingProject.service.UserService;

import java.awt.*;
import java.util.List;

@RestController
public class UserRoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> findAllUsers() {
        return userService.getUsers();
    }

    @GetMapping(value = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Role> findAllRoles() {
        return roleService.getAllRoles();
    }
}
