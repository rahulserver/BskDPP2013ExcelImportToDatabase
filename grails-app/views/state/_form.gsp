<%@ page import="com.bsk.dpp13.State" %>



<div class="fieldcontain ${hasErrors(bean: stateInstance, field: 'emailId', 'error')} ">
	<label for="emailId">
		<g:message code="state.emailId.label" default="Email Id" />
		
	</label>
	<g:field type="email" name="emailId" value="${stateInstance?.emailId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stateInstance, field: 'stateName', 'error')} ">
	<label for="stateName">
		<g:message code="state.stateName.label" default="State Name" />
		
	</label>
	<g:textField name="stateName" value="${stateInstance?.stateName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stateInstance, field: 'stateLevelSewadhariName', 'error')} ">
	<label for="stateLevelSewadhariName">
		<g:message code="state.stateLevelSewadhariName.label" default="State Level Sewadhari Name" />
		
	</label>
	<g:textField name="stateLevelSewadhariName" value="${stateInstance?.stateLevelSewadhariName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stateInstance, field: 'telephoneNumber', 'error')} ">
	<label for="telephoneNumber">
		<g:message code="state.telephoneNumber.label" default="Telephone Number" />
		
	</label>
	<g:field name="telephoneNumber" type="number" value="${stateInstance.telephoneNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stateInstance, field: 'samiti', 'error')} ">
	<label for="samiti">
		<g:message code="state.samiti.label" default="Samiti" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${stateInstance?.samiti?}" var="s">
    <li><g:link controller="samiti" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="samiti" action="create" params="['state.id': stateInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'samiti.label', default: 'Samiti')])}</g:link>
</li>
</ul>

</div>

