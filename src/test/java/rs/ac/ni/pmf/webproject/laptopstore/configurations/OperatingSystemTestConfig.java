package rs.ac.ni.pmf.webproject.laptopstore.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import rs.ac.ni.pmf.webproject.laptopstore.controllers.impl.OperatingSystemControllerImpl;
import rs.ac.ni.pmf.webproject.laptopstore.models.mappers.OperatingSystemMapper;
import rs.ac.ni.pmf.webproject.laptopstore.services.impl.OperatingSystemServiceImpl;

@Configuration
@Import({OperatingSystemControllerImpl.class, OperatingSystemServiceImpl.class, OperatingSystemMapper.class})
public class OperatingSystemTestConfig {
}
