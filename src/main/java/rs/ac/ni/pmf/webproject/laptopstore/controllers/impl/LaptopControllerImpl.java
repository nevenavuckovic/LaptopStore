package rs.ac.ni.pmf.webproject.laptopstore.controllers.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.ni.pmf.webproject.laptopstore.controllers.LaptopController;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.LaptopDTO;
import rs.ac.ni.pmf.webproject.laptopstore.searchoptions.LaptopSearchOption;
import rs.ac.ni.pmf.webproject.laptopstore.services.LaptopService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class LaptopControllerImpl implements LaptopController {

    @Autowired
    private LaptopService laptopService;

    private static final Logger logger = LoggerFactory.getLogger(ColorControllerImpl.class);

    @Override
    public CollectionModel<EntityModel<LaptopDTO>> getLaptops() {
        logger.info("URL: ../laptop");
        logger.debug("Getting all laptops...");
        List<EntityModel<LaptopDTO>> models = new ArrayList<>();
        List<LaptopDTO> laptops = laptopService.findLaptops();
        for (LaptopDTO laptopDTO:laptops) {
            models.add(EntityModel.of(laptopDTO));
        }

        return CollectionModel.of(models,
                linkTo(methodOn(LaptopControllerImpl.class).getLaptops()).withSelfRel());

    }

    @Override
    public EntityModel<LaptopDTO> getLaptop(Integer id) {
        logger.info("URL: ../laptop/{}", id);
        logger.debug("Getting laptop with id = {}...", id);
        return EntityModel.of(laptopService.findLaptopById(id));
    }

    @Override
    public EntityModel<LaptopDTO> addLaptop(LaptopDTO laptop) {
        logger.info("URL: ../laptop/color");
        logger.debug("Getting all colors...");
        return EntityModel.of(laptopService.saveLaptop(null, laptop));
    }

    @Override
    public EntityModel<LaptopDTO> updateLaptop(Integer id, LaptopDTO laptop) {
        logger.info("URL: ../laptop/{}", id);
        logger.debug("Updating laptop with id = {}...", id);
        return EntityModel.of(laptopService.saveLaptop(id, laptop));
    }

    @Override
    public CollectionModel<EntityModel<LaptopDTO>> laptopSearch(LaptopSearchOption options,
                                                                Integer pageNumber,
                                                                Integer pageSize){
        logger.info("URL: ../laptop/search");
        logger.debug("Searching laptops...");
        List<EntityModel<LaptopDTO>> models = new ArrayList<>();
        List<LaptopDTO> laptops = laptopService.laptopSearch(options, pageNumber, pageSize);
        for (LaptopDTO laptopDTO:laptops) {
            models.add(EntityModel.of(laptopDTO));
        }

        return CollectionModel.of(models,
                linkTo(methodOn(LaptopControllerImpl.class).getLaptops()).withSelfRel());

    }
}
