package rs.ac.ni.pmf.webproject.laptopstore.models.mappers;

import org.springframework.stereotype.Component;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.ProducerDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.ProducerEntity;

@Component
public class ProducerMapper {
    public ProducerDTO toDto(ProducerEntity entity)
    {
        ProducerDTO dto = new ProducerDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public ProducerEntity toEntity(ProducerDTO dto)
    {
        ProducerEntity entity = new ProducerEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}
