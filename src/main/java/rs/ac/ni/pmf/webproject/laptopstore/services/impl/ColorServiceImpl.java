package rs.ac.ni.pmf.webproject.laptopstore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.NullException;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.ResourceNotFoundException;
import rs.ac.ni.pmf.webproject.laptopstore.exceptions.ResourceAlreadyExistsException;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.ColorDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.ColorEntity;
import rs.ac.ni.pmf.webproject.laptopstore.models.mappers.ColorMapper;
import rs.ac.ni.pmf.webproject.laptopstore.repositories.ColorRepository;
import rs.ac.ni.pmf.webproject.laptopstore.services.ColorService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private ColorMapper colorMapper;

    @Override
    public List<ColorDTO> findColors() {
        List<ColorEntity> colorEntities = colorRepository.findAll();
        List<ColorDTO> colorDTOs = new ArrayList<>();
        for (ColorEntity colorEntity: colorEntities) {
            colorDTOs.add(colorMapper.toDto(colorEntity));
        }
        return colorDTOs;
    }

    @Override
    public ColorDTO findColorById(Integer id) {
        ColorEntity colorEntity = colorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Color with id = " + id + " not found"));
        return colorMapper.toDto(colorEntity);
    }

    @Override
    public ColorDTO saveColor(ColorDTO color) {
        if(color.getName() == null){
            throw new NullException("Color name can't be null");
        }
        ColorEntity colorEntity = colorRepository.findByName(color.getName());
        if(colorEntity != null){
            throw new ResourceAlreadyExistsException("Color with name = " + color.getName() + " already exists");
        }
        return colorMapper.toDto(colorRepository.save(colorMapper.toEntity(color)));
    }
}
