package pl.jarugalucas.spring5recipeapp.services;

import pl.jarugalucas.spring5recipeapp.commands.UnitOfMeasureCommand;
import java.util.Set;
public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUoms();
}