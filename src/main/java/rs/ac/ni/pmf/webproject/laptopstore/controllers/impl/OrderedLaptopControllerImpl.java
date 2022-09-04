package rs.ac.ni.pmf.webproject.laptopstore.controllers.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.ni.pmf.webproject.laptopstore.controllers.OrderedLaptopController;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.OrderedLaptopDTO;
import rs.ac.ni.pmf.webproject.laptopstore.searchoptions.OrderedLaptopSearchOption;
import rs.ac.ni.pmf.webproject.laptopstore.services.OrderedLaptopService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class OrderedLaptopControllerImpl implements OrderedLaptopController {

    @Autowired
    private OrderedLaptopService orderedLaptopService;

    private static final Logger logger = LoggerFactory.getLogger(ColorControllerImpl.class);

    @Override
    public CollectionModel<EntityModel<OrderedLaptopDTO>> getOrderedLaptops() {
        logger.info("URL: ../ordered-laptop");
        logger.debug("Getting all ordered laptops...");
        List<EntityModel<OrderedLaptopDTO>> models = new ArrayList<>();
        List<OrderedLaptopDTO> orderedLaptops = orderedLaptopService.findOrderedLaptops();
        for (OrderedLaptopDTO orderedLaptopDTO:orderedLaptops) {
            models.add(EntityModel.of(orderedLaptopDTO));
        }

        return CollectionModel.of(models,
                linkTo(methodOn(OrderedLaptopControllerImpl.class).getOrderedLaptops()).withSelfRel());

    }

    @Override
    public EntityModel<OrderedLaptopDTO> getOrderedLaptop(Integer id) {
        logger.info("URL: ../ordered-laptop/{}", id);
        logger.debug("Getting ordered laptop with id = {}...", id);
        return EntityModel.of(orderedLaptopService.findOrderedLaptopById(id));
    }

    @Override
    public EntityModel<OrderedLaptopDTO> addOrderedLaptop(OrderedLaptopDTO orderedLaptop) {
        logger.info("URL: ../ordered-laptop");
        logger.debug("Adding new ordered laptop...");
        return EntityModel.of(orderedLaptopService.saveOrderedLaptop(orderedLaptop));
    }

    @Override
    public EntityModel<OrderedLaptopDTO> sellOrderedLaptop(Integer id) {
        logger.info("URL: ../ordered-laptop/{}", id);
        logger.debug("Selling ordered laptop with id = {}...", id);
        return EntityModel.of(orderedLaptopService.sellOrderedLaptop(id));
    }

    @Override
    public CollectionModel<EntityModel<OrderedLaptopDTO>> orderedLaptopSearch(OrderedLaptopSearchOption options, Integer pageNumber, Integer pageSize) {
        logger.info("URL: ../ordered-laptop/search");
        logger.debug("Search ordered laptops...");
        List<EntityModel<OrderedLaptopDTO>> models = new ArrayList<>();
        List<OrderedLaptopDTO> orderedLaptops = orderedLaptopService.orderedLaptopSearch(options, pageNumber, pageSize);
        for (OrderedLaptopDTO orderedLaptopDTO:orderedLaptops) {
            models.add(EntityModel.of(orderedLaptopDTO));
        }

        return CollectionModel.of(models,
                linkTo(methodOn(LaptopControllerImpl.class).getLaptops()).withSelfRel());

    }
}
