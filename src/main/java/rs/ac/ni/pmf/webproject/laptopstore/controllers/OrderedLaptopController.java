package rs.ac.ni.pmf.webproject.laptopstore.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.OrderedLaptopDTO;
import rs.ac.ni.pmf.webproject.laptopstore.searchoptions.OrderedLaptopSearchOption;


@RequestMapping("/ordered-laptop")
public interface OrderedLaptopController {

    @GetMapping("")
    @Operation(summary = "Retrieve all laptops")
    CollectionModel<EntityModel<OrderedLaptopDTO>> getOrderedLaptops();

    @GetMapping("/{id}")
    @Operation(summary = "Retrieves ordered laptop")
    EntityModel<OrderedLaptopDTO> getOrderedLaptop(@PathVariable Integer id);

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Ordering laptop")
    EntityModel<OrderedLaptopDTO> addOrderedLaptop(@RequestBody OrderedLaptopDTO orderedLaptop);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Sells ordered laptop")
    EntityModel<OrderedLaptopDTO> sellOrderedLaptop(@PathVariable Integer id);

    @PostMapping("/search")
    @Operation(summary = "Searching through ordered laptops")
    CollectionModel<EntityModel<OrderedLaptopDTO>> orderedLaptopSearch(@RequestBody OrderedLaptopSearchOption options,
                                                         @RequestParam("page-number") Integer pageNumber,
                                                         @RequestParam("page-size") Integer pageSize);

}
