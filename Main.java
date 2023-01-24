import java.util.*;

public class Main {

	public static void main(String[] args) throws ExInsufficientArguments{
		boolean finished = false;
		Scanner in = new Scanner(System.in);
		System.out.println("Please type: startNewDay|DD-MMM-YYYY");
		String cmdLine1 = in.nextLine();
		String[] cmdLine1Parts = cmdLine1.split("\\|");
		System.out.println("\n> "+cmdLine1);
		SystemDate.createTheInstance(cmdLine1Parts[1]);

		while(!finished){
			System.out.println("Please type one of the functions according to the templates below:\n1)  hire|employee\'s name|count of annual leaves\n2)  listEmployees\n3)  startNewDay|DD-MMM-YYYY\n4)  setupTeam|TeamName|Leader\n5)  listTeams\n6)  addTeamMember|TeamName|Employee\'s name\n7)  listRoles|Employee\'s name\n8)  listTeamMembers\n9)  takeLeave|Employee\'s name|Type of Leave(AL, BL, NL, SL)|Starting DD-MMM-YYYY|Ending DD-MMM-YYYY|Team Name(if employee is leader of it)|New Leader\'s name\n10) listLeaves\n11) undo\n12) redo\n13) exit");
			String cmdLine = in.nextLine().trim();	
			if (cmdLine.equals("")) continue;  
			System.out.println("\n> "+cmdLine);

			try{
				String[] cmdParts = cmdLine.split("\\|"); 
				if(cmdParts.length == 0)
					throw new ExInsufficientArguments();
				else if (cmdParts[0].equals("hire")){
					if(cmdParts.length < 3)
						throw new ExInsufficientArguments();
					(new CmdHire()).execute(cmdParts);
				}
				else if (cmdParts[0].equals("listEmployees")){
					(new CmdListEmployees()).execute(cmdParts);
				}
				else if (cmdParts[0].equals("startNewDay")){
					if(cmdParts.length < 2)
						throw new ExInsufficientArguments();
					(new CmdStartNewDay()).execute(cmdParts);
				}
				else if (cmdParts[0].equals("setupTeam")){
					if(cmdParts.length < 3)
						throw new ExInsufficientArguments();
					(new CmdSetupTeam()).execute(cmdParts);
				}
				else if (cmdParts[0].equals("listTeams")){
					(new CmdListTeams()).execute(cmdParts);
				}
				else if (cmdParts[0].equals("undo")){
					RecordedCommand.undoOneCommand();
				}
				else if (cmdParts[0].equals("redo")){
					RecordedCommand.redoOneCommand();
				}
				else if (cmdParts[0].equals("addTeamMember")){
					if(cmdParts.length < 3)
						throw new ExInsufficientArguments();
					(new CmdAddTeamMember()).execute(cmdParts);
				}
				else if (cmdParts[0].equals("listRoles")){
					if(cmdParts.length < 2)
						throw new ExInsufficientArguments();
					(new CmdListRoles()).execute(cmdParts);
				}
				else if (cmdParts[0].equals("listTeamMembers")){
					(new CmdListTeamMembers()).execute(cmdParts);
				}
				else if (cmdParts[0].equals("takeLeave")){
					if(cmdParts.length < 5)
						throw new ExInsufficientArguments();
					(new CmdTakeLeave()).execute(cmdParts);
				}
				else if (cmdParts[0].equals("listLeaves")){
					if(cmdParts.length > 2){
						throw new ExInsufficientArguments();
					}
					(new CmdListLeaves()).execute(cmdParts);
				}
				else if (cmdParts[0].equals("exit")){
					finished = true;
				}
				else{
					continue;
				}
			} catch(ExInsufficientArguments e){
				System.out.println(e.getMessage());
			} catch(ExTeamExists e){
				System.out.println(e.getMessage());
			} catch(ExEmployeeExists e){
				System.out.println(e.getMessage());
			} catch(ExEmployeeNotFound e){
				System.out.println(e.getMessage());
			} catch(ExTooManyAnnualLeaves e){
				System.out.println(e.getMessage());
			} catch(ExMemberAlready e){
				System.out.println(e.getMessage());
			} catch(ExTeamNotFound e){
				System.out.println(e.getMessage());
			} catch(ExInvalidDate e){
				SystemDate sd = SystemDate.getInstance();
				System.out.printf("%s (%s)!\n", e.getMessage(), (Day)sd);
			} catch(ExOverlappedDate e){
				System.out.println(e.getMessage());
			} catch(ExWrongType e){
				System.out.println(e.getMessage());
			} catch(ExInsufficientBalance e){
				System.out.println(e.getMessage());
			} catch(ExNoBlockLeave e){
				System.out.println(e.getMessage());
			} catch(ExActingHeadUnavailable e){
				System.out.println(e.getMessage());
			} catch(ExEmployeeNotFoundInTeam e){
				System.out.println(e.getMessage());
			} catch(ExMissingInput e){
				System.out.println(e.getMessage());
			} catch(ExIsActingHead e){
				System.out.println(e.getMessage());
			}
			System.out.println("Continue?\nType Y or N:");
			String reply = in.next();
			if(reply.equals("Y")){continue;}
			else{finished = true;}
		}
		in.close();
		System.exit(0);
	}
}