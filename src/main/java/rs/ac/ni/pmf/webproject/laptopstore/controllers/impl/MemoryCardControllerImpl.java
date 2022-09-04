package rs.ac.ni.pmf.webproject.laptopstore.controllers.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.ni.pmf.webproject.laptopstore.controllers.MemoryCardController;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.MemoryCardDTO;
import rs.ac.ni.pmf.webproject.laptopstore.services.MemoryCardService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class MemoryCardControllerImpl implements MemoryCardController {

    @Autowired
    private MemoryCardService memoryCardService;

    private static final Logger logger = LoggerFactory.getLogger(ColorControllerImpl.class);

    @Override
    public CollectionModel<EntityModel<MemoryCardDTO>> getMemoryCards() {
        logger.info("URL: ../laptop/memory-card");
        logger.debug("Getting all memory cards...");
        List<EntityModel<MemoryCardDTO>> models = new ArrayList<>();
        List<MemoryCardDTO> memoryCards = memoryCardService.findMemoryCards();
        for (MemoryCardDTO memoryCardDTO:memoryCards) {
            models.add(EntityModel.of(memoryCardDTO));
        }

        return CollectionModel.of(models,
                linkTo(methodOn(MemoryCardControllerImpl.class).getMemoryCards()).withSelfRel());

    }

    @Override
    public EntityModel<MemoryCardDTO> getMemoryCard(Integer id) {
        logger.info("URL: ../laptop/memory-card/{}", id);
        logger.debug("Getting memory card with id = {}...", id);
        return EntityModel.of(memoryCardService.findMemoryCardById(id));
    }

    @Override
    public EntityModel<MemoryCardDTO> addMemoryCard(MemoryCardDTO memoryCard) {
        logger.info("URL: ../laptop/memory-card");
        logger.debug("Adding new memory card...");
        return EntityModel.of(memoryCardService.saveMemoryCard(memoryCard));
    }


}
