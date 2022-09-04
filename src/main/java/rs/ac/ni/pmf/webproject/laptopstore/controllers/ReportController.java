package rs.ac.ni.pmf.webproject.laptopstore.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.LaptopReportDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.OrderedLaptopReportDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.UserReportDTO;

import javax.servlet.http.HttpServletResponse;


@RequestMapping("/report")
public interface ReportController {

    @GetMapping("/laptop/pdf")
    @Operation(summary = "Exports laptop report to PDF")
    void exportLaptopReportToPDF(HttpServletResponse response);

    @GetMapping("/ordered-laptop/pdf")
    @Operation(summary = "Exports ordered laptop report to PDF")
    void exportOrderedLaptopReportToPDF(HttpServletResponse response);

    @GetMapping("/user/pdf")
    @Operation(summary = "Exports user report to PDF")
    void exportUserReportToPDF(HttpServletResponse response);

    @GetMapping("/laptop")
    @Operation(summary = "Retrieves laptop report")
    CollectionModel<EntityModel<LaptopReportDTO>> reportLaptops();

    @GetMapping("/ordered-laptop")
    @Operation(summary = "Retrieves ordered laptop report")
    CollectionModel<EntityModel<OrderedLaptopReportDTO>> reportOrderedLaptops();

    @GetMapping("/user")
    @Operation(summary = "Retrieves user report")
    CollectionModel<EntityModel<UserReportDTO>> reportUsers();

}
