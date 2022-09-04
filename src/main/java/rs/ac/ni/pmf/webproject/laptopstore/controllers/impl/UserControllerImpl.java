package rs.ac.ni.pmf.webproject.laptopstore.controllers.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.ni.pmf.webproject.laptopstore.controllers.UserController;
import rs.ac.ni.pmf.webproject.laptopstore.models.Role;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.UserDTO;
import rs.ac.ni.pmf.webproject.laptopstore.searchoptions.UserSearchOption;
import rs.ac.ni.pmf.webproject.laptopstore.services.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(ColorControllerImpl.class);

    @Override
    public CollectionModel<EntityModel<UserDTO>> getUsers() {
        logger.info("URL: ../user");
        logger.debug("Getting all users...");
        List<EntityModel<UserDTO>> models = new ArrayList<>();
        List<UserDTO> users = userService.findUsers();
        for (UserDTO user:users) {
            models.add(EntityModel.of(user));
        }

        return CollectionModel.of(models,
                linkTo(methodOn(UserControllerImpl.class).getUsers()).withSelfRel());
    }

    @Override
    public EntityModel<UserDTO> getUser(Integer id) {
        logger.info("URL: ../user/{}", id);
        logger.debug("Getting user with id = {}...", id);
        return EntityModel.of(userService.findUserById(id));
    }

    @Override
    public EntityModel<UserDTO> addUser(UserDTO user) {
        logger.info("URL: ../user");
        logger.debug("Adding new user...");
        return EntityModel.of(userService.saveUser(null, user));

    }

    @Override
    public EntityModel<UserDTO> addBuyer(UserDTO user) {
        logger.info("URL: ../user/register");
        logger.debug("Adding new user...");
        user.setRoleId(Role.getValueByCode("BUYER"));
        user.setActive(true);
        return EntityModel.of(userService.saveUser(null, user));

    }

    @Override
    public EntityModel<UserDTO> updateUser(Integer id, UserDTO user) {
        logger.info("URL: ../user/{}", id);
        logger.debug("Updating user with id = {}...", id);
        return EntityModel.of(userService.saveUser(id, user));
    }

    @Override
    public ResponseEntity<?> deleteUser(Integer id) {
        logger.info("URL: ../user/{}", id);
        logger.debug("Deleting user with id = {}...", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public CollectionModel<EntityModel<UserDTO>> userSearch(UserSearchOption options,
                                                            Integer pageNumber,
                                                            Integer pageSize){
        logger.info("URL: ../user/search");
        logger.debug("Searching users...");
        List<EntityModel<UserDTO>> models = new ArrayList<>();
        List<UserDTO> users = userService.userSearch(options, pageNumber, pageSize);
        for (UserDTO userDTO:users) {
            models.add(EntityModel.of(userDTO));
        }

        return CollectionModel.of(models,
                linkTo(methodOn(UserControllerImpl.class).getUsers()).withSelfRel());

    }

    @Override
    public void forgottenPassword(String email) {
        userService.forgottenPassword(email);
    }

    @Override
    public void changePassword(String token) {
        userService.changePassword(token);
    }

    @Override
    public void resetPassword(String token, String password) {
        userService.resetPassword(token, password);
    }


}
