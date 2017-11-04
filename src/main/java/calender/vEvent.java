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
    private String wholeDay;

    vEvent(){}
    public void setTerminName(String terminName){this.terminName = terminName;}
    public void setStartDate(String startDate){this.startDate = startDate;}
    public void setStartTime(String startTime){this.startTime = startTime;}
    public void setEndDate(String endDate){this.endDate = endDate;}
    public void setEndTime(String endTime){this.endTime = endTime;}
    public void setPlace(String place){this.place = place;}
    public void setRepeat(String repeat){this.repeat = repeat;}
    public void setDesc(String desc){this.desc = desc;}
    public void setWholeDay(String wholeDay){this.wholeDay = wholeDay;}

    public String getDesc() { return desc; }
    public String getEndDate() { return endDate; }
    public String getEndTime() { return endTime; }
    public String getPlace() { return place; }
    public String getRepeat() { return repeat; }
    public String getStartDate() { return startDate; }
    public String getStartTime() { return startTime; }
    public String getTerminName() { return terminName; }
    public String getWholeDay() { return wholeDay; }

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
        if(wholeDay != null){
            System.out.print(" ganztägig " + getWholeDay());
        }
        if(repeat != null){
            System.out.print(" Wiederholt sich: " + getRepeat());
        }

    }
}
