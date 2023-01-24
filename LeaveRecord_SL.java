public class LeaveRecord_SL extends LeaveRecord {

    @Override
    public String toString() {
        return String.format("%s to %s [SL]", super.startDate, super.endDate);
    }

    @Override
    public boolean wrongType() throws ExWrongType {
        // TODO Auto-generated method stub
        return false;
    }

    public LeaveRecord_SL(String day1, String day2){
        super(day1, day2);
    }

    @Override
    public String getType() {
        return "SL";
    }
    
}
