package com.ascending.training.repository;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecipeDaoTest {
    private RecipeDao recipeJDBCDao;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Before
    public void setUp() {
        recipeJDBCDao = new RecipeDao();
    }

    @After
    public void tearDown() {
        recipeJDBCDao = null;
    }

    @Test
    public void getRecipesTest() {
       logger.debug("start unit test for getRecipesTest...");

        assertEquals(0, recipeJDBCDao.getRecipes().size());
        //System.out.println(recipeJDBCDao.getRecipes());
        //assertEquals(6, recipeJDBCDao.getRecipes().size());
    }
}
