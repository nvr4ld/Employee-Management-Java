public class CmdListTeamMembers implements Command {

    public void execute(String[] cmdparts)
            throws ExTeamExists, ExEmployeeExists, ExEmployeeNotFound, ExTooManyAnnualLeaves {
        Company c = Company.getInstance();
        c.listTeamMembers();
        
    }
    
}
