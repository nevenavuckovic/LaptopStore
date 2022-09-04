package rs.ac.ni.pmf.webproject.laptopstore.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import rs.ac.ni.pmf.webproject.laptopstore.configurations.OrderedLaptopTestConfig;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.AlreadySoldException;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.NegativeNumberException;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.NullException;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.ResourceNotFoundException;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.LaptopDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.OrderedLaptopDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.*;
import rs.ac.ni.pmf.webproject.laptopstore.repositories.*;
import rs.ac.ni.pmf.webproject.laptopstore.searchoptions.LaptopSearchOption;
import rs.ac.ni.pmf.webproject.laptopstore.searchoptions.OrderedLaptopSearchOption;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@Import(OrderedLaptopTestConfig.class)
public class OrderedLaptopControllerTest {

    @Autowired
    private OrderedLaptopController orderedLaptopController;

    @Autowired
    private LaptopRepository laptopRepository;

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

    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    public void initializeData() {
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

        LaptopEntity laptopEntity1 = new LaptopEntity();
        laptopEntity1.setName("Test1");
        laptopEntity1.setPrice(60000);
        laptopEntity1.setQuantity(10);
        laptopEntity1.setOperatingSystem(operatingSystemEntity);
        laptopEntity1.setCore(coreEntity);
        laptopEntity1.setGraphicCard(graphicCardEntity);
        laptopEntity1.setMemoryCard(memoryCardEntity);
        laptopEntity1.setColor(colorEntity);
        laptopEntity1.setProducer(producerEntity);
        laptopEntity1.setProcessor(processorEntity);
        laptopEntity1.setHardDisk(hardDiskEntity);
        laptopRepository.save(laptopEntity1);

        LaptopEntity laptopEntity2 = new LaptopEntity();
        laptopEntity2.setName("Test2");
        laptopEntity2.setPrice(90000);
        laptopEntity2.setQuantity(15);
        laptopEntity2.setOperatingSystem(operatingSystemEntity);
        laptopEntity2.setCore(coreEntity);
        laptopEntity2.setGraphicCard(graphicCardEntity);
        laptopEntity2.setMemoryCard(memoryCardEntity);
        laptopEntity2.setColor(colorEntity);
        laptopEntity2.setProducer(producerEntity);
        laptopEntity2.setProcessor(processorEntity);
        laptopEntity2.setHardDisk(hardDiskEntity);
        laptopRepository.save(laptopEntity2);

        LaptopEntity laptopEntity3 = new LaptopEntity();
        laptopEntity3.setName("Test3");
        laptopEntity3.setPrice(80000);
        laptopEntity3.setQuantity(0);
        laptopEntity3.setOperatingSystem(operatingSystemEntity);
        laptopEntity3.setCore(coreEntity);
        laptopEntity3.setGraphicCard(graphicCardEntity);
        laptopEntity3.setMemoryCard(memoryCardEntity);
        laptopEntity3.setColor(colorEntity);
        laptopEntity3.setProducer(producerEntity);
        laptopEntity3.setProcessor(processorEntity);
        laptopEntity3.setHardDisk(hardDiskEntity);
        laptopRepository.save(laptopEntity3);

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("user1");
        userEntity.setEmail("user1@gmail.com");
        userEntity.setPassword("user1");
        userEntity.setRoleId(3);
        userEntity.setActive(true);
        userRepository.save(userEntity);

        OrderedLaptopDTO orderedLaptopDTO = new OrderedLaptopDTO();
        orderedLaptopDTO.setLaptop("Test1");
        orderedLaptopDTO.setBuyersEmail("user1@gmail.com");
        orderedLaptopController.addOrderedLaptop(orderedLaptopDTO);

        orderedLaptopDTO.setLaptop("Test2");
        orderedLaptopDTO.setBuyersEmail("user1@gmail.com");
        orderedLaptopController.addOrderedLaptop(orderedLaptopDTO);

    }

    @Test
    public void shouldGetAllOrderedLaptops() {
        assertThat(orderedLaptopController.getOrderedLaptops()).hasSize(2);
    }

    @Test
    public void shouldCheckIdOfOrderedLaptop(){
        assertThatThrownBy(() -> orderedLaptopController.getOrderedLaptop(3))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Ordered laptop with id = 3 not found");
    }

    @Test
    public void shouldGetOrderedLaptopById() {
        assertThat(orderedLaptopController.getOrderedLaptop(1).getContent().getLaptop()).isEqualTo("Test1");
    }

