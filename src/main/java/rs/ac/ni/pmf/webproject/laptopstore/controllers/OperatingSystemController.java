package rs.ac.ni.pmf.webproject.laptopstore.controllers;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.OperatingSystemDTO;

@RequestMapping("/laptop/operating-system")
public interface OperatingSystemController {

    @GetMapping("")
    CollectionModel<EntityModel<OperatingSystemDTO>> getOperatingSystems();

    @GetMapping("/{id}")
    EntityModel<OperatingSystemDTO> getOperatingSystem(@PathVariable Integer id);

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    EntityModel<OperatingSystemDTO> addOperatingSystem(@RequestBody OperatingSystemDTO operatingSystem);

}
