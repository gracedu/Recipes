package trainingProject.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import trainingProject.model.Comment;
import trainingProject.model.Recipe;
import trainingProject.model.User;

public class CommentDaoTest {
    private UserDao userDao;
    private User user;
    private RecipeDao recipeDao;
    private Recipe recipe1;
    private Recipe recipe2;
    private CommentDao commentDao;
    private Comment comment;
    private Comment comment2;

    @Before
    public void init() {
        commentDao = new CommentDaoImpl();
        comment = new Comment();

        comment.setContent("GOOD");

    }

    @After
    public void tearDown() {
        commentDao.delete(comment);
    }

    public void getCommentsTest() {
        Assert.assertEquals(1, commentDao.getComments().size());
    }
}
