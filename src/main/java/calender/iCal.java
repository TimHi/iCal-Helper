package calender;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.io.IOException;
import java.net.URISyntaxException;

public class iCal {

    //private String csvFile;
    private File csvFile;

    iCal(File file){
        this.csvFile = file;
    }
    void readCSV() {

        //Default delimiter is ,


        try{
        String filePath = csvFile.getCanonicalPath();
        CSVReader reader = new CSVReader(new FileReader(filePath));
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            // nextLine[] is an array of values from the line
            System.out.println(nextLine[0] + nextLine[1] + "etc...");
        }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }

        try{
            String filePath = csvFile.getCanonicalPath();
            CSVReader reader2 = new CSVReader(new FileReader(filePath), ';');

            String [] nextLine;
            while ((nextLine = reader2.readNext()) != null) {
                // nextLine[] is an array of values from the line
                System.out.println(nextLine[0] + nextLine[1] + "etc...");
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

}
