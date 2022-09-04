package rs.ac.ni.pmf.webproject.laptopstore.models.mappers;

import org.springframework.stereotype.Component;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.OperatingSystemDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.OperatingSystemEntity;

@Component
public class OperatingSystemMapper {
    public OperatingSystemDTO toDto(OperatingSystemEntity entity)
    {
        OperatingSystemDTO dto = new OperatingSystemDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public OperatingSystemEntity toEntity(OperatingSystemDTO dto)
    {
        OperatingSystemEntity entity = new OperatingSystemEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}
