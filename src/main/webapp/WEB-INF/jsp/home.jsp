
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
		<aui:fieldset>
		<aui:column columnWidth="75">
	    <div style="text-align: justify">
	 	<strong>Benvenuto nella pagina di registrazione al porlate.</strong>
		<br/><br/>
		<img src="<%=request.getContextPath()%>/images/3-steps.png"/>
		<!-- <img src="https://gridlab17.cnaf.infn.it/image/image_gallery?img_id=12355&t=1326102175121" alt="Fase 1" />  -->
		</div>
		</aui:column>
		<aui:column columnWidth="25">
		<br/><br/>
		<br/><br/>
		<br/><br/>
		<br/><br/>
		<br/><br/>
		<aui:form name="catalogForm" method="post" action="${showAddUserInfoUrl}">
			<aui:button-row>
				<aui:button type="submit" value="Registrati Adesso!!!"/>
			</aui:button-row>
		</aui:form>
		</aui:column>
		</aui:fieldset>
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
		

	</c:when>
	<c:otherwise>

		<%
			User userLF = (User) request.getAttribute(WebKeys.USER);
		%>
	Bentornato <strong><c:out value="<%=userLF.getFirstName() %>"></c:out>
		</strong>
		<br />
		<br />
	
		<portlet:actionURL var="editUserInfoActionUrl">
			<portlet:param name="myaction" value="editUserInfo" />
		</portlet:actionURL>
		<portlet:renderURL var="homeUrl">
			<portlet:param name="myaction" value="userInfos" />
		</portlet:renderURL>
		
		<jsp:useBean id="userInfo" type="portal.registration.domain.UserInfo"
			scope="request" />
		<jsp:useBean id="certList"
			type="java.util.List<portal.registration.domain.Certificate>"
			scope="request" />
		<jsp:useBean id="userToVoList"
			type="java.util.List<portal.registration.domain.Vo>" scope="request"></jsp:useBean>
		
		<div id="personalData">
			<h3 class="header-title">Dati Personali</h3>
		
			<liferay-ui:success key="user-updated-successufully"
				message="user-updated-successufully" />
		
			<liferay-ui:error key="error-updating-user"
				message="error-updating-user" />
			<liferay-ui:error key="problem-idp" message="problem-idp" />
			<liferay-ui:error key="problem-update-user"
				message="problem-update-user" />
			<liferay-ui:error key="user-first-name-required"
				message="user-first-name-required" />
			<liferay-ui:error key="user-last-name-required"
				message="user-last-name-required" />
			<liferay-ui:error key="user-institute-required"
				message="user-institute-required" />
			<liferay-ui:error key="user-mail-required" message="user-mail-required" />
			<liferay-ui:error key="user-valid-mail-required"
				message="user-valid-mail-required" />
			<liferay-ui:error key="user-username-required"
				message="user-username-required" />
			<liferay-ui:error key="user-phone-valid" message="user-phone-valid" />
			<liferay-ui:error key="user-mail-must-same"
				message="user-mail-must-same" />
			<liferay-ui:error key="user-username-must-same"
				message="user-username-must-same" />
			<liferay-ui:error key="problem-update-user-liferay"
				message="problem-update-user-liferay" />
			
			<div id="<portlet:namespace/>formOn">
				<a href="#editUserInfoForm" onclick="mostraModificaUtente();">Modifica
					Dati</a><br /> <br />
				<table>
					<tr>
						<td align="right" style="margin: 0; padding: 0 1em 0 0;">User:</td>
						<td><strong><c:out value="${userInfo.firstName }" />
						</strong> <strong><c:out value="${userInfo.lastName }" /> </strong></td>
					</tr>
					<tr>
						<td align="right" style="margin: 0; padding: 0 1em 0 0;">Istituto:</td>
						<td><strong><c:out value="${userInfo.institute }" />
						</strong></td>
					</tr>
					<tr>
						<td align="right" style="margin: 0; padding: 0 1em 0 0;">Username:</td>
						<td><strong><c:out value="${userInfo.username }" /> </strong>
						</td>
					</tr>
					<tr>
						<td align="right" style="margin: 0; padding: 0 1em 0 0;">e-Mail:</td>
						<td><strong><c:out value="${userInfo.mail }" /> </strong></td>
					</tr>
				</table>
			</div>
			<div id="<portlet:namespace/>formOFF" style="display: none;">
		
				<a href="#editUserInfoForm" onclick="nascondiModificaUtente();">Nascondi</a><br />
				<br />
		
				<aui:form name="editUserInfoForm" commandName="userInfo" method="post"
					action="${editUserInfoActionUrl}">
					
					<%
						pageContext.setAttribute("userId",
								userInfo.getUserId());
						
						//out.println("utente = " + userInfo.getUserId());
					%>
		
					<aui:layout>
		
						<aui:fieldset>
							<aui:column columnWidth="33">
								<aui:fieldset label="Dati Personali">
									<br></br>
		
									<aui:input name="userId" type="hidden"
										value="<%=userInfo.getUserId() %>" />
									<liferay-ui:error key="user-first-name-required"
										message="user-first-name-required" />
									<strong>First Name</strong><br/>
									<input name="firstName" type="text"
										value="<%=userInfo.getFirstName() %>" disabled="disabled"/>
									<liferay-ui:error key="user-last-name-required"
										message="user-last-name-required" />
									<br/><br/><strong>Last Name</strong><br/>
									<input name="lastName" type="text"
										value="<%=userInfo.getLastName() %>" disabled="disabled"/>
									<br/> <br/>
								</aui:fieldset>
							</aui:column>
		
							<aui:column columnWidth="33">
								<aui:fieldset label="Recapiti">
									<br></br>
									<liferay-ui:error key="user-institute-required"
										message="user-institute-required" />
									<strong>Institute</strong><br/>
									<input name="institute" type="text"
										value="<%=userInfo.getInstitute() %>" />
									<liferay-ui:error key="user-phone-valid"
										message="user-phone-valid" />
									<br/><br/><strong>Phone Number</strong><br/>
									<input name="phone" type="text"
										value="<%=userInfo.getPhone() %>"  />
									<br/> <br/>
								</aui:fieldset>
							</aui:column>
		
							<aui:column columnWidth="33">
								<aui:fieldset label="Dati Account">
									<br></br>
									<liferay-ui:error key="user-username-required"
										message="user-username-required" />
									<liferay-ui:error key="user-username-duplicate"
										message="user-username-duplicate" />
									<strong>Username</strong><br/>
									<input name="username" disabled="disabled" type="text"
										value="<%=userInfo.getUsername() %>" />
									<liferay-ui:error key="user-mail-required"
										message="user-mail-required" />
									<liferay-ui:error key="user-valid-mail-required"
										message="user-valid-mail-required" />
									<liferay-ui:error key="user-mail-duplicate"
										message="user-mail-duplicate" />
									<br/><br/><strong>e-Mail Address</strong><br/>
									<input name="mail" disabled="disabled" type="text"
										value="<%=userInfo.getMail() %>" />
									<br/> <br/>
								</aui:fieldset>
							</aui:column>
							
							<aui:button-row>
								<aui:button type="submit" />
							</aui:button-row>
						</aui:fieldset>
					</aui:layout>
				</aui:form>
			</div>
		</div>
		
		<div id="certificateData">
			<h3 class="header-title">I miei certificati</h3>
		
			<liferay-ui:success key="certificate-updated-successufully"
				message="certificate-updated-successufully" />
			<liferay-ui:success key="certificate-deleted-successufully"
				message="certificate-deleted-successufully" />
			<liferay-ui:success key="upload-cert-successufully"
				message="upload-cert-successufully" />
		
			<liferay-ui:error key="error-deleting-certificate"
				message="error-deleting-certificate" />
			<liferay-ui:error key="error-updating-certificate"
				message="error-updating-certificate" />
			<liferay-ui:error key="error-default-certificate"
				message="error-default-certificate" />
		
		
		
			<div id="<portlet:namespace/>certificatiOn">
		
				<a href="#apriCert" onclick="mostraCertificatiUtente();">Modifica
					Certificati</a><br /> <br /> Al momento hai effetuato l'upload di 
						<c:choose>
						<c:when test="${fn:length(certList)==0}" >
						<span style="color:red"><strong>#<c:out value="${fn:length(certList)}" /></strong></span>
						certificati<br />
						</c:when>
						<c:when test="${fn:length(certList)==1}" >
						<strong>#<c:out value="${fn:length(certList)}" />
						</strong>certificato<br />
						</c:when>
						<c:otherwise>
						<strong>#<c:out value="${fn:length(certList)}" />
						</strong>certificati<br />
						</c:otherwise>
						</c:choose>
				
				<c:if test="${fn:length(certList) > 0}">
				Il tuo certificato di default è: <strong> <c:forEach
							var="cert" items="${certList}">
							<c:if test="${cert.primaryCert == 'true'}">
								<c:out value="${cert.subject}" />
							</c:if>
						</c:forEach> </strong>
					<br />
				</c:if>
		
				<br />
		
		
			</div>
		
			<div id="<portlet:namespace/>certificatiOFF" style="display: none;">
		
				<a href="#cert" onclick="nascondiCertificatiUtente();">Nascondi</a><br />
				<br />
		
				<liferay-ui:search-container
					emptyResultsMessage="Non ci sono certificati per questo utente"
					delta="5">
					<liferay-ui:search-container-results>
						<%
							results = ListUtil.subList(certList,
											searchContainer.getStart(),
											searchContainer.getEnd());
									total = certList.size();
		
									pageContext.setAttribute("results", results);
									pageContext.setAttribute("total", total);
						%>
		
					</liferay-ui:search-container-results>
					<liferay-ui:search-container-row
						className="portal.registration.domain.Certificate"
						keyProperty="idCert" modelVar="Certificate">
						<liferay-ui:search-container-column-text name="Subject"
							property="subject" />
						<liferay-ui:search-container-column-text name="Issuer"
							property="issuer" />
							<liferay-ui:search-container-column-text name="Data Scadenza">
							<%
								Certificate cert = (Certificate) row.getObject();
		
													GregorianCalendar c = new GregorianCalendar();
													Date oggi = c.getTime();
		
													if (cert.getExpirationDate().before(oggi)) {
														%>
														<span style="color:red; font-weight:bold;"><c:out value="<%=cert.getExpirationDate().toString() %>"/></span>
														<%
														
													} else {
														%>
														<c:out value="<%=cert.getExpirationDate().toString() %>"/>
														<%
													}
							%>
							</liferay-ui:search-container-column-text>
						<liferay-ui:search-container-column-text name="Certificato primario"
							property="primaryCert" />
						<liferay-ui:search-container-column-text
							name="Rilasciato dalla CA-online" property="caonline" />
						<liferay-ui:search-container-column-text name="Username"
							property="usernameCert" />
						<liferay-ui:search-container-column-jsp
							path="/WEB-INF/jsp/admin-cert-action.jsp" align="right" />
					</liferay-ui:search-container-row>
					<liferay-ui:search-iterator />
				</liferay-ui:search-container>
				<a name="apriCert"></a>
		
				<portlet:renderURL var="uploadCertUrl">
					<portlet:param name="myaction" value="showUploadCert" />
		
				</portlet:renderURL>
		
				<aui:form name="catalogForm" method="post" commandName="userInfo" action="${uploadCertUrl}">
		
					<aui:input name="userId" type="hidden" value="${userInfo.userId }" />
					<aui:input name="username" type="hidden"
						value="${userInfo.username }" />
					<aui:input name="firstReg" type="hidden" value="false" />
		
					<aui:button-row>
						<aui:button type="submit" value="Carica certificato" />
					</aui:button-row>
				</aui:form>
		
			</div>
		</div>
		
		<div id="voData">
			<h3 class="header-title">Le mie VO</h3>
		
			<liferay-ui:success key="userToVo-adding-success"
				message="userToVo-adding-success" />
			<liferay-ui:success key="userToVo-updated-successufully"
				message="userToVo-updated-successufully" />
			<liferay-ui:success key="userToVo-deleted-successufully"
				message="userToVo-deleted-successufully" />
			<liferay-ui:success key="user-deactivate" message="user-deactivate" />
			<liferay-ui:success key="user-activate" message="user-activate" />
		
			<liferay-ui:error key="user-vo-list-empty" message="user-vo-list-empty" />
			<liferay-ui:error key="no-user-found-in-VO"
				message="no-user-found-in-VO" />
			<liferay-ui:error key="no-cert-for-user" message="no-cert-for-user" />
			<liferay-ui:error key="edg-mkgridmap-problem"
				message="edg-mkgridmap-problem" />
			<liferay-ui:error key="edg-mkgridmap-exception"
				message="edg-mkgridmap-exception" />
			<liferay-ui:error key="error-deleting-userToVo"
				message="error-deleting-userToVo" />
			<liferay-ui:error key="error-default-userToVo"
				message="error-default-userToVo" />
			<liferay-ui:error key="userToVo-already-exists"
				message="userToVo-already-exists" />
			<liferay-ui:error key="exception-deactivation-user"
				message="exception-deactivation-user" />
		
			<div id="<portlet:namespace/>voOn">
				<a href="#apriVo" onclick="mostraVoUtente();">Modifica VO</a> <br /> <br />
				Al momento appartieni a <strong>#<c:out
						value="<%= Integer.toString(userToVoList.size()) %>"></c:out> </strong> VO<br />
				<c:if test="${!empty defaultVo}">
			La tua VO di default è: <strong><c:out value="${defaultVo}" />
					</strong>
				</c:if>
				<c:if test="${!empty defaultFqan}">
					<br />Ruoli per la VO di default: <strong><c:out
							value="${fn:replace(defaultFqan,';',' ')}" /> </strong>
				</c:if>
		
				<br /> <br />
		
			</div>
		
			<div id="<portlet:namespace/>voOFF" style="display: none;">
		
				<a href="#vo" onclick="nascondiVoUtente();">Nascondi VO</a> <br /> <br />
		
				<liferay-ui:search-container
					emptyResultsMessage="Non appartieni a nessuna VO attualmente aggiungile qui in fianco"
					delta="5">
					<liferay-ui:search-container-results>
						<%
							results = ListUtil.subList(userToVoList,
											searchContainer.getStart(),
											searchContainer.getEnd());
									total = userToVoList.size();
		
									pageContext.setAttribute("results", results);
									pageContext.setAttribute("total", total);
						%>
		
					</liferay-ui:search-container-results>
					<liferay-ui:search-container-row
						className="portal.registration.domain.Vo" keyProperty="idVo"
						modelVar="Vo">
						<c:choose>
							<c:when test="${defaultVo == Vo.vo}">
								
								<liferay-ui:search-container-column-text name="Nome VO">
									<span style="color:red"> <c:out value="${Vo.vo }"></c:out> </span>
								</liferay-ui:search-container-column-text>
								<liferay-ui:search-container-column-text name="Default VO">
									<span style="color:red">true</span></liferay-ui:search-container-column-text>
								<liferay-ui:search-container-column-text name="Descrizione"> 
									<span style="color:red"><c:out value="${Vo.description}"></c:out></span> 
								</liferay-ui:search-container-column-text>
								<liferay-ui:search-container-column-text name="Ruoli"> 
									<span style="color:red"><c:out value="${fn:replace(userFqans[Vo.idVo],';',' ')}"></c:out></span> 
								</liferay-ui:search-container-column-text>
								<liferay-ui:search-container-column-jsp
									path="/WEB-INF/jsp/admin-vo-action.jsp" align="right" />
								
							</c:when>
							<c:otherwise> 
								<liferay-ui:search-container-column-text name="Nome VO"
									property="vo" />
								<liferay-ui:search-container-column-text name="Default VO">
									false</liferay-ui:search-container-column-text>
								<liferay-ui:search-container-column-text name="Descrizione"
									property="description" />
								<liferay-ui:search-container-column-text name="Ruoli"> 
									<c:out value="${fn:replace(userFqans[Vo.idVo],';',' ')}"></c:out>
								</liferay-ui:search-container-column-text>
								<liferay-ui:search-container-column-jsp
									path="/WEB-INF/jsp/admin-vo-action2.jsp" align="right" />
							</c:otherwise>
						</c:choose>
					</liferay-ui:search-container-row>
					<liferay-ui:search-iterator />
				</liferay-ui:search-container>
		
				<portlet:actionURL var="addUserToVOActionUrl">
					<portlet:param name="myaction" value="goToAddUserToVOForm" />
				</portlet:actionURL>
		
				<aui:form name="addUserToVOForm" method="post"
					action="${addUserToVOActionUrl}">
		
					<aui:input name="userId" type="hidden" value="${userInfo.userId}" />
					<aui:input name="firstReg" type="hidden" value="false" />
					<aui:button-row>
						<aui:button type="submit" value="Aggiungi VO" />
						<portlet:renderURL var="voUrl">
							<portlet:param name="myaction" value="showVOList" />
							<portlet:param name="waif" value="editUserInfoForm" />
							<portlet:param name="userId" value="${userInfo.userId}"/>
						</portlet:renderURL>
						<aui:button type="cancel" value="Richiedi appartenenza VO"
										onClick="location.href='${voUrl}';" />
					</aui:button-row>
				</aui:form>
				<a name="apriVo"></a>
		
			</div>
		</div>
		
		
		
		<portlet:actionURL var="deleteURL">
			<portlet:param name="myaction" value="deleteByUser" /> 
			<portlet:param name="userId" value="${userId}" /> 
		</portlet:actionURL>
		
		<aui:form name="catalogForm" method="post" action="${deleteUrl}">
			<aui:button-row>
				<aui:button type="cancel" value="Delete Account"
								onClick="verifyDelete('${deleteURL}')" />
			</aui:button-row>
		</aui:form>

	</c:otherwise>
</c:choose>



