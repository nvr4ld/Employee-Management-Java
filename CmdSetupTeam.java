public class CmdSetupTeam extends RecordedCommand{
    private Team t;
    public void execute(String[] cmdParts) throws ExTeamExists, ExEmployeeNotFound{
        Company c = Company.getInstance();
        if(!c.employeeExists(cmdParts[2])){
            throw new ExEmployeeNotFound();
        }
        if(c.teamExists(cmdParts[1])){
            throw new ExTeamExists();
        }

        t = c.createTeam(cmdParts[1], cmdParts[2]);
        

        addUndoCommand(this);
        clearRedoList();

        System.out.println("Done.");
    }

    @Override
    public void undoMe(){
        Company.getInstance().removeTeam(t);
        addRedoCommand(this);
    }

    @Override
    public void redoMe(){
        Company.getInstance().addTeam(t);
        addUndoCommand(this);
    }

}
