<%@ include file="/WEB-INF/jsp/init.jsp"%>


<% 
	ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW); 
	UserInfo userInfo = (UserInfo)row.getObject();	
	//long groupId = themeDisplay.getLayout().getGroupId(); 
	//String name = userInfo.getFirstName();
	String primKey = String.valueOf(userInfo.getUserId());
	String username = userInfo.getUsername();
	
	//User user = (User)request.getAttribute(WebKeys.USER);
	
	Boolean test = false;
	if(username.equals(user.getScreenName()))
		test = true;
		
	pageContext.setAttribute("test",test);
	
	long companyId = PortalUtil.getCompanyId(request);
	User liferayUser = UserLocalServiceUtil.getUserByEmailAddress(companyId, userInfo.getMail());
	
	pageContext.setAttribute("liferayUser",liferayUser.getUserId());
%>

<liferay-ui:icon-menu>

	<portlet:renderURL var="editURL">
		<portlet:param name="myaction" value="editUserInfoForm" />
		<portlet:param name="userId" value="<%=primKey %>" />
	</portlet:renderURL>
	<liferay-ui:icon image="edit" message="Edit" url="${editURL}" />
	<liferay-security:doAsURL  doAsUserId="${liferayUser}"  var="impersonateUserURL"/>
	<liferay-ui:icon image="impersonate_user" target="_blank" message="Impersonate User" url="${impersonateUserURL}" />
	
	<c:if test="${!test}">
		<portlet:actionURL var="deleteURL">
			<portlet:param name="myaction" value="removeUserInfo" /> 
			<portlet:param name="userId" value="<%= primKey %>" />
		</portlet:actionURL>
		<liferay-ui:icon-delete url="${deleteURL}" />
	</c:if>

</liferay-ui:icon-menu>