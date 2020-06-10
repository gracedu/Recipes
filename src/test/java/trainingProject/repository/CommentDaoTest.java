package trainingProject.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import trainingProject.model.Comment;

public class CommentDaoTest {
    private CommentDao commentDao;
    private Comment comment;

    @Before
    public void init() {
        commentDao = new CommentDaoImpl();
        comment = new Comment();

        comment.setContent("GOOD"); // need to work on the foreign key

    }

    @After
    public void tearDown() {
        commentDao.delete(comment);
    }

    public void getCommentsTest() {
        Assert.assertEquals(1, commentDao.getComments().size());
    }
}
