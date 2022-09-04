package rs.ac.ni.pmf.webproject.laptopstore.services;

import rs.ac.ni.pmf.webproject.laptopstore.models.dto.UserDTO;
import rs.ac.ni.pmf.webproject.laptopstore.searchoptions.UserSearchOption;

import java.util.List;

public interface UserService {

    List<UserDTO> findUsers();
    UserDTO findUserById(Integer id);
    UserDTO saveUser(Integer id, UserDTO user);
    void deleteUser(Integer id);
    List<UserDTO> userSearch(UserSearchOption options,
                             Integer pageNumber,
                             Integer pageSize);
    void forgottenPassword(String email);
    void changePassword(String token);
    void resetPassword(String token, String password);
}
