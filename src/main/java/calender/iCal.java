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
    List<vEvent> eventList = new ArrayList<>();

    iCal(File file){
        this.csvFile = file;
    }

    public void createCalenderFile(List<vEvent> eventList){
        ICalendar ical = new ICalendar();
        for(vEvent event : eventList) {
            VEvent calEvent = new VEvent();
            calEvent.setSummary(event.getTerminName());
            //Check if Event is over the whole day
            if(event.getWholeDay() != null) {
                calEvent.setDateStart(event.getStartDate(), false);
            }else{
                calEvent.setDateStart(event.getStartDate());
            }
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
        File file = new File("meeting.ics");
        try {
            ValidationWarnings warnings = ical.validate(ICalVersion.V2_0);
            System.out.println(warnings.toString());
            Biweekly.write(ical).go(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    List createEvents(List<String[]> calStrings) throws ParseException {
        List<vEvent> evList = new ArrayList<>();

        for(int z = 1; z < calStrings.size(); z++){
            vEvent csvEvent = new vEvent();
            //TODO find more efficiant way to do this
            if(calStrings.get(z)[0] != null && !calStrings.get(z)[0].isEmpty()){
                csvEvent.setTerminName(calStrings.get(z)[0]);
            }
            //Time before date because we check for time before we set date
            if(calStrings.get(z)[2] != null && !calStrings.get(z)[2].isEmpty()){
                csvEvent.setStartTime(calStrings.get(z)[2]);
            }
            if(calStrings.get(z)[1] != null && !calStrings.get(z)[1].isEmpty()){
                    csvEvent.setStartDate(calStrings.get(z)[1]);
            }
            if(calStrings.get(z)[4] != null && !calStrings.get(z)[4].isEmpty()){
                csvEvent.setEndTime(calStrings.get(z)[4]);
            }
            if(calStrings.get(z)[3] != null && !calStrings.get(z)[3].isEmpty()){
                csvEvent.setEndDate(calStrings.get(z)[3]);
            }
            if(calStrings.get(z)[5] != null && !calStrings.get(z)[5].isEmpty()){
                csvEvent.setPlace(calStrings.get(z)[5]);
            }
            if(calStrings.get(z)[6] != null && !calStrings.get(z)[6].isEmpty()){
                csvEvent.setRepeat(calStrings.get(z)[6]);
            }
            if(calStrings.get(z)[7] != null && !calStrings.get(z)[7].isEmpty()){
                csvEvent.setDesc(calStrings.get(z)[7]);
            }
            evList.add(csvEvent);
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

            try {
                eventList = createEvents(calStrings);
                createCalenderFile(eventList);
            } catch (ParseException e) {
                e.printStackTrace();
            }

           /*
           for(int i = 0; i < eventList.size(); i++){
               eventList.get(i).printElement();
           }

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
