<%@ page import="com.bsk.dpp13.Report; com.bsk.dpp13.Samiti" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>
    <style type="text/css" media="screen">
    #status {
        background-color: #eee;
        border: .2em solid #fff;
        margin: 2em 2em 1em;
        padding: 1em;
        width: 12em;
        float: left;
        -moz-box-shadow: 0px 0px 1.25em #ccc;
        -webkit-box-shadow: 0px 0px 1.25em #ccc;
        box-shadow: 0px 0px 1.25em #ccc;
        -moz-border-radius: 0.6em;
        -webkit-border-radius: 0.6em;
        border-radius: 0.6em;
    }

    .ie6 #status {
        display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
    }

    #status ul {
        font-size: 0.9em;
        list-style-type: none;
        margin-bottom: 0.6em;
        padding: 0;
    }

    #status li {
        line-height: 1.3;
    }

    #status h1 {
        text-transform: uppercase;
        font-size: 1.1em;
        margin: 0 0 0.3em;
    }

    #page-body {
        margin: 2em 1em 1.25em 18em;
    }

    h2 {
        margin-top: 1em;
        margin-bottom: 0.3em;
        font-size: 1em;
    }

    p {
        line-height: 1.5;
        margin: 0.25em 0;
    }

    #controller-list ul {
        list-style-position: inside;
    }

    #controller-list li {
        line-height: 1.3;
        list-style-position: inside;
        margin: 0.25em 0;
    }

    @media screen and (max-width: 480px) {
        #status {
            display: none;
        }

        #page-body {
            margin: 0 1em 1em;
        }

        #page-body h1 {
            margin-top: 0;
        }
    }
    </style>
</head>

<body>
%{--<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>--}%
%{--<div id="status" role="complementary">--}%
%{--<h1>Application Status</h1>--}%
%{--<ul>--}%
%{--<li>App version: <g:meta name="app.version"/></li>--}%
%{--<li>Grails version: <g:meta name="app.grails.version"/></li>--}%
%{--<li>Groovy version: ${GroovySystem.getVersion()}</li>--}%
%{--<li>JVM version: ${System.getProperty('java.version')}</li>--}%
%{--<li>Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</li>--}%
%{--<li>Controllers: ${grailsApplication.controllerClasses.size()}</li>--}%
%{--<li>Domains: ${grailsApplication.domainClasses.size()}</li>--}%
%{--<li>Services: ${grailsApplication.serviceClasses.size()}</li>--}%
%{--<li>Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>--}%
%{--</ul>--}%
%{--<h1>Installed Plugins</h1>--}%
%{--<ul>--}%
%{--<g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">--}%
%{--<li>${plugin.name} - ${plugin.version}</li>--}%
%{--</g:each>--}%
%{--</ul>--}%
%{--</div>--}%
<div id="page-body" role="main">
    <h1>Welcome to BSK DPP 2013 Prerna</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <div id="controller-list" role="navigation">
        <h2>Available Controllers:</h2>
        <ul>
            <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName }}">
                <li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
            </g:each>
        </ul>
    </div>

    <div id="unmailedToSamitiList">
        <g:set var="reportSamitiMailPendingList" value="${Report.findAllByMailedToSamitiAndDeleted(false, false)}"/>
        <h2>There are ${reportSamitiMailPendingList.size()} reports waiting for mail to samiti Sewadharis</h2>
        <table>
            <thead>
            <tr class="sortable">
                <th>Report Id</th>
                <th>Samiti</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${reportSamitiMailPendingList}" status="i" var="reportInstance">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                    <td>${reportInstance.karyalayaAssignedId}</td>
                    <td>${reportInstance.samiti.samitiName}</td>
                    <td><g:remoteLink controller="report" action="ajaxEmailToSamiti"
                                      id="${reportInstance.id}">Mail</g:remoteLink></td>
                </tr>
            </g:each>
            </tbody>
        </table>
        <g:set var="reportCenterMailPendingList" value="${Report.findAllByCenterIsNotNullAndMailedToCenterAndDeleted(false, false)}"/>
        <h2>Here are ${reportCenterMailPendingList.size()} reports waiting for mail to centres</h2>
        <table>
            <thead>
            <tr class="sortable">
                <th>Report Id</th>
                <th>Center</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${reportCenterMailPendingList}" status="i" var="reportInstance">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                    <td>${reportInstance?.karyalayaAssignedId}</td>
                    <td>${reportInstance?.center?.centerName?:null}</td>
                    <td><g:remoteLink controller="report" action="ajaxEmailToCenter"
                                      id="${reportInstance.id}">Mail</g:remoteLink></td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
