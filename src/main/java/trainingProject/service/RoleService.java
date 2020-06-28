package trainingProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainingProject.model.Role;
import trainingProject.repository.RoleDao;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;

    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }

    public List<Role> getAllRoles() {
        return roleDao.findAllRoles();
    }
}
