package rs.ac.ni.pmf.webproject.laptopstore.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import rs.ac.ni.pmf.webproject.laptopstore.configurations.LaptopTestConfig;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.*;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.LaptopDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.*;
import rs.ac.ni.pmf.webproject.laptopstore.repositories.*;
import rs.ac.ni.pmf.webproject.laptopstore.searchoptions.LaptopSearchOption;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@Import(LaptopTestConfig.class)
public class LaptopControllerTest {

    @Autowired
    private LaptopController laptopController;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private CoreRepository coreRepository;

    @Autowired
    private MemoryCardRepository memoryCardRepository;

    @Autowired
    private GraphicCardRepository graphicCardRepository;

    @Autowired
    private HardDiskRepository hardDiskRepository;

    @Autowired
    private OperatingSystemRepository operatingSystemRepository;

    @Autowired
    private ProcessorRepository processorRepository;

    @Autowired
    private ProducerRepository producerRepository;

    @BeforeEach
    public void initializeData(){

        ColorEntity colorEntity = new ColorEntity();
        colorEntity.setName("Bela");
        ColorEntity colorEntity1 =  colorRepository.save(colorEntity);

        CoreEntity coreEntity = new CoreEntity();
        coreEntity.setName(4);
        coreRepository.save(coreEntity);

        MemoryCardEntity memoryCardEntity = new MemoryCardEntity();
        memoryCardEntity.setName("4 GB");
        memoryCardRepository.save(memoryCardEntity);

        GraphicCardEntity graphicCardEntity = new GraphicCardEntity();
        graphicCardEntity.setName("Integrisana");
        graphicCardRepository.save(graphicCardEntity);

        HardDiskEntity hardDiskEntity = new HardDiskEntity();
        hardDiskEntity.setName("128 GB SSD");
        hardDiskRepository.save(hardDiskEntity);

        OperatingSystemEntity operatingSystemEntity = new OperatingSystemEntity();
        operatingSystemEntity.setName("Nema");
        operatingSystemRepository.save(operatingSystemEntity);

        ProcessorEntity processorEntity = new ProcessorEntity();
        processorEntity.setName("AMD Ryzen 3");
        processorRepository.save(processorEntity);

        ProducerEntity producerEntity = new ProducerEntity();
        producerEntity.setName("Acer");
        producerRepository.save(producerEntity);

        LaptopDTO laptopDTO = new LaptopDTO();
        laptopDTO.setName("Test1");
        laptopDTO.setPrice(60000);
        laptopDTO.setQuantity(10);
        laptopDTO.setOperatingSystemId(1);
        laptopDTO.setCoreId(1);
        laptopDTO.setGraphicCardId(1);
        laptopDTO.setMemoryCardId(1);
        laptopDTO.setColorId(1);
        laptopDTO.setProducerId(1);
        laptopDTO.setProcessorId(1);
        laptopDTO.setHardDiskId(1);
        laptopController.addLaptop(laptopDTO);

        laptopDTO.setName("Test2");
        laptopDTO.setPrice(90000);
        laptopDTO.setQuantity(5);
        laptopController.addLaptop(laptopDTO);

        laptopDTO.setName("Test3");
        laptopDTO.setPrice(80000);
        laptopDTO.setQuantity(15);
        laptopController.addLaptop(laptopDTO);


    }

    @Test
    public void shouldGetAllLaptops() {
        assertThat(laptopController.getLaptops()).hasSize(3);
    }

    @Test
    public void shouldCheckIdOfLaptop() {
        assertThatThrownBy(() -> laptopController.getLaptop(5))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Laptop with id = 5 not found");
    }

    @Test
    public void shouldGetLaptopById() {
        assertThat(laptopController.getLaptop(1).getContent().getName()).isEqualTo("Test1");
    }

