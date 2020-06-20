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

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ApplicationBootstrap.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;
    private User user;

    @Autowired
    private CommentService commentService;
    private Comment comment;

    @Autowired
    private RecipeService recipeService;
    private Recipe recipe;

    @Before
    public void setup() {
        userService = new UserService();
        user = new User("Lisi", "lisi@gmail.com", "12345");
        userService.save(user);

        recipe = new Recipe();
        recipe.setName("chicken");
        recipe.setIngredient("parsely and chicken");
        recipe.setDescription("wash them and put in water");
        recipe.setCuisine("Italian");
        recipe.setCreationDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        recipe.setUser(user);
        recipeService.save(recipe);

        comment = new Comment();
        comment.setContent("I like it!!");
        comment.setUser(user);
        comment.setRecipe(recipe);
        commentService.save(comment);
    }

    @After
    public void tearDown() {
        commentService.delete(comment);
        recipeService.delete(recipe);
        userService.delete(user);
    }

    @Test
    public void getUserEagerByRecipeTest() {
        User userResult = userService.getUserEagerByRecipe(user.getId());
        Assert.assertNotNull(userResult);
        Assert.assertEquals(userResult.getName(), user.getName());
        Assert.assertTrue(userResult.getRecipes().size() > 0);
    }

    @Test
    public void getUserEagerByCommentTest() {
        User userResult = userService.getUserEagerByComment(user.getId());
        Assert.assertNotNull(userResult);
        Assert.assertEquals(userResult.getName(), user.getName());
        Assert.assertTrue(userResult.getComments().size() > 0);
    }
}
