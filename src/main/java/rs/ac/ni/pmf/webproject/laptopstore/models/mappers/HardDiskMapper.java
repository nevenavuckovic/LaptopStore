package rs.ac.ni.pmf.webproject.laptopstore.models.mappers;

import org.springframework.stereotype.Component;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.HardDiskDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.HardDiskEntity;

@Component
public class HardDiskMapper {
    public HardDiskDTO toDto(HardDiskEntity entity)
    {
        HardDiskDTO dto = new HardDiskDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public HardDiskEntity toEntity(HardDiskDTO dto)
    {
        HardDiskEntity entity = new HardDiskEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}
