<%@ include file="/WEB-INF/jsp/init.jsp"%>


<%
	ResultRow row = (ResultRow) request
			.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
	Vo vo = (Vo) row.getObject();
	//long groupId = themeDisplay.getLayout().getGroupId(); 
	//String name = userInfo.getFirstName();
	String primKey = String.valueOf(vo.getIdVo());
	
	if (request.getParameter("userId") != null){
		pageContext.setAttribute("userId",request.getParameter("userId"));
		//out.println("userId = " + request.getParameter("userId"));
	}
%>

<liferay-ui:icon-menu>

	<portlet:renderURL var="editURL">
		<portlet:param name="myaction" value="showEditVo" />
		<portlet:param name="userId" value="${userId}" />
		<portlet:param name="idVo" value="<%=primKey %>" />
	</portlet:renderURL>
	<liferay-ui:icon image="edit" message="Set Role VO" url="${editURL}" />

	<portlet:actionURL var="defaultURL">
		<portlet:param name="myaction" value="setDefaultUserToVoEdit" />
		<portlet:param name="userId" value="${userId}" />
		<portlet:param name="idVo" value="<%=primKey %>" />
	</portlet:actionURL>
	<liferay-ui:icon image="tag" message="Set Default" url="${defaultURL}" />


	<portlet:actionURL var="deleteURL">
		<portlet:param name="myaction" value="removeUserToVoEdit" />
		<portlet:param name="userId" value="${userId}" />
		<portlet:param name="idVo" value="<%=primKey %>" />
	</portlet:actionURL>
	<liferay-ui:icon-delete url="${deleteURL}" />

</liferay-ui:icon-menu>