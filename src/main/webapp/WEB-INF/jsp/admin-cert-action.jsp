<%@ include file="/WEB-INF/jsp/init.jsp"%>


<%
	ResultRow row = (ResultRow) request
			.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
	Certificate certificate = (Certificate) row.getObject();
	//long groupId = themeDisplay.getLayout().getGroupId(); 
	//String name = userInfo.getFirstName();
	UserInfo userInfo = certificate.getUserInfo();
	String primKey = String.valueOf(certificate.getIdCert());
	String userId = String.valueOf(userInfo.getUserId());
	String primCert = certificate.getPrimaryCert();
%>

<liferay-ui:icon-menu>

	
	<portlet:renderURL var="updateURL">
		<portlet:param name="myaction" value="showUpdateCert" />
		<portlet:param name="idCert" value="<%= primKey %>" />
		<portlet:param name="userId" value="<%=userId %>" />
		<portlet:param name="primCert" value="<%=primCert %>" />
	</portlet:renderURL>
	<liferay-ui:icon image="edit" message="Update Cert" url="${updateURL}" />


	<portlet:actionURL var="deleteURL">
		<portlet:param name="myaction" value="removeCert" />
		<portlet:param name="idCert" value="<%= primKey %>" />
		<portlet:param name="userId" value="<%=userId %>" />
	</portlet:actionURL>
	<liferay-ui:icon-delete url="${deleteURL}" />

</liferay-ui:icon-menu>


	<!-- <portlet:actionURL var="editURL">
		<portlet:param name="myaction" value="setDefaultCert" />
		<portlet:param name="idCert" value="<%= primKey %>" />
		<portlet:param name="userId" value="<%=userId %>" />
	</portlet:actionURL>
	<liferay-ui:icon image="edit" message="Set Default" url="${editURL}" /> -->