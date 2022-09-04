package rs.ac.ni.pmf.webproject.laptopstore.controllers.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.ni.pmf.webproject.laptopstore.controllers.GraphicCardController;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.GraphicCardDTO;
import rs.ac.ni.pmf.webproject.laptopstore.services.GraphicCardService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class GraphicCardControllerImpl implements GraphicCardController {

    @Autowired
    private GraphicCardService graphicCardService;

    private static final Logger logger = LoggerFactory.getLogger(ColorControllerImpl.class);

    @Override
    public CollectionModel<EntityModel<GraphicCardDTO>> getGraphicCards() {
        logger.info("URL: ../laptop/graphic-card");
        logger.debug("Getting all graphic cards...");
        List<EntityModel<GraphicCardDTO>> models = new ArrayList<>();
        List<GraphicCardDTO> graphicCards = graphicCardService.findGraphicCards();
        for (GraphicCardDTO graphicCardDTO:graphicCards) {
            models.add(EntityModel.of(graphicCardDTO));
        }

        return CollectionModel.of(models,
                linkTo(methodOn(GraphicCardControllerImpl.class).getGraphicCards()).withSelfRel());

    }

    @Override
    public EntityModel<GraphicCardDTO> getGraphicCard(Integer id) {
        logger.info("URL: ../laptop/graphic-card/{}", id);
        logger.debug("Getting  graphic card with id = {}...", id);
        return EntityModel.of(graphicCardService.findGraphicCardById(id));
    }

    @Override
    public EntityModel<GraphicCardDTO> addGraphicCard(GraphicCardDTO graphicCard) {
        logger.info("URL: ../laptop/graphic-card");
        logger.debug("Adding new graphic card...");
        return EntityModel.of(graphicCardService.saveGraphicCard(graphicCard));
    }

}
