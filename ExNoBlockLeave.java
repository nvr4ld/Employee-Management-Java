public class ExNoBlockLeave extends Exception {
    public ExNoBlockLeave(){super();}
    public ExNoBlockLeave(String msg){super(msg);}
    public ExNoBlockLeave(int leaves){
        super(String.format("The annual leave is invalid.\nThe current balance is %d days only.\nThe employee has not taken any block leave yet.\nThe employee can take at most %d days of non-block annual leave\nbecause of the need to reserve 7 days for a block leave.", leaves, leaves-7));
    }
}
