package adminom

class Variable {
	String id
	String name
    String type
	String value
    Date time

    static belongsTo = [ processInstance : ProcessInstanceDetail ]

    static constraints = {
    }

    static mapping = {
        table 'ACT_HI_DETAIL'
        id column:'ID_'
        name column:'NAME_'
        value column:'TEXT_'
        type column:'VAR_TYPE_'
        time column:'TIME_'
        processInstance column:'PROC_INST_ID_'
        version false
    }
}
