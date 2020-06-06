package trainingProject.repository;

import trainingProject.model.Recipe;

import java.util.List;

public interface RecipeDao {
    Recipe save(Recipe recipe);
    List<Recipe> getRecipes();
    Recipe getBy(Long recipeId);
    boolean delete(Recipe recipe);
    //List<Recipe> getRecipesEager();

}
