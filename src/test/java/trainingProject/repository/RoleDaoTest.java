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

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class RoleDaoTest {
    @Autowired
    private RoleDao roleDao;
    private Role r1;
    private Role r2;

    @Before
    public void init() {
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
    }

    @Test
    public void findAllRolesTest() {
        List<Role> result = roleDao.findAllRoles();
        Assert.assertNotNull(result);
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void getRoleByNameTest() {
        Role result = roleDao.getRoleByName("people");
        Assert.assertEquals("people", result.toString());

        Role result2 = roleDao.getRoleByName("King");
        Assert.assertEquals("King".toLowerCase(), result2.toString());
    }
}
