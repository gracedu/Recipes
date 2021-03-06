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
import trainingProject.model.Comment;
import trainingProject.model.Recipe;
import trainingProject.model.User;

import java.util.Calendar;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class CommentDaoTest {
    @Autowired
    private UserDao userDao;
    private User user;

    @Autowired
    private RecipeDao recipeDao;
    private Recipe recipe1;
    private Recipe recipe2;

    @Autowired
    private CommentDao commentDao;
    private Comment comment1;
    private Comment comment2;

    @Before
    public void init() {
        //userDao = new UserDaoImpl();
        user = new User("Meow", "meow@gmail.com", "abcd");
        userDao.save(user);

        //recipeDao = new RecipeDaoImpl();
        recipe1 = new Recipe();
        recipe1.setCreationDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        recipe1.setCuisine("Greek");
        recipe1.setDescription("roast chicken in the oven");
        recipe1.setIngredient("parsely, chicken, salt, pepper");
        recipe1.setName("Greek style roast chicken");
        recipe1.setUser(user);
        recipeDao.save(recipe1);

        recipe2 = new Recipe();
        recipe2.setCreationDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        recipe2.setCuisine("Chinese");
        recipe2.setDescription("wash all vegetables, add oil to wok, then add vegetable and salt, stir for five minutes");
        recipe2.setIngredient("beans, broccoli, cabbage");
        recipe2.setName("stir fry vegetables");
        recipe2.setUser(user);
        recipeDao.save(recipe2);

        //commentDao = new CommentDaoImpl();

        comment1 = new Comment();
        comment1.setContent("GOOD");
        comment1.setUser(user);
        comment1.setRecipe(recipe1);
        commentDao.save(comment1);

        comment2 = new Comment();
        comment2.setContent("This is my favorite recipe");
        comment2.setUser(user);
        comment2.setRecipe(recipe1);
        commentDao.save(comment2);

    }

    @After
    public void tearDown() {
        commentDao.delete(comment1);
        commentDao.delete(comment2);
        recipeDao.delete(recipe1);
        recipeDao.delete(recipe2);
        userDao.delete(user);

    }

    @Test
    public void getByTest() {
        Comment commentResult = commentDao.getBy(comment1.getId());
        Assert.assertEquals(commentResult.getContent(), comment1.getContent());
        Assert.assertEquals(commentResult.getId(), comment1.getId());
    }

    @Test
    public void getCommentsTest() {
        Assert.assertEquals(2, commentDao.getComments().size());
    }

    @Test
    public void getCommentByRecipeTest() {
        //from Recipe r left join fetch r.comments where r.id=:id
        List<Comment> result = commentDao.getBy(recipe1);
        Assert.assertNotNull(result);
        //Recipe recipeResult = recipeDao.getRecipeEagerByComment(recipe1.getId());
        //Assert.assertEquals(recipeResult.getComments().toString(), result.toString());
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void getCommentsByUserTest() {
        List<Comment> result = commentDao.getBy(user);
        Assert.assertNotNull(result);
        Assert.assertEquals(2, result.size());
    }

}
