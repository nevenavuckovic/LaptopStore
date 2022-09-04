package rs.ac.ni.pmf.webproject.laptopstore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.*;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.LaptopDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.*;
import rs.ac.ni.pmf.webproject.laptopstore.models.mappers.LaptopMapper;
import rs.ac.ni.pmf.webproject.laptopstore.repositories.*;
import rs.ac.ni.pmf.webproject.laptopstore.repositories.specifications.LaptopsSpecification;
import rs.ac.ni.pmf.webproject.laptopstore.searchoptions.LaptopSearchOption;
import rs.ac.ni.pmf.webproject.laptopstore.services.LaptopService;

import java.util.ArrayList;
import java.util.List;

@Service
public class LaptopServiceImpl implements LaptopService {

    @Autowired
    private LaptopRepository laptopRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private MemoryCardRepository memoryCardRepository;

    @Autowired
    private GraphicCardRepository graphicCardRepository;

    @Autowired
    private HardDiskRepository hardDiskRepository;

    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    private ProcessorRepository processorRepository;

    @Autowired
    private CoreRepository coreRepository;

    @Autowired
    private OperatingSystemRepository operatingSystemRepository;

    @Autowired
    private LaptopMapper laptopMapper;

    @Override
    public List<LaptopDTO> findLaptops() {
        List<LaptopEntity> laptopEntities = laptopRepository.findAll();
        List<LaptopDTO> laptopDTOs = new ArrayList<>();
        for (LaptopEntity laptopEntity: laptopEntities) {
            laptopDTOs.add(laptopMapper.toDto(laptopEntity));
        }
        return laptopDTOs;
    }

    @Override
    public LaptopDTO findLaptopById(Integer id) {
        LaptopEntity laptopEntity = laptopRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Laptop with id = " + id + " not found"));
        return laptopMapper.toDto(laptopEntity);
    }

    @Override
    public LaptopDTO saveLaptop(Integer id, LaptopDTO laptop) {
        LaptopEntity laptopEntity = laptopMapper.toEntity(laptop);
        if(id != null) {
            laptopEntity = laptopRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Laptop with id = " + id + " not found"));
        }
        if(laptop.getQuantity() != null) {
            if(laptop.getQuantity() <= 0) {
                throw new NegativeNumberException("Quantity can't be negative number");
            }
            laptopEntity.setQuantity(laptop.getQuantity());
        }


        if(laptop.getName() != null){
            if(laptopRepository.findByName(laptop.getName()) != null){
                throw new ResourceAlreadyExistsException("Laptop with name = " + laptop.getName() + " already exists");
            }
            laptopEntity.setName(laptop.getName());
        } else if(id == null){
            throw new NullException("Name can't be null");
        }

        if(id == null && laptop.getPrice() == null){
            throw new NullException("Price can't be null");
        }
        if(laptop.getPrice() != null) {
            if(laptop.getPrice() <= 0) {
                throw new NegativeNumberException("Price can't be negative number or 0");
            }
            laptopEntity.setPrice(laptop.getPrice());
        }

        if (laptop.getColorId() != null) {
            ColorEntity colorEntity = colorRepository.findById(laptop.getColorId()).orElseThrow(() -> new ResourceNotFoundException("Color with id = " + laptop.getColorId() + " not found"));
            laptopEntity.setColor(colorEntity);
        } else if (id == null){
            throw new NullException("Color id can't be null");
        }

        if (laptop.getCoreId() != null) {
            CoreEntity coreEntity = coreRepository.findById(laptop.getCoreId()).orElseThrow(() -> new ResourceNotFoundException("Core with id = " + laptop.getCoreId() + " not found"));
            laptopEntity.setCore(coreEntity);
        } else if (id == null){
            throw new NullException("Core id can't be null");
        }

        if (laptop.getGraphicCardId() != null) {
            GraphicCardEntity graphicCardEntity = graphicCardRepository.findById(laptop.getGraphicCardId()).orElseThrow(() -> new ResourceNotFoundException("Graphic card with id = " + laptop.getGraphicCardId() + " not found"));
            laptopEntity.setGraphicCard(graphicCardEntity);
        } else if (id == null){
            throw new NullException("Graphic card id can't be null");
        }

        if (laptop.getHardDiskId() != null) {
            HardDiskEntity hardDiskEntity = hardDiskRepository.findById(laptop.getHardDiskId()).orElseThrow(() -> new ResourceNotFoundException("Hard disk with id = " + laptop.getHardDiskId() + " not found"));
            laptopEntity.setHardDisk(hardDiskEntity);
        } else if (id == null){
            throw new NullException("Hard disk id can't be null");
        }

        if (laptop.getMemoryCardId() != null) {
            MemoryCardEntity memoryCardEntity = memoryCardRepository.findById(laptop.getMemoryCardId()).orElseThrow(() -> new ResourceNotFoundException("Memory card with id = " + laptop.getMemoryCardId() + " not found"));
            laptopEntity.setMemoryCard(memoryCardEntity);
        } else if (id == null){
            throw new NullException("Memory card id can't be null");
        }

        if (laptop.getOperatingSystemId() != null) {
            OperatingSystemEntity operatingSystemEntity = operatingSystemRepository.findById(laptop.getOperatingSystemId()).orElseThrow(() -> new ResourceNotFoundException("Operating system with id = " + laptop.getOperatingSystemId() + " not found"));
            laptopEntity.setOperatingSystem(operatingSystemEntity);
        } else if (id == null){
            throw new NullException("Operating system id can't be null");
        }

        if (laptop.getProducerId() != null) {
            ProducerEntity producerEntity = producerRepository.findById(laptop.getProducerId()).orElseThrow(() -> new ResourceNotFoundException("Producer with id = " + laptop.getProducerId() + " not found"));
            laptopEntity.setProducer(producerEntity);
        } else if (id == null){
            throw new NullException("Producer id can't be null");
        }

        if (laptop.getProcessorId() != null) {
            ProcessorEntity processorEntity = processorRepository.findById(laptop.getProcessorId()).orElseThrow(() -> new ResourceNotFoundException("Processor with id = " + laptop.getProcessorId() + " not found"));
            laptopEntity.setProcessor(processorEntity);
        } else if (id == null && laptop.getProcessorId() == null){
            throw new NullException("Processor id can't be null");
        }

        laptopRepository.save(laptopEntity);
        return laptopMapper.toDto(laptopEntity);
    }

    @Override
    public List<LaptopDTO> laptopSearch(LaptopSearchOption options,
                                        Integer pageNumber,
                                        Integer pageSize)
    {
        if(pageNumber <= 0){
            throw new PageException("Page number must be grater than 0");
        }
        if(pageSize <= 0){
            throw new PageException("Page size must be grater than 0");
        }
        Page<LaptopEntity> laptopEntities = laptopRepository.findAll(LaptopsSpecification.laptopSearch(options),
                                            PageRequest.of(pageNumber-1, pageSize));
        List<LaptopDTO> laptopDTOs = new ArrayList<>();
        for (LaptopEntity laptopEntity: laptopEntities) {
            laptopDTOs.add(laptopMapper.toDto(laptopEntity));
        }
        return laptopDTOs;
    }

}
