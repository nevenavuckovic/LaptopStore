package rs.ac.ni.pmf.webproject.laptopstore.controllers.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.ni.pmf.webproject.laptopstore.controllers.ProcessorController;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.ProcessorDTO;
import rs.ac.ni.pmf.webproject.laptopstore.services.ProcessorService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ProcessorControllerImpl implements ProcessorController {

    @Autowired
    private ProcessorService processorService;

    private static final Logger logger = LoggerFactory.getLogger(ColorControllerImpl.class);

    @Override
    public CollectionModel<EntityModel<ProcessorDTO>> getProcessors() {
        logger.info("URL: ../laptop/processor");
        logger.debug("Getting all processors...");
        List<EntityModel<ProcessorDTO>> models = new ArrayList<>();
        List<ProcessorDTO> processors = processorService.findProcessors();
        for (ProcessorDTO processorDTO:processors) {
            models.add(EntityModel.of(processorDTO));
        }

        return CollectionModel.of(models,
                linkTo(methodOn(ProcessorControllerImpl.class).getProcessors()).withSelfRel());

    }

    @Override
    public EntityModel<ProcessorDTO> getProcessor(Integer id) {
        logger.info("URL: ../laptop/processor/{}", id);
        logger.debug("Getting processor with id = {}...", id);
        return EntityModel.of(processorService.findProcessorById(id));
    }

    @Override
    public EntityModel<ProcessorDTO> addProcessor(ProcessorDTO processor) {
        logger.info("URL: ../laptop/processor");
        logger.debug("Adding new processor...");
       return EntityModel.of(processorService.saveProcessor(processor));
    }


}
