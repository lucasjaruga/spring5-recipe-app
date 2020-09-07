package pl.jarugalucas.spring5recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jarugalucas.spring5recipeapp.model.Recipe;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}