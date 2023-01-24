public class ExIsActingHead extends Exception{
    public ExIsActingHead(){super();}
    public ExIsActingHead(String msg){super(msg);}
    public ExIsActingHead(ActingHead ah){
        super(String.format("Cannot take leave.  %s is the acting head of %s during %s!", ah.getActingHead().getName(), ah.getTeam().getName(), ah.getRecord().toStringv2()));
    }
}
