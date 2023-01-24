public class LeaveRecord_BL extends LeaveRecord{

    @Override
    public String toString() {
        return String.format("%s to %s [BL]", super.startDate, super.endDate);
    }

    @Override
    public boolean wrongType() throws ExWrongType {
        if(Day.countDiff(super.startDate, super.endDate)<7){
            throw new ExWrongType("To apply annual leave for 6 days or less, you should use the normal Annual Leave (AL) type.");
        }

        return false;
    }

    public LeaveRecord_BL(String day1, String day2){
        super(day1, day2);
    }

    @Override
    public String getType() {
        return "BL";
    }
    
}
