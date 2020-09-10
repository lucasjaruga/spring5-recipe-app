package pl.jarugalucas.spring5recipeapp.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.jarugalucas.spring5recipeapp.model.*;
import pl.jarugalucas.spring5recipeapp.repositories.CategoryRepository;
import pl.jarugalucas.spring5recipeapp.repositories.RecipeRepository;
import pl.jarugalucas.spring5recipeapp.repositories.UnitOfMeasureRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public DataLoader(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
        log.debug("I'm in the Bootstrap to load some data");
    }

    private List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>(2);

        //get UOMs
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByUom("Each");

        if (!eachUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> tablespoonUomOptional = unitOfMeasureRepository.findByUom("Tablespoon");

        if (!tablespoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> teaspoonUomOptional = unitOfMeasureRepository.findByUom("Teaspoon");

        if (!teaspoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByUom("Dash");

        if (!dashUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByUom("Cup");

        if (!cupUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByUom("Pint");

        if (!pintUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> ounceUomOptional = unitOfMeasureRepository.findByUom("Ounce");

        if (!ounceUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        //get Units
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tablespoonUom = eachUomOptional.get();
        UnitOfMeasure teaspoonUom = eachUomOptional.get();
        UnitOfMeasure dashUom = eachUomOptional.get();
        UnitOfMeasure pinchUom = eachUomOptional.get();
        UnitOfMeasure cupUom = eachUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();

        // get Categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if (!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category not found");
        }

        Optional<Category> italianCategoryOptional = categoryRepository.findByDescription("Italian");

        if (!italianCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category not found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if (!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category not found");
        }

        Optional<Category> fastFoodCategoryOptional = categoryRepository.findByDescription("Fast Food");

        if (!fastFoodCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category not found");
        }

        //get Categories Optionals
        Category american = americanCategoryOptional.get();
        Category italian = italianCategoryOptional.get();
        Category mexican = mexicanCategoryOptional.get();
        Category fastFood = fastFoodCategoryOptional.get();

        //Guacamole
        Recipe guacRecipe = new Recipe();
        Notes guacNote = new Notes();

        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setServings(4);
        guacRecipe.setSource("SimplyRecipes.com");
        guacRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacRecipe.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl. 2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.) 3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving. 4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");
        guacRecipe.setDifficulty(Difficulty.MODERATE);
        guacNote.setRecipeNotes("The best guacamole keeps it simple: just ripe avocados, salt, a squeeze of lime, onions, chiles, cilantro, and some chopped tomato. Serve it as a dip at your next party or spoon it on top of tacos for an easy dinner upgrade. ");

        guacRecipe.setNote(guacNote);

        guacRecipe.getCategories().add(american);
        guacRecipe.getCategories().add(mexican);

        guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("salt, more to taste", new BigDecimal(0.25), teaspoonUom));
        guacRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(1), tablespoonUom));
        guacRecipe.addIngredient(new Ingredient("cup of minced red onion or thinly sliced green onion", new BigDecimal(2), tablespoonUom));
        guacRecipe.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("cilantro (leaves and tender stems), finely chopped", new BigDecimal(2), tablespoonUom));
        guacRecipe.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(1), dashUom));
        guacRecipe.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(0.5), eachUom));
        guacRecipe.addIngredient(new Ingredient("Red radishes or jicama, to garnish", new BigDecimal(1), eachUom));
        guacRecipe.addIngredient(new Ingredient("Tortilla chips, to serve", new BigDecimal(1), eachUom));

        recipes.add(guacRecipe);

        // Spicy Grilled Chicken
        Recipe spicyGrilledChicken = new Recipe();
        Notes chickenNote = new Notes();

        spicyGrilledChicken.setDescription("Spicy Grilled Chicken Tacos Recipe");
        spicyGrilledChicken.setPrepTime(20);
        spicyGrilledChicken.setCookTime(15);
        spicyGrilledChicken.setServings(6);
        spicyGrilledChicken.setSource("SimplyRecipes.com");
        spicyGrilledChicken.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        spicyGrilledChicken.setDirections("\"1 Prepare a gas or charcoal grill for medium-high, direct heat.\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"Set aside to marinate while the grill heats and you prepare the rest of the toppings. 3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"Wrap warmed tortillas in a tea towel to keep them warm until serving.\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\"");
        spicyGrilledChicken.setDifficulty(Difficulty.EASY);
        chickenNote.setRecipeNotes("Spicy grilled chicken tacos! Quick marinade, then grill. Ready in about 30 minutes. Great for a quick weeknight dinner, backyard cookouts, and tailgate parties. ");

        spicyGrilledChicken.setNote(chickenNote);

        spicyGrilledChicken.getCategories().add(american);
        spicyGrilledChicken.getCategories().add(mexican);

        spicyGrilledChicken.addIngredient(new Ingredient("ancho chili powder", new BigDecimal(2), tablespoonUom));
        spicyGrilledChicken.addIngredient(new Ingredient("dried oregano", new BigDecimal(1), teaspoonUom));
        spicyGrilledChicken.addIngredient(new Ingredient("dried cumin", new BigDecimal(1), teaspoonUom));
        spicyGrilledChicken.addIngredient(new Ingredient("sugar", new BigDecimal(1), teaspoonUom));
        spicyGrilledChicken.addIngredient(new Ingredient("salt", new BigDecimal(0.5), teaspoonUom));
        spicyGrilledChicken.addIngredient(new Ingredient("clove garlic, finely chopped", new BigDecimal(1), eachUom));
        spicyGrilledChicken.addIngredient(new Ingredient("finely grated orange zest", new BigDecimal(1), tablespoonUom));
        spicyGrilledChicken.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tablespoonUom));
        spicyGrilledChicken.addIngredient(new Ingredient("olive oil", new BigDecimal(2), tablespoonUom));
        spicyGrilledChicken.addIngredient(new Ingredient("skinless, boneless chicken thighs (1 1/4 pounds)", new BigDecimal(6), eachUom));
        spicyGrilledChicken.addIngredient(new Ingredient("small corn tortillas", new BigDecimal(8), eachUom));
        spicyGrilledChicken.addIngredient(new Ingredient("packed baby arugula (3 ounces)", new BigDecimal(3), cupUom));
        spicyGrilledChicken.addIngredient(new Ingredient("medium ripe avocados, sliced", new BigDecimal(2), eachUom));
        spicyGrilledChicken.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), eachUom));
        spicyGrilledChicken.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(0.5), pintUom));
        spicyGrilledChicken.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(0.25), eachUom));
        spicyGrilledChicken.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(1), eachUom));
        spicyGrilledChicken.addIngredient(new Ingredient("sour cream thinned with 1/4 cup milk", new BigDecimal(0.5), cupUom));
        spicyGrilledChicken.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(1), eachUom));

        recipes.add(spicyGrilledChicken);

        return recipes;
    }
}