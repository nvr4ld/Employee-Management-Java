public class CmdListRoles implements Command {

    public void execute(String[] cmdParts) throws ExEmployeeNotFound{
        Company c = Company.getInstance();
        Employee e = c.searchEmployee(cmdParts[1]);
        if(e == null)
            throw new ExEmployeeNotFound();
        c.listRoles(e);
    }

}
