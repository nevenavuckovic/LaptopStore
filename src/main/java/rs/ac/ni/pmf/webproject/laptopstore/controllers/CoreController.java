package rs.ac.ni.pmf.webproject.laptopstore.controllers;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.CoreDTO;

@RequestMapping("/laptop/core")
public interface CoreController {

    @GetMapping("")
    CollectionModel<EntityModel<CoreDTO>> getCores();

    @GetMapping("/{id}")
    EntityModel<CoreDTO> getCore(@PathVariable Integer id);

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    EntityModel<CoreDTO> addCore(@RequestBody CoreDTO core);


}
