public class ActingHead implements Comparable<ActingHead>{
    private Employee actingHead;
    private LeaveRecord leave;
    private Team team;

    public ActingHead(Employee e, LeaveRecord l, Team t){
        this.actingHead = e;
        this.leave = l;
        this.team = t;
    }

    @Override
    public String toString(){
        return leave.toStringv2() + ": " + actingHead.getName();
    }

    public Employee getActingHead(){
        return actingHead;
    }

    public LeaveRecord getRecord(){
        return leave;
    }

    public Team getTeam(){
        return team;
    }

    @Override
    public int compareTo(ActingHead another){
        return this.leave.compareTo(another.leave);
    }
}
