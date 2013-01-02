<%@ include file="/WEB-INF/jsp/init.jsp"%>

<style type="text/css">
<!--

-->


#action{
		width: 74%;
		box-shadow: 10px 10px 5px #888;
		border: 1px;
		border-color: #C8C9CA;
		border-style: solid;
		background-color: #EFEFEF;
		border-radius: 5px;
		padding: 10px;
		margin-right: 9px;
		margin-left: 10px;
		float: left;
	}

</style>

<div>
		<%@ include file="/WEB-INF/jsp/summary.jsp" %>	
		
		
<div id="action">
    

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
						
						<c:if test="${!empty  userInfo.firstName}">
							<aui:input label="First Name" name="firstName" type="input" value="${userInfo.firstName}" readonly="readonly"/>
						</c:if>
						<c:if test="${empty  userInfo.firstName}">
							<aui:input label="First Name" name="firstName" type="input" value="${userInfo.firstName}"/>
						</c:if>
						
						<c:if test="${!empty  userInfo.lastName}">
							<aui:input label="Last Name" name="lastName" type="input" value="${userInfo.lastName}" readonly="readonly"/>
						</c:if>
						<c:if test="${empty  userInfo.lastName}">
							<aui:input label="Last Name" name="lastName" type="input" value="${userInfo.lastName}" />
						</c:if>
						
						<c:if test="${!empty  userInfo.institute}">
							<aui:input label="Institute" name="institute" type="input" value="${userInfo.institute}" readonly="readonly"/>
						</c:if>
						<c:if test="${empty  userInfo.institute}">
							<aui:input label="Institute" name="institute" type="input" value="${userInfo.institute}" />
						</c:if>
						
						<aui:input name="phone" type="hidden" />
						
						<c:if test="${!empty  userInfo.mail}">
							<aui:input label="e-Mail addess" name="mail" type="input" value="${userInfo.mail }" readonly="readonly"/>
						</c:if>
						<c:if test="${empty  userInfo.mail}">
							<aui:input label="e-Mail addess" name="mail" type="input" value="${userInfo.mail }" />
						</c:if>
						
						<aui:input  name="username" type="hidden" value="${userInfo.username }"/>
						<aui:input  name="fromIDP" type="hidden" value="${fromIDP }"/>
						
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
<div style="clear:both;"></div>
</div>