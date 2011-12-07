<%@ include file="/WEB-INF/jsp/init.jsp"%>

<style>
div.function {
	padding: 1em 5em 1em 1em;
	border: 1px;
	border-color: #C8C9CA;
	border-style: solid;
	background-color: #D1D6DC;
}
#tabella {
	margin: 3px 0 3px 0;
	padding: 1em;
/* 	border: 1px; */
/* 	border-color: #C8C9CA; */
/* 	border-style: solid; */
}
#search{float:left;}
#addVo{float:left;}
#clear{clear:both;}
</style>

<portlet:renderURL var="homeUrl">
	<portlet:param name="myaction" value="userInfos" />
</portlet:renderURL>

<h1 class="header-title">Lista Virtual Organization</h1>

<a href="${homeUrl}">Home</a>

<br />
<br />

<%
	if (request.getParameter("userId") != null)
													pageContext.setAttribute("userId",
															request.getParameter("userId"));
												if (request.getParameter("waif") != null)
													pageContext.setAttribute("waif",
															request.getParameter("waif"));
														%>

<div class="function">
			<aui:fieldset>
			<aui:column columnWidth="50">
			<portlet:actionURL var="searchVOActionUrl">
				<portlet:param name="myaction" value="searchVo" />
			</portlet:actionURL>
			
			<aui:form name="searchUserInfo" method="post"
				action="${searchVOActionUrl}">
				<aui:layout>
					<aui:button-row>
					<aui:input name="userId" type="hidden" value="${userId}" />
					<aui:input name="waif" type="hidden" value="${waif}" />
					<aui:input name="key" label="Cerca VO" type="text" inlineField="true" inlineLabel="true"/>
					<aui:button type="submit" value="Cerca" inlineField="true"/>
					<portlet:actionURL var="backURL">
						<portlet:param name="myaction" value="searchReset" />
						<portlet:param name="waif" value="${waif}" />
						<portlet:param name="userId" value="${userId}" />
					</portlet:actionURL>
					<aui:button type="cancel" value="Azzera Ricerca"
						onClick="location.href='${backURL}';" />
					</aui:button-row>
				</aui:layout>
			</aui:form>
			
			<c:if test="${!empty searchVo }">
				<br/>
				Ricerca: <strong><c:out value="${searchVo}" /></strong>	
			</c:if>
			</aui:column>
			</aui:fieldset>
		</div>
<div id="tabella">

<jsp:useBean id="vos"
	type="java.util.List<portal.registration.domain.Vo>" scope="request" />
	
<%
    PortletURL itURL = renderResponse.createRenderURL();
	itURL.setParameter("myaction","showVOList");
	itURL.setParameter("userId",request.getParameter("userId"));
	itURL.setParameter("waif",request.getParameter("waif"));
	
%>



<liferay-ui:search-container
	emptyResultsMessage="Non ci sono utenti iscritti" delta="20" iteratorURL="<%= itURL %>">
	
	<liferay-ui:search-container-results>
		<%
			results = ListUtil.subList(vos, searchContainer.getStart(),
							searchContainer.getEnd());
					total = vos.size();

					pageContext.setAttribute("results", results);
					pageContext.setAttribute("total", total);
		%>


	</liferay-ui:search-container-results>
	<liferay-ui:search-container-row
		className="portal.registration.domain.Vo" keyProperty="idVo"
		modelVar="vo">
		<liferay-ui:search-container-column-text name="VO Name"
			property="vo" />
		<liferay-ui:search-container-column-text name="Hostname">
			<a href="https://${vo.host}" target="_blank"><c:out value="${vo.host}" />
			</a>
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text name="Enrollement URL">
			<a href="${vo.enrollementUrl}" target="_blank"><c:out value="${vo.enrollementUrl}" />
			</a>
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text name="Descrizione VO"
			property="description" />
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator />
</liferay-ui:search-container>
</div>

<portlet:renderURL var="backURL">
				<portlet:param name="myaction" value="<%= request.getParameter("waif")%>" />
				<portlet:param name="userId" value="<%= request.getParameter("userId")%>" />
			</portlet:renderURL>

<aui:form name="completeListForm" method="post"
	action="${backURL}">
	<aui:button-row>
		<aui:button type="submit" value="Indietro" />
	</aui:button-row>
</aui:form>


