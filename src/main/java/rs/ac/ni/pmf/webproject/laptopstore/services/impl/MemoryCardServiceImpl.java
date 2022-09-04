package rs.ac.ni.pmf.webproject.laptopstore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.NullException;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.ResourceAlreadyExistsException;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.ResourceNotFoundException;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.MemoryCardDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.MemoryCardEntity;
import rs.ac.ni.pmf.webproject.laptopstore.models.mappers.MemoryCardMapper;
import rs.ac.ni.pmf.webproject.laptopstore.repositories.MemoryCardRepository;
import rs.ac.ni.pmf.webproject.laptopstore.services.MemoryCardService;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemoryCardServiceImpl implements MemoryCardService {

    @Autowired
    private MemoryCardRepository memoryCardRepository;

    @Autowired
    private MemoryCardMapper memoryCardMapper;

    @Override
    public List<MemoryCardDTO> findMemoryCards() {
        List<MemoryCardEntity> memoryCardEntities = memoryCardRepository.findAll();
        List<MemoryCardDTO> memoryCardDTOs = new ArrayList<>();
        for (MemoryCardEntity memoryCardEntity: memoryCardEntities) {
            memoryCardDTOs.add(memoryCardMapper.toDto(memoryCardEntity));
        }
        return memoryCardDTOs;
    }

    @Override
    public MemoryCardDTO findMemoryCardById(Integer id) {
        MemoryCardEntity memoryCardEntity = memoryCardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Memory card with id = " + id + " not found"));
        return memoryCardMapper.toDto(memoryCardEntity);
    }

    @Override
    public MemoryCardDTO saveMemoryCard(MemoryCardDTO memoryCard) {
        if(memoryCard.getName() == null){
            throw new NullException("Memory card name can't be null");
        }
        MemoryCardEntity memoryCardEntity = memoryCardRepository.findByName(memoryCard.getName());
        if(memoryCardEntity != null){
            throw new ResourceAlreadyExistsException("Memory card with name = " + memoryCard.getName() + " already exists");
        }
        return memoryCardMapper.toDto(memoryCardRepository.save(memoryCardMapper.toEntity(memoryCard)));
    }
}
