package rs.ac.ni.pmf.webproject.laptopstore.controllers;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.ColorDTO;

@RequestMapping("/laptop/color")
public interface ColorController {

    @GetMapping("")
    CollectionModel<EntityModel<ColorDTO>> getColors();

    @GetMapping("/{id}")
    EntityModel<ColorDTO> getColor(@PathVariable Integer id);

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    EntityModel<ColorDTO> addColor(@RequestBody ColorDTO laptop);

}
