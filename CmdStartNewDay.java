public class CmdStartNewDay extends RecordedCommand{
    private String prevDate;
    private String newDate;
    public void execute(String[] cmdParts){
        SystemDate s = SystemDate.getInstance();
        prevDate = s.toString();
        newDate = cmdParts[1];
        SystemDate.createTheInstance(cmdParts[1]);

        addUndoCommand(this);
        clearRedoList();

        System.out.println("Done.");
    }

    @Override
    public void undoMe(){
        SystemDate.createTheInstance(prevDate);
        addRedoCommand(this);
    }

    @Override
    public void redoMe(){
        SystemDate.createTheInstance(newDate);
        addUndoCommand(this);
    }
}