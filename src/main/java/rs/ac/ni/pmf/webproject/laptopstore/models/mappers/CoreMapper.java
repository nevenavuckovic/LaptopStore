package rs.ac.ni.pmf.webproject.laptopstore.models.mappers;

import org.springframework.stereotype.Component;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.CoreDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.CoreEntity;

@Component
public class CoreMapper {
    public CoreDTO toDto(CoreEntity entity)
    {
        CoreDTO dto = new CoreDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public CoreEntity toEntity(CoreDTO dto)
    {
        CoreEntity entity = new CoreEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}
