package trainingProject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    //TODO  One user created this recipe
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

    /*
    //TODO update name, ingredient, description
    @RequestMapping(value = "/{Id}", method = RequestMethod.PATCH)
        public Recipe updateRecipe(@PathVariable("iD") long id, ) {

    }

     */
}
