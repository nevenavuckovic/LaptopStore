package rs.ac.ni.pmf.webproject.laptopstore.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import rs.ac.ni.pmf.webproject.laptopstore.configurations.OperatingSystemTestConfig;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.NullException;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.ResourceAlreadyExistsException;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.ResourceNotFoundException;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.OperatingSystemDTO;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@Import(OperatingSystemTestConfig.class)
public class OperatingSystemControllerTest {

    @Autowired
    private OperatingSystemController operatingSystemController;

    @BeforeEach
    public void initializeData() {
        OperatingSystemDTO operatingSystemDTO = new OperatingSystemDTO();
        operatingSystemDTO.setName("Nema");
        operatingSystemController.addOperatingSystem(operatingSystemDTO);
        operatingSystemDTO.setName("Windows 10");
        operatingSystemController.addOperatingSystem(operatingSystemDTO);
    }

    @Test
    public void shouldGetAllOperatingSystems() {
        assertThat(operatingSystemController.getOperatingSystems()).hasSize(2);
    }

    @Test
    public void shouldCheckIdOfOperatingSystem() {
        assertThatThrownBy(() -> operatingSystemController.getOperatingSystem(3))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Operating system with id = 3 not found");
    }

    @Test
    public void shouldGetOperatingSystemById() {
        assertThat(operatingSystemController.getOperatingSystem(1).getContent().getName()).isEqualTo("Nema");
    }

    @Test void shouldCheckNameOfOperatingSystem(){
        OperatingSystemDTO operatingSystemDTO = new OperatingSystemDTO();

        assertThatThrownBy(() -> operatingSystemController.addOperatingSystem(operatingSystemDTO))
                .isInstanceOf(NullException.class)
                .hasMessage("Operating system name can't be null");

        operatingSystemDTO.setName("Nema");

        assertThatThrownBy(() -> operatingSystemController.addOperatingSystem(operatingSystemDTO))
                .isInstanceOf(ResourceAlreadyExistsException.class)
                .hasMessage("Operating system with name = Nema already exists");
    }

    @Test
    void shouldAddOperatingSystem(){
        OperatingSystemDTO operatingSystemDTO = new OperatingSystemDTO();
        operatingSystemDTO.setName("Linux");

        assertThat(operatingSystemController.addOperatingSystem(operatingSystemDTO).getContent())
                .isInstanceOf(OperatingSystemDTO.class);
    }

}
