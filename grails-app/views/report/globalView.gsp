<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap"/>
		<g:set var="pageName" value="${message(code: 'report.global.label', default: 'Global Report')}" />
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
					<h5><g:message code="report.global.label"/></h5>
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
						<h2><g:message code="report.global.counters.label"/>"</h2>
						<div class="span9" id='chart1' style='height:400px;width:500px'>
						</div>
						<r:script>
							var plot1 = jQuery.jqplot ('chart1', 
								[[
								<g:each in="${counters}" var="c">
									[ "${c.key}", ${c.value} ],
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
						<h2><g:message code="report.global.creations.label"/>"</h2>
						<div class="span9" id='chart2' style='height:400px;width:700px'>
						</div>
						<r:script>
							var plot2 = $.jqplot('chart2', 
								[ <g:each in="${created}" var="p">
									[ <g:each in="${p.value}" var="val">${val.value},</g:each>],
								  </g:each>
								], {
								stackSeries: true,
						        seriesDefaults:{
						            renderer:$.jqplot.BarRenderer,
						            rendererOptions: {fillToZero: true}
						        },
						        series:[
						        	<g:each in="${created}" var="p">
						        	{label:'${p.key}'},
						        	</g:each>
						        ],
						        legend: {
						            show: true,
						            //placement: 'outsideGrid'
						        },
						        axes: {
						            xaxis: {
						                renderer: $.jqplot.CategoryAxisRenderer,
						                ticks: [ <g:each status="i" in="${period}" var="p">'${(i % 5) == 0 ? p : ''}',</g:each>]
						            },
						            yaxis: {
										padMin: 0,
						                tickOptions: {formatString: '%d'}
						            }
						        }
					    	});
						</r:script>
					</div>
				</div>
			</div>

		</div>
	</body>
	</html>

