public class ExInvalidDate extends Exception{
    public ExInvalidDate(){super("Wrong Date. Leave start date must not be earlier than the system date ");}
    public ExInvalidDate(String message){super(message);}
}