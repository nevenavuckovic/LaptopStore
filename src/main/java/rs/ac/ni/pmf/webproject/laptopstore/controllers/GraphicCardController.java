package rs.ac.ni.pmf.webproject.laptopstore.controllers;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.GraphicCardDTO;

@RequestMapping("/laptop/graphic-card")
public interface GraphicCardController {

    @GetMapping("")
    CollectionModel<EntityModel<GraphicCardDTO>> getGraphicCards();

    @GetMapping("/{id}")
    EntityModel<GraphicCardDTO> getGraphicCard(@PathVariable Integer id);

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    EntityModel<GraphicCardDTO> addGraphicCard(@RequestBody GraphicCardDTO graphicCard);


}
