import java.util.ArrayList;
import java.util.Collections;

public class Team implements Comparable<Team> {
    private String teamName;
	private Employee head;
	private Day dateSetup;
    private ArrayList<Employee> members = new ArrayList<>();
    private ArrayList<ActingHead> actingHeads = new ArrayList<>();

    public Team(String n, Employee hd) {
        this.teamName = n;
        this.head = hd;
        members.add(hd);
        this.dateSetup = SystemDate.getInstance().clone();//Set all object fields (Read lab sheet!)
    }

    public static void list(ArrayList<Team> list) {
        //Learn: "-" means left-aligned
        System.out.printf("%-30s%-10s%-13s\n", "Team Name", "Leader", "Setup Date");
        for (Team t : list)
            System.out.printf("%-30s%-10s%-13s\n", t.teamName, t.head.getName(), t.dateSetup); //display t.teamName, etc..
    }

    public static void listMembers(ArrayList<Team> list){
        for(Team t: list){
            System.out.printf("%s:\n", t.teamName);
            for(Employee e: t.members){
                if(e == t.head){
                    System.out.println(e.getName() + " (Head of Team)");
                }
                else{
                    System.out.println(e.getName());
                }
                
            }
            if(t.actingHeads.size()!=0){
                System.out.println("Acting heads: ");
                for(ActingHead ah: t.actingHeads){
                    System.out.println(ah);
                }
            }
            System.out.println();
        }        
    }

    @Override
    public int compareTo(Team another)  {
        return this.teamName.compareTo(another.teamName);
    }

    public String getName(){
        return teamName;
    }
    public void addMember(Employee e){
        members.add(e);
        Collections.sort(members);
    }
    public void removeMember(Employee e){
        members.remove(e);
    }
    public boolean memberOfTeam(Employee e){
        for(Employee member: members){
            if(member == e){
                return true;
            }
        }
        return false;
    }

    public boolean headOfTeam(Employee e){
        if(e == this.head){
            return true;
        }
        return false;
    }

    public void addActingHead(ActingHead ah){
        actingHeads.add(ah);
        Collections.sort(actingHeads);
    }

    public void removeActingHead(ActingHead ah){
        actingHeads.remove(ah);
    }

    public ActingHead checkAvailability(Employee e, String s1, String s2){
        Day d1 = new Day(s1);
        Day d2 = new Day(s2);
        for(ActingHead ah: actingHeads){
            if(ah.getActingHead() == e && ah.getRecord().checkOverlap(d1, d2)){
                return ah;
            }
        }
        return null;
    }

}   
