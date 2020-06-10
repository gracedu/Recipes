package trainingProject.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import trainingProject.model.User;

public class UserDaoTest {
    private UserDao userDao;
    private User user;
    @Before
    public void init() {
        userDao = new UserDaoImpl();
        user = new User();
        user.setUserName("gracedu");
        user.setEmail("gracedjx@gmail.com");
        user.setPassword("1234");
        userDao.save(user);
    }

    @After
    public void tearDown() {
        userDao.delete(user);
    }

    @Test
    public void getUsersTest() {
        Assert.assertEquals(1, userDao.getUsers().size());
    }
}
