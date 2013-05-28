package adminom

class ProcessDefinition {

	String id;
	String key;
	String name;
	Integer version;
	String deploymentId;
	String resourceName;
	String diagramResourceName;
	String category;
	String description

    static constraints = {
    }

    static mapping = {
        datasource 'activiti'
        table 'ACT_RE_PROCDEF' 
    	id column:'ID_'
		category column:'CATEGORY_'
		name column:'NAME_'
		key column:'KEY_'
		version column:'VERSION_'
		deploymentId column:'DEPLOYMENT_ID_'
		resourceName column:'RESOURCE_NAME_'
		diagramResourceName column:'DGRM_RESOURCE_NAME_'
		description column:'DESCRIPTION_'
		version false
 	}
}
