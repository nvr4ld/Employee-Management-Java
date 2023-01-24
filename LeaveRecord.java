public abstract class LeaveRecord implements Comparable<LeaveRecord>{
    protected Day startDate;
    protected Day endDate;

    LeaveRecord(String day1, String day2){
        startDate = new Day(day1);
        endDate = new Day(day2);
    }

    public Day getStartDate(){
        return startDate;
    }
    
    public Day getEndDate(){
        return endDate;
    }

    @Override
    public abstract String toString();

    public String toStringv2(){
        return String.format("%s to %s", startDate, endDate);
    }

    public abstract String getType();

    @Override
    public int compareTo(LeaveRecord another){
        return this.startDate.compareTo(another.startDate);
    }

    public boolean checkOverlap(Day d1, Day d2){
        Day d3 = this.startDate.clone();
        Day d4 = this.endDate.clone();
        while(d3.compareTo(d4)!=0){
            if(d3.compareTo(d1) == 0 || d3.compareTo(d2) == 0){
                return true;
            }
            d3.advance();
        }
        Day d5 = d1.clone();
        while(d5.compareTo(d2) != 0){
            if(d5.compareTo(d3) == 0 || d5.compareTo(d4) == 0){
                return true;
            }
            d5.advance();
        }

        return false;
    }
    public abstract boolean wrongType() throws ExWrongType;

    public int getDiff(){
        return Day.countDiff(startDate, endDate);
    }
}
