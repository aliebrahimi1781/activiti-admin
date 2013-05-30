<!DOCTYPE HTML>
<%@ page import="org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes" %>

<html lang="en">
	<head>
		<meta charset="utf-8">
		<title><g:layoutTitle default="${message(code: 'default.application.name')}"/></title>
		<meta name="description" content="">
		<meta name="author" content="">

		<meta name="viewport" content="initial-scale = 1.0">

		<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
		<!--[if lt IE 9]>
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->

		<r:require modules="scaffolding"/>

		<!-- Le fav and touch icons -->
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		
		<g:layoutHead/>
		<r:require module="application"/>
		<r:layoutResources/>
	</head>

	<body>

		<nav class="navbar navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container-fluid">
					
					<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</a>
					
					<a class="brand" href="${createLink(uri: '/')}"><g:message code="default.application.name"/></a>

					<div class="nav-collapse">
						<ul class="nav">							
							<li><a href="${createLink(uri: '/')}"><g:message code="default.nav.home"/></a></li>
							<li><g:link controller="ProcessInstance" action="index"><g:message code="default.nav.monitoring"/></g:link></li>
							<li><g:link controller="Report" action="globalView"><g:message code="default.nav.reporting"/></g:link></li>
							<li><a href="${createLink(uri: '/')}"><g:message code="default.nav.administration"/></a></li>
						</ul>
					</div>
				</div>
			</div>
		</nav>

		<div class="container-fluid">
			<g:layoutBody/>

			<hr>

			<footer>
				<div class="container">
					<p>Copyright &copy; Capgemini 2012. All Rights Reserved</p>
				</div>
			</footer>
		</div>

		<r:layoutResources/>

	</body>
</html>
