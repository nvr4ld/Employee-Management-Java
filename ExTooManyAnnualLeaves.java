public class ExTooManyAnnualLeaves extends Exception {
    public ExTooManyAnnualLeaves(){super("Out of range (Entitled Annual Leaves should be within 0-300)!");}
    public ExTooManyAnnualLeaves(String msg){super(msg);}
}
