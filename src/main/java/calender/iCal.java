package calender;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;
import org.apache.commons.lang3.ObjectUtils;

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

    List createEvents(List<String[]> calStrings){
        List<vEvent> evList = new ArrayList<>();

        for(int z = 1; z < calStrings.size(); z++){
            vEvent event = new vEvent();
            //TODO find more efficiant way to do this
            if(calStrings.get(z)[0] != null){
                event.setTerminName(calStrings.get(z)[0]);
            }
            if(calStrings.get(z)[1] != null){
                event.setStartDate(calStrings.get(z)[1]);
            }
            if(calStrings.get(z)[2] != null){
                event.setStartTime(calStrings.get(z)[2]);
            }
            if(calStrings.get(z)[3] != null){
                event.setEndDate(calStrings.get(z)[3]);
            }
            if(calStrings.get(z)[4] != null){
                event.setEndTime(calStrings.get(z)[4]);
            }
            if(calStrings.get(z)[5] != null){
                event.setPlace(calStrings.get(z)[5]);
            }
            if(calStrings.get(z)[6] != null){
                event.setRepeat(calStrings.get(z)[6]);
            }
            if(calStrings.get(z)[7] != null){
                event.setDesc(calStrings.get(z)[7]);
            }
            if(calStrings.get(z)[8] != null){
                event.setWholeDay(calStrings.get(z)[8]);
            }
            evList.add(event);
            }
        return evList;
    }

    void readCSV() {

        try{
            String filePath = csvFile.getCanonicalPath();
            CSVReader reader2 = new CSVReader(new FileReader(filePath), ',');

            String [] nextLine;
            List<String[]> calStrings = new ArrayList<>();
           while ((nextLine = reader2.readNext()) != null) {
                   calStrings.add(nextLine);
            }

           List<vEvent> eventList;
           eventList = createEvents(calStrings);
           for(int i = 0; i < eventList.size(); i++){
               eventList.get(i).printElement();
           }
    /*
    for(int z = 0; z < calStrings.size(); z++){
        for(int y = 0; y < calStrings.get(z).length; y++) {
            System.out.print(calStrings.get(z)[y] + " ");
               }
               System.out.print("\n");
    }
    */
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

}
