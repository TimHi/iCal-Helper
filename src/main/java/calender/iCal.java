package calender;

import biweekly.Biweekly;
import biweekly.ICalVersion;
import biweekly.ICalendar;
import biweekly.ValidationWarnings;
import biweekly.component.VEvent;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

public class iCal {

    private File csvFile;
    public String fileNameFull;
    public List<vEvent> eventList = new ArrayList<>();

    iCal(){

    }
    public void setFile(File file){this.csvFile = file;}

    List<vEvent> getEventList(){
        return this.eventList;
    }

    List <String> getEventString(){
        List <String> eventStrings = new ArrayList<>();
        for(int i = 0; i < eventList.size(); i++){
            eventStrings.add(eventList.get(i).eventToString());
        }
        return eventStrings;
    }

    public String getFileName(){
        String returnString =  fileNameFull.replace(".csv", ".ics");
        return returnString;
    }
    public void setFileName(String fileName){this.fileNameFull = fileName;}

    public void createCalenderFile(List<vEvent> eventList){
        ICalendar ical = new ICalendar();
        for(vEvent event : eventList) {
            VEvent calEvent = new VEvent();
            calEvent.setSummary(event.getTerminName());

             calEvent.setDateStart(event.getStartDate());

            if(event.getEndDate() != null) {
                calEvent.setDateEnd(event.getEndDate());
            }
            calEvent.setCreated(new Date());
            if(event.getPlace() != null){
                calEvent.setLocation(event.getPlace());
            }
            calEvent.setDescription(event.getDesc());

            ical.addEvent(calEvent);
        }
        String fileName = getFileName();
        File file = new File(fileName);
        try {
            Main.println("Creating ics File");
            ValidationWarnings warnings = ical.validate(ICalVersion.V2_0);
            Biweekly.write(ical).go(file);
            Main.println("Finished building ics file");
            String location = "File located at: " + file.getAbsolutePath();
            Main.println(location);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    List createEvents(List<String[]> calStrings) throws ParseException {

        List<vEvent> evList = new ArrayList<>();

        for(int z = 1; z < calStrings.size(); z++){
            vEvent csvEvent = new vEvent();
            if(calStrings.get(z)[0] != null && !calStrings.get(z)[0].isEmpty()){
                csvEvent.setTerminName(calStrings.get(z)[0]);
            }
            if(calStrings.get(z)[1] != null && !calStrings.get(z)[1].isEmpty()){
                csvEvent.setStartDate(calStrings.get(z)[1]);
            }
            if(calStrings.get(z)[2] != null && !calStrings.get(z)[2].isEmpty()){
                csvEvent.setEndDate(calStrings.get(z)[2]);
            }
            if(calStrings.get(z)[3] != null && !calStrings.get(z)[3].isEmpty()){
                csvEvent.setPlace(calStrings.get(z)[3]);
            }
            if(calStrings.get(z)[4] != null && !calStrings.get(z)[4].isEmpty()){
                csvEvent.setDesc(calStrings.get(z)[4]);
            }
            evList.add(csvEvent);
            }
        return evList;
    }

    void readCSV() {

        try{
            Main.println("Reading CSV");
            String filePath = csvFile.getCanonicalPath();
            CSVReader reader2 = new CSVReader(new FileReader(filePath), ',');

            String [] nextLine;
            List<String[]> calStrings = new ArrayList<>();

           while ((nextLine = reader2.readNext()) != null) {
                   calStrings.add(nextLine);
            }

           List<vEvent> eventList;

            try {
                eventList = createEvents(calStrings);
                this.eventList = eventList;
                createCalenderFile(eventList);

            } catch (ParseException e) {
                e.printStackTrace();
            }

        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

}
