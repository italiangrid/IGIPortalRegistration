<%@ include file="/WEB-INF/jsp/init.jsp"%>

<script type="text/javascript">
<!--
	//-->
	function mostraModificaUtente() {
		$("#<portlet:namespace/>formOFF").show("slow");
		$("#<portlet:namespace/>formOn").hide("slow");
	}

	function nascondiModificaUtente() {
		$("#<portlet:namespace/>formOFF").hide("slow");
		$("#<portlet:namespace/>formOn").show("slow");
	}

	function mostraCertificatiUtente() {
		$("#<portlet:namespace/>certificatiOFF").show("slow");
		$("#<portlet:namespace/>certificatiOn").hide("slow");
	}

	function nascondiCertificatiUtente() {
		$("#<portlet:namespace/>certificatiOFF").hide("slow");
		$("#<portlet:namespace/>certificatiOn").show("slow");
	}

	function mostraVoUtente() {
		$("#<portlet:namespace/>voOFF").show("slow");
		$("#<portlet:namespace/>voOn").hide("slow");
	}

	function nascondiVoUtente() {
		$("#<portlet:namespace/>voOFF").hide("slow");
		$("#<portlet:namespace/>voOn").show("slow");
	}
	
	function verifyDelete(url){
		var agree=confirm("Sei sicuro di voler eliminare il tuo account?");
		if (agree)
			return location.href=url ;
	}

	$(document).ready(function() {
		//nascondiCertificatiUtente();
		//nascondiModificaUtente();
		//nascondiVoUtente();
	});
</script>

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

div#personalData {
	padding: 1em;
	border: 1px;
	border-color: #C8C9CA;
	border-style: solid;
	background-color: #EFEFEF;
}

div#certificateData {
	margin: 3px 0 3px 0;
	padding: 1em;
	border: 1px;
	border-color: #C8C9CA;
	border-style: solid;
	background-color: #EFEFEF;
}

div#voData {
	padding: 1em;
	border: 1px;
	border-color: #C8C9CA;
	border-style: solid;
	background-color: #EFEFEF;
}

</style>



<portlet:renderURL var="showAskForCertificateUrl">
	<portlet:param name="myaction" value="askForCertificate" />
</portlet:renderURL>

<jsp:useBean id="userInfos"
	type="java.util.List<it.italiangrid.portal.dbapi.domain.UserInfo>"
	scope="request" />
<jsp:useBean id="idps"
	type="java.util.List<it.italiangrid.portal.dbapi.domain.Idp>" scope="request" />
<jsp:useBean id="idpsName" type="java.util.Map" scope="request" />


<liferay-ui:success key="user-delated-successufully"
	message="user-delated-successufully" />
<liferay-ui:success key="user-registration-success"
	message="user-registration-success" />

<liferay-ui:error key="use-condition-not-acepted"
	message="use-condition-not-acepted" />
<liferay-ui:error key="exception-activation-user"
	message="exception-activation-user" />

<c:choose>
	<c:when test="<%= !themeDisplay.isSignedIn() %>"> 
		<div id="container2">
			<aui:fieldset>
			<aui:column columnWidth="75">
		    <div style="text-align: justify">
		 	
			<!-- <img src="<%=request.getContextPath()%>/images/3-steps.png"/>
			<img src="https://gridlab17.cnaf.infn.it/image/image_gallery?img_id=12355&t=1326102175121" alt="Fase 1" />  -->
			
			Registration information.
			
			</div>
			</aui:column>
			<aui:column columnWidth="25">
				<aui:form name="catalogForm" action="${showAskForCertificateUrl}">
					<aui:button-row>
						<aui:button type="submit" value="Register NOW!!!"/>
					</aui:button-row>
				</aui:form>
			</aui:column>
			</aui:fieldset>
		</div>
	</c:when>
	<c:when test="<%= (themeDisplay.isSignedIn()) && (request.isUserInRole("administrator")) %>">
		<div id="container2">
	  	<div id="presentation">
		
			<%
					User userLF = (User) request.getAttribute(WebKeys.USER);

					String saluto= "Hi " + userLF.getFirstName();
				%>
		  	<aui:fieldset label="<%=saluto %>"></aui:fieldset>
			<br />
			<br />
		</div>
		
		<div class="function">
			<aui:fieldset>
			<aui:column columnWidth="50">
			<portlet:actionURL var="searchVOActionUrl">
				<portlet:param name="myaction" value="searchUser" />
			</portlet:actionURL>
			
			
			<aui:form name="searchUserInfo" 
				action="${searchVOActionUrl}">
				<aui:layout>
					<aui:button-row>
					<aui:input name="key" label="Cerca Utente" type="text" inlineField="true" inlineLabel="true"/>
					<aui:button type="submit" value="Search" inlineField="true"/>
					<portlet:actionURL var="backURL">
						<portlet:param name="myaction" value="searchResetUser" />
					</portlet:actionURL>
					<aui:button type="cancel" value="Erase search"
						onClick="location.href='${backURL}';" />
					</aui:button-row>
				</aui:layout>
			</aui:form>
			
			<c:if test="${!empty search }">
				<br/>
				Search: <strong><c:out value="${search}" /></strong>	
			</c:if>
			</aui:column>
			</aui:fieldset>
		</div>
		<div id="tabella">
		<liferay-ui:search-container
			emptyResultsMessage="No user registred" delta="20">
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
				className="it.italiangrid.portal.dbapi.domain.UserInfo" keyProperty="userId"
				modelVar="UserInfo">
				<liferay-ui:search-container-column-text name="Last Name"
					property="lastName" />
				<liferay-ui:search-container-column-text name="First Name"
					property="firstName" />
				<liferay-ui:search-container-column-text name="Institute"
					property="institute" />
				<liferay-ui:search-container-column-text name="e-Mail"
					property="mail" />

				<%
					UserInfo ui = (UserInfo) row.getObject();
									String res = (String) idpsName.get(ui.getUserId());
				%>
				<liferay-ui:search-container-column-jsp
					path="/WEB-INF/jsp/admin-action.jsp" align="right" />
			</liferay-ui:search-container-row>
			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
		</div>
		
		</div>
	</c:when>
	<c:otherwise>

		<%@ include file="/WEB-INF/jsp/editUserInfoForm.jsp" %>

	</c:otherwise>
</c:choose>


