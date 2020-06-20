package trainingProject.service;

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
public class RecipeServiceTest {
    @Autowired
    RecipeService recipeService;
    private Recipe recipe1;

    @Autowired
    private CommentService commentService;
    private Comment comment;

    @Autowired
    private UserService userService;
    private User user1;
    private User user2;

    @Before
    public void setup() {
        //userDao = new UserDaoImpl();
        user1 = new User();
        user1.setEmail("Hulu@gmail.com");
        user1.setPassword("1234");
        user1.setName("Hulu");
        userService.save(user1);
        user2 = new User("Golds", "golds@gmail.com", "haha");
        userService.save(user2);

        //recipeDao = new RecipeDaoImpl();
        recipe1 = new Recipe();
        recipe1.setCreationDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        recipe1.setCuisine("Greek");
        recipe1.setDescription("roast chicken in the oven");
        recipe1.setIngredient("parsely, chicken, salt, pepper");
        recipe1.setName("Greek style roast chicken");
        recipe1.setUser(user1);
        recipeService.save(recipe1);

        //commentDao = new CommentDaoImpl();
        comment = new Comment();
        comment.setRecipe(recipe1);
        comment.setUser(user2);
        comment.setContent("I like it. Thank you!!");
        commentService.save(comment);
    }

    @After
    public void tearDown() {
        commentService.delete(comment);
        recipeService.delete(recipe1);
        userService.delete(user1);
        userService.delete(user2);
    }

    @Test
    public void getRecipeEagerByCommentTest() {
        Recipe recipeResult = recipeService.getRecipeEagerByComment(recipe1.getId());
        Assert.assertNotNull(recipeResult);
        Assert.assertEquals(recipeResult.getName(), recipe1.getName());
        Assert.assertTrue(recipeResult.getComments().size() > 0);
    }

    @Test
    public void getByUserTest() {
        List<Recipe> recipeResult = recipeService.getBy(user1);
        Assert.assertNotNull(recipeResult);
        Assert.assertEquals(1, recipeResult.size());
    }
}
