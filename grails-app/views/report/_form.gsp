<%@ page import="com.bsk.dpp13.Report" %>


<div class="fieldcontain ${hasErrors(bean: reportInstance, field: 'karyalayaAssignedId', 'error')} required">
    <label for="karyalayaAssignedId">
        <g:message code="report.karyalayaAssignedId.label" default="Report ID"/>
        <span class="required-indicator">*</span>
    </label>
    <input type="text" name="karyalayaAssignedId" value="${reportInstance?.karyalayaAssignedId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: reportInstance, field: 'd', 'error')} required">
    <label for="d">
        <g:message code="report.d.label" default="D"/>
        <span class="required-indicator">*</span>
    </label>
    <g:datePicker name="d" precision="day" value="${reportInstance?.d}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: reportInstance, field: 'formFile', 'error')} required">
    <label for="formFile">
        <g:message code="report.formFile.label" default="Excel File"/>
        <span class="required-indicator">*</span>
    </label>
    <input type="file" id="myExcelFile" name="myExcelFile" accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/>
</div>

<div class="fieldcontain ${hasErrors(bean: reportInstance, field: 'samiti', 'error')} required">
    <label for="samiti">
        <g:message code="report.samiti.label" default="Samiti"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="samiti" name="samiti.id" from="${com.bsk.dpp13.Samiti.list()}" optionKey="id" optionValue="samitiName" required=""
              value="${reportInstance?.samiti?.id}" class="many-to-one" />
</div>

<div class="fieldcontain ${hasErrors(bean: reportInstance, field: 'school', 'error')} ">
    <label for="school">
        <g:message code="report.school.label" default="School"/>

    </label>

    %{--<ul class="one-to-many">--}%
        %{--<g:each in="${reportInstance?.school ?}" var="s">--}%
            %{--<li><g:link controller="school" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>--}%
        %{--</g:each>--}%
        %{--<li class="add">--}%
            %{--<g:link controller="school" action="create"--}%
                    %{--params="['report.id': reportInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'school.label', default: 'School')])}</g:link>--}%
        %{--</li>--}%
    %{--</ul>--}%

</div>

