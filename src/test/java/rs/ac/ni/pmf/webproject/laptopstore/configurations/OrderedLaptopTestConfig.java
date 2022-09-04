package rs.ac.ni.pmf.webproject.laptopstore.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import rs.ac.ni.pmf.webproject.laptopstore.controllers.impl.OrderedLaptopControllerImpl;
import rs.ac.ni.pmf.webproject.laptopstore.models.mappers.OrderedLaptopMapper;
import rs.ac.ni.pmf.webproject.laptopstore.services.impl.OrderedLaptopServiceImpl;

@Configuration
@Import({OrderedLaptopControllerImpl.class, OrderedLaptopServiceImpl.class, OrderedLaptopMapper.class})
public class OrderedLaptopTestConfig {
}
