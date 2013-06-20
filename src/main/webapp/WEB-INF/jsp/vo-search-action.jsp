<%@ include file="/WEB-INF/jsp/init.jsp"%>


<%
	ResultRow row = (ResultRow) request
			.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
	Vo vo = (Vo) row.getObject();
	//long groupId = themeDisplay.getLayout().getGroupId(); 
	//String name = userInfo.getFirstName();
	String primKey = String.valueOf(vo.getIdVo());
%>

<liferay-ui:icon-menu>

	<portlet:actionURL var="addUserToVOActionUrl">
		<portlet:param name="myaction" value="addVo" />
		<portlet:param name="VOids" value="<%=primKey %>" />
		
	</portlet:actionURL>
	<liferay-ui:icon image="edit" message="Add VO" url="${addUserToVOActionUrl}" />

</liferay-ui:icon-menu>