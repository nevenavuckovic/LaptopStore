package rs.ac.ni.pmf.webproject.laptopstore.models.pdfexporters;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.LaptopReportDTO;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class LaptopReportPDFExporter {
    private List<LaptopReportDTO> laptopReportDTOs;

    public LaptopReportPDFExporter(List<LaptopReportDTO> laptopReportDTOs) {
        this.laptopReportDTOs = laptopReportDTOs;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        cell.setPadding(5);

        cell.setPhrase(new Phrase("Producer"));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Count"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Quantity"));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (LaptopReportDTO laptopReportDTO : laptopReportDTOs) {
            table.addCell(laptopReportDTO.getName());
            table.addCell(String.valueOf(laptopReportDTO.getCount()));
            table.addCell(String.valueOf(laptopReportDTO.getQuantity()));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Paragraph p = new Paragraph("Count and quantity of laptops by producer");
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.5f, 3.5f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();
    }
}
