public class CmdListEmployees implements Command {
    
    public void execute(String[] cmdParts){
        Company c = Company.getInstance();
        c.listEmployees();
    }

}
