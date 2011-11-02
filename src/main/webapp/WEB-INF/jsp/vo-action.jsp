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


	<portlet:actionURL var="deleteURL">
		<portlet:param name="myaction" value="removeUserToVo" />
		<portlet:param name="userId"
			value="<%=request.getParameter("userId") %>" />
		<portlet:param name="idVo" value="<%=primKey %>" />
	</portlet:actionURL>
	<liferay-ui:icon-delete url="${deleteURL}" />

</liferay-ui:icon-menu>