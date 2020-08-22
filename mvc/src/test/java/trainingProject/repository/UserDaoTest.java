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
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class UserDaoTest {
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
       // userDao = new UserDaoImpl(); //new user
        user = new User();
       // user.setId(250L);
        user.setName("graceeeedu"+ UUID.randomUUID());
        user.setEmail("gracedjx@gmail.com"+UUID.randomUUID());
        user.setPassword("1234");
        userDao.save(user);

     //   recipeDao = new RecipeDaoImpl();

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

     //   commentDao = new CommentDaoImpl();

        comment1 = new Comment();
        comment1.setContent("It's good");
        comment1.setUser(user);
        comment1.setRecipe(recipe1);
        commentDao.save(comment1);

        comment2 = new Comment();
        comment2.setContent("It's too hard for me");
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
        User userResult = userDao.getBy(user.getId());
        Assert.assertEquals(userResult.getName(), user.getName());
        Assert.assertEquals(userResult.getEmail(), user.getEmail());
    }

    @Test
    public void getUsersTest() {
        Assert.assertEquals(1, userDao.getUsers().size());
    }


    @Test
    public void getUserEagerByRecipeTest() { //based on table recipes(user_id)
        User userResult = userDao.getUserEagerByRecipe(user.getId());
        Assert.assertNotNull(userResult);
        Assert.assertEquals(userResult.getName(), user.getName());
        Assert.assertTrue(userResult.getRecipes().size() > 0);

    }

    @Test
    public void getUserEagerByCommentTest() { //based on table comments(user_id)
        User userResult = userDao.getUserEagerByComment(user.getId());
        Assert.assertNotNull(userResult);
        Assert.assertEquals(userResult.getName(), user.getName());
        Assert.assertTrue(userResult.getComments().size() > 0);
    }

    @Test
    public void getUserByCredentialsTest() {
        User u1 = userDao.getUserByCredentials(user.getEmail(), user.getPassword());
        Assert.assertEquals(u1.getName(), user.getName());
    }
}
