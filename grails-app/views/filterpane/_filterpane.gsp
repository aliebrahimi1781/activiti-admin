<div id="${fp.containerId}"
     class="filterpane${fp.containerIsDialog} ${fp.containerClass}"
     style="${fp.containerVisibleStyle}${fp.containerStyle}">
  <g:if test="${fp.showTitle}">
    <h2>${fp.title}</h2>
  </g:if>
  <g:set var="renderForm" value="${fp.customForm == false}"/>
  <g:if test="${renderForm}">
    <form name="${fp.formName}" id="${fp.formName}" method="post" action="${createLink(action: fp.formAction)}" class="form-horizontal">
  </g:if>
<%-- Do we still need this hidden prop? --%>
  <input type="hidden" name="filterProperties" value="${fp.filterProperties}"/>
  <input type="hidden" name="listDistinct" value="${fp.listDistinct}"/>
  <input type="hidden" name="uniqueCountColumn" value="${fp.uniqueCountColumn}"/>


  <g:each in="${fp.properties}" var="propMap">
    <div class="control-group">
      <g:render template="/filterpane/filterpaneProperty" model="${propMap}"/>
    </div>
  </g:each>

  <g:if test="${fp.showSortPanel == true}">

    <div class="control-group">
      <g:render template="/filterpane/filterpaneSort" model="${fp.sortModel}"/>
    </div>

  </g:if>
  <g:else>
    <input type="hidden" name="sort" value="${params.sort}"/>
    <input type="hidden" name="order" value="${params.order}"/>
  </g:else>

  <g:if test="${fp.showButtons == true}">
    <g:render template="/filterpane/filterpaneButtons" model="${fp.buttonModel}" />
  </g:if>

  <g:if test="${renderForm}">
    </form>
  </g:if>
</div>
