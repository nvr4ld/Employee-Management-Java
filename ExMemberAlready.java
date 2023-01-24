public class ExMemberAlready extends Exception {
    public ExMemberAlready(){super("The employee has joined the team already!");}
    public ExMemberAlready(String msg){super(msg);}
}
