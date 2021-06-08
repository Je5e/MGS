package helper;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;

public class ConverterToFileImp implements IConverterFile {
    @Override
    public void convertToPDF(String fileName) {
        try {
            Document document = new Document(fileName);
            document.save("report.pdf", SaveFormat.PDF);
            System.out.println("Pdf file was generated.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
