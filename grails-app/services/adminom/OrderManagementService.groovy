package adminom
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.ContentType
import groovyx.net.http.Method
import groovyx.net.http.RESTClient
import groovy.json.JsonSlurper

import org.codehaus.groovy.grails.commons.ApplicationHolder

class OrderManagementService {
    def grailsApplication = ApplicationHolder.application
    def processDefinitionCache = [:]
    def sdf = new java.text.SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ssz", Locale.FRANCE)
    def omClient = new HTTPBuilder( grailsApplication.getConfig().order.management.json.host )
    
    def callOM(serviceUrl, query = [:]) {
        log.debug "URL omClient : ${grailsApplication.getConfig().order.management.json.URL}"
        log.debug "callOM ${serviceUrl}"
        log.debug "basic auth ${grailsApplication.getConfig().order.management.json.user}, ${grailsApplication.getConfig().order.management.json.password}"
        def ret = ""
        //omClient.auth.basic grailsApplication.getConfig().order.management.json.user, grailsApplication.getConfig().order.management.json.password
        omClient.headers[ 'Authorization' ] = "Basic " + "${grailsApplication.getConfig().order.management.json.user}:${grailsApplication.getConfig().order.management.json.password}".getBytes('iso-8859-1').encodeBase64()
    
        omClient.request(Method.GET, ContentType.JSON) { 
                uri.path = grailsApplication.getConfig().order.management.json.service + serviceUrl
                uri.query = query
               
                response.success = { resp, json ->
                    ret = json.toString()
                }
                response.failure = { resp ->
                    log.warn "Unexpected error: ${resp.statusLine.statusCode} : ${resp.statusLine.reasonPhrase}"
                    log.debug omClient.uri
                }
            }
        log.debug "ret = ${ret}"
        return ret
        
    }

    def callOMraw(serviceUrl) {
        log.debug "URL omClientraw : ${grailsApplication.getConfig().order.management.json.URL}"
        log.debug "callOMraw ${serviceUrl}"
        log.debug "basic auth ${grailsApplication.getConfig().order.management.json.user}, ${grailsApplication.getConfig().order.management.json.password}"
        def ret = ""
        //omClient.auth.basic grailsApplication.getConfig().order.management.json.user, grailsApplication.getConfig().order.management.json.password
        //omClient.headers[ 'Authorization' ] = "Basic " + "${grailsApplication.getConfig().order.management.json.user}:${grailsApplication.getConfig().order.management.json.password}".getBytes('iso-8859-1').encodeBase64()
    
        omClient.request(Method.GET, ContentType.ANY) { 
                uri.path = grailsApplication.getConfig().order.management.json.service + serviceUrl
                
                response.success = { resp, bin ->
                    ret = bin.getBytes()
                }
                response.failure = { resp ->
                    log.warn "Unexpected error: ${resp.statusLine.statusCode} : ${resp.statusLine.reasonPhrase}"
                    log.debug omClient.uri
                }
            }
        return ret
        
    }

    def getProcessDefinitions() {
        def jx = callOM("/process-definitions", [size:Integer.MAX_VALUE-1])
        if (jx == null || jx == "") return null
        def dalist = new JsonSlurper().parseText( jx )
        log.debug "result /process-definitions sluper : ${dalist}"

        def defs = [:]
        for (i in dalist.data) {
            def pi = new ProcessDefinition(i)
            pi.setId(i.id)
            defs[i.id] = pi
        }

        log.debug "getProcessDefinitions - result : ${defs}"
        return defs
    }

    def getProcessDefinition(id) {
        def definition = processDefinitionCache[id]
        if (definition == null) {
            processDefinitionCache = getProcessDefinitions()
            definition = processDefinitionCache[id]
        }
        return definition
    }

    def getProcessInstances(start=0, max=10) {
        log.debug("getProcessInstances ${start}, ${max}")
        def jx = callOM("/process-instances", [start:start, size:max])
        if (jx == null || jx == "") return null

        def dalist = new JsonSlurper().parseText( jx )
        log.debug "result /process-instances sluper : ${dalist}"
        def processes = []
        for (i in dalist.data) {

            def pi = new ProcessInstance(
                            businessKey : i.businessKey,
                            processDefinitionId : i.processDefinitionId,
                            startTime : sdf.parse(i.startTime)
                            )
            pi.setId(i.id)
            processes.add(pi)
        }
        def instances = [process : processes, total : dalist.total, start : dalist.start, size : dalist.size]
        log.debug "getProcessInstances - result : ${instances}"
        return instances
    }

    def getProcessInstanceDetail(processInstanceId){
        log.debug("getProcessInstanceDetail ${processInstanceId}")
        def jx = callOM("/process-instance/${processInstanceId}")
        if (jx == null || jx == "") return null

        def dalist = new JsonSlurper().parseText( jx )
        log.debug "result /process-instance sluper : ${dalist}"
        def pi = new ProcessInstanceDetail(
                        processDefinitionId : dalist.processDefinitionId,
                        businessKey : dalist.businessKey,
                        startTime : sdf.parse(dalist.startTime),
                        startActivityId : dalist.startActivityId,
                        startUserId : dalist.startUserId,
                        completed : dalist.completed,
                        endTime : dalist.endTime,
                        endActivityId : dalist.endActivityId,
                        duration : dalist.duration
                        )
        pi.setId(dalist.processInstanceId)
        pi.tasks = dalist.tasks.collect { new Task(it) }
        pi.activities = dalist.activities.collect { new Activity(it) }
        pi.variables = dalist.variables.collect {new Variable(it) }
        pi.historyVariables = dalist.historyVariables.collect { new HistoryVariable(it) }

        log.debug "getProcessInstanceDetail - result : ${pi}"
        return pi
    }

    def getProcessInstancesDetail(start=0, max=10) {
        log.debug("getProcessInstancesDetail ${start}, ${max}")
        def listInstances = getProcessInstances(start, max)
        if (!listInstances) return null
        def process = listInstances.process.collect { getProcessInstanceDetail(it.id) }
        def instancesDetail =  [process : process, total : listInstances.total, start : listInstances.start, size : listInstances.size]
        log.debug "getProcessInstanceDetail - result : ${instancesDetail}"
        return instancesDetail
    }

    def getProcessInstanceDiagram(processInstanceId) {
        log.debug("getProcessInstanceDiagram ${processInstanceId}")
        def jx = callOMraw("/diagram/${processInstanceId}")
        if (jx == null || jx == "") return null 
        else return jx
    }

     def getProcessDefinitionDiagram(processInstanceId) {
        log.debug("getProcessDefinitionDiagram ${processInstanceId}")
        def jx = callOMraw("/process-definition/${processInstanceId}/diagram")
        if (jx == null || jx == "") return null 
        else return jx
    }

    def getOrderDetail(processInstanceId) {
        log.debug("getOrderDetail ${processInstanceId}")
        def jx = callOM("/details/${processInstanceId}")
        if (jx == null || jx == "") return null

        def dalist = new JsonSlurper().parseText( jx ).order
        log.debug "result /detail sluper : ${dalist}"
        return dalist
    }


}
