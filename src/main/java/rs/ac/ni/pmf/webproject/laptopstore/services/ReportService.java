package rs.ac.ni.pmf.webproject.laptopstore.services;

import rs.ac.ni.pmf.webproject.laptopstore.models.dto.LaptopReportDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.OrderedLaptopReportDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.UserReportDTO;

import java.util.List;

public interface ReportService {

    List<LaptopReportDTO> reportLaptops();
    List<OrderedLaptopReportDTO> reportOrderedLaptops();
    List<UserReportDTO> reportUsers();
}
