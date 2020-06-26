package trainingProject.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import trainingProject.model.Recipe;
import trainingProject.service.RecipeService;

import java.util.List;


@RestController
@RequestMapping(value = "/recipe")
public class RecipeController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private RecipeService recipeService;

    // /recipe GET
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Recipe> getRecipes() {
        logger.debug("Getting all recipes");
        return recipeService.getRecipes();
    }

    // /recipe POST
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Recipe create(@RequestBody Recipe recipe) {
        logger.debug("Creating a recipe");
        Recipe r = recipeService.save(recipe);
        return r;
    }

    // /recipe/1 GET
    @RequestMapping(value = "/{Id}", method = RequestMethod.GET)
    public Recipe getRecipeById(@PathVariable("Id") Long id) {
        logger.debug("Getting recipe by id " + id);
        return recipeService.getBy(id);
    }

    // /recipe/197?name=anotherNewName PATCH
    @RequestMapping(value = "/{Id}", method = RequestMethod.PATCH)
    public Recipe updateRecipeName(@PathVariable("Id") long id, @RequestParam("name") String name) {
        logger.debug("change name");
        Recipe r = recipeService.getBy(id);
        r.setName(name);
        r = recipeService.update(r);
        return r;
    }

/*
    @RequestMapping(value = "/{Id}", method = RequestMethod.PATCH)
    public Recipe updateRecipeIngredient(@PathVariable("Id") long id, @RequestParam("ingredient") String ingredient) {
        logger.debug("change ingredient");
        Recipe r = recipeService.getBy(id);
        r.setIngredient(ingredient);
        r = recipeService.update(r);
        return r;
    }

/*
    @RequestMapping(value = "/{Id}", method = RequestMethod.PATCH)
    public Recipe updateRecipeDescription(@PathVariable("Id") long id, @RequestParam("description") String description) {
        logger.debug("change description");
        Recipe r = recipeService.getBy(id);
        r.setDescription(description);
        r = recipeService.update(r);
        return r;
    }

     */
}
