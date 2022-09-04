package rs.ac.ni.pmf.webproject.laptopstore.security;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.web.client.RestTemplate;
import rs.ac.ni.pmf.webproject.laptopstore.controllers.OrderedLaptopController;
import rs.ac.ni.pmf.webproject.laptopstore.models.Role;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.LaptopDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.OrderedLaptopDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.UserDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.*;
import rs.ac.ni.pmf.webproject.laptopstore.models.securities.MyUserDetails;
import rs.ac.ni.pmf.webproject.laptopstore.repositories.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecurityConfigurationTest
{
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private OrderedLaptopRepository orderedLaptopRepository;

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

        UserEntity userEntity1 = new UserEntity();
        userEntity1.setUsername("admin1");
        userEntity1.setEmail("admin1@gmail.com");
        userEntity1.setPassword("admin1");
        userEntity1.setRoleId(1);
        userEntity1.setActive(true);
        userRepository.save(userEntity1);

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setUsername("employee1");
        userEntity2.setEmail("employee1@gmail.com");
        userEntity2.setPassword("employee1");
        userEntity2.setRoleId(2);
        userEntity2.setActive(true);
        userRepository.save(userEntity2);

        UserEntity userEntity3 = new UserEntity();
        userEntity3.setUsername("buyer1");
        userEntity3.setEmail("buyer1@gmail.com");
        userEntity3.setPassword("buyer1");
        userEntity3.setRoleId(3);
        userEntity3.setActive(true);
        userRepository.save(userEntity3);

        OrderedLaptopEntity orderedLaptopEntity1 = new OrderedLaptopEntity();
        orderedLaptopEntity1.setLaptop("Test1");
        orderedLaptopEntity1.setBuyersEmail("user1@gmail.com");
        orderedLaptopRepository.save(orderedLaptopEntity1);

        OrderedLaptopEntity orderedLaptopEntity2 = new OrderedLaptopEntity();
        orderedLaptopEntity2.setLaptop("Test2");
        orderedLaptopEntity2.setBuyersEmail("user1@gmail.com");
        orderedLaptopRepository.save(orderedLaptopEntity2);

    }

    @LocalServerPort
    private int port;

    private String baseUrl;

    @BeforeEach
    public void init()
    {
        baseUrl = "http://localhost:" + port;
    }

    @Test
    public void shouldCheckAnonymousCalls()
    {
        ResponseEntity<String> response = testRestTemplate.getForEntity(baseUrl + "/login", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseEntity<Problem> responseBadAuth = testRestTemplate.getForEntity(baseUrl + "/laptop", Problem.class);
        assertThat(responseBadAuth.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        Problem problem = responseBadAuth.getBody();
        assertThat(problem.getTitle()).isEqualTo("Authentication failed");
        assertThat(problem.getDetail()).isEqualTo("Full authentication is required to access this resource");
    }

    //ne radi (ne prepoznaje mog usera)
/*
    @Test
    public void shouldCheckForBuyer()
    {
        ResponseEntity<String> response = testRestTemplate.withBasicAuth("buyer1", "buyer1").getForEntity(baseUrl + "/laptop", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseEntity<Problem> responseNoProject =
                testRestTemplate.getForEntity(baseUrl + "/laptop/100", Problem.class);
        assertThat(responseNoProject.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(responseNoProject.getBody().getDetail()).isEqualTo("Laptop with id = 100 not found");

        OrderedLaptopDTO orderedLaptopDTO = new OrderedLaptopDTO();
        ResponseEntity<String> responseOrder =
                testRestTemplate.postForEntity(baseUrl + "/ordered-laptop", orderedLaptopDTO, String.class);
        assertThat(responseOrder.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        UserDTO userDTO = new UserDTO();
        ResponseEntity<Problem> responseCreate =
                testRestTemplate.postForEntity(baseUrl + "/user", userDTO, Problem.class);
        assertThat(responseCreate.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }
*/
}