
<%@ page import="com.bsk.dpp13.State" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'state.label', default: 'State')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-state" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-state" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list state">
			
				<g:if test="${stateInstance?.emailId}">
				<li class="fieldcontain">
					<span id="emailId-label" class="property-label"><g:message code="state.emailId.label" default="Email Id" /></span>
					
						<span class="property-value" aria-labelledby="emailId-label"><g:fieldValue bean="${stateInstance}" field="emailId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${stateInstance?.stateName}">
				<li class="fieldcontain">
					<span id="stateName-label" class="property-label"><g:message code="state.stateName.label" default="State Name" /></span>
					
						<span class="property-value" aria-labelledby="stateName-label"><g:fieldValue bean="${stateInstance}" field="stateName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${stateInstance?.stateLevelSewadhariName}">
				<li class="fieldcontain">
					<span id="stateLevelSewadhariName-label" class="property-label"><g:message code="state.stateLevelSewadhariName.label" default="State Level Sewadhari Name" /></span>
					
						<span class="property-value" aria-labelledby="stateLevelSewadhariName-label"><g:fieldValue bean="${stateInstance}" field="stateLevelSewadhariName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${stateInstance?.telephoneNumber}">
				<li class="fieldcontain">
					<span id="telephoneNumber-label" class="property-label"><g:message code="state.telephoneNumber.label" default="Telephone Number" /></span>
					
						<span class="property-value" aria-labelledby="telephoneNumber-label"><g:fieldValue bean="${stateInstance}" field="telephoneNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${stateInstance?.samiti}">
				<li class="fieldcontain">
					<span id="samiti-label" class="property-label"><g:message code="state.samiti.label" default="Samiti" /></span>
					
						<g:each in="${stateInstance.samiti}" var="s">
						<span class="property-value" aria-labelledby="samiti-label"><g:link controller="samiti" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${stateInstance?.id}" />
					<g:link class="edit" action="edit" id="${stateInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
