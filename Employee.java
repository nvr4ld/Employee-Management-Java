import java.util.ArrayList;
import java.util.Collections;

public class Employee implements Comparable<Employee> {
    private String name;
    private int annualLeaves;
    private int sickLeaves;
    private int blockLeaves;
    private ArrayList<LeaveRecord> leaves = new ArrayList<>();

    public Employee(String n, int yle){
        this.name = n;
        this.annualLeaves = yle;
        this.sickLeaves = 135;
        this.blockLeaves = 0;
    }

    public String getName(){
        return name;
    }

    public int getAnnualLeaves(){
        return annualLeaves;
    }

    public int getSickLeaves(){
        return sickLeaves;
    }

    public static Employee searchEmployee(ArrayList<Employee> list, String name){
        for(Employee e:list){
            if(e.getName().equals(name)){
                return e;
            }
        }
        return null;
    }

    @Override
    public int compareTo(Employee another)  {
        return this.name.compareTo(another.name);
    }

    public void takeLeave(LeaveRecord l){
        if(l instanceof LeaveRecord_AL || l instanceof LeaveRecord_BL){
            annualLeaves -= l.getDiff();
            if(l instanceof LeaveRecord_BL){
                this.blockLeaves++;
            }
        }
        else if(l instanceof LeaveRecord_SL){
            sickLeaves -= l.getDiff();
        }
        leaves.add(l);
        Collections.sort(leaves);
    }

    public void listLeaves(){
        if(leaves.size() != 0){
            for(LeaveRecord l:leaves){
                System.out.println(l);
            }
        }
        else{
            System.out.println("No leave record");
        }
        
    }

    public void removeLeave(LeaveRecord l){
        if(l instanceof LeaveRecord_AL || l instanceof LeaveRecord_BL){
            annualLeaves += l.getDiff();
            if(l instanceof LeaveRecord_BL){
                this.blockLeaves--;
            }
        }
        else if(l instanceof LeaveRecord_SL){
            sickLeaves += l.getDiff();
        }
        leaves.remove(l);
    }

    public LeaveRecord checkOverlap(String s1, String s2){
        Day d1 = new Day(s1);
        Day d2 = new Day(s2);
        for(LeaveRecord l:leaves){
            if(l.checkOverlap(d1, d2))
                return l;
        }
        return null;
    }

    public boolean hasBlockLeaves(){
        if(this.blockLeaves == 0){
            return false;
        }
        return true;
    }

    public LeaveRecord isAvailable(LeaveRecord l){
        for(LeaveRecord leave:leaves){
            if(l.checkOverlap(leave.getStartDate(), leave.getEndDate()))
                return leave;
        }
        return null;
    }

}
