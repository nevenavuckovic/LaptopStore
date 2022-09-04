package rs.ac.ni.pmf.webproject.laptopstore.services;

import rs.ac.ni.pmf.webproject.laptopstore.models.dto.ColorDTO;

import java.util.List;

public interface ColorService {

    List<ColorDTO> findColors();
    ColorDTO findColorById(Integer id);
    ColorDTO saveColor(ColorDTO color);
}
