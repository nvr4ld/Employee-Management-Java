public class CmdHire extends RecordedCommand  {
    private Employee e;
    private Company c;
    public void execute(String[] cmdParts) throws ExEmployeeExists, ExTooManyAnnualLeaves{
        c = Company.getInstance();
        if(c.employeeExists(cmdParts[1]))
            throw new ExEmployeeExists(); 
        if(Integer.parseInt(cmdParts[2])>300)
            throw new ExTooManyAnnualLeaves();
        e = new Employee(cmdParts[1], Integer.parseInt(cmdParts[2]));
        c.addEmployee(e);

        addUndoCommand(this);
        clearRedoList();
        
        System.out.println("Done.");
    }

    @Override
	public void undoMe()
	{
        Company.getInstance().removeEmployee(e);
		addRedoCommand(this); //<====== upon undo, we should keep a copy in the redo list (addRedoCommand is implemented in RecordedCommand.java)
	}
	
	@Override
	public void redoMe()
	{
        c.addEmployee(e);
		addUndoCommand(this); //<====== upon redo, we should keep a copy in the undo list
	}
    
}
