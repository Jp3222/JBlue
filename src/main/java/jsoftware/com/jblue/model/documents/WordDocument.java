/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.documents;

import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author juanp
 */
public class WordDocument extends Document implements DocumentModel {

    public WordDocument(DocumentsBuilder builder) {
        super(builder);
    }

    @Override
    public boolean export() {
        return true;
    }

    public void crearPdf(String ruta) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {

                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(25, 750);
                contentStream.showText("PDF generado con Apache PDFBox.");
                contentStream.newLine(); // Salto de línea
                contentStream.endText();
            }

            document.save(ruta);
        }
    }

}
