package adminom

class ProcessInstanceDetail {
	String id
    String processDefinitionId 
    String businessKey 
    Date startTime
    String startActivityId
    String startUserId
    String status
    Date endTime
    String endActivityId
    Long duration
    String parentProcess

    Date startDate

    static hasMany = [ 
        tasks: Task, 
        activities: Activity,
        variables: Variable,
        historyVariables: HistoryVariable
    ]
    
    static constraints = {
    }

    static mapping = {
        datasource 'activiti'
        table 'VIEW_HI_PROCINST'
        id column:'ID_'
        processInstanceId column:'PROC_INST_ID_'
        businessKey column:'BUSINESS_KEY_'
        processDefinitionId column:'PROC_DEF_ID_'
        startTime column:'START_TIME_'
        endTime column:'END_TIME_'
        duration column:'DURATION_'
        startUserId column:'START_USER_ID_'
        startActivityId column:'START_ACT_ID_'
        endActivityId column:'END_ACT_ID_'
        parentProcess column:'SUPER_PROCESS_INSTANCE_ID_'
        status column:'STATUS'
        startDate formula: "trunc(START_TIME_)"

        version false
    }
}
