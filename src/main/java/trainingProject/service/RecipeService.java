package trainingProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainingProject.model.Recipe;
import trainingProject.model.User;
import trainingProject.repository.RecipeDao;

import java.util.List;

@Service
public class RecipeService {
    @Autowired
    private RecipeDao recipeDao;

    public Recipe save(Recipe recipe) {
        return recipeDao.save(recipe);
    }

    public List<Recipe> getRecipes() {
        return recipeDao.getRecipes();
    }

    public Recipe getBy(Long id) {
        return recipeDao.getBy(id);
    }

    public boolean delete(Recipe recipe) {
        return recipeDao.delete(recipe);
    }

    public Recipe getRecipeEagerByComment(Long id) {
        return recipeDao.getRecipeEagerByComment(id);
    }
    public List<Recipe> getBy(User user) {
        return recipeDao.getBy(user);
    }
}
