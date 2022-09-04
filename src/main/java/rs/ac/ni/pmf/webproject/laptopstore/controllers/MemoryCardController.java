package rs.ac.ni.pmf.webproject.laptopstore.controllers;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.MemoryCardDTO;

@RequestMapping("/laptop/memory-card")
public interface MemoryCardController {

    @GetMapping("")
    CollectionModel<EntityModel<MemoryCardDTO>> getMemoryCards();

    @GetMapping("/{id}")
    EntityModel<MemoryCardDTO> getMemoryCard(@PathVariable Integer id);

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    EntityModel<MemoryCardDTO> addMemoryCard(@RequestBody MemoryCardDTO memoryCard);


}
