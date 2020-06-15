package trainingProject.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import trainingProject.model.Recipe;
import trainingProject.model.User;

import java.util.Calendar;

public class UserDaoTest {
    private UserDao userDao;
    private User user;
    private RecipeDao recipeDao;
    private Recipe recipe1;
    private Recipe recipe2;
    @Before
    public void init() {
        userDao = new UserDaoImpl(); //new user
        user = new User();
        user.setName("graceeeedu");
        user.setEmail("gracedjx@gmail.com");
        user.setPassword("1234");
        userDao.save(user);

        recipeDao = new RecipeDaoImpl();
        recipe1 = new Recipe();

        recipe1 = new Recipe(); //new recipe
        recipe1.setCreationDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        recipe1.setCuisine("Greek");
        recipe1.setDescription("roast chicken in the oven");
        recipe1.setIngredient("parsely, chicken, salt, pepper");
        recipe1.setName("Greek style roast chicken");
        recipe1.setUser(user);
        recipeDao.save(recipe1);

        recipe2 = new Recipe(); // new recipe
        recipe2.setCreationDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        recipe2.setCuisine("Greek");
        recipe2.setDescription("roast chicken in the oven");
        recipe2.setIngredient("parsely, chicken, salt, pepper");
        recipe2.setName("Greek style roast chicken");
        recipe2.setUser(user);
        recipeDao.save(recipe2);


    }

    @After
    public void tearDown() {
        recipeDao.delete(recipe1);
        recipeDao.delete(recipe2);
        userDao.delete(user);
    }

    @Test
    public void getUsersTest() {
        Assert.assertEquals(1, userDao.getUsers().size());
    }

    @Test
    public void getUserEagerByTest() {
        User userResult = userDao.getUserEagerBy(user.getId());
        Assert.assertNotNull(userResult);
        Assert.assertEquals(userResult.getName(), user.getName());
        Assert.assertTrue(userResult.getRecipes().size() > 0);

    }
}
