package rs.ac.ni.pmf.webproject.laptopstore.services;

import rs.ac.ni.pmf.webproject.laptopstore.models.dto.OrderedLaptopDTO;
import rs.ac.ni.pmf.webproject.laptopstore.searchoptions.OrderedLaptopSearchOption;

import java.util.List;

public interface OrderedLaptopService {

    List<OrderedLaptopDTO> findOrderedLaptops();
    OrderedLaptopDTO findOrderedLaptopById(Integer id);
    OrderedLaptopDTO saveOrderedLaptop(OrderedLaptopDTO orderedLaptop);
    OrderedLaptopDTO sellOrderedLaptop(Integer id);
    List<OrderedLaptopDTO> orderedLaptopSearch(OrderedLaptopSearchOption options,
                                        Integer pageNumber,
                                        Integer pageSize);
}
