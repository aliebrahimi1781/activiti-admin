package adminom

class ProcessInstance {

	String id;
  	String businessKey;
  	String processDefinitionId;
  	Date startTime;
  	String startUserId;
    
    static constraints = {
    }

	static mapping = {
        datasource 'activiti'
        table 'ACT_HI_PROCINST'
        id column:'ID_'
        processInstanceId column:'PROC_INST_ID_'
        businessKey column:'BUSINESS_KEY_'
        processDefinitionId column:'PROC_DEF_ID_'
        startTime column:'START_TIME_'
        startUserId column:'START_USER_ID_'
        version false
    }
}
