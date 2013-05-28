package adminom

class Activity {
	String id
	String activityName
	String activityType
	Date startTime
	Date endTime
	Long duration

	static belongsTo = [ processInstance : ProcessInstanceDetail ]
    static constraints = {
    }

    static mapping = {
        datasource 'activiti'
        table 'ACT_HI_ACTINST'
        id column:'ID_'
		activityName column:'ACT_NAME_'
		activityType column:'ACT_TYPE_'
		assignee column:'ASSIGNEE_'
		startTime column:'START_TIME_'
		endTime column:'END_TIME_'
		duration column:'DURATION_'
		processInstance column : 'PROC_INST_ID_'
		version false
    }
}
