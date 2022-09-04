package rs.ac.ni.pmf.webproject.laptopstore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.NullException;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.ResourceAlreadyExistsException;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.ResourceNotFoundException;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.ProcessorDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.ProcessorEntity;
import rs.ac.ni.pmf.webproject.laptopstore.models.mappers.ProcessorMapper;
import rs.ac.ni.pmf.webproject.laptopstore.repositories.ProcessorRepository;
import rs.ac.ni.pmf.webproject.laptopstore.services.ProcessorService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProcessorServiceImpl implements ProcessorService {

    @Autowired
    private ProcessorRepository processorRepository;

    @Autowired
    private ProcessorMapper processorMapper;

    @Override
    public List<ProcessorDTO> findProcessors() {
        List<ProcessorEntity> processorEntities = processorRepository.findAll();
        List<ProcessorDTO> processorDTOs = new ArrayList<>();
        for (ProcessorEntity processorEntity: processorEntities) {
            processorDTOs.add(processorMapper.toDto(processorEntity));
        }
        return processorDTOs;
    }

    @Override
    public ProcessorDTO findProcessorById(Integer id) {
        ProcessorEntity processorEntity = processorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Processor with id = " + id + " not found"));
        return processorMapper.toDto(processorEntity);
    }

    @Override
    public ProcessorDTO saveProcessor(ProcessorDTO processor) {
        if(processor.getName() == null){
            throw new NullException("Processor name can't be null");
        }
        ProcessorEntity processorEntity = processorRepository.findByName(processor.getName());
        if(processorEntity != null){
            throw new ResourceAlreadyExistsException("Processor with name = " + processor.getName() + " already exists");
        }
        return processorMapper.toDto(processorRepository.save(processorMapper.toEntity(processor)));
    }
}
