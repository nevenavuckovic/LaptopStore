package rs.ac.ni.pmf.webproject.laptopstore.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.NullException;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.ResourceNotFoundException;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.PageException;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.ResourceAlreadyExistsException;
import rs.ac.ni.pmf.webproject.laptopstore.models.Role;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.UserDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.UserEntity;
import rs.ac.ni.pmf.webproject.laptopstore.models.mappers.UserMapper;
import rs.ac.ni.pmf.webproject.laptopstore.repositories.UserRepository;
import rs.ac.ni.pmf.webproject.laptopstore.repositories.specifications.UsersSpecification;
import rs.ac.ni.pmf.webproject.laptopstore.searchoptions.UserSearchOption;
import rs.ac.ni.pmf.webproject.laptopstore.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public List<UserDTO> findUsers() {
        logger.debug("Listing all users...");
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (UserEntity userEntity:userEntities) {
            userDTOs.add(userMapper.toDto(userEntity));
        }
        return userDTOs;
    }

    @Override
    public UserDTO findUserById(Integer id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id = " + id + " not found"));
        return userMapper.toDto(userEntity);
    }

    @Override
    public UserDTO saveUser(Integer id, UserDTO user) {
        UserEntity userEntity = userMapper.toEntity(user);
        if(id != null){
            userEntity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id = " + id + " not found"));
        }
        if(id == null && (user.getUsername() == null || user.getEmail() == null || user.getPassword() == null || user.getRoleId() == null)){
            throw new NullException("Username, email, password and roleId can't be null");
        }

        if(user.getUsername() != null) {
            if(userRepository.findByUsername(user.getUsername()) != null) {
                throw new ResourceAlreadyExistsException("User with username = " + user.getUsername() + " already exists");
            }
            userEntity.setUsername(user.getUsername());
        }

        if(user.getEmail() != null) {
            if(userRepository.findByEmail(user.getEmail()) != null) {
                throw new ResourceAlreadyExistsException("User with email = " + user.getEmail() + " already exists");
            }
            userEntity.setEmail(user.getEmail());
        }

        if(user.getRoleId() != null) {
            if(Role.getCodeByValue(user.getRoleId()) == null) {
                throw new ResourceNotFoundException("Role with id = " + user.getRoleId() + " not found");
            }
            userEntity.setRoleId(user.getRoleId());
        }
        if(id == null || user.getPassword() != null) {
            userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if(user.getActive() != null){
            userEntity.setActive(user.getActive());
        }

        return userMapper.toDto(userRepository.save(userEntity));
    }



    @Override
    public void deleteUser(Integer id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id = " + id + " not found"));
        userRepository.delete(userEntity);
    }

    @Override
    public List<UserDTO> userSearch(UserSearchOption options,
                                    Integer pageNumber,
                                    Integer pageSize)
    {
        if(pageNumber <= 0){
            throw new PageException("Page number must be grater than 0");
        }
        if(pageSize <= 0){
            throw new PageException("Page size must be grater than 0");
        }
        Page<UserEntity> userEntities = userRepository.findAll(UsersSpecification.userSearch(options),
                PageRequest.of(pageNumber-1, pageSize));
        List<UserDTO> userDTOs = new ArrayList<>();
        for (UserEntity userEntity: userEntities) {
            userDTOs.add(userMapper.toDto(userEntity));
        }
        return userDTOs;
    }

    @Override
    public void forgottenPassword(String email) {
        UserEntity user = userRepository.findByEmail(email);
        if(user == null){
            throw new ResourceNotFoundException("User with email = " + email + " not found");
        }
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        userRepository.save(user);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Complete Password Reset!");
        mailMessage.setFrom("prodavnicalaptopova@gmail.com");
        mailMessage.setText("To complete the password reset process, please click here: http://localhost:8084/user/change-password/" + token);
        javaMailSender.send(mailMessage);
    }

    @Override
    public void changePassword(String token) {
        userRepository.findByToken(token).orElseThrow(() -> new ResourceNotFoundException("User with token = " + token + " not found"));
    }

    @Override
    public void resetPassword(String token, String password) {
        UserEntity userEntity = userRepository.findByToken(token).orElseThrow(() -> new ResourceNotFoundException("User with token = " + token + " not found"));
        userEntity.setToken(null);
        userEntity.setPassword(passwordEncoder.encode(password));
        userRepository.save(userEntity);
    }
}
