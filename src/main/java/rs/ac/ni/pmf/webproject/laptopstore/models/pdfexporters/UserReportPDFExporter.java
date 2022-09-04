package rs.ac.ni.pmf.webproject.laptopstore.models.pdfexporters;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import rs.ac.ni.pmf.webproject.laptopstore.models.Role;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.UserReportDTO;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class UserReportPDFExporter {
    private List<UserReportDTO> userReportDTOs;

    public UserReportPDFExporter(List<UserReportDTO> userReportDTOs) {
        this.userReportDTOs = userReportDTOs;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.GRAY);
        cell.setPadding(5);

        cell.setPhrase(new Phrase("Role"));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Count"));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (UserReportDTO userReportDTO : userReportDTOs) {
            table.addCell(Role.getCodeByValue(userReportDTO.getRoleId()));
            table.addCell(String.valueOf(userReportDTO.getCount()));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Paragraph p = new Paragraph("Count of users by role");
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.5f, 3.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();
    }
}
