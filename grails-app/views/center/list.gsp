
<%@ page import="com.bsk.dpp13.Center" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'center.label', default: 'Center')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-center" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-center" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="emailId" title="${message(code: 'center.emailId.label', default: 'Email Id')}" />
					
						<g:sortableColumn property="centerName" title="${message(code: 'center.centerName.label', default: 'Center Name')}" />
					
						<g:sortableColumn property="centerLevelSewadhariName" title="${message(code: 'center.centerLevelSewadhariName.label', default: 'Center Level Sewadhari Name')}" />
					
						<g:sortableColumn property="centerTelephoneNumber" title="${message(code: 'center.centerTelephoneNumber.label', default: 'Center Telephone Number')}" />
					
						<th><g:message code="center.state.label" default="State" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${centerInstanceList}" status="i" var="centerInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${centerInstance.id}">${fieldValue(bean: centerInstance, field: "emailId")}</g:link></td>
					
						<td>${fieldValue(bean: centerInstance, field: "centerName")}</td>
					
						<td>${fieldValue(bean: centerInstance, field: "centerLevelSewadhariName")}</td>
					
						<td>${centerInstance.centerTelephoneNumber}</td>
					
						<td>${fieldValue(bean: centerInstance, field: "state")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${centerInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
