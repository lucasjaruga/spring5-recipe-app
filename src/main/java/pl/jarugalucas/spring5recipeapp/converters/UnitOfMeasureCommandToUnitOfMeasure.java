package pl.jarugalucas.spring5recipeapp.converters;

import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.jarugalucas.spring5recipeapp.commands.UnitOfMeasureCommand;
import pl.jarugalucas.spring5recipeapp.model.UnitOfMeasure;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Synchronized // Spring does not guarantee thread safety, annotation from Lombok
    @Nullable
    @Override
    public UnitOfMeasure convert (UnitOfMeasureCommand source){
        if(source == null)
            return null;

        final UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(source.getId());
        uom.setUom(source.getDescription());
        return uom;
    }
}