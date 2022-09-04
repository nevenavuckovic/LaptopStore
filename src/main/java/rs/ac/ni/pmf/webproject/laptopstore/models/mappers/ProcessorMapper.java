package rs.ac.ni.pmf.webproject.laptopstore.models.mappers;

import org.springframework.stereotype.Component;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.ProcessorDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.ProcessorEntity;

@Component
public class ProcessorMapper {
    public ProcessorDTO toDto(ProcessorEntity entity)
    {
        ProcessorDTO dto = new ProcessorDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public ProcessorEntity toEntity(ProcessorDTO dto)
    {
        ProcessorEntity entity = new ProcessorEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}
