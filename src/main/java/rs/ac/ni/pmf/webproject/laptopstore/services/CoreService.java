package rs.ac.ni.pmf.webproject.laptopstore.services;

import rs.ac.ni.pmf.webproject.laptopstore.models.dto.CoreDTO;

import java.util.List;

public interface CoreService {

    List<CoreDTO> findCores();
    CoreDTO findCoreById(Integer id);
    CoreDTO saveCore(CoreDTO core);
}
