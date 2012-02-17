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
	if (request.getParameter("firstReg") != null){
		pageContext.setAttribute("firstReg",request.getParameter("firstReg"));
		//out.println("userId = " + request.getParameter("userId"));
	}
%>

<liferay-ui:icon-menu>

	<portlet:actionURL var="addUserToVOActionUrl">
		<portlet:param name="myaction" value="addUserToVO" />
		<portlet:param name="userId"
			value="<%=request.getParameter("userId") %>" />
		<portlet:param name="VOids" value="<%=primKey %>" />
		<portlet:param name="firstReg" value="${firstReg}" />
		
	</portlet:actionURL>
	<liferay-ui:icon image="edit" message="Add VO" url="${addUserToVOActionUrl}" />

</liferay-ui:icon-menu>