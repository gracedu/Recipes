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
import trainingProject.model.Image;
import trainingProject.model.Recipe;
import trainingProject.model.User;

import java.util.Calendar;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class ImageDaoTest {
    @Autowired
    private ImageDao imageDao;
    private Image image1;
    private Image image2;

    @Autowired
    private UserDao userDao;
    private User user1;
    private User user2;

    @Autowired
    private RecipeDao recipeDao;
    private Recipe recipe1;
    private Recipe recipe2;

    @Before
    public void setUp() {
        user1 = new User();
        user1.setEmail("Hulu@gmail.com");
        user1.setPassword("1234");
        user1.setName("Hulu");
        userDao.save(user1);
        user2 = new User("Golds", "golds@gmail.com", "haha");
        userDao.save(user2);

        recipe1 = new Recipe();
        recipe1.setCreationDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        recipe1.setCuisine("Greek");
        recipe1.setDescription("roast chicken in the oven");
        recipe1.setIngredient("parsely, chicken, salt, pepper");
        recipe1.setName("Greek style roast chicken");
        recipe1.setUser(user1);
        recipeDao.save(recipe1);

        recipe2 = new Recipe();
        recipe2.setCreationDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        recipe2.setCuisine("Chinese");
        recipe2.setDescription("wash all vegetables, add oil to wok, then add vegetable and salt, stir for five minutes");
        recipe2.setIngredient("beans, broccoli, cabbage");
        recipe2.setName("stir fry vegetables");
        recipe2.setUser(user2);
        recipeDao.save(recipe2);

        image1 = new Image();
        //image1.setId(1L); //it doesn't affect the database.
        image1.setTitle("photo of potato");
        image1.setRecipe(recipe1);
        imageDao.save(image1);

        image2 = new Image();
       // image2.setId(2L);
        image2.setTitle("chicken's picture");
        image2.setRecipe(recipe2);
        imageDao.save(image2);
    }

    @After
    public void tearDown() {
        imageDao.delete(image1);
        imageDao.delete(image2);
        recipeDao.delete(recipe1);
        recipeDao.delete(recipe2);
        userDao.delete(user1);
        userDao.delete(user2);
    }

    @Test
    public void getImagesTest() {
        List<Image> images = imageDao.getImages();
        Assert.assertNotNull(images);
        Assert.assertEquals(2, images.size());
    }

    @Test
    public void getByIdTest() {
        Image result1 = imageDao.getBy(image1.getId());
        Image result2 = imageDao.getBy(image2.getId());
        Assert.assertEquals(image1.getId(),result1.getId());
        Assert.assertEquals(image2.getId(),result2.getId());
    }

    //TODO
    @Test
    public void getByUserTest() {

    }

    @Test
    public void getByRecipeTest() {
        List<Image> result = imageDao.getBy(recipe1);
        Assert.assertEquals(result.size(), 1);
    }
}
