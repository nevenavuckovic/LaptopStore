package rs.ac.ni.pmf.webproject.laptopstore.models.mappers;

import org.springframework.stereotype.Component;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.MemoryCardDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.MemoryCardEntity;

@Component
public class MemoryCardMapper {
    public MemoryCardDTO toDto(MemoryCardEntity entity)
    {
        MemoryCardDTO dto = new MemoryCardDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public MemoryCardEntity toEntity(MemoryCardDTO dto)
    {
        MemoryCardEntity entity = new MemoryCardEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}
