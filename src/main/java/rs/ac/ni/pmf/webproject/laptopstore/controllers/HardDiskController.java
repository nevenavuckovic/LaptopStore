package rs.ac.ni.pmf.webproject.laptopstore.controllers;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.HardDiskDTO;

@RequestMapping("/laptop/hard-disk")
public interface HardDiskController {

    @GetMapping("")
    CollectionModel<EntityModel<HardDiskDTO>> getHardDisks();

    @GetMapping("/{id}")
    EntityModel<HardDiskDTO> getHardDisk(@PathVariable Integer id);

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    EntityModel<HardDiskDTO> addHardDisk(@RequestBody HardDiskDTO hardDisk);

}
