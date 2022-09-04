package rs.ac.ni.pmf.webproject.laptopstore.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import rs.ac.ni.pmf.webproject.laptopstore.controllers.impl.LaptopControllerImpl;
import rs.ac.ni.pmf.webproject.laptopstore.models.mappers.LaptopMapper;
import rs.ac.ni.pmf.webproject.laptopstore.services.impl.LaptopServiceImpl;

@Configuration
@Import({LaptopControllerImpl.class, LaptopServiceImpl.class, LaptopMapper.class})
public class LaptopTestConfig {
}
