package rs.ac.ni.pmf.webproject.laptopstore.models.pdfexporters;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.OrderedLaptopReportDTO;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class OrderedLaptopReportPDFExporter {
    private List<OrderedLaptopReportDTO> orderedLaptopReportDTOs;

    public OrderedLaptopReportPDFExporter(List<OrderedLaptopReportDTO> orderedLaptopReportDTOs) {
        this.orderedLaptopReportDTOs = orderedLaptopReportDTOs;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.PINK);
        cell.setPadding(5);

        cell.setPhrase(new Phrase("Purchased"));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Count"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Average price"));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (OrderedLaptopReportDTO orderedLaptopReportDTO : orderedLaptopReportDTOs) {
            table.addCell(String.valueOf(orderedLaptopReportDTO.getPurchased()));
            table.addCell(String.valueOf(orderedLaptopReportDTO.getCount()));
            table.addCell(String.valueOf(orderedLaptopReportDTO.getAveragePrice()));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Paragraph p = new Paragraph("Count and average price of ordered and purchased laptops");
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
