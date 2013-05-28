package adminom
import org.springframework.web.servlet.support.RequestContextUtils as RCU;
import java.text.DateFormatSymbols
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.springframework.context.i18n.LocaleContextHolder

class ReportController {
    static defaultAction = "globalView"

    def getDefinitions() {
        ProcessInstanceDetail.withCriteria {
            projections {
                distinct("processDefinitionId")
            }
        } as Set
    }
    def globalView() {
        def now = new Date()
        def messageSource = ApplicationHolder.application.mainContext.getBean('messageSource')
        String dateFormat = messageSource.getMessage("default.date.format.short",null,'dd/MM/yyyy',LocaleContextHolder.locale )
        def period = ((now-30)..now).collect { g.formatDate(format: dateFormat, date: it) }
        
        def processCountByDefinition = ProcessInstanceDetail.withCriteria {
                projections {
                    count("id")
                    groupProperty("processDefinitionId")
                    
                    between('startTime', now-30, now)
                }
            }.inject([:].withDefault{0}) { map, row -> 
                            map[row[1].find(/[^:]+/)] += row[0]
                            map
                      }
        log.debug processCountByDefinition

        def processCreationByDateAndDefinition = ProcessInstanceDetail.withCriteria {
            projections {
                    
                    groupProperty("processDefinitionId")
                    groupProperty("startDate")
                    count("id")
                    between('startTime', now-30, now)
                }
            }.inject([:].withDefault { 
                            period.collectEntries { index -> [ (index) : 0] }
                                } ) 
                { map, row -> 
                    map[row[0].find(/[^:]+/)][ g.formatDate(format: dateFormat, date: row[1])] += row[2]
                    map
                }
        processCreationByDateAndDefinition.each { log.debug  it }
        
        [ alldef: getDefinitions(), period:period, counters:processCountByDefinition, created:processCreationByDateAndDefinition ]
    }

    def detail() {
        log.debug "Detail report for ${params.id}"
        def now = new Date()
        
        def activitiesStats = Activity.withCriteria {
                processInstance {
                    eq("processDefinitionId", params.id)
                    between('startTime', now-30, now)
                }
                projections {
                    groupProperty("activityName")
                    count("id")
                    avg("duration")
                    sum("duration")
                    eq("activityType", "userTask")
                }
            }.inject([:]) { map, row -> 
                            map[row[0]] = [ count: row[1], avg: (row[2] / 1000) as int, sum:(row[3] / 1000) as int]
                            map
                      }
        log.debug activitiesStats
        def map = [alldef: getDefinitions(), counters : activitiesStats ]
        render(view: "/report/processView", model: map)
    }
}
