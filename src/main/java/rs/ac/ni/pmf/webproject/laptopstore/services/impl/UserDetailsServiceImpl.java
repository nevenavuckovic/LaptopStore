package rs.ac.ni.pmf.webproject.laptopstore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.UserEntity;
import rs.ac.ni.pmf.webproject.laptopstore.models.securities.MyUserDetails;
import rs.ac.ni.pmf.webproject.laptopstore.repositories.UserRepository;


public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        UserEntity user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with username " + username + " not found");
        }

        return new MyUserDetails(user);
    }

}
