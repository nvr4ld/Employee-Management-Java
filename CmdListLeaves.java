public class CmdListLeaves implements Command {

    @Override
    public void execute(String[] cmdparts) throws ExTeamExists, ExEmployeeExists, ExEmployeeNotFound,
            ExTooManyAnnualLeaves, ExMemberAlready, ExTeamNotFound {
        if(cmdparts.length == 2){
            Employee e = Company.getInstance().searchEmployee(cmdparts[1]);
            e.listLeaves();
        }
        else{
            Company.getInstance().listLeaves();
        }
        
        
    }
    
}
