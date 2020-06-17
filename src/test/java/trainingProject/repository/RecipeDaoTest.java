package trainingProject.repository;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import trainingProject.model.Comment;
import trainingProject.model.Recipe;
import trainingProject.model.User;

import java.util.Calendar;
import java.util.List;

public class RecipeDaoTest {
    private RecipeDao recipeDao;
    private Recipe recipe1;

    private CommentDao commentDao;
    private Comment comment;

    private UserDao userDao;
    private User user1;
    private User user2;

    @Before
    public void setUp() {
        userDao = new UserDaoImpl();
        user1 = new User();
        user1.setEmail("Hulu@gmail.com");
        user1.setPassword("1234");
        user1.setName("Hulu");
        userDao.save(user1);
        user2 = new User("Golds", "golds@gmail.com", "haha");
        userDao.save(user2);

        recipeDao = new RecipeDaoImpl();
        recipe1 = new Recipe();
        recipe1.setCreationDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        recipe1.setCuisine("Greek");
        recipe1.setDescription("roast chicken in the oven");
        recipe1.setIngredient("parsely, chicken, salt, pepper");
        recipe1.setName("Greek style roast chicken");
        recipe1.setUser(user1);
        recipeDao.save(recipe1);

        commentDao = new CommentDaoImpl();
        comment = new Comment();
        comment.setRecipe(recipe1);
        comment.setUser(user2);
        comment.setContent("I like it. Thank you!!");
        commentDao.save(comment);

    }

    @After
    public void tearDown() {
        commentDao.delete(comment);
        recipeDao.delete(recipe1);
        userDao.delete(user1);
        userDao.delete(user2);


    }

    @Test
    public void getByTest() {
        Recipe recipeResult = recipeDao.getBy(recipe1.getId());
        Assert.assertEquals(recipeResult.getDescription(), recipe1.getDescription());
    }

    @Test
    public void getRecipesTest() {
        Assert.assertEquals(1, recipeDao.getRecipes().size());
    }

    @Test
    public void getRecipeEagerByCommentTest() {
        Recipe recipeResult = recipeDao.getRecipeEagerByComment(recipe1.getId());
        Assert.assertNotNull(recipeResult);
        Assert.assertEquals(recipeResult.getName(), recipe1.getName());
        Assert.assertTrue(recipeResult.getComments().size() > 0);
    }

    @Test
    public void getByUserTest() {
        List<Recipe> recipeResult = recipeDao.getBy(user1);
        Assert.assertNotNull(recipeResult);
        Assert.assertEquals(1, recipeResult.size());
    }

}
