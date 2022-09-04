package rs.ac.ni.pmf.webproject.laptopstore.controllers.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.ni.pmf.webproject.laptopstore.controllers.OperatingSystemController;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.OperatingSystemDTO;
import rs.ac.ni.pmf.webproject.laptopstore.services.OperatingSystemService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class OperatingSystemControllerImpl implements OperatingSystemController {

    @Autowired
    private OperatingSystemService operatingSystemService;

    private static final Logger logger = LoggerFactory.getLogger(ColorControllerImpl.class);

    @Override
    public CollectionModel<EntityModel<OperatingSystemDTO>> getOperatingSystems() {
        logger.info("URL: ../laptop/operating-system");
        logger.debug("Getting all operating systems...");
        List<EntityModel<OperatingSystemDTO>> models = new ArrayList<>();
        List<OperatingSystemDTO> operatingSystems = operatingSystemService.findOperatingSystems();
        for (OperatingSystemDTO operatingSystemDTO :operatingSystems) {
            models.add(EntityModel.of(operatingSystemDTO));
        }

        return CollectionModel.of(models,
                linkTo(methodOn(OperatingSystemControllerImpl.class).getOperatingSystems()).withSelfRel());

    }

    @Override
    public EntityModel<OperatingSystemDTO> getOperatingSystem(Integer id) {
        logger.info("URL: ../laptop/operating-system/{}", id);
        logger.debug("Getting operating system with id = {}...", id);
        return EntityModel.of(operatingSystemService.findOperatingSystemById(id));
    }

    @Override
    public EntityModel<OperatingSystemDTO> addOperatingSystem(OperatingSystemDTO operatingSystem) {
        logger.info("URL: ../laptop/operating-system");
        logger.debug("Adding new operating system...");
        return EntityModel.of(operatingSystemService.saveOperatingSystem(operatingSystem));

    }

}
