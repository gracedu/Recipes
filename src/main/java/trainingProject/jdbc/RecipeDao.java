package trainingProject.jdbc;

import trainingProject.model.Recipe;
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


    //create a recipe
    public void createUser(Recipe recipe) {
        Connection conn = null;

        try {
            //STEP 2: Open a connection
            logger.debug("Connecting to database...");
            conn = DriverManager.getConnection(DBURL, USER, PASS);

            //STEP 3: Execute a query
            logger.info("Creating statement...");

            String sql;
            sql = "INSERT INTO recipes (recipe_name, ingredient, description, pulisher, cuisine) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            logger.info("Filling data...");
            preparedStatement.setString(1, recipe.getRecipeName());
            preparedStatement.setString(2, recipe.getIngredient());
            preparedStatement.setString(3, recipe.getDescription());
            preparedStatement.setString(4, recipe.getPublisher());
            preparedStatement.setString(5, recipe.getCuisine());
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
            logger.error("error found", e);
        } finally {
            try {
                if(conn != null) conn.close();
            }
            catch(SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //STEP 2: Open a connection
            logger.debug("Connecting to database...");
            conn = DriverManager.getConnection(DBURL, USER, PASS);

            //STEP 3: Execute a query
            logger.info("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM recipes";
            rs = stmt.executeQuery(sql);
            logger.info("Converting data...");
            //STEP 4: Extract data from result set
            while(rs.next()) {
                //Retrieve by column recipe_name
                Long recipe_id  = rs.getLong("recipe_id");
                String recipe_name = rs.getString("recipe_name");
                String description = rs.getString("description");
                Date creationDate = rs.getDate("creation_date");
                String ingredient = rs.getString("ingredient");
                String publisher = rs.getString("publisher");
                String cuisine = rs.getString("cuisine");
                //Fill the object
                Recipe recipe = new Recipe();
                recipe.setRecipeId(recipe_id);
                recipe.setRecipeName(recipe_name);
                recipe.setDescription(description);
                recipe.setCreationDate(creationDate);
                recipe.setIngredient(ingredient);
                recipe.setPublisher(publisher);
                recipe.setCuisine(cuisine);
                recipes.add(recipe);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            logger.error("error found", e);
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



}
