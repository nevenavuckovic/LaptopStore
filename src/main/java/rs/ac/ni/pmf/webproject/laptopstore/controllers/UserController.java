package rs.ac.ni.pmf.webproject.laptopstore.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.UserDTO;
import rs.ac.ni.pmf.webproject.laptopstore.searchoptions.UserSearchOption;

@RequestMapping("/user")
public interface UserController {

    @GetMapping("")
    @Operation(summary = "Retrieve all user")
    CollectionModel<EntityModel<UserDTO>> getUsers();

    @GetMapping("/{id}")
    @Operation(summary = "Retrieves user")
    EntityModel<UserDTO> getUser(@PathVariable Integer id);

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creating new user")
    EntityModel<UserDTO> addUser(@RequestBody UserDTO user);

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registering buyer")
    EntityModel<UserDTO> addBuyer(@RequestBody UserDTO user);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Updating users details")
    EntityModel<UserDTO> updateUser(@PathVariable Integer id, @RequestBody UserDTO user);

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleting user")
    ResponseEntity<?> deleteUser(@PathVariable Integer id);

    @PostMapping("/search")
    @Operation(summary = "Search though users")
    CollectionModel<EntityModel<UserDTO>> userSearch(@RequestBody UserSearchOption options,
                                                     @RequestParam("page-number") Integer pageNumber,
                                                     @RequestParam("page-size") Integer pageSize);

    @PostMapping("/forgotten-password")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Changing forgotten password")
    void forgottenPassword(@RequestBody String email);

    @GetMapping("/change-password/{token}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Checking token")
    void changePassword(@PathVariable("token") String token);

    @PutMapping("/reset-password/{token}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Resets password")
    void resetPassword(@PathVariable String token, @RequestBody String password);
}