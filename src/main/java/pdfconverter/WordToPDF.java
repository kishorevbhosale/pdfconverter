package pdfconverter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class WordToPDF {
    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        String ext = FilenameUtils.getExtension("C:\\Users\\SB\\Documents\\files\\test.docx");
        StringBuilder output = new StringBuilder();
        if ("docx".equalsIgnoreCase(ext)) {
            output = readDocxFile("C:\\Users\\SB\\Documents\\files\\test.docx");
        } else if ("doc".equalsIgnoreCase(ext)) {
            output = readDocFile("C:\\Users\\SB\\Documents\\files\\test.doc");
        } else {
            System.out.println("INVALID FILE TYPE. ONLY .doc and .docx are permitted.");
        }
        writePdfFile(output);
    }
    public static StringBuilder readDocFile(String fileName) {
        StringBuilder output = new StringBuilder();
        File file = new File(fileName);
        try(FileInputStream fis = new FileInputStream(file.getAbsolutePath())) {
            HWPFDocument doc = new HWPFDocument(fis);
            WordExtractor we = new WordExtractor(doc);
            String[] paragraphs = we.getParagraphText();
            for (String para : paragraphs) {
                output.append("\n" + para + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    public static StringBuilder readDocxFile(String fileName) {
        StringBuilder output = new StringBuilder();
        File file = new File(fileName);
        try(FileInputStream fis = new FileInputStream(file.getAbsolutePath())) {
            XWPFDocument document = new XWPFDocument(fis);
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            for (XWPFParagraph para : paragraphs) {
                output.append("\n" + para.getText() + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    public static void writePdfFile(StringBuilder output) throws FileNotFoundException, DocumentException {
        File file = new File("C:\\Users\\SB\\Documents\\files\\itext-test.pdf");
        FileOutputStream fileout = new FileOutputStream(file);
        Document document = new Document();
        PdfWriter.getInstance(document, fileout);
        document.addAuthor("kb");
        document.addTitle("My Converted PDF");
        document.open();
        String[] splitter = output.toString().split("\\n");
        for (int i = 0; i < splitter.length; i++) {
            Chunk chunk = new Chunk(splitter[i]);
            Font font = new Font();
            chunk.setFont(font);
            document.add(chunk);
            Paragraph paragraph = new Paragraph();
            paragraph.add("");
            document.add(paragraph);
        }
        document.close();
    }

}
