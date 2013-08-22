
<%@ page import="com.bsk.dpp13.Samiti" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'samiti.label', default: 'Samiti')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-samiti" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-samiti" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="samitiName" title="${message(code: 'samiti.samitiName.label', default: 'Samiti Name')}" />
					
						<g:sortableColumn property="emailId" title="${message(code: 'samiti.emailId.label', default: 'Email Id')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${samitiInstanceList}" status="i" var="samitiInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${samitiInstance.id}">${fieldValue(bean: samitiInstance, field: "samitiName")}</g:link></td>
					
						<td>${fieldValue(bean: samitiInstance, field: "emailId")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${samitiInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
