
<%
	/**
	 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
	 *
	 * This library is free software; you can redistribute it and/or modify it under
	 * the terms of the GNU Lesser General Public License as published by the Free
	 * Software Foundation; either version 2.1 of the License, or (at your option)
	 * any later version.
	 *
	 * This library is distributed in the hope that it will be useful, but WITHOUT
	 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
	 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
	 * details.
	 */
%>

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

<portlet:renderURL var="showAddUserInfoUrl">
	<portlet:param name="myaction" value="addUserInfoForm" />
</portlet:renderURL>







<jsp:useBean id="userInfos"
	type="java.util.List<portal.registration.domain.UserInfo>"
	scope="request" />
<jsp:useBean id="idps"
	type="java.util.List<portal.registration.domain.Idp>" scope="request" />
<jsp:useBean id="idpsName" type="java.util.Map" scope="request" />


<liferay-ui:success key="user-delated-successufully"
	message="user-delated-successufully" />
<liferay-ui:success key="user-registration-success"
	message="user-registration-success" />

<liferay-ui:error key="use-condition-not-acepted"
	message="use-condition-not-acepted" />
<liferay-ui:error key="exception-activation-user"
	message="exception-activation-user" />



<c:if test="<%= !themeDisplay.isSignedIn() %>">

</c:if>

