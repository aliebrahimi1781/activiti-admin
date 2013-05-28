package adminom

class HistoryVariable {
	String id
	String variableName
	String variableValue
	String variableType
	Integer revision

    static belongsTo = [ processInstance : ProcessInstanceDetail ]

    static constraints = {
    }

    static mapping = {
        datasource 'activiti'
        table 'ACT_HI_VARINST'
        id column:'ID_'
        variableName column:'NAME_'
        variableValue column:'TEXT_'
        variableType  column:'VAR_TYPE_'
        revision column:'REV_'
        processInstance column:'PROC_INST_ID_'
        version false
    }
}
