<%@ page import="com.bsk.dpp13.School" %>



<div class="fieldcontain ${hasErrors(bean: schoolInstance, field: 'cl3', 'error')} required">
	<label for="cl3">
		<g:message code="school.cl3.label" default="Cl3" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="cl3" type="number" min="0" value="${schoolInstance.cl3}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: schoolInstance, field: 'cl4', 'error')} required">
	<label for="cl4">
		<g:message code="school.cl4.label" default="Cl4" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="cl4" type="number" min="0" value="${schoolInstance.cl4}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: schoolInstance, field: 'cl5', 'error')} required">
	<label for="cl5">
		<g:message code="school.cl5.label" default="Cl5" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="cl5" type="number" min="0" value="${schoolInstance.cl5}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: schoolInstance, field: 'cl6', 'error')} required">
	<label for="cl6">
		<g:message code="school.cl6.label" default="Cl6" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="cl6" type="number" min="0" value="${schoolInstance.cl6}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: schoolInstance, field: 'cl7', 'error')} required">
	<label for="cl7">
		<g:message code="school.cl7.label" default="Cl7" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="cl7" type="number" min="0" value="${schoolInstance.cl7}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: schoolInstance, field: 'cl8', 'error')} required">
	<label for="cl8">
		<g:message code="school.cl8.label" default="Cl8" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="cl8" type="number" min="0" value="${schoolInstance.cl8}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: schoolInstance, field: 'cl9', 'error')} required">
	<label for="cl9">
		<g:message code="school.cl9.label" default="Cl9" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="cl9" type="number" min="0" value="${schoolInstance.cl9}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: schoolInstance, field: 'cl10', 'error')} required">
	<label for="cl10">
		<g:message code="school.cl10.label" default="Cl10" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="cl10" type="number" min="0" value="${schoolInstance.cl10}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: schoolInstance, field: 'cl11', 'error')} required">
	<label for="cl11">
		<g:message code="school.cl11.label" default="Cl11" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="cl11" type="number" min="0" value="${schoolInstance.cl11}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: schoolInstance, field: 'cl12', 'error')} required">
	<label for="cl12">
		<g:message code="school.cl12.label" default="Cl12" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="cl12" type="number" min="0" value="${schoolInstance.cl12}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: schoolInstance, field: 'college', 'error')} required">
	<label for="college">
		<g:message code="school.college.label" default="College" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="college" type="number" min="0" value="${schoolInstance.college}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: schoolInstance, field: 'schoolName', 'error')} ">
	<label for="schoolName">
		<g:message code="school.schoolName.label" default="School Name" />
		
	</label>
	<g:textField name="schoolName" value="${schoolInstance?.schoolName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: schoolInstance, field: 'report', 'error')} required">
	<label for="report">
		<g:message code="school.report.label" default="Report" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="report" name="report.id" from="${com.bsk.dpp13.Report.list()}" optionKey="id" required="" value="${schoolInstance?.report?.id}" class="many-to-one"/>
</div>

