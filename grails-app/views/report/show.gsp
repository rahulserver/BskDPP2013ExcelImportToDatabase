
<%@ page import="com.bsk.dpp13.Report" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'report.label', default: 'Report')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-report" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-report" class="content scaffold-show"  role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list report">

				<g:if test="${reportInstance?.karyalayaAssignedId}">
				<li class="fieldcontain">
					<span id="karyalayaAssignedId-label" class="property-label"><g:message code="report.karyalayaAssignedId.label" default="Report ID" /></span>
                    <span class="property-value" aria-labelledby="karyalayaAssignedId-label"><g:fieldValue field="karyalayaAssignedId" bean="${reportInstance}"/></span>
				</li>
				</g:if>
                <g:if test="${reportInstance?.formFile}">
				<li class="fieldcontain">
					<span id="formFile-label" class="property-label"><g:message code="report.formFile.label" default="Excel File" /></span>
                    <span class="property-value" aria-labelledby="formFile-label"><g:link controller="report" action="downloadFile" params='[report:"${reportInstance.karyalayaAssignedId}",formFile:"${reportInstance?.formFile}",targetUri: (request.forwardURI - request.contextPath)]'><g:fieldValue bean="${reportInstance}" field="formFile"/></g:link></span>
				</li>
				</g:if>

				<g:if test="${reportInstance?.d}">
				<li class="fieldcontain">
					<span id="d-label" class="property-label"><g:message code="report.d.label" default="Date" /></span>

						<span class="property-value" aria-labelledby="d-label"><g:formatDate date="${reportInstance?.d}" /></span>

				</li>
				</g:if>

				<g:if test="${reportInstance?.samiti}">
				<li class="fieldcontain">
					<span id="samiti-label" class="property-label"><g:message code="report.samiti.label" default="Samiti" /></span>

						<span class="property-value" aria-labelledby="samiti-label"><g:link controller="samiti" action="show" id="${reportInstance?.samiti?.id}">${reportInstance?.samiti?.samitiName.encodeAsHTML()}</g:link></span>

				</li>
				</g:if>

			</ol>

            <g:if test="${reportInstance?.school}">
                <table>
                    <tr>
                        <th>Name</th><th>3</th><th>4</th><th>5</th><th>6</th><th>7</th><th>8</th><th>9</th><th>10</th><th>11</th><th>12</th><th>College</th>
                    </tr>
                </table>
                <div style="height:300px;overflow-y:auto">
                <table>
                    <g:each in="${reportInstance.school.sort({it.sNo})}" var="s">
                        <tr>
                        <td>${s.schoolName}</td>
                        <td>${s.cl3}</td>
                        <td>${s.cl4}</td>
                        <td>${s.cl5}</td>
                        <td>${s.cl6}</td>
                        <td>${s.cl7}</td>
                        <td>${s.cl8}</td>
                        <td>${s.cl9}</td>
                        <td>${s.cl10}</td>
                        <td>${s.cl11}</td>
                        <td>${s.cl12}</td>
                        <td>${s.college}</td>
                        </tr>
                    </g:each>
                    </table>
                </div>
                <table>
                    <tr style="font-weight: bold;">
                        <td>Total</td>
                        <td>${reportInstance.school.cl3.sum()}</td>
                        <td>${reportInstance.school.cl4.sum()}</td>
                        <td>${reportInstance.school.cl5.sum()}</td>
                        <td>${reportInstance.school.cl6.sum()}</td>
                        <td>${reportInstance.school.cl7.sum()}</td>
                        <td>${reportInstance.school.cl8.sum()}</td>
                        <td>${reportInstance.school.cl9.sum()}</td>
                        <td>${reportInstance.school.cl10.sum()}</td>
                        <td>${reportInstance.school.cl11.sum()}</td>
                        <td>${reportInstance.school.cl12.sum()}</td>
                        <td>${reportInstance.school.college.sum()}</td>

                    </tr>
                </table>

            </g:if>

			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${reportInstance?.id}" />
					<g:link class="edit" action="edit" id="${reportInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
