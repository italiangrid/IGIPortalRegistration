<%@ include file="/WEB-INF/jsp/init.jsp"%>

<div id="container2">
	${registrationModel } <br/><br/>
	<a href="https://halfback.cnaf.infn.it/app1/index.jsp">GO</a> <br/><br/>
	
	<c:forEach items="${cookie}" var="currentCookie">  

            <!-- Compare each incoming cookie with the cookies kept in the servlet,
                 if there's not a match then redirect to the login page. Otherwise,
                 show the contents of the page below --> 


            ${currentCookie.value.name} - ${currentCookie.value.value}<br/>
            
            <jsp:useBean id="registrationModel" class="it.italiangrid.portal.registration.model.RegistrationModel" />
			<c:choose>
				<c:when test="${currentCookie.value.name=='haveCertificate'}">
					<c:set target="${registrationModel }" property="haveCertificate" value="${currentCookie.value.value }"/>
				</c:when>
				<c:when test="${currentCookie.value.name=='issuer'}">
					<c:set target="${registrationModel }" property="issuer" value="${currentCookie.value.value }"/>
				</c:when>
				<c:when test="${currentCookie.value.name=='subject'}">
					<c:set target="${registrationModel }" property="subject" value="${currentCookie.value.value }"/>
				</c:when>
				<c:when test="${currentCookie.value.name=='vos'}">
					<c:set target="${registrationModel }" property="vos" value="${currentCookie.value.value }"/>
				</c:when>
				<c:when test="${currentCookie.value.name=='searchVo'}">
					<c:set target="${registrationModel }" property="searchVo" value="${currentCookie.value.value }"/> 
				</c:when>
				<c:when test="${currentCookie.value.name=='certificateUserId'}">
					<c:set target="${registrationModel }" property="certificateUserId" value="${currentCookie.value.value }"/> 
				</c:when>
			</c:choose>
            


    </c:forEach>  
     <br/><br/>
    ${registrationModel } <br/><br/>
    

<portlet:actionURL var="addUserActionUrl">
	<portlet:param name="myaction" value="addUser" />
</portlet:actionURL>
<portlet:renderURL var="homeUrl">
	<portlet:param name="myaction" value="userInfos" />
</portlet:renderURL>

<liferay-ui:error key="error-saving-registration"
	message="error-saving-registration" />
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
<liferay-ui:error key="user-username-duplicate"
	message="user-username-duplicate" />
<liferay-ui:error key="user-mail-duplicate"
	message="user-mail-duplicate" />
<liferay-ui:error key="user-phone-valid" message="user-phone-valid" />

<jsp:useBean id="userInfo" type="it.italiangrid.portal.dbapi.domain.UserInfo"
	scope="request" />
	


<aui:form name="addUserForm" commandName="userInfo"
	action="${addUserActionUrl}">

	<aui:layout>

		<h1 class="header-title">User Data</h1>

		<br></br>

		<aui:fieldset>
			<div id="<portlet:namespace/>idpOk">
				<aui:column columnWidth="25">

					<aui:fieldset label="Personal data">
						
						<strong>First Name</strong><br/>
						<aui:input label="First Name" name="firstName" type="input" value="${userInfo.firstName}" />
						
						<aui:input label="Last Name" name="lastName" type="input" value="${userInfo.lastName}" />
						
						<aui:input label="Institute" name="institute" type="input" value="${userInfo.institute}" />
						
						<aui:input name="phone" type="hidden" />
						
						<aui:input label="e-Mail addess" name="mail" type="input" value="${userInfo.mail }"/>
						
						<aui:input  name="username" type="hidden" value="${userInfo.username }"/>
						
						<aui:input  name="haveCertificate" type="hidden" value="${registrationModel.haveCertificate }"/>
						<aui:input  name="issuer" type="hidden" value="${registrationModel.issuer }"/>
						<aui:input  name="subject" type="hidden" value="${registrationModel.subject }"/>
						<aui:input  name="vos" type="hidden" value="${registrationModel.vos }"/>
						<aui:input  name="searchVo" type="hidden" value="${registrationModel.searchVo }"/>
						<aui:input  name="certificateUserId" type="hidden" value="${registrationModel.certificateUserId }"/>
						
					</aui:fieldset>

				</aui:column>
				

				<aui:button-row>
					<aui:button type="submit" value="Continue"/>
					<aui:button type="cancel" value="Abort"
						onClick="location.href='${homeUrl}';" />
				</aui:button-row>
			</div>
		</aui:fieldset>
	</aui:layout>
</aui:form>



</div>