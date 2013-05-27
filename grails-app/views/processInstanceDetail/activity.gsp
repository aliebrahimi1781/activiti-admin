<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h3>${activityInstance.activityName} - ${activityInstance.id}</h3>
	</div>
	<div class="modal-body">
		<ul class="nav nav-tabs">
		<li class="active"><a href="#tab-request" data-toggle="tab">Request</a></li>
		<li><a href="#tad-response" data-toggle="tab">Response</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="tab-request"><p>Here's go the XML request message</p></div>
			<div class="tab-pane" id="tad-response"><p>Here's go the XML response message</p></div>
		</div>

	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
		<g:link class="btn btn-warning" action="show" id="${activityInstance?.id}"><g:message code="default.button.activity.retry.label"/></g:link>
		<g:link class="btn btn-danger" action="show" id="${activityInstance?.id}"><g:message code="default.button.activity.cancel.label"/></g:link>
		<g:link class="btn btn-danger" action="show" id="${activityInstance?.id}"><g:message code="default.button.activity.forceok.label"/></g:link>
	</div>
</div>