package rs.ac.ni.pmf.webproject.laptopstore.models.mappers;

import org.springframework.stereotype.Component;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.ColorDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.ColorEntity;

@Component
public class ColorMapper {
    public ColorDTO toDto(ColorEntity entity)
    {
        ColorDTO dto = new ColorDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public ColorEntity toEntity(ColorDTO dto)
    {
        ColorEntity entity = new ColorEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}
