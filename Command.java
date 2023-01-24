interface Command {
    void execute(String[] cmdparts) throws ExTeamExists, ExEmployeeExists, ExEmployeeNotFound, ExTooManyAnnualLeaves, ExMemberAlready, ExTeamNotFound, ExInvalidDate, ExOverlappedDate, ExWrongType, ExInsufficientBalance, ExNoBlockLeave, ExInsufficientArguments, ExActingHeadUnavailable, ExEmployeeNotFoundInTeam, ExMissingInput, ExIsActingHead;   
}
