package rs.ac.ni.pmf.webproject.laptopstore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.NullException;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.ResourceAlreadyExistsException;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.ResourceNotFoundException;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.HardDiskDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.HardDiskEntity;
import rs.ac.ni.pmf.webproject.laptopstore.models.mappers.HardDiskMapper;
import rs.ac.ni.pmf.webproject.laptopstore.repositories.HardDiskRepository;
import rs.ac.ni.pmf.webproject.laptopstore.services.HardDiskService;

import java.util.ArrayList;
import java.util.List;

@Service
public class HardDiskServiceImpl implements HardDiskService {

    @Autowired
    private HardDiskRepository hardDiskRepository;

    @Autowired
    private HardDiskMapper hardDiskMapper;

    @Override
    public List<HardDiskDTO> findHardDisks() {
        List<HardDiskEntity> hardDiskEntities = hardDiskRepository.findAll();
        List<HardDiskDTO> hardDiskDTOS = new ArrayList<>();
        for (HardDiskEntity hardDiskEntity: hardDiskEntities) {
            hardDiskDTOS.add(hardDiskMapper.toDto(hardDiskEntity));
        }
        return hardDiskDTOS;
    }

    @Override
    public HardDiskDTO findHardDiskById(Integer id) {
        HardDiskEntity hardDiskEntity = hardDiskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hard disk with id = " + id + " not found"));
        return hardDiskMapper.toDto(hardDiskEntity);
    }

    @Override
    public HardDiskDTO saveHardDisk(HardDiskDTO hardDisk) {
        if(hardDisk.getName() == null){
            throw new NullException("Hard disk name can't be null");
        }
        HardDiskEntity hardDiskEntity = hardDiskRepository.findByName(hardDisk.getName());
        if(hardDiskEntity != null){
            throw new ResourceAlreadyExistsException("Hard disk with name = " + hardDisk.getName() + " already exists");
        }
        return hardDiskMapper.toDto(hardDiskRepository.save(hardDiskMapper.toEntity(hardDisk)));
    }
}
