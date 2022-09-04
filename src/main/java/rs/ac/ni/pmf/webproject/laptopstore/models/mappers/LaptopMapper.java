package rs.ac.ni.pmf.webproject.laptopstore.models.mappers;

import org.springframework.stereotype.Component;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.LaptopDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.*;

@Component
public class LaptopMapper {
    public LaptopDTO toDto(LaptopEntity entity)
    {
        LaptopDTO dto = new LaptopDTO();
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setQuantity(entity.getQuantity());
        dto.setId(entity.getId());
        dto.setProducerId(entity.getProducer().getId());
        dto.setColorId(entity.getColor().getId());
        dto.setCoreId(entity.getCore().getId());
        dto.setGraphicCardId(entity.getGraphicCard().getId());
        dto.setHardDiskId(entity.getHardDisk().getId());
        dto.setMemoryCardId(entity.getMemoryCard().getId());
        dto.setOperatingSystemId(entity.getOperatingSystem().getId());
        dto.setProcessorId(entity.getProcessor().getId());
        return dto;
    }

    public LaptopEntity toEntity(LaptopDTO dto)
    {
        LaptopEntity entity = new LaptopEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setQuantity(dto.getQuantity());
        return entity;
    }
}
