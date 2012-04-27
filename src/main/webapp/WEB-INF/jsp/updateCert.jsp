<%@ include file="/WEB-INF/jsp/init.jsp"%>

<script type="text/javascript">
<!--
	//-->

	function verifyPassword() {
		var pwd1 = $("#<portlet:namespace/>password").val();
		var pwd2 = $("#<portlet:namespace/>passwordVerify").val();
		var output = "";
		if (pwd1 == pwd2) {
			$("#<portlet:namespace/>password").css("background", "#ACDFA7");
			$("#<portlet:namespace/>passwordVerify").css("background",
					"#ACDFA7");
		} else {
			$("#<portlet:namespace/>password").css("background", "#FDD");
			$("#<portlet:namespace/>passwordVerify").css("background",
					"#FF9999");
			output = "KO";
		}
	}

	$(document).ready(function() {

	});
</script>


<style>
span .<portlet:namespace />pwd {
	float: right;
}

div#noteText {
	margin-top: 10px;
	text-align: justify;
}

h5#usernameAlert {
	margin-top: 10px;
}
</style>

<liferay-ui:success key="user-saved-successufully"
	message="user-saved-successufully" />
<liferay-ui:error key="error-uploading-certificate"
	message="error-uploading-certificate" />
<liferay-ui:error key="myproxy-exception" message="myproxy-exception" />
<liferay-ui:error key="cert-password-incorrect"
	message="cert-password-incorrect" />
<liferay-ui:error key="cert-pass1-required"
	message="cert-pass1-required" />
<liferay-ui:error key="cert-pass2-required"
	message="cert-pass2-required" />
<liferay-ui:error key="no-valid-cert" message="no-valid-cert" />
<liferay-ui:error key="key-pass-required" message="key-pass-required" />
<liferay-ui:error key="no-valid-cert-subject"
	message="no-valid-cert-subject" />
<liferay-ui:error key="no-valid-cert-issuer"
	message="no-valid-cert-issuer" />
<liferay-ui:error key="no-valid-cert-enddate"
	message="no-valid-cert-enddate" />
<liferay-ui:error key="user-certificate-expired"
	message="user-certificate-expired" />
<liferay-ui:error key="certificate-duplicate"
	message="certificate-duplicate" />
<liferay-ui:error key="error-certificate-to-update"
	message="error-certificate-to-update" />
<liferay-ui:error key="no-valid-key"
	message="no-valid-key" />
<liferay-ui:error key="error-password-too-short"
	message="error-password-too-short" />
<liferay-ui:error key="key-password-failure"
	message="key-password-failure" />







<portlet:actionURL var="updateCertUrl">
	<portlet:param name="myaction" value="updateCert" />
</portlet:actionURL>

<aui:form name="uploadCertForm" action="${updateCertUrl}"
	enctype="multipart/form-data">
	<aui:layout>

		<h1 class="header-title">Update Certificate</h1>

		<portlet:renderURL var="homeUrl">
			<portlet:param name="myaction" value="userInfos" />
		</portlet:renderURL>

		<br></br>

		<aui:fieldset>

			<aui:column columnWidth="25">

				<aui:fieldset label="Upload Certificate">
					<br />
					<%
						if (request.getParameter("userId") != null)
												pageContext.setAttribute("userId",
														request.getParameter("userId"));
						if (request.getParameter("idCert") != null)
							pageContext.setAttribute("idCert",
									request.getParameter("idCert"));
						if (request.getParameter("primCert") != null)
							pageContext.setAttribute("primCert",
									request.getParameter("primCert"));
					%>


					<aui:input name="userId" type="hidden" value="${userId}" />
					<aui:input name="idCert" type="hidden" value="${idCert}" />

					<aui:input name="usercert" type="file" label="p12 format Certificate"
						value="${usercert }" />
					<!--<aui:input name="userkey" type="file" label="Chiave"
						value="${userkey }" />-->

					<aui:input id="keyPass" name="keyPass" type="password"
						label="Password of your certificate" />

				</aui:fieldset>

				<br />
				<br />
			</aui:column>

			<aui:column columnWidth="25">

				<aui:fieldset label="Security and Option">
					<br />

					Insert a password that will be used for proxy retrieval.<br/>
					In the future we will request you only this password for using the portal.

					<aui:input id="password" name="password" type="password"
						label="Password" value="${password }" />

					<aui:input id="passwordVerify" name="passwordVerify"
						type="password" label="Retype Password" value="${passwordVerify }"
						onkeyup="verifyPassword();" />

					<aui:input name="primaryCert" type="checkbox" value="${primCert}"
						label="This is default certificate" />

				</aui:fieldset>

				<br />
				<br />
			</aui:column>

			<aui:column columnWidth="25">

				<aui:fieldset label="Note">
					<br />

					<strong>Don't forget these data.</strong>
					<br />
					<div id="noteText">
						
						<br /> The <strong>password</strong> that you have insert will 
						not be saved. If you lose your password can not be
						recover and will need to delete the certificate
						saved and log in again to upload the user certificate. Keep them
						so in a safe place.
						
					</div>
				</aui:fieldset>

				<br />
				<br />
			</aui:column>

			<aui:button-row>
				<aui:button type="submit" value="Update Certificate" />
				<aui:button type="cancel" value="Back"
					onClick="location.href='${homeUrl}';" />

			</aui:button-row>

		</aui:fieldset>
	</aui:layout>
</aui:form>