<%@ include file="/WEB-INF/jsp/init.jsp"%>


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
<liferay-ui:error key="no-valid-key"
	message="no-valid-key" />
<liferay-ui:error key="key-password-failure"
	message="key-password-failure" />
	
	

<portlet:actionURL var="uploadCertUrl">
	<portlet:param name="myaction" value="uploadCert" />
</portlet:actionURL>

<aui:form name="uploadCertForm" method="post" action="${uploadCertUrl}"
	enctype="multipart/form-data">
	<aui:layout>

		<h1 class="header-title">Upload Certificato</h1>

		<portlet:renderURL var="homeUrl">
			<portlet:param name="myaction" value="userInfos" />
		</portlet:renderURL>
		<a href="${homeUrl}">Home</a>

		<br></br>

		<aui:fieldset>

			<aui:column columnWidth="25">

				<aui:fieldset label="Upload Certificate">
					<br />
					<%
						if (request.getParameter("userId") != null)
												pageContext.setAttribute("userId",
														request.getParameter("userId"));
											if (request.getParameter("username") != null)
												pageContext.setAttribute("username",
														request.getParameter("username"));
					%>


					<aui:input name="userId" type="hidden" value="${userId}" />
					<aui:input name="username" type="hidden" value="${username}" />
					<aui:input name="firstReg" type="hidden" value="${firstReg}" />

					<aui:input name="usercert" type="file" label="Certificato"
						value="${usercert }" />
					<aui:input name="userkey" type="file" label="Chiave"
						value="${userkey }" />

					<aui:input id="keyPass" name="keyPass" type="password"
						label="Password Della Chiave Privata" />

				</aui:fieldset>

				<br />
				<br />
			</aui:column>

			<aui:column columnWidth="25">

				<aui:fieldset label="Sicurezza e Opzioni">
					<br />
					
					
					Per recuperare il certificato dovrai usare questi dati:
					
					<aui:input id="password" name="password" type="password"
						label="Password" value="${password }" />

					<aui:input id="passwordVerify" name="passwordVerify"
						type="password" label="Retype Password" value="${passwordVerify }"
						onkeyup="verifyPassword();" />

					<aui:input name="primaryCert" type="checkbox" value="true"
						label="Ritieni questo certificato come principale" />

				</aui:fieldset>

				<br />
				<br />
			</aui:column>

			<aui:column columnWidth="25">

				<aui:fieldset label="Note">
					<br />

					<strong>Non dimenticare questi dati.</strong>
					<br />
					<div id="noteText">
						Lo <strong>username</strong> da usare per il recupero del
						certificato proxy verr&agrave indicato nella tabella riassuntiva
						dei certificati tramite la moce "Edit" del men&ugrave "Actions".<br />
						<br /> Le <strong>password</strong> che hai inserito non verranno
						salvate. Nel caso di smarrimento password non sar&agrave possibile
						recuperarle e sar&agrave necessario eliminare il certificato
						salvato e rieffettuare l'upload del certificato utente. Mantenerle
						quindi in luogo sicuro.
						</p>
					</div>
				</aui:fieldset>

				<br />
				<br />
			</aui:column>

			<aui:button-row>
				<aui:button type="submit" value="Upload Certificate" />
				<c:choose>
					<c:when test="${firstReg == false}">

						<aui:button type="cancel" value="Home"
							onClick="location.href='${homeUrl}';" />
					</c:when>
					<c:otherwise>
						<portlet:renderURL var="backURL">
							<portlet:param name="myaction" value="editUserInfoForm" />
							<portlet:param name="userId" value="${userId}" />
						</portlet:renderURL>
						<aui:button type="cancel" value="Indietro"
							onClick="location.href='${backURL}';" />
					</c:otherwise>
				</c:choose>

			</aui:button-row>

		</aui:fieldset>
	</aui:layout>
</aui:form>
