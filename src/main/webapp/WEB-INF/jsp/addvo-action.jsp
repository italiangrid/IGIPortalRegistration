<%@ include file="/WEB-INF/jsp/init.jsp"%>


<%
	ResultRow row = (ResultRow) request
			.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
	Vo vo = (Vo) row.getObject();
	String primKey = String.valueOf(vo.getIdVo());
%>

<liferay-ui:icon-menu>

	<portlet:renderURL var="editURL">
		<portlet:param name="myaction" value="showEditFQAN" />
		<portlet:param name="idVo" value="<%=primKey %>" />
		<portlet:param name="subject" value="${registrationModel.subject }"/>
		<portlet:param name="issuer" value="${registrationModel.issuer }"/>
		<portlet:param name="expiration" value="${registrationModel.expiration }"/>
		<portlet:param name="haveCertificate" value="${registrationModel.haveCertificate }"/>
		<portlet:param name="certificateUserId" value="${registrationModel.certificateUserId }"/>
		<portlet:param name="vos" value="${registrationModel.vos }"/>
		<portlet:param name="searchVo" value="${registrationModel.searchVo }"/>
		<portlet:param name="mail" value="${registrationModel.mail }"/>
		<portlet:param name="haveIDP" value="${registrationModel.haveIDP }"/>
		<portlet:param name="firstName" value="${registrationModel.firstName }"/>
		<portlet:param name="lastName" value="${registrationModel.lastName }"/>
		<portlet:param name="institute" value="${registrationModel.institute }"/>
		<portlet:param name="email" value="${registrationModel.email }"/>
		<portlet:param name="userStatus" value="${registrationModel.userStatus }"/>
		<portlet:param name="certificateStatus" value="${registrationModel.certificateStatus }"/>
		<portlet:param name="voStatus" value="${registrationModel.voStatus }"/>
		<portlet:param name="verifyUser" value="${registrationModel.verifyUser }"/>
	</portlet:renderURL>
	<liferay-ui:icon image="edit" message="Set Role VO" url="${editURL}" />
	
	<portlet:actionURL var="editURL">
		<portlet:param name="myaction" value="setDefaultVo" />
		<portlet:param name="idVo" value="<%=primKey %>" />
		<portlet:param name="subject" value="${registrationModel.subject }"/>
		<portlet:param name="issuer" value="${registrationModel.issuer }"/>
		<portlet:param name="expiration" value="${registrationModel.expiration }"/>
		<portlet:param name="haveCertificate" value="${registrationModel.haveCertificate }"/>
		<portlet:param name="certificateUserId" value="${registrationModel.certificateUserId }"/>
		<portlet:param name="vos" value="${registrationModel.vos }"/>
		<portlet:param name="searchVo" value="${registrationModel.searchVo }"/>
		<portlet:param name="mail" value="${registrationModel.mail }"/>
		<portlet:param name="haveIDP" value="${registrationModel.haveIDP }"/>
		<portlet:param name="firstName" value="${registrationModel.firstName }"/>
		<portlet:param name="lastName" value="${registrationModel.lastName }"/>
		<portlet:param name="institute" value="${registrationModel.institute }"/>
		<portlet:param name="email" value="${registrationModel.email }"/>
		<portlet:param name="userStatus" value="${registrationModel.userStatus }"/>
		<portlet:param name="certificateStatus" value="${registrationModel.certificateStatus }"/>
		<portlet:param name="voStatus" value="${registrationModel.voStatus }"/>
		<portlet:param name="verifyUser" value="${registrationModel.verifyUser }"/>
	</portlet:actionURL>
	<liferay-ui:icon image="tag" message="Default VO" url="${editURL}" />

	<portlet:actionURL var="deleteURL">
		<portlet:param name="myaction" value="deleteVo" />
		<portlet:param name="idVo" value="<%=primKey %>" />
		<portlet:param name="subject" value="${registrationModel.subject }"/>
		<portlet:param name="issuer" value="${registrationModel.issuer }"/>
		<portlet:param name="expiration" value="${registrationModel.expiration }"/>
		<portlet:param name="haveCertificate" value="${registrationModel.haveCertificate }"/>
		<portlet:param name="certificateUserId" value="${registrationModel.certificateUserId }"/>
		<portlet:param name="vos" value="${registrationModel.vos }"/>
		<portlet:param name="searchVo" value="${registrationModel.searchVo }"/>
		<portlet:param name="mail" value="${registrationModel.mail }"/>
		<portlet:param name="haveIDP" value="${registrationModel.haveIDP }"/>
		<portlet:param name="firstName" value="${registrationModel.firstName }"/>
		<portlet:param name="lastName" value="${registrationModel.lastName }"/>
		<portlet:param name="institute" value="${registrationModel.institute }"/>
		<portlet:param name="email" value="${registrationModel.email }"/>
		<portlet:param name="userStatus" value="${registrationModel.userStatus }"/>
		<portlet:param name="certificateStatus" value="${registrationModel.certificateStatus }"/>
		<portlet:param name="voStatus" value="${registrationModel.voStatus }"/>
		<portlet:param name="verifyUser" value="${registrationModel.verifyUser }"/>
	</portlet:actionURL>
	<liferay-ui:icon-delete url="${deleteURL}" />

</liferay-ui:icon-menu>
