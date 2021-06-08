package pdfconverter;


import com.aspose.words.Document;
import com.aspose.words.PdfImageCompression;
import com.aspose.words.PdfSaveOptions;
import com.aspose.words.PdfTextCompression;

public class WordToPdfUsingAspose {
    public static void main(String[] args) throws Exception {
        Document doc = new Document("C:\\Users\\SB\\Documents\\files\\B-902.docx");
        PdfSaveOptions options = new PdfSaveOptions();
// Text and image compression
        options.setTextCompression(PdfTextCompression.FLATE);
        options.setImageCompression(PdfImageCompression.AUTO);
        doc.save("C:\\Users\\SB\\Documents\\files\\output.pdf");
    }
}
