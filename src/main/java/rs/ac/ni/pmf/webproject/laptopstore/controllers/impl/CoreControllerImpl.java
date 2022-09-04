package rs.ac.ni.pmf.webproject.laptopstore.controllers.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.ni.pmf.webproject.laptopstore.controllers.CoreController;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.ColorDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.CoreDTO;
import rs.ac.ni.pmf.webproject.laptopstore.services.CoreService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CoreControllerImpl implements CoreController {

    @Autowired
    private CoreService coreService;

    private static final Logger logger = LoggerFactory.getLogger(ColorControllerImpl.class);

    @Override
    public CollectionModel<EntityModel<CoreDTO>> getCores() {
        logger.info("URL: ../laptop/core");
        logger.debug("Getting all cores...");
        List<EntityModel<CoreDTO>> models = new ArrayList<>();
        List<CoreDTO> cores = coreService.findCores();
        for (CoreDTO coreDTO:cores) {
            models.add(EntityModel.of(coreDTO));
        }

        return CollectionModel.of(models,
                linkTo(methodOn(CoreControllerImpl.class).getCores()).withSelfRel());

    }

    @Override
    public EntityModel<CoreDTO> getCore(Integer id) {
        logger.info("URL: ../laptop/core/{}", id);
        logger.debug("Getting core with id = {}...", id);
        return EntityModel.of(coreService.findCoreById(id));
    }

    @Override
    public EntityModel<CoreDTO> addCore(CoreDTO core) {
        logger.info("URL: ../laptop/core");
        logger.debug("Adding new core...");
        return EntityModel.of(coreService.saveCore(core));

    }


}
