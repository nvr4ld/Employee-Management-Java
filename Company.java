import java.util.ArrayList;
import java.util.Collections; //Provides sorting
public class Company {
    

    private ArrayList<Employee> allEmployees;
    private ArrayList<Team> allTeams;

    private static Company instance = new Company() ;

    private Company() {
        allEmployees = new ArrayList<>();
        allTeams = new ArrayList<>();
    }

    public static Company getInstance() {
        return instance;
    }

    public void listTeams() {
        Team.list(allTeams);
    }

    public void listTeamMembers(){
        Team.listMembers(allTeams);
    }

    public void listEmployees(){
        for(Employee e:allEmployees){
            System.out.printf("%s (Entitled Annual Leaves: %d days)\n", e.getName(), e.getAnnualLeaves());
        }
    }

    public Employee createEmployee(String n, int a ) // See how it is called in main()
    {
        Employee e = new Employee(n, a);
        allEmployees.add(e);
        Collections.sort( allEmployees ); //allEmployees
        return e;
    }

    public Team createTeam(String n, String hd) // See how it is called in main()
    {
        Employee e = Employee.searchEmployee(allEmployees, hd);
        Team t = new Team(n, e);
        allTeams.add(t);
        Collections.sort(allTeams); //allTeams
        return t; //Why return?  Ans: Later you'll find it useful for undoable comments.
    }

    public void removeTeam(Team t){
        allTeams.remove(t);
    }

    public void removeEmployee(Employee e){
        allEmployees.remove(e);
    }

    public void addEmployee(Employee e){
        allEmployees.add(e);
        Collections.sort( allEmployees ); //allEmployees
    }
    
    public void addTeam(Team t){
        allTeams.add(t);
        Collections.sort(allTeams);
    }
    public boolean teamExists(String name){
        for(Team t:allTeams){
            if(t.getName().equals(name))
                return true;
        }
        return false;
    }
    public boolean employeeExists(String name){
        for(Employee e:allEmployees){
            if(name.equals(e.getName()))
                return true;
        }
        return false;
    }
    public Team searchTeam(String name){
        for(Team t:allTeams){
            if(t.getName().equals(name))
                return t;
        }
        return null;
    }
    public Employee searchEmployee(String name){
        return Employee.searchEmployee(allEmployees, name);
    }
    public void listRoles(Employee e){
        boolean isMember = false;
        for(Team t: allTeams){
            if(t.memberOfTeam(e)){
                isMember = true;
                if(t.headOfTeam(e)){
                    System.out.println(t.getName() + " (Head of Team)");
                }
                else{
                    System.out.println(t.getName());
                }
            }
        }
        if(!isMember){
            System.out.println("No role");
        }
    }

    public void listLeaves(){
        for(Employee e: allEmployees){
            System.out.println(e.getName() + ":");
            e.listLeaves();
        }
    }

    public int isHead(Employee e){
        int count = 0;
        for(Team t: allTeams){
            if(t.headOfTeam(e)){
                count++;
            }
        }
        return count;
    }

    public Team missingTeam(String[] cmdparts){
        ArrayList<Team> headOfTeams = new ArrayList<>();
        for(Team t: allTeams){
            if(t.headOfTeam(this.searchEmployee(cmdparts[1]))){
                headOfTeams.add(t);
            }
        }
        int len = 5;
        while(cmdparts.length>len){
            Team temp;
            temp = this.searchTeam(cmdparts[len]);
            headOfTeams.remove(temp);
            len += 2;
        }
        return headOfTeams.get(0);        
       
    }

    public ActingHead isAvailable(Employee e, String startDate, String endDate){
        for(Team t: allTeams){
            if(t.checkAvailability(e, startDate, endDate) != null){
                return t.checkAvailability(e, startDate, endDate);
            }
        }
        return null;
    }
}
