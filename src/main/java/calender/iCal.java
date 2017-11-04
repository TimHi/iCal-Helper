package calender;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
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
    List<vEvent> eventList = new ArrayList<>();

    iCal(File file){
        this.csvFile = file;
    }

    List createEvents(String[] lines){
        List<vEvent> evList = new ArrayList<>();
        for(int i = 0; i < lines.size();i++){
            System.out.print(lines[i]);
        }
        return evList;
    }

    void readCSV() {

        try{
            String filePath = csvFile.getCanonicalPath();
            CSVReader reader2 = new CSVReader(new FileReader(filePath), ',');

            String [] nextLine = new String[0];
            createEvents(nextLine);
            /*while ((nextLine = reader2.readNext()) != null) {
                // nextLine[] is an array of values from the line
                System.out.println(nextLine[0] + nextLine[1] + "etc...");
            }*/

        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

}
