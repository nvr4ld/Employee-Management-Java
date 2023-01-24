public class LeaveRecord_AL extends LeaveRecord{

    @Override
    public boolean wrongType() throws ExWrongType {

        if(Day.countDiff(super.startDate, super.endDate)>6){
            throw new ExWrongType("To apply annual leave for 7 days or more, please use the Block Leave (BL) type.");
        }

        return false;
    }

    public LeaveRecord_AL(String day1, String day2){
        super(day1, day2);
    }

    @Override
    public String toString(){
        return String.format("%s to %s [AL]", super.startDate, super.endDate);
    }

    @Override
    public String getType() {
        return "AL";
    }
    
}