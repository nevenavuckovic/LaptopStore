package rs.ac.ni.pmf.webproject.laptopstore.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import rs.ac.ni.pmf.webproject.laptopstore.controllers.impl.UserControllerImpl;
import rs.ac.ni.pmf.webproject.laptopstore.models.mappers.UserMapper;
import rs.ac.ni.pmf.webproject.laptopstore.services.impl.UserServiceImpl;

@Configuration
@Import({UserControllerImpl.class, UserServiceImpl.class, UserMapper.class, JavaMailSenderImpl.class,
        BCryptPasswordEncoder.class})
public class UserTestConfig {
}