    @Test
    public void shouldCheckLaptopOfOrderedLaptop(){
        OrderedLaptopDTO orderedLaptopDTO = new OrderedLaptopDTO();
        orderedLaptopDTO.setBuyersEmail("user1@gmail.com");

        assertThatThrownBy(() -> orderedLaptopController.addOrderedLaptop(orderedLaptopDTO))
                .isInstanceOf(NullException.class)
                .hasMessage("Laptop name can't be null");

        orderedLaptopDTO.setLaptop("Test4");

        assertThatThrownBy(() -> orderedLaptopController.addOrderedLaptop(orderedLaptopDTO))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Laptop Test4 not found");
    }

    @Test
    public void shouldCheckBuyersEmailOfOrderedLaptop(){
        OrderedLaptopDTO orderedLaptopDTO = new OrderedLaptopDTO();
        orderedLaptopDTO.setLaptop("Test1");

        assertThatThrownBy(() -> orderedLaptopController.addOrderedLaptop(orderedLaptopDTO))
                .isInstanceOf(NullException.class)
                .hasMessage("Email can't be null");

        orderedLaptopDTO.setBuyersEmail("user3@gmail.com");

        assertThatThrownBy(() -> orderedLaptopController.addOrderedLaptop(orderedLaptopDTO))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("User with email = user3@gmail.com not found");
    }

    @Test
    public void shouldCheckQuantityOfOrderedLaptop(){
        OrderedLaptopDTO orderedLaptopDTO = new OrderedLaptopDTO();
        orderedLaptopDTO.setLaptop("Test3");
        orderedLaptopDTO.setBuyersEmail("user1@gmail.com");

        assertThatThrownBy(() -> orderedLaptopController.addOrderedLaptop(orderedLaptopDTO))
                .isInstanceOf(NegativeNumberException.class)
                .hasMessage("You can't order laptop Test3 because quantity of laptop is 0");
    }

    @Test
    public void shouldAddOrderedLaptop(){
        OrderedLaptopDTO orderedLaptopDTO = new OrderedLaptopDTO();
        orderedLaptopDTO.setLaptop("Test2");
        orderedLaptopDTO.setBuyersEmail("user1@gmail.com");
        assertThat(orderedLaptopController.addOrderedLaptop(orderedLaptopDTO).getContent()).isInstanceOf(OrderedLaptopDTO.class);
    }

    @Test
    public void shouldCheckIdOfOrderedLaptopTryingToSell(){
        assertThatThrownBy(() -> orderedLaptopController.sellOrderedLaptop(3))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Ordered laptop with id = 3 not found");
    }

    @Test
    public void shouldCheckIfOrderedLaptopHasBeenSold(){
        orderedLaptopController.sellOrderedLaptop(1);
        assertThatThrownBy(() -> orderedLaptopController.sellOrderedLaptop(1))
                .isInstanceOf(AlreadySoldException.class)
                .hasMessage("Ordered laptop has already been sold");
    }

    @Test
    public void shouldSellOrderedLaptop(){
        assertThat(orderedLaptopController.sellOrderedLaptop(1).getContent()).isInstanceOf(OrderedLaptopDTO.class);
    }

    @Test
    public void shouldSearchLaptops() {
        OrderedLaptopSearchOption orderedLaptopSearchOption = new OrderedLaptopSearchOption();
        orderedLaptopSearchOption.setLaptop("Test");
        orderedLaptopSearchOption.setPurchased(false);

        CollectionModel<EntityModel<OrderedLaptopDTO>> orderedLaptopSearch = orderedLaptopController.orderedLaptopSearch(orderedLaptopSearchOption, 1, 4);
        assertThat(orderedLaptopSearch).hasSize(2);
    }

    @Test
    public void shouldSearchLaptopsSortDesc() {
        orderedLaptopController.sellOrderedLaptop(1);
        OrderedLaptopSearchOption orderedLaptopSearchOption = new OrderedLaptopSearchOption();
        orderedLaptopSearchOption.setLaptop("Test");
        orderedLaptopSearchOption.setBuyersEmail("user1@gmail.com");
        orderedLaptopSearchOption.setDateOfOrder(new Date(System.currentTimeMillis()));
        orderedLaptopSearchOption.setDateOfPurchase(new Date(System.currentTimeMillis()));
        orderedLaptopSearchOption.setPurchased(true);

        CollectionModel<EntityModel<OrderedLaptopDTO>> orderedLaptopSearch = orderedLaptopController.orderedLaptopSearch(orderedLaptopSearchOption, 1, 4);
        assertThat(orderedLaptopSearch).hasSize(1);
        OrderedLaptopDTO orderedLaptopDTO = orderedLaptopSearch.iterator().next().getContent();
        assertThat(orderedLaptopDTO.getLaptop()).isEqualTo("Test1");
    }



}