    @Test
    public void shouldCheckNameOfAddingLaptop() {
        LaptopDTO laptopDTO = new LaptopDTO();
        laptopDTO.setPrice(50000);
        laptopDTO.setQuantity(11);
        laptopDTO.setOperatingSystemId(1);
        laptopDTO.setCoreId(1);
        laptopDTO.setGraphicCardId(1);
        laptopDTO.setMemoryCardId(1);
        laptopDTO.setColorId(1);
        laptopDTO.setProducerId(1);
        laptopDTO.setProcessorId(1);
        laptopDTO.setHardDiskId(1);

        assertThatThrownBy(() -> laptopController.addLaptop(laptopDTO))
                .isInstanceOf(NullException.class)
                .hasMessage("Name can't be null");

        laptopDTO.setName("Test1");

        assertThatThrownBy(() -> laptopController.addLaptop(laptopDTO))
                .isInstanceOf(ResourceAlreadyExistsException.class)
                .hasMessage("Laptop with name = Test1 already exists");
    }

    @Test
    public void shouldCheckPriceOfAddingLaptop() {
        LaptopDTO laptopDTO = new LaptopDTO();
        laptopDTO.setName("Test");
        laptopDTO.setQuantity(11);
        laptopDTO.setOperatingSystemId(1);
        laptopDTO.setCoreId(1);
        laptopDTO.setGraphicCardId(1);
        laptopDTO.setMemoryCardId(1);
        laptopDTO.setColorId(1);
        laptopDTO.setProducerId(1);
        laptopDTO.setProcessorId(1);
        laptopDTO.setHardDiskId(1);

        assertThatThrownBy(() -> laptopController.addLaptop(laptopDTO))
                .isInstanceOf(NullException.class)
                .hasMessage("Price can't be null");

        laptopDTO.setPrice(-60000);

        assertThatThrownBy(() -> laptopController.addLaptop(laptopDTO))
                .isInstanceOf(NegativeNumberException.class)
                .hasMessage("Price can't be negative number or 0");
    }

    @Test
    public void shouldCheckQuantityOfAddingLaptop() {
        LaptopDTO laptopDTO = new LaptopDTO();
        laptopDTO.setName("Test");
        laptopDTO.setPrice(60000);
        laptopDTO.setQuantity(-10);
        laptopDTO.setOperatingSystemId(1);
        laptopDTO.setCoreId(1);
        laptopDTO.setGraphicCardId(1);
        laptopDTO.setMemoryCardId(1);
        laptopDTO.setColorId(1);
        laptopDTO.setProducerId(1);
        laptopDTO.setProcessorId(1);
        laptopDTO.setHardDiskId(1);


        assertThatThrownBy(() -> laptopController.addLaptop(laptopDTO))
                .isInstanceOf(NegativeNumberException.class)
                .hasMessage("Quantity can't be negative number");
    }

    @Test
    public void shouldCheckColorOfAddingLaptop() {
        LaptopDTO laptopDTO = new LaptopDTO();
        laptopDTO.setName("Test");
        laptopDTO.setPrice(60000);
        laptopDTO.setQuantity(11);
        laptopDTO.setOperatingSystemId(1);
        laptopDTO.setCoreId(1);
        laptopDTO.setGraphicCardId(1);
        laptopDTO.setMemoryCardId(1);
        laptopDTO.setProducerId(1);
        laptopDTO.setProcessorId(1);
        laptopDTO.setHardDiskId(1);

        assertThatThrownBy(() -> laptopController.addLaptop(laptopDTO))
                .isInstanceOf(NullException.class)
                .hasMessage("Color id can't be null");

        laptopDTO.setColorId(2);

        assertThatThrownBy(() -> laptopController.addLaptop(laptopDTO))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Color with id = 2 not found");
    }

    @Test
    public void shouldAddLaptop() {

        LaptopDTO laptopDTO = new LaptopDTO();
        laptopDTO.setName("Test");
        laptopDTO.setPrice(60000);
        laptopDTO.setQuantity(11);
        laptopDTO.setColorId(1);
        laptopDTO.setOperatingSystemId(1);
        laptopDTO.setCoreId(1);
        laptopDTO.setGraphicCardId(1);
        laptopDTO.setMemoryCardId(1);
        laptopDTO.setProducerId(1);
        laptopDTO.setProcessorId(1);
        laptopDTO.setHardDiskId(1);


        assertThat(laptopController.addLaptop(laptopDTO).getContent()).isInstanceOf(LaptopDTO.class);
    }


