<div class="form-actions">
  <input type="button"
         class="btn"
         value="${cancelText}" 
         onclick="return grailsFilterPane.hideElement('${containerId}');" />
  <input type="button"
         class="btn"
         value="${clearText}" 
         onclick="return grailsFilterPane.clearFilterPane('${formName}');" />
  <g:actionSubmit class="btn btn-primary" value="${applyText}" action="${action}" />
</div>