public class CmdAddTeamMember extends RecordedCommand{
    private Team t;
    private Employee e;
    public void execute(String[] cmdParts) throws ExMemberAlready, ExEmployeeNotFound, ExTeamNotFound{
        Company c = Company.getInstance();
        this.t = c.searchTeam(cmdParts[1]);
        this.e = c.searchEmployee(cmdParts[2]);
        if(e == null){
            throw new ExEmployeeNotFound();
        }
        if(t == null){
            throw new ExTeamNotFound();
        }
        if(t.memberOfTeam(e)){
            throw new ExMemberAlready();
        }
        t.addMember(e);

        addUndoCommand(this);
        clearRedoList();

        System.out.println("Done.");
    }

    @Override
    public void undoMe(){
        t.removeMember(e);
        addRedoCommand(this);
    }

    @Override
    public void redoMe(){
        t.addMember(e);
        addUndoCommand(this);
    }
}
