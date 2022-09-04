package rs.ac.ni.pmf.webproject.laptopstore.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.LaptopDTO;
import rs.ac.ni.pmf.webproject.laptopstore.searchoptions.LaptopSearchOption;

@RequestMapping("/laptop")
public interface LaptopController {

    @GetMapping("")
    @Operation(summary = "Retrieve all laptops")
    CollectionModel<EntityModel<LaptopDTO>> getLaptops();

    @GetMapping("/{id}")
    @Operation(summary = "Retrieves laptop")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the laptop"),
            @ApiResponse(responseCode = "404", description = "Laptop not found")
    })
    EntityModel<LaptopDTO> getLaptop(@PathVariable Integer id);

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates new laptop")
    EntityModel<LaptopDTO> addLaptop(@RequestBody LaptopDTO laptop);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Updates laptops details")
    EntityModel<LaptopDTO> updateLaptop(@PathVariable Integer id, @RequestBody LaptopDTO laptop);

    @PostMapping("/search")
    @Operation(summary = "Searching through laptops")
    CollectionModel<EntityModel<LaptopDTO>> laptopSearch(@RequestBody LaptopSearchOption options,
                                                         @RequestParam("page-number") Integer pageNumber,
                                                         @RequestParam("page-size") Integer pageSize);

}
