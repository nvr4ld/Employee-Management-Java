public class ExInsufficientBalance extends Exception{
    public ExInsufficientBalance(){super();}
    public ExInsufficientBalance(String msg){super(msg);}
    public ExInsufficientBalance(int n, String s){super(String.format("Insufficient balance of %s leaves. %d days left only!", s, n));}
}
