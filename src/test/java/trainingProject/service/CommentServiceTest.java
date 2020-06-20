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
public class CommentServiceTest {
    @Autowired
    private CommentService commentService;
    private Comment comment1;
    private Comment comment2;

    @Autowired
    private UserService userService;
    private User user;

    @Autowired
    private RecipeService recipeService;
    private Recipe recipe1;
    private Recipe recipe2;

    @Before
    public void setup() {
        //userDao = new UserDaoImpl();
        user = new User("Meow", "meow@gmail.com", "abcd");
        userService.save(user);

        //recipeDao = new RecipeDaoImpl();
        recipe1 = new Recipe();
        recipe1.setCreationDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        recipe1.setCuisine("Greek");
        recipe1.setDescription("roast chicken in the oven");
        recipe1.setIngredient("parsely, chicken, salt, pepper");
        recipe1.setName("Greek style roast chicken");
        recipe1.setUser(user);
        recipeService.save(recipe1);

        recipe2 = new Recipe();
        recipe2.setCreationDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        recipe2.setCuisine("Chinese");
        recipe2.setDescription("wash all vegetables, add oil to wok, then add vegetable and salt, stir for five minutes");
        recipe2.setIngredient("beans, broccoli, cabbage");
        recipe2.setName("stir fry vegetables");
        recipe2.setUser(user);
        recipeService.save(recipe2);

        //commentDao = new CommentDaoImpl();

        comment1 = new Comment();
        comment1.setContent("GOOD");
        comment1.setUser(user);
        comment1.setRecipe(recipe1);
        commentService.save(comment1);

        comment2 = new Comment();
        comment2.setContent("This is my favorite recipe");
        comment2.setUser(user);
        comment2.setRecipe(recipe1);
        commentService.save(comment2);

    }

    @After
    public void tearDown() {
        commentService.delete(comment1);
        commentService.delete(comment2);
        recipeService.delete(recipe1);
        recipeService.delete(recipe2);
        userService.delete(user);
    }

    @Test
    public void getByRecipeTest() {
        List<Comment> result = commentService.getBy(recipe1);
        Assert.assertNotNull(result);
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void getByUserTest() {
        List<Comment> result = commentService.getBy(user);
        Assert.assertNotNull(result);
        Assert.assertEquals(2, result.size());
    }
}
