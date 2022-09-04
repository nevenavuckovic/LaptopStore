package rs.ac.ni.pmf.webproject.laptopstore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.NullException;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.ResourceAlreadyExistsException;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.ResourceNotFoundException;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.CoreDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.CoreEntity;
import rs.ac.ni.pmf.webproject.laptopstore.models.mappers.CoreMapper;
import rs.ac.ni.pmf.webproject.laptopstore.repositories.CoreRepository;
import rs.ac.ni.pmf.webproject.laptopstore.services.CoreService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoreServiceImpl implements CoreService {

    @Autowired
    private CoreRepository coreRepository;

    @Autowired
    private CoreMapper coreMapper;

    @Override
    public List<CoreDTO> findCores() {
        List<CoreEntity> coreEntities = coreRepository.findAll();
        List<CoreDTO> coreDTOs = new ArrayList<>();
        for (CoreEntity coreEntity: coreEntities) {
            coreDTOs.add(coreMapper.toDto(coreEntity));
        }
        return coreDTOs;
    }

    @Override
    public CoreDTO findCoreById(Integer id) {
        CoreEntity coreEntity = coreRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Core with id = " + id + " not found"));
        return coreMapper.toDto(coreEntity);
    }

    @Override
    public CoreDTO saveCore(CoreDTO core) {
        if(core.getName() == null){
            throw new NullException("Core name can't be null");
        }
        CoreEntity coreEntity = coreRepository.findByName(core.getName());
        if(coreEntity != null){
            throw new ResourceAlreadyExistsException("Core with name = " + core.getName() + " already exists");
        }
        return coreMapper.toDto(coreRepository.save(coreMapper.toEntity(core)));
    }
}
