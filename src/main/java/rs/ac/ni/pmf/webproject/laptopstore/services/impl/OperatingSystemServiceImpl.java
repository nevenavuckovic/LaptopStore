package rs.ac.ni.pmf.webproject.laptopstore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.NullException;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.ResourceAlreadyExistsException;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.ResourceNotFoundException;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.OperatingSystemDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.OperatingSystemEntity;
import rs.ac.ni.pmf.webproject.laptopstore.models.mappers.OperatingSystemMapper;
import rs.ac.ni.pmf.webproject.laptopstore.repositories.OperatingSystemRepository;
import rs.ac.ni.pmf.webproject.laptopstore.services.OperatingSystemService;

import java.util.ArrayList;
import java.util.List;

@Service
public class OperatingSystemServiceImpl implements OperatingSystemService {

    @Autowired
    private OperatingSystemRepository operatingSystemRepository;

    @Autowired
    private OperatingSystemMapper operatingSystemMapper;

    @Override
    public List<OperatingSystemDTO> findOperatingSystems() {
        List<OperatingSystemEntity> operatingSystemEntities = operatingSystemRepository.findAll();
        List<OperatingSystemDTO> operatingSystemDTOs = new ArrayList<>();
        for (OperatingSystemEntity operatingSystemEntity: operatingSystemEntities) {
            operatingSystemDTOs.add(operatingSystemMapper.toDto(operatingSystemEntity));
        }
        return operatingSystemDTOs;
    }

    @Override
    public OperatingSystemDTO findOperatingSystemById(Integer id) {
        OperatingSystemEntity operatingSystemEntity = operatingSystemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Operating system with id = " + id + " not found"));
        return operatingSystemMapper.toDto(operatingSystemEntity);
    }

    @Override
    public OperatingSystemDTO saveOperatingSystem(OperatingSystemDTO operatingSystem) {
        if(operatingSystem.getName() == null){
            throw new NullException("Operating system name can't be null");
        }
        OperatingSystemEntity operatingSystemEntity = operatingSystemRepository.findByName(operatingSystem.getName());
        if(operatingSystemEntity != null){
            throw new ResourceAlreadyExistsException("Operating system with name = " + operatingSystem.getName() + " already exists");
        }
        return operatingSystemMapper.toDto(operatingSystemRepository.save(operatingSystemMapper.toEntity(operatingSystem)));
    }

}
