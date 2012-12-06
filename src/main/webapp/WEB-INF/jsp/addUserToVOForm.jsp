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

<div id="container2">

<liferay-ui:error key="user-vo-list-empty" message="user-vo-list-empty"/>
<liferay-ui:error key="no-user-found-in-VO" message="no-user-found-in-VO"/>
<liferay-ui:error key="no-cert-for-user" message="no-cert-for-user"/>
<liferay-ui:error key="edg-mkgridmap-problem" message="edg-mkgridmap-problem"/>
<liferay-ui:error key="edg-mkgridmap-exception" message="edg-mkgridmap-exception"/>


<h1 class="header-title">Virtual Organization List</h1>

<%
	if (request.getParameter("userId") != null){
		pageContext.setAttribute("userId",request.getParameter("userId"));
	}
	if (request.getParameter("firstReg") != null){
		pageContext.setAttribute("firstReg",request.getParameter("firstReg"));
	}
											
%>


<div class="function">
			<aui:fieldset>
			<aui:column columnWidth="50">
			<portlet:actionURL var="searchVOActionUrl">
				<portlet:param name="myaction" value="searchUserToVo" />
			</portlet:actionURL>
			
			<aui:form name="searchUserInfo"
				action="${searchVOActionUrl}">
				<aui:layout>
					<aui:button-row>
					<aui:input name="userId" type="hidden" value="${userId}" />
					<aui:input name="firstReg" type="hidden" value="${firstReg}" />
					<aui:input name="key" label="Cerca VO" type="text" inlineField="true" inlineLabel="true"/>
					<aui:button type="submit" value="Search" inlineField="true"/>
					<portlet:actionURL var="backURL">
						<portlet:param name="myaction" value="searchResetUserToVo" />
						<portlet:param name="userId" value="${userId}" />
						<portlet:param name="firstReg" value="${firstReg}" />
					</portlet:actionURL>
					<aui:button type="cancel" value="Erase search"
						onClick="location.href='${backURL}';" />
					</aui:button-row>
				</aui:layout>
			</aui:form>
			
			<c:if test="${!empty searchUserToVo }">
				<br/>
				Search: <strong><c:out value="${searchUserToVo}" /></strong>	
			</c:if>
			</aui:column>
			</aui:fieldset>
		</div>
<div id="tabella">

<jsp:useBean id="vos"
	type="java.util.List<it.italiangrid.portal.dbapi.domain.Vo>" scope="request" />

	
<%
    PortletURL itURL = renderResponse.createRenderURL();
	itURL.setParameter("myaction","showAddUserToVO");
	itURL.setParameter("userId",request.getParameter("userId"));
	itURL.setParameter("firstReg",request.getParameter("firstReg"));
	
%>

<aui:column columnWidth="40">

<liferay-ui:search-container
	emptyResultsMessage="VO not find" delta="20" iteratorURL="<%= itURL %>">
	
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
		className="it.italiangrid.portal.dbapi.domain.Vo" keyProperty="idVo"
		modelVar="vo">
		<liferay-ui:search-container-column-text name="VO Name"
			property="vo" />
		<liferay-ui:search-container-column-jsp
								path="/WEB-INF/jsp/vo-select-action.jsp" align="right" />
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator />
</liferay-ui:search-container>

</aui:column>
</div>

<c:if test="${firstReg == false}">
<portlet:renderURL var="backURL">
				<portlet:param name="myaction" value="editUserInfoForm" />
				<portlet:param name="userId" value="<%=request.getParameter("userId")%>" />
			</portlet:renderURL>
</c:if>
<c:if test="${firstReg == true}">
<portlet:renderURL var="backURL">
				<portlet:param name="myaction" value="showAddUserToVoPresents" />
				<portlet:param name="userId" value="<%=request.getParameter("userId")%>" />
			</portlet:renderURL>
</c:if>
<aui:form name="completeListForm"
	action="${backURL}">
	<aui:button-row>
		<aui:button type="submit" value="Back" />
	</aui:button-row>
</aui:form>

</div>
