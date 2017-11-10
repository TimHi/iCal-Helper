package calender;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class vEvent {
    private String terminName;
    private Date startDate;
    private String startTime;
    private Date endDate;
    private String endTime;
    private String place;
    private String desc;


    vEvent(){}
    public void setTerminName(String terminName){this.terminName = terminName;}

    public void setStartDate(String stringStartDate) throws ParseException {
            //Check if string is dd.MM.yyyy HH:mm or dd.MM.yy HH:mm or
        System.out.print(stringStartDate.length() + " ");
            if(stringStartDate.length() == 16 || stringStartDate.length() == 14) {
                Date date = new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(stringStartDate);
                String dateString2 = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
                Date parsedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateString2);
                this.startDate = parsedDate;
            }else{
                Date date = new SimpleDateFormat("dd.MM.yyyy").parse(stringStartDate);
                String dateString2 = new SimpleDateFormat("yyyy-MM-dd").format(date);
                Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateString2);
                this.startDate = parsedDate;
            }
    }
    public void setStartTime(String startTime){this.startTime = startTime;}

    public void setEndDate(String duration){
       Date endDate = this.startDate;
        Long time = endDate.getTime();
        time +=(2*60*60*1000);
        endDate = new Date(time);
        this.endDate = endDate;
    }
    public void setEndTime(String endTime){this.endTime = endTime;}
    public void setPlace(String place){this.place = place;}
    public void setDesc(String desc){this.desc = desc;}

    public String getDesc() { return desc; }
    public Date getEndDate() { return endDate; }
    public String getEndTime() { return endTime; }
    public String getPlace() { return place; }
    public Date getStartDate() { return startDate; }
    public String getStartTime() { return startTime; }
    public String getTerminName() { return terminName; }

    public String eventToString(){
        String retString = getTerminName() + " - " + getStartDate();
        if(getEndDate()!= null) {
            retString = retString + " - " + getEndDate();
        }
        if(getPlace() != null) {
            retString = retString + " - " + getPlace();
        }
        if(getDesc() != null){
            retString = retString + " - " + getDesc();
        }
        return retString;
    }

    public void printElement(){
        System.out.print("\n--------------------------------\n");
        System.out.print("Name: " + getTerminName() + " am " + getStartDate());
        if(startTime != null){
            System.out.print(":"+getStartTime());
        }
        if(endDate != null){
            System.out.print(" endet am " + getEndDate());
        }
        if(endTime != null){
            System.out.print(":" + getEndTime());
        }
        if(desc != null){
            System.out.print(" Beschreibung: " + getDesc());
        }
        if(place!= null){
            System.out.print(" in " + getPlace());
        }
    }
}
