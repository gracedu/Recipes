package trainingProject.jdbc;

import trainingProject.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

public class UserDaoTest {
    private UserDao userJDBCDao;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Before
    public void setUp() {
        userJDBCDao = new UserDao();
    }

    @After
    public void tearDown() {
        userJDBCDao = null;
    }

    @Test
    public void createUserTest() {
        User user1 = new User("dump1ing", "cat@gmail.com", "12345");
        User user2 = new User("Hulu", "hulu@hotmail.com", "asdf");
        User user3 = new User("Golds", "golds@gmail.com", "ghjkl");

        userJDBCDao.createUser(user1);
        userJDBCDao.createUser(user2);
        userJDBCDao.createUser(user3);
    }

    @Test
    public void getUsersTest() {
        logger.debug("start unit test for getUsersTest...");
        assertEquals(3, userJDBCDao.getUsers().size());
        //System.out.println(recipeJDBCDao.getRecipes());
        //assertEquals(6, recipeJDBCDao.getRecipes().size());
    }

    @Test
    public void getUserNameByEmailTest() {
        logger.debug("start unit test for getUserNameByEmailTest...");
        assertEquals("Hulu", userJDBCDao.getUserNameByEmail("hulu@hotmail.com"));
        assertEquals("dump1ing", userJDBCDao.getUserNameByEmail("cat@gmail.com"));
    }

}
