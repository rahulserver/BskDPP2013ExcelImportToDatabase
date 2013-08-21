<%@ page import="com.bsk.dpp13.School" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'school.label', default: 'School')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-school" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                             default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-school" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="sNo"
                              title="${message(code: 'school.schoolName.label', default: 'S_NO')}"/>
            <g:sortableColumn property="schoolName"
                              title="${message(code: 'school.schoolName.label', default: 'SchoolName')}"/>
            <g:sortableColumn property="cl3" title="${message(code: 'school.cl3.label', default: 'Cl3')}"/>

            <g:sortableColumn property="cl4" title="${message(code: 'school.cl4.label', default: 'Cl4')}"/>

            <g:sortableColumn property="cl5" title="${message(code: 'school.cl5.label', default: 'Cl5')}"/>

            <g:sortableColumn property="cl6" title="${message(code: 'school.cl6.label', default: 'Cl6')}"/>

            <g:sortableColumn property="cl7" title="${message(code: 'school.cl7.label', default: 'Cl7')}"/>

            <g:sortableColumn property="cl8" title="${message(code: 'school.cl8.label', default: 'Cl8')}"/>
            <g:sortableColumn property="cl9" title="${message(code: 'school.cl9.label', default: 'Cl9')}"/>
            <g:sortableColumn property="cl10" title="${message(code: 'school.cl10.label', default: 'Cl10')}"/>
            <g:sortableColumn property="cl11" title="${message(code: 'school.cl11.label', default: 'Cl11')}"/>
            <g:sortableColumn property="cl12" title="${message(code: 'school.cl12.label', default: 'Cl12')}"/>
            <g:sortableColumn property="college" title="${message(code: 'school.college.label', default: 'college')}"/>

        </tr>
        </thead>
        <tbody>
        <g:each in="${schoolInstanceList}" status="i" var="schoolInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show"
                            id="${schoolInstance.id}">${fieldValue(bean: schoolInstance, field: "sNo")}</g:link></td>
                <td>${fieldValue(bean: schoolInstance, field: "schoolName")}</td>

                <td>${fieldValue(bean: schoolInstance, field: "cl3")}</td>
                <td>${fieldValue(bean: schoolInstance, field: "cl4")}</td>

                <td>${fieldValue(bean: schoolInstance, field: "cl5")}</td>

                <td>${fieldValue(bean: schoolInstance, field: "cl6")}</td>

                <td>${fieldValue(bean: schoolInstance, field: "cl7")}</td>

                <td>${fieldValue(bean: schoolInstance, field: "cl8")}</td>
                <td>${fieldValue(bean: schoolInstance, field: "cl9")}</td>
                <td>${fieldValue(bean: schoolInstance, field: "cl10")}</td>
                <td>${fieldValue(bean: schoolInstance, field: "cl11")}</td>
                <td>${fieldValue(bean: schoolInstance, field: "cl12")}</td>
                <td>${fieldValue(bean: schoolInstance, field: "college")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${schoolInstanceTotal}"/>
    </div>
</div>
</body>
</html>
