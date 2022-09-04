package rs.ac.ni.pmf.webproject.laptopstore.models.mappers;

import org.springframework.stereotype.Component;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.GraphicCardDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.GraphicCardEntity;

@Component
public class GraphicCardMapper {
    public GraphicCardDTO toDto(GraphicCardEntity entity)
    {
        GraphicCardDTO dto = new GraphicCardDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public GraphicCardEntity toEntity(GraphicCardDTO dto)
    {
        GraphicCardEntity entity = new GraphicCardEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}
