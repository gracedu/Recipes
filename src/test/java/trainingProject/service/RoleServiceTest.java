package trainingProject.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import trainingProject.ApplicationBootstrap;
import trainingProject.model.Role;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class RoleServiceTest {
    @Autowired
    private RoleService roleService;
    private Role r1;
    private Role r2;

    @Before
    public void init() {
        r1 = new Role();
        r1.setName("pirate");
        r1.setAllowedCreate(true);
        r1.setAllowedRead(true);
        r1.setAllowedUpdate(true);
        r1.setAllowedDelete(true);
        roleService.save(r1);

        r2 = new Role();
        r2.setName("samurai");
        r1.setAllowedCreate(false);
        r1.setAllowedRead(true);
        r1.setAllowedUpdate(false);
        r1.setAllowedDelete(false);
        roleService.save(r2);
    }

    @After
    public void destroy() {
        roleService.delete(r1);
        roleService.delete(r2);
    }

    @Test
    public void getRoleByNameTest() {
        Role role = roleService.getRoleByName("pirate");

    }

    @Test
    public void getAllRolesTest() {

    }
}
