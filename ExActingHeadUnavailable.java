public class ExActingHeadUnavailable extends Exception{
    public ExActingHeadUnavailable(){super();}
    public ExActingHeadUnavailable(String msg){super(msg);}
    public ExActingHeadUnavailable(String employeeName, LeaveRecord l){
        super(String.format("%s is on leave during %s!", employeeName, l.toString()));
    }
}