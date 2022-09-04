package rs.ac.ni.pmf.webproject.laptopstore.services;

import rs.ac.ni.pmf.webproject.laptopstore.models.dto.ProcessorDTO;

import java.util.List;

public interface ProcessorService {

    List<ProcessorDTO> findProcessors();
    ProcessorDTO findProcessorById(Integer id);
    ProcessorDTO saveProcessor(ProcessorDTO processor);
}
