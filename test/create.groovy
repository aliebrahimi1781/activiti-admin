@Grab(group='org.codehaus.groovy.modules.http-builder',
        module='http-builder', version='0.6' )
import groovyx.net.http.*
import static groovyx.net.http.Method.POST
import static groovyx.net.http.ContentType.*
@Grab(group='log4j',
        module='log4j', version='1.2.17' )
import org.apache.log4j.*

Logger log = Logger.getLogger("")
LogManager.rootLogger.setLevel(Level.DEBUG)

log.info "Creation du HTTPBuilder"
def http = new HTTPBuilder( "http://localhost:8080/capactiviti-webapp/rest/process/start")
http.contentType = TEXT
http.headers = [ Accept : 'application/xml', ]
log.info "HTTPBuilder : ${http}"
http.request( POST, XML ) { req ->
    body = """
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<order>
  <orderId>18</orderId>
  <clientId>14-544-258</clientId>
  <products>
    <action>creation</action>
    <id>5</id>
    <name>FIXE</name>
  </products>
  <products>
    <action>migration</action>
    <id>6</id>
    <name>ADSL</name>
    <settings>
      <name>param1</name>
      <value>value1</value>
    </settings>
  </products>

  <products>
    <action>creation</action>
    <id>6</id>
    <name>AUTRE</name>
    <settings>
      <name>param1</name>
      <value>value1</value>
    </settings>
  </products>
</order>
"""

    response.success = { resp, data ->
        log.info data
            }
}
