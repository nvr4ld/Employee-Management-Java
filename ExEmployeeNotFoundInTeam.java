public class ExEmployeeNotFoundInTeam extends Exception {
    public ExEmployeeNotFoundInTeam(){super();}
    public ExEmployeeNotFoundInTeam(String msg){super(msg);}
    public ExEmployeeNotFoundInTeam(String teamName, String employeeName){
        super(String.format("Employee (%s) not found for %s!", employeeName, teamName));
    }
}
