package rs.ac.ni.pmf.webproject.laptopstore.controllers;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.ProcessorDTO;

@RequestMapping("/laptop/processor")
public interface ProcessorController {

    @GetMapping("")
    CollectionModel<EntityModel<ProcessorDTO>> getProcessors();

    @GetMapping("/{id}")
    EntityModel<ProcessorDTO> getProcessor(@PathVariable Integer id);

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    EntityModel<ProcessorDTO> addProcessor(@RequestBody ProcessorDTO processor);

}
