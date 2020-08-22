package trainingProject.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import trainingProject.ApplicationBootstrap;
import trainingProject.model.Role;
import trainingProject.model.User;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class RoleDaoTest {
    @Autowired
    private RoleDao roleDao;
    private Role r1;
    private Role r2;

    @Autowired
    private UserDao userDao;
    private User u1;

    @Before
    public void init() {
        u1 = new User();
        u1.setName("henry");
        u1.setEmail("hen@gmail.com");
        u1.setPassword("1234");
        userDao.save(u1);

        r1 = new Role();
        r1.setName("King");
        r1.setAllowedCreate(true);
        r1.setAllowedRead(true);
        r1.setAllowedUpdate(true);
        r1.setAllowedDelete(true);
        roleDao.save(r1);

        r2 = new Role();
        r2.setName("people");
        r1.setAllowedCreate(false);
        r1.setAllowedRead(true);
        r1.setAllowedUpdate(false);
        r1.setAllowedDelete(false);
        roleDao.save(r2);
    }

    @After
    public void tearDown() {
        roleDao.delete(r1);
        roleDao.delete(r2);
        userDao.delete(u1);
    }

    @Test
    public void findAllRolesTest() {
        List<Role> result = roleDao.findAllRoles();
        Assert.assertNotNull(result);
        Assert.assertEquals("[user]", u1.getRoles().toString());
        Assert.assertEquals(3, result.size());
    }

    @Test
    public void getRoleByNameTest() {
        Role result = roleDao.getRoleByName("people");
        Assert.assertEquals("people", result.toString());

        Role result2 = roleDao.getRoleByName("King");
        Assert.assertEquals("King".toLowerCase(), result2.toString().toLowerCase());
    }
}
