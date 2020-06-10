package trainingProject.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import trainingProject.model.Recipe;

import java.util.Calendar;

public class RecipeDaoTest {
    private RecipeDao recipeDao;
    private Recipe recipe1;
    @Before
    public void setUp() {
        recipeDao = new RecipeDaoImpl();
        recipe1 = new Recipe();
        recipe1.setCreationDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        recipe1.setCuisine("Greek");
        recipe1.setDescription("roast chicken in the oven");
        recipe1.setIngredient("parsely, chicken, salt, pepper");
        recipe1.setPublisher("dimitria"); //this is a foreign key reference(user.user_name), will have a problem if run test
        recipe1.setRecipeName("Greek style roast chicken");
        //recipe1.setRecipeId(1);
        recipe1 = recipeDao.save(recipe1);

    }

    @After
    public void tearDown() {
        recipeDao.delete(recipe1);
    }

    @Test
    public void getRecipesTest() {
        Assert.assertEquals(1, recipeDao.getRecipes().size());
    }

}
