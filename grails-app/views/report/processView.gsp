<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap"/>
		<g:set var="pageName" value="${message(code: 'report.detailed.label', default: 'Global Report')}" />
		<title><g:message code="default.show.label" args="[pageName]" /></title>
		<r:require module="jqplot" />
	</head>
	<body>
		<div class="page-header">
			<h1><g:message code="default.show.label" args="[pageName]" /></h1>
		</div>
		<div class="row-fluid">
			<aside id="application-status" class="span3">
				<div class="well sidebar-nav">
					<h5><g:link action="globalView" controller="Report"><g:message code="report.global.label"/></g:link></h5>
					<h5><g:message code="report.detailed.label"/></h5>
					<ul>
						<g:each in = "${alldef}">
						<li><g:link action="detail" controller="Report" id="${it}">${it}</g:link></li>
						</g:each>
					</ul>
					
				</div>
			</aside>

			<div class="span9">
				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<div class="row-fluid">
					<div class="row">
						<h2><g:message code="report.detailed.counters.label"/>"</h2>
						<div class="span9" id='chart1' style='height:400px;width:500px'>
						</div>
						<r:script>
							var plot1 = jQuery.jqplot ('chart1', 
								[[
								<g:each in="${counters}" var="c">
									[ "${c.key}", ${c.value.sum} ],
								</g:each>
								]],
							    {
							      seriesDefaults: {
							        renderer: jQuery.jqplot.PieRenderer,
							        rendererOptions: {
							          showDataLabels: true
							        }
							      },
							      legend: { show:true, location: 'e' }
							    }
							  );
						</r:script>
					</div>
					<div class="row">
						<h2><g:message code="report.details.activities.label"/></h2>
						<table class="table table-bordered">
                    		<thead>
                        		<tr>
                        			<g:sortableColumn property="activityName" title="${message(code: 'activity.activityName.label', default: 'Activity Name')}" />
                        			<th>Avg execution Time</th>
                        			<th>Number of executions</th>
                        		</tr>
                        	</thead>
                        	<tbody>
                        		<g:each in="${counters}" var="c">
									<tr>
										<td>${c.key}</td>
										<td>${c.value.avg}</td>
										<td>${c.value.count}</td>
									</tr>
								</g:each>
                			</tbody>
                		</table>
						
					</div>
				</div>
			</div>

		</div>
	</body>
	</html>

