package rs.ac.ni.pmf.webproject.laptopstore.services;

import rs.ac.ni.pmf.webproject.laptopstore.models.dto.GraphicCardDTO;

import java.util.List;

public interface GraphicCardService {

    List<GraphicCardDTO> findGraphicCards();
    GraphicCardDTO findGraphicCardById(Integer id);
    GraphicCardDTO saveGraphicCard(GraphicCardDTO graphicCard);
}
