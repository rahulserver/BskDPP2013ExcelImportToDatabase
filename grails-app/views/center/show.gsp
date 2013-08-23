
<%@ page import="com.bsk.dpp13.Center" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'center.label', default: 'Center')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-center" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-center" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list center">
			
				<g:if test="${centerInstance?.emailId}">
				<li class="fieldcontain">
					<span id="emailId-label" class="property-label"><g:message code="center.emailId.label" default="Email Id" /></span>
					
						<span class="property-value" aria-labelledby="emailId-label"><g:fieldValue bean="${centerInstance}" field="emailId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${centerInstance?.centerName}">
				<li class="fieldcontain">
					<span id="centerName-label" class="property-label"><g:message code="center.centerName.label" default="Center Name" /></span>
					
						<span class="property-value" aria-labelledby="centerName-label"><g:fieldValue bean="${centerInstance}" field="centerName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${centerInstance?.centerLevelSewadhariName}">
				<li class="fieldcontain">
					<span id="centerLevelSewadhariName-label" class="property-label"><g:message code="center.centerLevelSewadhariName.label" default="Center Level Sewadhari Name" /></span>
					
						<span class="property-value" aria-labelledby="centerLevelSewadhariName-label"><g:fieldValue bean="${centerInstance}" field="centerLevelSewadhariName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${centerInstance?.centerTelephoneNumber}">
				<li class="fieldcontain">
					<span id="centerTelephoneNumber-label" class="property-label"><g:message code="center.centerTelephoneNumber.label" default="Center Telephone Number" /></span>
					
						<span class="property-value" aria-labelledby="centerTelephoneNumber-label"><g:fieldValue bean="${centerInstance}" field="centerTelephoneNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${centerInstance?.state}">
				<li class="fieldcontain">
					<span id="state-label" class="property-label"><g:message code="center.state.label" default="State" /></span>
					
						<span class="property-value" aria-labelledby="state-label"><g:link controller="state" action="show" id="${centerInstance?.state?.id}">${centerInstance?.state.stateName?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${centerInstance?.id}" />
					<g:link class="edit" action="edit" id="${centerInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
