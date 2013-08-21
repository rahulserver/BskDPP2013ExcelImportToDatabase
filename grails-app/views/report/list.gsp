<%@ page import="com.bsk.dpp13.Report" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'report.label', default: 'Report')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div id="message" class="error"></div>

<div id="error" class="error"></div>
<g:javascript>
    function bookRemoved(bookId) {
        Effect.toggle('book-' + bookId, 'appear');
    }
</g:javascript>

<a href="#list-report" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                             default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-report" style="overflow:auto;height:100%;" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="formFile"
                              title="${message(code: 'report.formFile.label', default: 'Report ID')}"/>
            <g:sortableColumn property="karyalayaAssignedId"
                              title="${message(code: 'report.formFile.label', default: 'Excel File')}"/>

            <g:sortableColumn property="d" title="${message(code: 'report.d.label', default: 'D')}"/>

            <th><g:message code="report.samiti.label" default="Samiti"/></th>

            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${reportInstanceList}" status="i" var="reportInstance">
            <tr id="report-${reportInstance.id}" class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show"
                            id="${reportInstance.id}">${fieldValue(bean: reportInstance, field: "karyalayaAssignedId")}</g:link></td>
                <td>${fieldValue(bean: reportInstance, field: "formFile")}</td>

                <td><g:formatDate date="${reportInstance.d}"/></td>

                <td>${fieldValue(bean: reportInstance, field: "samiti")}</td>

                <td><g:remoteLink controller="report" action="ajaxDelete" id="${reportInstance.id}"
                                  update="[success: 'message', failure: 'error']"
                                  oncomplete="reportRemoved(${reportInstance.id});">delete</g:remoteLink></td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${reportInstanceTotal}"/>
    </div>
</div>
</body>
</html>
