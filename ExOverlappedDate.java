public class ExOverlappedDate extends Exception {
    public ExOverlappedDate(){super("Wrong Date. Leave start date must not be earlier than the system date ");}
    public ExOverlappedDate(String message){super(message);}
    public ExOverlappedDate(LeaveRecord l){
        super(String.format("Leave overlapped: The leave period %s to %s [%s] is found!", l.getStartDate().toString(), l.getEndDate().toString(), l.getType()));}
}
