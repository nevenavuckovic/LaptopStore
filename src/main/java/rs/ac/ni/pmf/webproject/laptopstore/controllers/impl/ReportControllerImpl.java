package rs.ac.ni.pmf.webproject.laptopstore.controllers.impl;

import com.lowagie.text.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.ni.pmf.webproject.laptopstore.controllers.ReportController;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.LaptopReportDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.OrderedLaptopReportDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.UserReportDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.pdfexporters.LaptopReportPDFExporter;
import rs.ac.ni.pmf.webproject.laptopstore.models.pdfexporters.OrderedLaptopReportPDFExporter;
import rs.ac.ni.pmf.webproject.laptopstore.models.pdfexporters.UserReportPDFExporter;
import rs.ac.ni.pmf.webproject.laptopstore.services.ReportService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class ReportControllerImpl implements ReportController {

    @Autowired
    ReportService reportService;

    private static final Logger logger = LoggerFactory.getLogger(ColorControllerImpl.class);

    @Override
    public void exportLaptopReportToPDF(HttpServletResponse response){
        logger.info("URL: ../report/laptop/pdf");
        logger.debug("Exporting laptop report to PDF...");
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=laptop_report_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<LaptopReportDTO> laptopReportDTOs = reportService.reportLaptops();

        LaptopReportPDFExporter exporter = new LaptopReportPDFExporter(laptopReportDTOs);
        try {
            exporter.export(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e){
            e.printStackTrace();
        }

    }

    @Override
    public void exportOrderedLaptopReportToPDF(HttpServletResponse response){
        logger.info("URL: ../report/ordered-laptop/pdf");
        logger.debug("Exporting ordered laptop report to PDF...");
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=ordered_laptop_report_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<OrderedLaptopReportDTO> orderedLaptopReportDTOs = reportService.reportOrderedLaptops();

        OrderedLaptopReportPDFExporter exporter = new OrderedLaptopReportPDFExporter(orderedLaptopReportDTOs);
        try {
            exporter.export(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e){
            e.printStackTrace();
        }

    }

    @Override
    public void exportUserReportToPDF(HttpServletResponse response){
        logger.info("URL: ../report/user/pdf");
        logger.debug("Exporting user report to PDF...");
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=user_report_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<UserReportDTO> userReportDTOs = reportService.reportUsers();

        UserReportPDFExporter exporter = new UserReportPDFExporter(userReportDTOs);

        try {
            exporter.export(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e){
            e.printStackTrace();
        }

    }

    @Override
    public CollectionModel<EntityModel<LaptopReportDTO>> reportLaptops() {
        logger.info("URL: ../report/laptop");
        logger.debug("Getting laptop report...");
        List<EntityModel<LaptopReportDTO>> models = new ArrayList<>();
        List<LaptopReportDTO> laptops = reportService.reportLaptops();
        for (LaptopReportDTO laptopDTO:laptops) {
            models.add(EntityModel.of(laptopDTO));
        }

        return CollectionModel.of(models);
    }

    @Override
    public CollectionModel<EntityModel<OrderedLaptopReportDTO>> reportOrderedLaptops() {
        logger.info("URL: ../report/ordered-laptop");
        logger.debug("Getting ordered laptop report...");
        List<EntityModel<OrderedLaptopReportDTO>> models = new ArrayList<>();
        List<OrderedLaptopReportDTO> orderedLaptops = reportService.reportOrderedLaptops();
        for (OrderedLaptopReportDTO orderedLaptopReportDTO:orderedLaptops) {
            models.add(EntityModel.of(orderedLaptopReportDTO));
        }

        return CollectionModel.of(models);
    }

    @Override
    public CollectionModel<EntityModel<UserReportDTO>> reportUsers() {
        logger.info("URL: ../report/user");
        logger.debug("Getting user report...");
        List<EntityModel<UserReportDTO>> models = new ArrayList<>();
        List<UserReportDTO> userReportDTOS = reportService.reportUsers();
        for (UserReportDTO userReportDTO:userReportDTOS) {
            models.add(EntityModel.of(userReportDTO));
        }

        return CollectionModel.of(models);
    }

}
