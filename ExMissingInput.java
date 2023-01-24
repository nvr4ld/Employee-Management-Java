public class ExMissingInput extends Exception {
    public ExMissingInput(){super();}
    public ExMissingInput(String msg){super(msg);}
    public ExMissingInput(Team t){super(String.format("Missing input:  Please give the name of the acting head for %s", t.getName()));}
}
