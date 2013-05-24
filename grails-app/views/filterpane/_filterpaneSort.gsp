<div>
	<g:message code="fp.tag.filterPane.sort.orderByText" default="Order by" />

	<g:if test="${sortValueMessagePrefix == null}">
	<!-- g:if test="${g.message(code:sortValueMessagePrefix, default:'false') == 'false'}"-->
		<g:select name="sort" 
				from="${sortedProperties}" 
				keys="${sortKeys}" 
				optionValue="filterPaneFieldName" 
				value="${sortValue}"
				noSelection="${noSelection}" 
				class = "selectpicker"/>
	</g:if>
	<g:else>
		<g:select name="sort"
				from="${sortedProperties}"
				keys="${sortKeys}"
				valueMessagePrefix="${sortValueMessagePrefix}"
				noSelection="${noSelection}"
				value="${sortValue}" 
				class = "selectpicker"/>
	</g:else>
	&nbsp;
	<g:radio name="order" value="asc" checked="${orderAsc}" />
	&nbsp;<g:message code="fp.tag.filterPane.sort.ascending" default="Ascending" />
	<g:radio name="order" value="desc" checked="${orderDesc}" />
	&nbsp;<g:message code="fp.tag.filterPane.sort.descending" default="Descending" />
</div>
