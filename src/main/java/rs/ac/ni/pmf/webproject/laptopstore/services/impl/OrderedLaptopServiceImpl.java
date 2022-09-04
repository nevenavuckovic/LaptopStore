package rs.ac.ni.pmf.webproject.laptopstore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.*;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.OrderedLaptopDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.LaptopEntity;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.OrderedLaptopEntity;
import rs.ac.ni.pmf.webproject.laptopstore.models.mappers.OrderedLaptopMapper;
import rs.ac.ni.pmf.webproject.laptopstore.repositories.LaptopRepository;
import rs.ac.ni.pmf.webproject.laptopstore.repositories.OrderedLaptopRepository;
import rs.ac.ni.pmf.webproject.laptopstore.repositories.UserRepository;
import rs.ac.ni.pmf.webproject.laptopstore.repositories.specifications.OrderedLaptopsSpecification;
import rs.ac.ni.pmf.webproject.laptopstore.searchoptions.OrderedLaptopSearchOption;
import rs.ac.ni.pmf.webproject.laptopstore.services.OrderedLaptopService;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderedLaptopServiceImpl implements OrderedLaptopService {

    @Autowired
    private OrderedLaptopRepository orderedLaptopRepository;

    @Autowired
    private LaptopRepository laptopRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderedLaptopMapper orderedLaptopMapper;

    @Override
    public List<OrderedLaptopDTO> findOrderedLaptops() {
        List<OrderedLaptopEntity> orderedLaptopEntities = orderedLaptopRepository.findAll();
        List<OrderedLaptopDTO> orderedLaptopDTOs = new ArrayList<>();
        for (OrderedLaptopEntity orderedLaptopEntity: orderedLaptopEntities) {
            orderedLaptopDTOs.add(orderedLaptopMapper.toDto(orderedLaptopEntity));
        }
        return orderedLaptopDTOs;
    }

    @Override
    public OrderedLaptopDTO findOrderedLaptopById(Integer id) {
        OrderedLaptopEntity orderedLaptopEntity = orderedLaptopRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ordered laptop with id = " + id + " not found"));
        return orderedLaptopMapper.toDto(orderedLaptopEntity);
    }

    @Override
    public OrderedLaptopDTO saveOrderedLaptop(OrderedLaptopDTO orderedLaptop) {
        if(orderedLaptop.getLaptop() == null){
            throw new NullException("Laptop name can't be null");
        }
        if(orderedLaptop.getBuyersEmail() == null){
            throw new NullException("Email can't be null");
        }
        if(userRepository.findByEmail(orderedLaptop.getBuyersEmail()) == null){
            throw new ResourceNotFoundException("User with email = " + orderedLaptop.getBuyersEmail() + " not found");
        }
        LaptopEntity laptopEntity = laptopRepository.findByName(orderedLaptop.getLaptop());
        if(laptopEntity == null){
            throw new ResourceNotFoundException("Laptop " + orderedLaptop.getLaptop() + " not found");
        }
        if (laptopEntity.getQuantity() == 0) {
            throw new NegativeNumberException("You can't order laptop " + orderedLaptop.getLaptop() + " because quantity of laptop is 0");
        }

        OrderedLaptopEntity orderedLaptopEntity = new OrderedLaptopEntity();
        orderedLaptopEntity.setLaptop(orderedLaptop.getLaptop());
        orderedLaptopEntity.setPrice(laptopEntity.getPrice());
        orderedLaptopEntity.setBuyersEmail(orderedLaptop.getBuyersEmail());

        return orderedLaptopMapper.toDto(orderedLaptopRepository.save(orderedLaptopEntity));
    }

    @Override
    public OrderedLaptopDTO sellOrderedLaptop(Integer id){
        OrderedLaptopEntity orderedLaptopEntity = orderedLaptopRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Ordered laptop with id = " + id + " not found"));
        if(orderedLaptopEntity.getPurchased()){
            throw new AlreadySoldException("Ordered laptop has already been sold");
        }
        orderedLaptopEntity.setPurchased(true);
        return orderedLaptopMapper.toDto(orderedLaptopRepository.save(orderedLaptopEntity));
    }

    @Override
    public List<OrderedLaptopDTO> orderedLaptopSearch(OrderedLaptopSearchOption options,
                                                      Integer pageNumber,
                                                      Integer pageSize)
    {
        if(pageNumber <= 0){
            throw new PageException("Page number must be grater than 0");
        }
        if(pageSize <= 0){
            throw new PageException("Page size must be grater than 0");
        }
        Page<OrderedLaptopEntity> orderedLaptopEntities = orderedLaptopRepository.findAll(OrderedLaptopsSpecification.orderedLaptopSearch(options),
                PageRequest.of(pageNumber-1, pageSize));
        List<OrderedLaptopDTO> orderedLaptopDTOs = new ArrayList<>();
        for (OrderedLaptopEntity orderedLaptopEntity: orderedLaptopEntities) {
            orderedLaptopDTOs.add(orderedLaptopMapper.toDto(orderedLaptopEntity));
        }
        return orderedLaptopDTOs;
    }
}
