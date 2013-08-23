<%@ page import="com.bsk.dpp13.Center" %>



<div class="fieldcontain ${hasErrors(bean: centerInstance, field: 'emailId', 'error')} ">
	<label for="emailId">
		<g:message code="center.emailId.label" default="Email Id" />
		
	</label>
	<g:field type="email" name="emailId" value="${centerInstance?.emailId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: centerInstance, field: 'centerName', 'error')} ">
	<label for="centerName">
		<g:message code="center.centerName.label" default="Center Name" />
		
	</label>
	<g:textField name="centerName" value="${centerInstance?.centerName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: centerInstance, field: 'centerLevelSewadhariName', 'error')} ">
	<label for="centerLevelSewadhariName">
		<g:message code="center.centerLevelSewadhariName.label" default="Center Level Sewadhari Name" />
		
	</label>
	<g:textField name="centerLevelSewadhariName" value="${centerInstance?.centerLevelSewadhariName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: centerInstance, field: 'centerTelephoneNumber', 'error')} ">
	<label for="centerTelephoneNumber">
		<g:message code="center.centerTelephoneNumber.label" default="Center Telephone Number" />
		
	</label>
	<g:field name="centerTelephoneNumber" type="number" value="${centerInstance.centerTelephoneNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: centerInstance, field: 'state', 'error')} required">
	<label for="state">
		<g:message code="center.state.label" default="State" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="state" name="state.id" from="${com.bsk.dpp13.State.list()}" optionKey="id" optionValue="stateName" required="" value="${centerInstance?.state?.id}" class="many-to-one"/>
</div>

