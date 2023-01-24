import java.util.ArrayList;

public class CmdTakeLeave extends RecordedCommand {
    private LeaveRecord l;
    private Employee e;
    private ArrayList <ActingHead> actingheads = new ArrayList<>();
    @Override
    public void execute(String[] cmdparts) throws ExTeamExists, ExEmployeeExists, ExEmployeeNotFound,
            ExTooManyAnnualLeaves, ExMemberAlready, ExTeamNotFound, ExInvalidDate, ExOverlappedDate, ExWrongType, ExInsufficientBalance, 
            ExNoBlockLeave, ExInsufficientArguments, ExActingHeadUnavailable, ExEmployeeNotFoundInTeam, ExMissingInput, ExIsActingHead {

        Company c = Company.getInstance();
        this.e = c.searchEmployee(cmdparts[1]);

        if(e == null){
            throw new ExEmployeeNotFound();
        }

        if(c.isAvailable(e, cmdparts[3], cmdparts[4]) != null){
            throw new ExIsActingHead(c.isAvailable(e, cmdparts[3], cmdparts[4]));
        }

        int cntHead = c.isHead(e);
        if(cmdparts.length < 5+2*cntHead){
            throw new ExMissingInput(c.missingTeam(cmdparts));
        }

        SystemDate sd = SystemDate.getInstance();
        Day d = new Day(cmdparts[3]);

        if(d.compareTo((Day)sd) == -1){
            throw new ExInvalidDate();
        }
        if(e.checkOverlap(cmdparts[3], cmdparts[4]) != null){
            throw new ExOverlappedDate(e.checkOverlap(cmdparts[3], cmdparts[4]));
        }
        
        if(cmdparts[2].equals("AL")){
            this.l = new LeaveRecord_AL(cmdparts[3], cmdparts[4]);
            int diff = l.getDiff();

            if(l.wrongType()){
                throw new ExWrongType("To apply annual leave for 7 days or more, please use the Block Leave (BL) type.");
            }

            if(e.getAnnualLeaves()<diff)
                throw new ExInsufficientBalance(e.getAnnualLeaves(), "annual");

            if(!e.hasBlockLeaves() && e.getAnnualLeaves() - diff < 7){
                throw new ExNoBlockLeave(e.getAnnualLeaves());
            }
        }
        else if(cmdparts[2].equals("BL")){
            this.l = new LeaveRecord_BL(cmdparts[3], cmdparts[4]);
            int diff = l.getDiff();

            if(l.wrongType()){
                throw new ExWrongType("To apply annual leave for 6 days or less, you should use the normal Annual Leave (AL) type.");
            }

            if(e.getAnnualLeaves()<diff)
                throw new ExInsufficientBalance(e.getAnnualLeaves(), "annual");
        }
        else if(cmdparts[2].equals("SL")){
            this.l = new LeaveRecord_SL(cmdparts[3], cmdparts[4]);
            int diff = l.getDiff();

            if(e.getSickLeaves()<diff)
                throw new ExInsufficientBalance(e.getSickLeaves(), "sick");
        }
        else{
            this.l = new LeaveRecord_NL(cmdparts[3], cmdparts[4]);
        }

        int counter = 0;
        while(counter != cntHead){
            Team t = c.searchTeam(cmdparts[5+2*counter]);
            Employee e2 = c.searchEmployee(cmdparts[6+2*counter]);
            if(t == null){
                throw new ExTeamNotFound();
            }
            else if(e2 == null || !t.memberOfTeam(e2)){
                throw new ExEmployeeNotFoundInTeam(cmdparts[5+2*counter], cmdparts[6+2*counter]);
            }
            else{
                if(e2.isAvailable(l)!=null)
                    throw new ExActingHeadUnavailable(cmdparts[6+2*counter], e2.isAvailable(l));
                ActingHead ah = new ActingHead(e2, l, t);
                this.actingheads.add(ah);
            }
            counter++;
        }

        for(ActingHead ah: actingheads){
            ah.getTeam().addActingHead(ah);
        }

        e.takeLeave(l);
        
        addUndoCommand(this);
        clearRedoList();

        System.out.println("Done.");
    }
    
    @Override
    public void undoMe() {
        e.removeLeave(l);
        for(ActingHead ah: actingheads){
            ah.getTeam().removeActingHead(ah);
        }
        addRedoCommand(this);
    }

    
    @Override
    public void redoMe() {
        e.takeLeave(l);
        for(ActingHead ah: actingheads){
            ah.getTeam().addActingHead(ah);
        }
        addUndoCommand(this);
    }

    
    
}