<c:choose>
	<c:when test="<%= !themeDisplay.isSignedIn() %>"> 
	    <div style="text-align: justify">
	 	<strong>Benvenuto nella pagina di registrazione al porlate.</strong> <br/><br/>
		Una volta effettuato con successo tutto il percorso di registrazione potrai accedere a tutti i servizi offerti dal portale per l'uso della GRID. <br/> <br/>
		
		Se sei già registrato effettua il login nella <aui:a href="https://flyback.cnaf.infn.it">home</aui:a> del portale e potrai accedere a questa pagina per modificare i tuoi dati <br/><br/>
		Se non sei registrato al portale usa il pulsante qui sotto per farlo subito. <br/> <br/>
		</div>
		<aui:form name="catalogForm" method="post" action="${showAddUserInfoUrl}">
			<aui:button-row>
				<aui:button type="submit" value="Registrati Adesso!!!"/>
			</aui:button-row>
		</aui:form>

	</c:when>
	<c:when test="<%= request.isUserInRole("administrator") %>">
  
  	Bentornato <strong><c:out
				value="<%=((User) request.getAttribute(WebKeys.USER)).getFirstName() %>"></c:out>
		</strong>
		<br />
		<br />
		
		<div class="function">
			<aui:fieldset>
			<aui:column columnWidth="50">
			<portlet:actionURL var="searchVOActionUrl">
				<portlet:param name="myaction" value="searchUser" />
			</portlet:actionURL>
			
			<aui:form name="searchUserInfo" method="post"
				action="${searchVOActionUrl}">
				<aui:layout>
					<aui:button-row>
					<aui:input name="key" label="Cerca Utente" type="text" inlineField="true" inlineLabel="true"/>
					<aui:button type="submit" value="Cerca" inlineField="true"/>
					<portlet:actionURL var="backURL">
						<portlet:param name="myaction" value="searchResetUser" />
					</portlet:actionURL>
					<aui:button type="cancel" value="Azzera Ricerca"
						onClick="location.href='${backURL}';" />
					</aui:button-row>
				</aui:layout>
			</aui:form>
			
			<c:if test="${!empty search }">
				<br/>
				Ricerca: <strong><c:out value="${search}" /></strong>	
			</c:if>
			</aui:column>
			</aui:fieldset>
		</div>
		<div id="tabella">
		<liferay-ui:search-container
			emptyResultsMessage="Non ci sono utenti iscritti" delta="5">
			<liferay-ui:search-container-results>
				<%
					results = ListUtil.subList(userInfos,
							searchContainer.getStart(),
							searchContainer.getEnd());
					total = userInfos.size();

					pageContext.setAttribute("idps", idps);
					pageContext.setAttribute("results", results);
					pageContext.setAttribute("total", total);
				%>


			</liferay-ui:search-container-results>
			<liferay-ui:search-container-row
				className="portal.registration.domain.UserInfo" keyProperty="userId"
				modelVar="UserInfo">
				<liferay-ui:search-container-column-text name="Last Name"
					property="lastName" />
				<liferay-ui:search-container-column-text name="First Name"
					property="firstName" />
				<liferay-ui:search-container-column-text name="Institute"
					property="institute" />
				<liferay-ui:search-container-column-text name="Phone Number"
					property="phone" />
				<liferay-ui:search-container-column-text name="e-Mail"
					property="mail" />

				<%
					UserInfo ui = (UserInfo) row.getObject();
									String res = (String) idpsName.get(ui.getUserId());
				%>

				<liferay-ui:search-container-column-text name="IDP">
					<c:out value="<%= res %>" />
				</liferay-ui:search-container-column-text>
				<liferay-ui:search-container-column-text name="Username"
					property="username" />
				<liferay-ui:search-container-column-jsp
					path="/WEB-INF/jsp/admin-action.jsp" align="right" />
			</liferay-ui:search-container-row>
			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
		</div>
		<aui:form name="catalogForm" method="post"
			action="${showAddUserInfoUrl}">
			<aui:button-row>
				<aui:button type="submit" value="Add User" />
			</aui:button-row>
		</aui:form>

	</c:when>
	<c:otherwise>

		<%
			User userLF = (User) request.getAttribute(WebKeys.USER);
		%>
	Bentornato <strong><c:out value="<%=userLF.getFirstName() %>"></c:out>
		</strong>
		<br />
		<br />
	
	Ora puoi modificare il tuo profilo!!<br />
		<br />
		<br />

		<liferay-ui:search-container
			emptyResultsMessage="Non ci sono utenti iscritti" delta="5">
			<liferay-ui:search-container-results>
				<%
					int i = 0;
					int primKey = 0;
					UserInfo tmp = null;
					for (i = 0; i < userInfos.size(); i++) {
						tmp = userInfos.get(i);
						if (tmp.getUsername().equals(
								userLF.getScreenName())) {
							primKey = tmp.getUserId();
							break;
						}
					}

					List<UserInfo> displayUser = new ArrayList<UserInfo>();
					displayUser.add(tmp);

					results = ListUtil.subList(displayUser,
							searchContainer.getStart(),
							searchContainer.getEnd());
					total = displayUser.size();

					pageContext.setAttribute("idps", idps);
					pageContext.setAttribute("results", results);
					pageContext.setAttribute("total", total);
				%>


			</liferay-ui:search-container-results>
			<liferay-ui:search-container-row
				className="portal.registration.domain.UserInfo" keyProperty="userId"
				modelVar="UserInfo">
				<liferay-ui:search-container-column-text name="First Name"
					property="firstName" />
				<liferay-ui:search-container-column-text name="Last Name"
					property="lastName" />
				<liferay-ui:search-container-column-text name="Institute"
					property="institute" />
				<liferay-ui:search-container-column-text name="Phone Number"
					property="phone" />
				<liferay-ui:search-container-column-text name="e-Mail"
					property="mail" />

				<%
					UserInfo ui = (UserInfo) row.getObject();
									String res = (String) idpsName.get(ui.getUserId());
				%>

				<liferay-ui:search-container-column-text name="IDP">
					<c:out value="<%= res %>" />
				</liferay-ui:search-container-column-text>
				<liferay-ui:search-container-column-text name="Username"
					property="username" />
				<liferay-ui:search-container-column-jsp
					path="/WEB-INF/jsp/admin-action.jsp" align="right" />
			</liferay-ui:search-container-row>
			<liferay-ui:search-iterator />
		</liferay-ui:search-container>

	</c:otherwise>
</c:choose>