    @Test
    public void shouldCheckIdOfUpdatingLaptop() {
        LaptopDTO laptopDTO = new LaptopDTO();
        laptopDTO.setName("Test");
        laptopDTO.setPrice(60000);
        laptopDTO.setQuantity(11);
        laptopDTO.setOperatingSystemId(1);
        laptopDTO.setProducerId(1);
        laptopDTO.setProcessorId(1);
        laptopDTO.setHardDiskId(1);

        assertThatThrownBy(() -> laptopController.updateLaptop(4, laptopDTO))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Laptop with id = 4 not found");
    }

    @Test
    public void shouldUpdateLaptop() {
        LaptopDTO laptopDTO = new LaptopDTO();
        laptopDTO.setName("Test");
        laptopDTO.setPrice(70000);
        laptopDTO.setQuantity(11);

        assertThat(laptopController.updateLaptop(1, laptopDTO).getContent()).isInstanceOf(LaptopDTO.class);
    }

    @Test
    public void shouldCheckPageNumberOfSearchedLaptops() {
        LaptopSearchOption laptopSearchOption = new LaptopSearchOption();
        laptopSearchOption.setName("Test");
        laptopSearchOption.setMinPrice(70000);
        laptopSearchOption.setMaxPrice(100000);
        laptopSearchOption.setColor("Bela");
        laptopSearchOption.setCore(4);
        laptopSearchOption.setMemoryCard("4 GB");
        laptopSearchOption.setGraphicCard("Integrisana");
        laptopSearchOption.setHardDisk("128 GB SSD");
        laptopSearchOption.setOperatingSystem("Nema");
        laptopSearchOption.setProcessor("AMD Ryzen 3");
        laptopSearchOption.setProducer("Acer");

        assertThatThrownBy(() -> laptopController.laptopSearch(laptopSearchOption, 0, 2))
                .isInstanceOf(PageException.class)
                .hasMessage("Page number must be grater than 0");
    }

    @Test
    public void shouldCheckPageSizeSearchLaptops() {
        LaptopSearchOption laptopSearchOption = new LaptopSearchOption();
        laptopSearchOption.setName("Test");
        laptopSearchOption.setMinPrice(70000);
        laptopSearchOption.setMaxPrice(100000);
        laptopSearchOption.setColor("Bela");
        laptopSearchOption.setCore(4);
        laptopSearchOption.setMemoryCard("4 GB");
        laptopSearchOption.setGraphicCard("Integrisana");
        laptopSearchOption.setHardDisk("128 GB SSD");
        laptopSearchOption.setOperatingSystem("Nema");
        laptopSearchOption.setProcessor("AMD Ryzen 3");
        laptopSearchOption.setProducer("Acer");

        assertThatThrownBy(() -> laptopController.laptopSearch(laptopSearchOption, 1, 0))
                .isInstanceOf(PageException.class)
                .hasMessage("Page size must be grater than 0");
    }

    @Test
    public void shouldSearchLaptops() {
        LaptopSearchOption laptopSearchOption = new LaptopSearchOption();
        laptopSearchOption.setName("Test");
        laptopSearchOption.setMinPrice(50000);
        laptopSearchOption.setMaxPrice(100000);
        laptopSearchOption.setColor("Bela");
        laptopSearchOption.setCore(4);
        laptopSearchOption.setMemoryCard("4 GB");
        laptopSearchOption.setGraphicCard("Integrisana");
        laptopSearchOption.setHardDisk("128 GB SSD");
        laptopSearchOption.setOperatingSystem("Nema");
        laptopSearchOption.setProcessor("AMD Ryzen 3");
        laptopSearchOption.setProducer("Acer");

        assertThat(laptopController.laptopSearch(laptopSearchOption, 2, 2)).hasSize(1);
    }

    @Test
    public void shouldSearchLaptopsSortDesc() {
        LaptopSearchOption laptopSearchOption = new LaptopSearchOption();
        laptopSearchOption.setName("Test");
        laptopSearchOption.setMaxPrice(80000);
        laptopSearchOption.setAscending(false);

        CollectionModel<EntityModel<LaptopDTO>> laptopSearch = laptopController.laptopSearch(laptopSearchOption, 1, 4);
        assertThat(laptopSearch).hasSize(2);
        LaptopDTO laptopDTO = laptopSearch.iterator().next().getContent();
        assertThat(laptopDTO.getName()).isEqualTo("Test3");
    }


}
