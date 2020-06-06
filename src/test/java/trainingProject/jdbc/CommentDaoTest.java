package trainingProject.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.assertEquals;

public class CommentDaoTest {
    private CommentDao commentJDBCDao;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Before
    public void setUp() {
        commentJDBCDao = new CommentDao();
    }

    @After
    public void tearDown() {
        commentJDBCDao = null;
    }

    @Test
    public void getCommentsTest() {
        logger.debug("start unit test for getCommentsTest...");
        assertEquals(0, commentJDBCDao.getComments().size());
    }
}
