package pl.jarugalucas.spring5recipeapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jarugalucas.spring5recipeapp.services.RecipeService;

@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"/", "/index", "/index.html"})
    public String getIndexPage(Model model){

        log.debug("I'm in the controller to get index page");

        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }
}