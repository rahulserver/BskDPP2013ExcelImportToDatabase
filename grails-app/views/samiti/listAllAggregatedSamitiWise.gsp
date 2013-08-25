<%@ page import="com.bsk.dpp13.*" %>
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
						<g:sortableColumn property="cl3" title="${message(code: 'samiti.samitiName.label', default: '3')}" />
						<g:sortableColumn property="cl4" title="${message(code: 'samiti.samitiName.label', default: '4')}" />
						<g:sortableColumn property="cl5" title="${message(code: 'samiti.samitiName.label', default: '5')}" />
						<g:sortableColumn property="cl6" title="${message(code: 'samiti.samitiName.label', default: '6')}" />
						<g:sortableColumn property="cl7" title="${message(code: 'samiti.samitiName.label', default: '7')}" />
						<g:sortableColumn property="cl8" title="${message(code: 'samiti.samitiName.label', default: '8')}" />
						<g:sortableColumn property="cl9" title="${message(code: 'samiti.samitiName.label', default: '9')}" />
						<g:sortableColumn property="cl10" title="${message(code: 'samiti.samitiName.label', default: '10')}" />
						<g:sortableColumn property="cl11" title="${message(code: 'samiti.samitiName.label', default: '11')}" />
						<g:sortableColumn property="cl12" title="${message(code: 'samiti.samitiName.label', default: '12')}" />
						<g:sortableColumn property="cl13" title="${message(code: 'samiti.samitiName.label', default: '13')}" />

					</tr>
				</thead>
				<tbody>
				<g:each in="${samitiInstanceList}" status="i" var="samitiInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					    <td><g:link action="show" id="${samitiInstance.id}">${samitiInstance.samitiName}</g:link></td>
                        <g:set var="cl3List" value="${samitiInstance.report.school*.sum(){it.cl3}}" />
                        <td>
                            ${samitiInstance.aggregatedReportMap.cl3Total?:0}
                        </td><td>
                            ${samitiInstance.aggregatedReportMap.cl4Total?:0}
                        </td><td>
                            ${samitiInstance.aggregatedReportMap.cl5Total?:0}
                        </td><td>
                            ${samitiInstance.aggregatedReportMap.cl6Total?:0}
                        </td><td>
                            ${samitiInstance.aggregatedReportMap.cl7Total?:0}
                        </td><td>
                            ${samitiInstance.aggregatedReportMap.cl8Total?:0}
                        </td><td>
                            ${samitiInstance.aggregatedReportMap.cl9Total?:0}
                        </td><td>
                            ${samitiInstance.aggregatedReportMap.cl10Total?:0}
                        </td><td>
                            ${samitiInstance.aggregatedReportMap.cl11Total?:0}
                        </td><td>
                            ${samitiInstance.aggregatedReportMap.cl12Total?:0}
                        </td><td>
                            ${samitiInstance.aggregatedReportMap.cl13Total?:0}
                        </td>

					</tr>
				</g:each>
				</tbody>
                <tfoot>
                    <tr>
                        <td>Total</td>
                        <td>${(samitiInstanceList*.aggregatedReportMap)*.cl3Total.sum({it?:0})}</td>
                        <td>${(samitiInstanceList*.aggregatedReportMap)*.cl4Total.sum({it?:0})}</td>
                        <td>${(samitiInstanceList*.aggregatedReportMap)*.cl5Total.sum({it?:0})}</td>
                        <td>${(samitiInstanceList*.aggregatedReportMap)*.cl6Total.sum({it?:0})}</td>
                        <td>${(samitiInstanceList*.aggregatedReportMap)*.cl7Total.sum({it?:0})}</td>
                        <td>${(samitiInstanceList*.aggregatedReportMap)*.cl8Total.sum({it?:0})}</td>
                        <td>${(samitiInstanceList*.aggregatedReportMap)*.cl9Total.sum({it?:0})}</td>
                        <td>${(samitiInstanceList*.aggregatedReportMap)*.cl10Total.sum({it?:0})}</td>
                        <td>${(samitiInstanceList*.aggregatedReportMap)*.cl11Total.sum({it?:0})}</td>
                        <td>${(samitiInstanceList*.aggregatedReportMap)*.cl12Total.sum({it?:0})}</td>
                        <td>${(samitiInstanceList*.aggregatedReportMap)*.cl13Total.sum({it?:0})}</td>

                    </tr>
                </tfoot>
			</table>
            <table>
                <thead>
                    <tr><th>GBTG</th><th>SSKK</th><th>DBPC</th><th>CHG</th><th>KPK</th><th>POUC</th><th>CBAG</th><th>CBAB</th><th>BADG</th><th>DSMS</th></tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            ${(School.findById(1).getGeometryBoxAndTulsiGoli())}
                        </td>
                        <td>
                            ${samitiInstanceList*.report*.school*.sapsidiKaKhel.sum()}
                        </td><td>
                            ${samitiInstanceList*.report*.school*.drawingBook.sum({it?:0})}
                        </td><td>
                            ${samitiInstanceList*.report*.school*.colour.sum({it?:0})}
                        </td><td>
                            ${samitiInstanceList*.report*.school*.chhotiGhadi.sum({it?:0})}
                        </td>><td>
                            ${samitiInstanceList*.report*.school*.karmphalKhel.sum({it?:0})}
                        </td><td>
                            ${samitiInstanceList*.report*.school*.pouch.sum({it?:0})}
                        </td><td>
                            ${samitiInstanceList*.report*.school*.collegeBag.sum({it?:0})}
                        </td><td>
                            ${samitiInstanceList*.report*.school*.collegeBagBig.sum({it?:0})}
                        </td><td>
                            ${samitiInstanceList*.report*.school*.badiGhadi.sum({it?:0})}
                        </td><td>
                            ${samitiInstanceList*.report*.school*.diarySms.sum({it?:0})}
                        </td>
                    </tr>
                </tbody>
            </table>
			<div class="pagination">
				<g:paginate total="${samitiInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
