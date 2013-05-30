package adminom

class ProcessInstance {

	String id
    String processDefinitionId 
    String businessKey 
    Date startTime
    String status
    Date endTime
    Long duration
    String parentProcess
    Order order
    Date startDate

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
        parentProcess column:'SUPER_PROCESS_INSTANCE_ID_'
        status column:'STATUS'
        order column:'ORDER_ID'
        startDate formula: "trunc(START_TIME_)"

        version false
    }
}
