package adminom

class Task {
	String id
	String taskDefinitionKey
	String taskName
	String description
	String owner
	String assignee
	Date startTime
	Date dueDate
	//Boolean completed
	Date endTime
	Long duration

	static belongsTo = [ processInstance : ProcessInstanceDetail ]
    static constraints = {
    }

    static mapping = {
        datasource 'activiti'
        table 'ACT_HI_TASKINST'
        id column:'ID_'
		processDefinitionId column:'PROC_DEF_ID_'
		processInstance column:'PROC_INST_ID_'
		taskName column:'NAME_'
		description column:'DESCRIPTION_'
		owner column:'OWNER_'
		assignee column:'ASSIGNEE_'
		startTime column:'START_TIME_'
		endTime column:'END_TIME_'
		duration column:'DURATION_'
		taskDefinitionKey column:'TASK_DEF_KEY_'
		dueDate column:'DUE_DATE_'
		version false
	}

}
