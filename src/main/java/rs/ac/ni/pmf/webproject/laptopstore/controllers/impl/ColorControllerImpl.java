package rs.ac.ni.pmf.webproject.laptopstore.controllers.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.ni.pmf.webproject.laptopstore.controllers.ColorController;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.ColorDTO;
import rs.ac.ni.pmf.webproject.laptopstore.services.ColorService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ColorControllerImpl implements ColorController {

    @Autowired
    private ColorService colorService;

    private static final Logger logger = LoggerFactory.getLogger(ColorControllerImpl.class);

    @Override
    public CollectionModel<EntityModel<ColorDTO>> getColors() {
        logger.info("URL: ../laptop/color");
        logger.debug("Getting all colors...");
        List<EntityModel<ColorDTO>> models = new ArrayList<>();
        List<ColorDTO> colors = colorService.findColors();
        for (ColorDTO colorDTO:colors) {
            models.add(EntityModel.of(colorDTO));
        }
        return CollectionModel.of(models);
    }

    @Override
    public EntityModel<ColorDTO> getColor(Integer id) {
        logger.info("URL: ../laptop/color/{}", id);
        logger.debug("Getting color with id = {}...", id);
        return EntityModel.of(colorService.findColorById(id));
    }

    @Override
    public EntityModel<ColorDTO> addColor(ColorDTO color) {
        logger.info("URL: ../laptop/color");
        logger.debug("Adding new color...");
        return EntityModel.of(colorService.saveColor(color));
    }



}
