
<%@ page import="com.bsk.dpp13.Samiti" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'samiti.label', default: 'Samiti')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-samiti" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-samiti" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list samiti">
			
				<g:if test="${samitiInstance?.samitiName}">
				<li class="fieldcontain">
					<span id="samitiName-label" class="property-label"><g:message code="samiti.samitiName.label" default="Samiti Name" /></span>
					
						<span class="property-value" aria-labelledby="samitiName-label"><g:fieldValue bean="${samitiInstance}" field="samitiName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${samitiInstance?.report}">
				<li class="fieldcontain">
					<span id="report-label" class="property-label"><g:message code="samiti.report.label" default="Report" /></span>
					
						<g:each in="${samitiInstance.report}" var="r">
						<span class="property-value" aria-labelledby="report-label"><g:link controller="report" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${samitiInstance?.id}" />
					<g:link class="edit" action="edit" id="${samitiInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
