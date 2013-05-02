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
		omClient.headers[ 'Authorization' ] = "Basic " + "${grailsApplication.getConfig().order.management.json.user}:${grailsApplication.getConfig().order.management.json.password}".getBytes('iso-8859-1').encodeBase64()
	
		omClient.request(Method.GET, ContentType.BINARY) { 
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
		/* def jx="""
		{ 
		  "data": [ 
		    { 
		      "id": "financialReport:1", 
		      "key": "financialReport", 
		      "version": 1, 
		      "name": "Monthly financial report", 
		      "resourceName": "org/activiti/examples/bpmn/usertask/FinancialReportProcess.bpmn20.xml", 
		      "diagramResourceName": "org/activiti/examples/bpmn/usertask/FinancialReportProcess.png", 
		      "deploymentId": "10", 
		      "startFormResourceKey": null, 
		      "isGraphicNotationDefined": true                  
		    } 
		  ], 
		  "total": 1, 
		  "start": 0, 
		  "sort": "id", 
		  "order": "asc", 
		  "size": 1 
		}
		""" */
		def jx = callOM("/process-definitions", [size:Integer.MAX_VALUE-1])
		if (jx == null || jx == "") return null
		def dalist = new JsonSlurper().parseText( jx )
		def defs = [:]
		for (i in dalist.data) {
			def pi = new ProcessDefinition(i)
			pi.setId(i.id)
			defs[i.id] = pi
		}
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
		/* def jx="""
		{ 
		    "data": [ 
		      { 
		        "id": "2", 
		        "processDefinitionId": "financialReport:1", 
		        "businessKey": "55", 
		        "startTime": "2010-10-13T14:54:26.750+02:00", 
		        "startUserId": "kermit" 
		      }, 
		      { 
		        "id": "12", 
		        "processDefinitionId": "financialReport:1", 
		        "businessKey": "67", 
		        "startTime": "2013-04-13T21:18:10.450+02:00", 
		        "startUserId": "kermit" 
		      } 
		    ], 
		    "total": 2, 
		    "start": 0, 
		    "sort": "id", 
		    "order": "asc", 
		    "size": 2 
		  }
		""" */
		log.debug("getProcessInstances ${start}, ${max}")
		def jx = callOM("/process-instances", [start:start, size:max])
		if (jx == null || jx == "") return null

		def dalist = new JsonSlurper().parseText( jx )
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
		return [process : processes, total : dalist.total, start : dalist.start, size : dalist.size]
    }

    def getProcessInstanceDetail(processInstanceId){
		/* def jx="""
		{ 
		    "id": "2", 
		    "processDefinitionId": "financialReport:1", 
		    "businessKey": "55", 
		    "startTime": "2010-10-13T14:54:26.750+02:00", 
		    "startActivityId": "startFinancialAnalysis", 
		    "startUserId": "kermit", 
		    "completed": false, 
		    "tasks": [ 
		      { 
		        "taskId": "3", 
		        "taskName": "Analyze report", 
		        "owner": null, 
		        "assignee": "Kermit", 
		        "startTime": "2010-10-13T14:53:26.750+02:00", 
		        "completed": false 
		      } 
		    ], 
		    "activities": [ 
		      { 
		        "activityId": "4", 
		        "activityName": "Get report", 
		        "activityType": "ServiceTask", 
		        "startTime": "2010-10-13T14:53:25.750+02:00", 
		        "completed": true, 
		        "startTime": "2010-10-13T14:53:25.950+02:00", 
		        "duration": 200 
		      } 
		    ], 
		    "variables": [ 
		      { 
		        "variableName": "reportName", 
		        "variableValue": "classified.pdf", 
		      } 
		    ],
		    "historyVariables": [ 
		      { 
		        "variableName": "reportName", 
		        "variableValue": "classified.pdf", 
		        "variableType": "String", 
		        "revision": 1, 
		        "time": "2010-10-13T14:53:26.750+02:00" 
		      } 
		    ] 
		     
		  }
		"""
		*/
		def jx = callOM("/process-instance/${processInstanceId}")
		if (jx == null || jx == "") return null

		def dalist = new JsonSlurper().parseText( jx )
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

		return pi
    }

    def getProcessInstancesDetail(start=0, max=10) {
    	def listInstances = getProcessInstances(start, max)
    	if (!listInstances) return null
    	def process = listInstances.process.collect { getProcessInstanceDetail(it.id) }
    	return [process : process, total : listInstances.total, start : listInstances.start, size : listInstances.size]
    }

    def getProcessInstanceDiagram(processInstanceId) {
    	def jx = callOMraw("/process-instance/${processInstanceId}/diagram")
		if (jx == null || jx == "") return null 
		else return jx
    }

     def getProcessDefinitionDiagram(processInstanceId) {
    	def jx = callOMraw("/process-definition/${processInstanceId}/diagram")
		if (jx == null || jx == "") return null 
		else return jx
    }
}
