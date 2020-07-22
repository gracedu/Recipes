package trainingProject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import trainingProject.model.Role;
import trainingProject.model.User;
import trainingProject.service.RoleService;

@RestController
@RequestMapping(value = "/role")
public class RoleController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private RoleService roleService;

    @RequestMapping(value="", method = RequestMethod.POST)
    public Role create(@RequestBody Role role) {
        Role newRole = roleService.save(role);
        return newRole;
    }
}
