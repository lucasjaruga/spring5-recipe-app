package pl.jarugalucas.spring5recipeapp.services;

import pl.jarugalucas.spring5recipeapp.commands.RecipeCommand;
import pl.jarugalucas.spring5recipeapp.model.Recipe;
import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);
}