package trainingProject.repository;

import trainingProject.model.Role;

import java.util.List;

public interface RoleDao {
    Role getRoleByName(String name);
    List<Role> findAllRoles();
}
