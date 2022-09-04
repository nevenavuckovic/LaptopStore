package rs.ac.ni.pmf.webproject.laptopstore.controllers.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.ni.pmf.webproject.laptopstore.controllers.HardDiskController;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.HardDiskDTO;
import rs.ac.ni.pmf.webproject.laptopstore.services.HardDiskService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class HardDiskControllerImpl implements HardDiskController {

    @Autowired
    private HardDiskService hardDiskService;

    private static final Logger logger = LoggerFactory.getLogger(ColorControllerImpl.class);

    @Override
    public CollectionModel<EntityModel<HardDiskDTO>> getHardDisks() {
        logger.info("URL: ../laptop/hard-disk");
        logger.debug("Getting all hard disks...");
        List<EntityModel<HardDiskDTO>> models = new ArrayList<>();
        List<HardDiskDTO> hardDisks = hardDiskService.findHardDisks();
        for (HardDiskDTO hardDiskDTO:hardDisks) {
            models.add(EntityModel.of(hardDiskDTO));
        }

        return CollectionModel.of(models,
                linkTo(methodOn(HardDiskControllerImpl.class).getHardDisks()).withSelfRel());

    }

    @Override
    public EntityModel<HardDiskDTO> getHardDisk(Integer id) {
        logger.info("URL: ../laptop/hard-disk/{}", id);
        logger.debug("Getting hard disk with id = {}...", id);
        return EntityModel.of(hardDiskService.findHardDiskById(id));
    }

    @Override
    public EntityModel<HardDiskDTO> addHardDisk(HardDiskDTO hardDisk) {
        logger.info("URL: ../laptop/hard-disk");
        logger.debug("Adding new hard disk...");
        return EntityModel.of(hardDiskService.saveHardDisk(hardDisk));

    }

}
