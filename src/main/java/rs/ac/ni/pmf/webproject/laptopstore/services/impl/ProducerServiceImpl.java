package rs.ac.ni.pmf.webproject.laptopstore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.NullException;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.ResourceAlreadyExistsException;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.ResourceNotFoundException;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.ProducerDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.ProducerEntity;
import rs.ac.ni.pmf.webproject.laptopstore.models.mappers.ProducerMapper;
import rs.ac.ni.pmf.webproject.laptopstore.repositories.ProducerRepository;
import rs.ac.ni.pmf.webproject.laptopstore.services.ProducerService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    private ProducerMapper producerMapper;

    @Override
    public List<ProducerDTO> findProducers() {
        List<ProducerEntity> ProducerEntities = producerRepository.findAll();
        List<ProducerDTO> producerDTOs = new ArrayList<>();
        for (ProducerEntity producerEntity: ProducerEntities) {
            producerDTOs.add(producerMapper.toDto(producerEntity));
        }
        return producerDTOs;
    }

    @Override
    public ProducerDTO findProducerById(Integer id) {
        ProducerEntity producerEntity = producerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Producer with id = " + id + " not found"));
        return producerMapper.toDto(producerEntity);
    }

    @Override
    public ProducerDTO saveProducer(ProducerDTO producer) {
        if(producer.getName() == null){
            throw new NullException("Producer name can't be null");
        }
        ProducerEntity producerEntity = producerRepository.findByName(producer.getName());
        if(producerEntity != null){
            throw new ResourceAlreadyExistsException("Producer with name = " + producer.getName() + " already exists");
        }
        return producerMapper.toDto(producerRepository.save(producerMapper.toEntity(producer)));
    }
}
