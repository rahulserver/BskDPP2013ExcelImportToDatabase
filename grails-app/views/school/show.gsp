
<%@ page import="com.bsk.dpp13.School" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'school.label', default: 'School')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-school" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-school" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list school">
			
				<g:if test="${schoolInstance?.cl3}">
				<li class="fieldcontain">
					<span id="cl3-label" class="property-label"><g:message code="school.cl3.label" default="Cl3" /></span>
					
						<span class="property-value" aria-labelledby="cl3-label"><g:fieldValue bean="${schoolInstance}" field="cl3"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${schoolInstance?.cl4}">
				<li class="fieldcontain">
					<span id="cl4-label" class="property-label"><g:message code="school.cl4.label" default="Cl4" /></span>
					
						<span class="property-value" aria-labelledby="cl4-label"><g:fieldValue bean="${schoolInstance}" field="cl4"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${schoolInstance?.cl5}">
				<li class="fieldcontain">
					<span id="cl5-label" class="property-label"><g:message code="school.cl5.label" default="Cl5" /></span>
					
						<span class="property-value" aria-labelledby="cl5-label"><g:fieldValue bean="${schoolInstance}" field="cl5"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${schoolInstance?.cl6}">
				<li class="fieldcontain">
					<span id="cl6-label" class="property-label"><g:message code="school.cl6.label" default="Cl6" /></span>
					
						<span class="property-value" aria-labelledby="cl6-label"><g:fieldValue bean="${schoolInstance}" field="cl6"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${schoolInstance?.cl7}">
				<li class="fieldcontain">
					<span id="cl7-label" class="property-label"><g:message code="school.cl7.label" default="Cl7" /></span>
					
						<span class="property-value" aria-labelledby="cl7-label"><g:fieldValue bean="${schoolInstance}" field="cl7"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${schoolInstance?.cl8}">
				<li class="fieldcontain">
					<span id="cl8-label" class="property-label"><g:message code="school.cl8.label" default="Cl8" /></span>
					
						<span class="property-value" aria-labelledby="cl8-label"><g:fieldValue bean="${schoolInstance}" field="cl8"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${schoolInstance?.cl9}">
				<li class="fieldcontain">
					<span id="cl9-label" class="property-label"><g:message code="school.cl9.label" default="Cl9" /></span>
					
						<span class="property-value" aria-labelledby="cl9-label"><g:fieldValue bean="${schoolInstance}" field="cl9"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${schoolInstance?.cl10}">
				<li class="fieldcontain">
					<span id="cl10-label" class="property-label"><g:message code="school.cl10.label" default="Cl10" /></span>
					
						<span class="property-value" aria-labelledby="cl10-label"><g:fieldValue bean="${schoolInstance}" field="cl10"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${schoolInstance?.cl11}">
				<li class="fieldcontain">
					<span id="cl11-label" class="property-label"><g:message code="school.cl11.label" default="Cl11" /></span>
					
						<span class="property-value" aria-labelledby="cl11-label"><g:fieldValue bean="${schoolInstance}" field="cl11"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${schoolInstance?.cl12}">
				<li class="fieldcontain">
					<span id="cl12-label" class="property-label"><g:message code="school.cl12.label" default="Cl12" /></span>
					
						<span class="property-value" aria-labelledby="cl12-label"><g:fieldValue bean="${schoolInstance}" field="cl12"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${schoolInstance?.college}">
				<li class="fieldcontain">
					<span id="college-label" class="property-label"><g:message code="school.college.label" default="College" /></span>
					
						<span class="property-value" aria-labelledby="college-label"><g:fieldValue bean="${schoolInstance}" field="college"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${schoolInstance?.schoolName}">
				<li class="fieldcontain">
					<span id="schoolName-label" class="property-label"><g:message code="school.schoolName.label" default="School Name" /></span>
					
						<span class="property-value" aria-labelledby="schoolName-label"><g:fieldValue bean="${schoolInstance}" field="schoolName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${schoolInstance?.report}">
				<li class="fieldcontain">
					<span id="report-label" class="property-label"><g:message code="school.report.label" default="Report" /></span>
					
						<span class="property-value" aria-labelledby="report-label"><g:link controller="report" action="show" id="${schoolInstance?.report?.id}">${schoolInstance?.report?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${schoolInstance?.id}" />
					<g:link class="edit" action="edit" id="${schoolInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
