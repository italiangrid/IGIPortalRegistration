<%@ include file="/WEB-INF/jsp/init.jsp"%>


<% 
	ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW); 
	UserInfo userInfo = (UserInfo)row.getObject();	
	//long groupId = themeDisplay.getLayout().getGroupId(); 
	//String name = userInfo.getFirstName();
	String primKey = String.valueOf(userInfo.getUserId());
%>

<liferay-ui:icon-menu>

	<portlet:renderURL var="editURL">
		<portlet:param name="myaction" value="editUserInfoForm" />
		<portlet:param name="userId" value="<%=primKey %>" />
	</portlet:renderURL>
	<liferay-ui:icon image="edit" message="Edit" url="${editURL}" />


<portlet:actionURL var="deleteURL">
	<portlet:param name="myaction" value="removeUserInfo" /> 
	<portlet:param name="userId" value="<%= primKey %>" />
</portlet:actionURL>
<liferay-ui:icon-delete url="${deleteURL}" />

</liferay-ui:icon-menu>