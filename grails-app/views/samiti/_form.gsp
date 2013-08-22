<%@ page import="com.bsk.dpp13.Samiti" %>



<div class="fieldcontain ${hasErrors(bean: samitiInstance, field: 'samitiName', 'error')} ">
	<label for="samitiName">
		<g:message code="samiti.samitiName.label" default="Samiti Name" />
		
	</label>
	<g:textField name="samitiName" value="${samitiInstance?.samitiName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: samitiInstance, field: 'emailId', 'error')} ">
	<label for="emailId">
		<g:message code="samiti.emailId.label" default="Email Id" />
		
	</label>
	<g:field type="email" name="emailId" value="${samitiInstance?.emailId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: samitiInstance, field: 'report', 'error')} ">
	<label for="report">
		<g:message code="samiti.report.label" default="Report" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${samitiInstance?.report?}" var="r">
    <li><g:link controller="report" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="report" action="create" params="['samiti.id': samitiInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'report.label', default: 'Report')])}</g:link>
</li>
</ul>

</div>

