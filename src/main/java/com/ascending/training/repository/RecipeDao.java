package com.ascending.training.repository;

import com.ascending.training.model.Recipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeDao {
    private static final String DBURL = "jdbc:postgresql://localhost:5431/recipes";
    private static final String USER = "admin";
    private static final String PASS = "password";
    private Logger logger = LoggerFactory.getLogger(getClass());

    //CRUD
    public List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //STEP 2: Open a connection
            logger.debug("open connection...");
            conn = DriverManager.getConnection(DBURL, USER, PASS);

            //STEP 3: Execute a query
            logger.info("create statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM recipes";
            rs = stmt.executeQuery(sql);
            //STEP 4: Extract data from result set
            while(rs.next()) {
                //Retrieve by column name
                Long id  = rs.getLong("recipe_id");
                String name = rs.getString("recipe_name");
                String description = rs.getString("description");
                Date date = rs.getDate("creation_date");
                String ingredient = rs.getString("ingredient");
                String publisher = rs.getString("publisher");
                String cuisine = rs.getString("cuisine");
                //Fill the object
                Recipe recipe = new Recipe();
                recipe.setId(id);
                recipe.setName(name);
                recipe.setDescription(description);
                recipe.setDate(date);
                recipe.setIngredient(ingredient);
                recipe.setPublisher(publisher);
                recipe.setCuisine(cuisine);
                recipes.add(recipe);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            //STEP 6: finally block used to close resources
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            }
            catch(SQLException se) {
                se.printStackTrace();
            }
        }
        return recipes;
    }
/*
    public static void main(String[] args) {
        RecipeDao recipeJDBCDao = new RecipeDao();
        System.out.println(recipeJDBCDao.getRecipes());
    }

 */
}
