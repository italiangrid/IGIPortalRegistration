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
	
	<c:if test="${proxyDownloaded=='false' }">
	<portlet:renderURL var="deleteURLNoProxy">
		<portlet:param name="myaction" value="removeCertNoProxy" />
		<portlet:param name="idCert" value="<%= primKey %>" />
		<portlet:param name="userId" value="<%=userId %>" />
		<portlet:param name="primCert" value="<%=primCert %>" />
	</portlet:renderURL>
	<liferay-ui:icon-delete url="javascript:$(this).modal({width:800, height:600, message:true, redirect:'https://flyback.cnaf.infn.it/web/guest/registration', myurl: 'https://www.google.com" />
	</c:if>
	<c:if test="${proxyDownloaded=='true' }">	
	<portlet:actionURL var="deleteURL">
		<portlet:param name="myaction" value="removeCert" />
		<portlet:param name="idCert" value="<%= primKey %>" />
		<portlet:param name="userId" value="<%=userId %>" />
	</portlet:actionURL>
	<liferay-ui:icon-delete url="${deleteURL}" />
	</c:if>
</liferay-ui:icon-menu>
