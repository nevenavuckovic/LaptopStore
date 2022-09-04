package rs.ac.ni.pmf.webproject.laptopstore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.NullException;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.ResourceAlreadyExistsException;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.ResourceNotFoundException;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.GraphicCardDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.GraphicCardEntity;
import rs.ac.ni.pmf.webproject.laptopstore.models.mappers.GraphicCardMapper;
import rs.ac.ni.pmf.webproject.laptopstore.repositories.GraphicCardRepository;
import rs.ac.ni.pmf.webproject.laptopstore.services.GraphicCardService;

import java.util.ArrayList;
import java.util.List;

@Service
public class GraphicCardServiceImpl implements GraphicCardService {

    @Autowired
    private GraphicCardRepository graphicCardRepository;

    @Autowired
    private GraphicCardMapper graphicCardMapper;

    @Override
    public List<GraphicCardDTO> findGraphicCards() {
        List<GraphicCardEntity> graphicCardEntities = graphicCardRepository.findAll();
        List<GraphicCardDTO> graphicCardDTOs = new ArrayList<>();
        for (GraphicCardEntity graphicCardEntity: graphicCardEntities) {
            graphicCardDTOs.add(graphicCardMapper.toDto(graphicCardEntity));
        }
        return graphicCardDTOs;
    }

    @Override
    public GraphicCardDTO findGraphicCardById(Integer id) {
        GraphicCardEntity graphicCardEntity = graphicCardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Graphic card with id = " + id + " not found"));
        return graphicCardMapper.toDto(graphicCardEntity);
    }

    @Override
    public GraphicCardDTO saveGraphicCard(GraphicCardDTO graphicCard) {
        if(graphicCard.getName() == null){
            throw new NullException("Graphic card name can't be null");
        }
        GraphicCardEntity graphicCardEntity = graphicCardRepository.findByName(graphicCard.getName());
        if(graphicCardEntity != null){
            throw new ResourceAlreadyExistsException("Graphic card with name = " + graphicCard.getName() + " already exists");
        }
        return graphicCardMapper.toDto(graphicCardRepository.save(graphicCardMapper.toEntity(graphicCard)));
    }
}
