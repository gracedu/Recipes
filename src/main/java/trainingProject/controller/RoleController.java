package trainingProject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import trainingProject.model.Role;
import trainingProject.model.User;
import trainingProject.service.RoleService;

@RestController
@RequestMapping(value = "/role")
public class RoleController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private RoleService roleService;

    // /role POST
    @RequestMapping(value="", method = RequestMethod.POST)
    public Role create(@RequestBody Role role) {
        Role newRole = roleService.save(role);
        return newRole;
    }

    // /role DELETE
    @RequestMapping(value="", method = RequestMethod.DELETE)
    public boolean delete(@RequestBody Role role) {
        boolean result = roleService.delete(role);
        return result;
    }

    // /role?name=roleName GET
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Role getRoleByName(@RequestParam("name") String name) {
        Role result = roleService.getRoleByName(name);
        return result;
    }



/*
    public Role getAllRoles() {

    }*/
}
