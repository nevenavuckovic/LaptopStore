package rs.ac.ni.pmf.webproject.laptopstore.services;

import rs.ac.ni.pmf.webproject.laptopstore.models.dto.LaptopDTO;
import rs.ac.ni.pmf.webproject.laptopstore.searchoptions.LaptopSearchOption;

import java.util.List;

public interface LaptopService {

    List<LaptopDTO> findLaptops();
    LaptopDTO findLaptopById(Integer id);
    LaptopDTO saveLaptop(Integer id, LaptopDTO laptop);
    List<LaptopDTO> laptopSearch(LaptopSearchOption options,
                                 Integer pageNumber,
                                 Integer pageSize);
}
