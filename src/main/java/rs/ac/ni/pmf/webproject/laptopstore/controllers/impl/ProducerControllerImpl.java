package rs.ac.ni.pmf.webproject.laptopstore.controllers.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.ni.pmf.webproject.laptopstore.controllers.ProducerController;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.ProducerDTO;
import rs.ac.ni.pmf.webproject.laptopstore.services.ProducerService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ProducerControllerImpl implements ProducerController {

    @Autowired
    private ProducerService producerService;

    private static final Logger logger = LoggerFactory.getLogger(ColorControllerImpl.class);

    @Override
    public CollectionModel<EntityModel<ProducerDTO>> getProducers() {
        logger.info("URL: ../laptop/producer");
        logger.debug("Getting all producers...");
        List<EntityModel<ProducerDTO>> models = new ArrayList<>();
        List<ProducerDTO> producers = producerService.findProducers();
        for (ProducerDTO producerDTO:producers) {
            models.add(EntityModel.of(producerDTO));
        }

        return CollectionModel.of(models,
                linkTo(methodOn(ProducerControllerImpl.class).getProducers()).withSelfRel());

    }

    @Override
    public EntityModel<ProducerDTO> getProducer(Integer id) {
        logger.info("URL: ../laptop/producer/{}", id);
        logger.debug("Getting producer with id = {}...", id);
        return EntityModel.of(producerService.findProducerById(id));
    }

    @Override
    public EntityModel<ProducerDTO> addProducer(ProducerDTO producer) {
        logger.info("URL: ../laptop/producer");
        logger.debug("Adding new producer...");
        return EntityModel.of(producerService.saveProducer(producer));

    }
}
