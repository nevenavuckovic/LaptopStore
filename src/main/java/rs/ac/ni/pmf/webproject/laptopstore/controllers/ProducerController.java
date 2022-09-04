package rs.ac.ni.pmf.webproject.laptopstore.controllers;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.ProducerDTO;

@RequestMapping("/laptop/producer")
public interface ProducerController {

    @GetMapping("")
    CollectionModel<EntityModel<ProducerDTO>> getProducers();

    @GetMapping("/{id}")
    EntityModel<ProducerDTO> getProducer(@PathVariable Integer id);

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    EntityModel<ProducerDTO> addProducer(@RequestBody ProducerDTO producer);

}
