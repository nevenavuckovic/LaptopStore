package rs.ac.ni.pmf.webproject.laptopstore.services;

import rs.ac.ni.pmf.webproject.laptopstore.models.dto.OperatingSystemDTO;

import java.util.List;

public interface OperatingSystemService {

    List<OperatingSystemDTO> findOperatingSystems();
    OperatingSystemDTO findOperatingSystemById(Integer id);
    OperatingSystemDTO saveOperatingSystem(OperatingSystemDTO operatingSystem);
}
