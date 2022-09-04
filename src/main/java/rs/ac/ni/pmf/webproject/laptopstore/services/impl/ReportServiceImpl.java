package rs.ac.ni.pmf.webproject.laptopstore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.LaptopReportDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.OrderedLaptopReportDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.UserReportDTO;
import rs.ac.ni.pmf.webproject.laptopstore.repositories.ReportRepository;
import rs.ac.ni.pmf.webproject.laptopstore.services.ReportService;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Override
    public List<LaptopReportDTO> reportLaptops() {
        return reportRepository.reportLaptops();
    }

    @Override
    public List<OrderedLaptopReportDTO> reportOrderedLaptops(){
        return reportRepository.reportOrderedLaptops();
    }

    @Override
    public List<UserReportDTO> reportUsers() {
        return reportRepository.reportUsers();
    }


}
