package rs.ac.ni.pmf.webproject.laptopstore.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import rs.ac.ni.pmf.webproject.laptopstore.configurations.UserTestConfig;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.NullException;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.PageException;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.ResourceAlreadyExistsException;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.ResourceNotFoundException;
import rs.ac.ni.pmf.webproject.laptopstore.models.Role;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.LaptopDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.UserDTO;
import rs.ac.ni.pmf.webproject.laptopstore.searchoptions.LaptopSearchOption;
import rs.ac.ni.pmf.webproject.laptopstore.searchoptions.UserSearchOption;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@DataJpaTest
@Import(UserTestConfig.class)
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @BeforeEach
    public void initializeData()
    {
        UserDTO user1 = new UserDTO();
        user1.setUsername("test1");
        user1.setEmail("test1@gmail.com");
        user1.setPassword("test1");
        user1.setRoleId(1);
        user1.setActive(true);
        userController.addUser(user1);

        UserDTO user2 = new UserDTO();
        user2.setUsername("test2");
        user2.setEmail("test2@gmail.com");
        user2.setPassword("test2");
        user2.setRoleId(2);
        user2.setActive(true);
        userController.addUser(user2);

        UserDTO user3 = new UserDTO();
        user3.setUsername("test3");
        user3.setEmail("test3@gmail.com");
        user3.setPassword("test3");
        user3.setRoleId(3);
        user3.setActive(true);
        userController.addUser(user3);
    }
    @Test
    public void shouldGetAllUsers() {
        assertThat(userController.getUsers()).hasSize(3);
    }

    @Test
    public void shouldCheckIdOfUser() {
        assertThatThrownBy(() -> userController.getUser(5))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("User with id = 5 not found");
    }

    @Test
    public void shouldGetUserById() {
        assertThat(userController.getUser(1).getContent().getUsername()).isEqualTo("test1");
    }

    @Test
    public void shouldCheckUsernameOfUser()
    {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("test4@gmail.com");
        userDTO.setPassword("test4");
        userDTO.setRoleId(Role.getValueByCode("ADMIN"));
        userDTO.setActive(true);

        assertThatThrownBy(() -> userController.addUser(userDTO))
                .isInstanceOf(NullException.class)
                .hasMessage("Username, email, password and roleId can't be null");

        userDTO.setUsername("test1");

        assertThatThrownBy(() -> userController.addUser(userDTO))
                .isInstanceOf(ResourceAlreadyExistsException.class)
                .hasMessage("User with username = test1 already exists");
    }

    @Test
    public void shouldCheckEmailOfUser()
    {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("test4");
        userDTO.setPassword("test4");
        userDTO.setRoleId(Role.getValueByCode("ADMIN"));
        userDTO.setActive(true);

        assertThatThrownBy(() -> userController.addUser(userDTO))
                .isInstanceOf(NullException.class)
                .hasMessage("Username, email, password and roleId can't be null");

        userDTO.setEmail("test1@gmail.com");

        assertThatThrownBy(() -> userController.addUser(userDTO))
                .isInstanceOf(ResourceAlreadyExistsException.class)
                .hasMessage("User with email = test1@gmail.com already exists");
    }

    @Test
    public void shouldCheckRoleOfUser()
    {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("test4");
        userDTO.setEmail("test4@gmail.com");
        userDTO.setPassword("test4");
        userDTO.setActive(true);

        assertThatThrownBy(() -> userController.addUser(userDTO))
                .isInstanceOf(NullException.class)
                .hasMessage("Username, email, password and roleId can't be null");

        userDTO.setRoleId(4);

        assertThatThrownBy(() -> userController.addUser(userDTO))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Role with id = 4 not found");
    }

    @Test
    public void shouldAddUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("test4");
        userDTO.setEmail("test4@gmail.com");
        userDTO.setPassword("test4");
        userDTO.setRoleId(1);
        userDTO.setActive(true);

        assertThat(userController.addUser(userDTO).getContent()).isInstanceOf(UserDTO.class);
    }


    @Test
    public void shouldCheckIdOfUpdatingUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("test4");
        userDTO.setEmail("test4@gmail.com");
        userDTO.setPassword("test4");
        userDTO.setRoleId(2);

        assertThatThrownBy(() -> userController.updateUser(4, userDTO))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("User with id = 4 not found");
    }

    @Test
    public void shouldUpdateLaptop() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("test4");
        userDTO.setEmail("test4@gmail.com");
        userDTO.setPassword("test4");
        userDTO.setRoleId(2);

        assertThat(userController.updateUser(1, userDTO).getContent()).isInstanceOf(UserDTO.class);
    }

    @Test
    public void shouldSearchUsers() {
        UserSearchOption userSearchOption = new UserSearchOption();
        userSearchOption.setUsername("test");
        userSearchOption.setEmail("test");
        userSearchOption.setRole("ADMIN");

        assertThat(userController.userSearch(userSearchOption, 1, 2)).hasSize(1);
    }


}
