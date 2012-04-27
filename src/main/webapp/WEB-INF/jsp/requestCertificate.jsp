<%@ include file="/WEB-INF/jsp/init.jsp"%>

<portlet:renderURL var="homeUrl">
	<portlet:param name="myaction" value="userInfos" />
</portlet:renderURL>


<h1 class="header-title">Request User Certificate</h1>

<a href="${homeUrl}">Home</a>

<br/><br/>

Istruction for certificate request.