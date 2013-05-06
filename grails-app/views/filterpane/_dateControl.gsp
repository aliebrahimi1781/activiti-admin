    <%=bootstrap.datePicker(ctrlAttrs)%>
    <g:if test="${ctrlAttrs.name?.endsWith('To')}">
      <input type="hidden"
             name="filter.${ctrlAttrs.domain}.${ctrlAttrs.propertyName}_isDayPrecision"
             value="${ctrlAttrs.isDayPrecision}"/>
    </g:if>
