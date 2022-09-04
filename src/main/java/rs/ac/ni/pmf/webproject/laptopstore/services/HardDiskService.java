package rs.ac.ni.pmf.webproject.laptopstore.services;

import rs.ac.ni.pmf.webproject.laptopstore.models.dto.HardDiskDTO;

import java.util.List;

public interface HardDiskService {

    List<HardDiskDTO> findHardDisks();
    HardDiskDTO findHardDiskById(Integer id);
    HardDiskDTO saveHardDisk(HardDiskDTO hardDisk);
}
