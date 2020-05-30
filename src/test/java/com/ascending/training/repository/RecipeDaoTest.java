package com.ascending.training.repository;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class RecipeDaoTest {
    @Test
    public void getRecipesTest() {
       // logger.debug("start unit test for getRecipesTest...");
        RecipeDao recipeJDBCDao = new RecipeDao();
        assertEquals(0, recipeJDBCDao.getRecipes().size());
        //System.out.println(recipeJDBCDao.getRecipes());
        assertEquals(6, recipeJDBCDao.getRecipes().size());
    }
}
