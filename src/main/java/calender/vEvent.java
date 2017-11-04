package calender;

public class vEvent {
    private String terminName;
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    private String place;
    private String repeat;
    private String desc;
    private boolean wholeDay;

    vEvent(String tname, String sDate){
        this.terminName = tname;
        this.startDate = sDate;
    }
}
