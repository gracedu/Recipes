package trainingProject.repository;

import trainingProject.model.Recipe;
import trainingProject.model.User;

import java.util.List;

public interface RecipeDao {
    Recipe save(Recipe recipe);
    List<Recipe> getRecipes();
    Recipe getBy(Long id);
    boolean delete(Recipe recipe);
    Recipe getRecipeEagerByComment(Long id);
    List<Recipe> getBy(User user);
    Recipe update(Recipe recipe);

}
