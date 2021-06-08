package pdfconverter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class ImageToPDF {
    public static void main(String[] args) throws IOException, DocumentException {
        generatePDFFromImage();
    }

    private static void generatePDFFromImage() throws IOException, DocumentException {
        Document document = new Document();
        String input = "C:\\Users\\SB\\Documents\\files\\kb.jpg";
        String output = "C:\\Users\\SB\\Documents\\files\\kb_image.pdf";
        FileOutputStream fos = new FileOutputStream(output);

        PdfWriter writer = PdfWriter.getInstance(document, fos);
        writer.open();
        document.open();
        document.add(Image.getInstance(input));
        document.close();
        writer.close();
    }

}
