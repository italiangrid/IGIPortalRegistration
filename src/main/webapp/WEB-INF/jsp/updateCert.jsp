<%@ include file="/WEB-INF/jsp/init.jsp"%>

<script type="text/javascript">
<!--
	//-->

	var check=false;

	function verifyPassword() {
		var pwd1 = $("#<portlet:namespace/>password").val();
		var pwd2 = $("#<portlet:namespace/>passwordVerify").val();
		var output = "";
		if (pwd1 == pwd2) {
			$("#<portlet:namespace/>password").css("background", "#ACDFA7");
			$("#<portlet:namespace/>passwordVerify").css("background",
					"#ACDFA7");
			check=true;
		} else {
			$("#<portlet:namespace/>password").css("background", "#FDD");
			$("#<portlet:namespace/>passwordVerify").css("background",
					"#FF9999");
			output = "KO";
			check=false;
		}
	}
	
	function printCheck(element){
		$('#'+element+'_img').remove();
		if(!$('#'+element).val()){
			$('#'+element).after("<img id='"+element+"_img' src='<%=request.getContextPath()%>/images/close-button2.png' width='16' height='16'  style='padding-left:5px;'/>");
		}else{
			$('#'+element).after("<img id='"+element+"_img' src='<%=request.getContextPath()%>/images/success.png' style='padding-left:5px;'/>");
		}
	}
	
	function validate(){
		$(".proxyPwd").hide();
		$(".pwd").hide();
		$(".p12").hide();
		allOK = true;
		//alert(allOK);
		if(check==false){
			allOK = false;
			$(".proxyPwd").show();
			//alert(allOK);
		}
		//alert("valore: " + $("#<portlet:namespace/>keyPass").val());
		//alert("check: " + !$("#<portlet:namespace/>keyPass").val());
		if(!$("#<portlet:namespace/>keyPass").val()){
			allOK = false;
			$(".pwd").show();
			//alert(allOK);
		}
		//alert($("#<portlet:namespace/>usercert").val());
		//alert(!$("#<portlet:namespace/>usercert").val());
		if(!$("#<portlet:namespace/>usercert").val()){
			allOK = false;
			$(".p12").show();
			//alert(allOK);
		}
		//alert(allOK);
		return allOK;
		//return false;
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

<aui:form name="uploadCertForm" action="${updateCertUrl}"
	enctype="multipart/form-data">
	<aui:layout>

		<h1 class="header-title">Update Certificate</h1>

		<portlet:renderURL var="homeUrl">
			<portlet:param name="myaction" value="userInfos" />
		</portlet:renderURL>

		<br></br>

		<aui:fieldset>

			<aui:column columnWidth="25" style="margin-left:30px;">

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
					
					<portlet:renderURL var="homeUrl">
						<portlet:param name="myaction" value="editUserInfoForm" />
						<portlet:param name="userId" value="${userId}" />
					</portlet:renderURL>


					<aui:input name="userId" type="hidden" value="${userId}" />
					<aui:input name="idCert" type="hidden" value="${idCert}" />


					<div class="portlet-msg-error p12" style="display:none;">
						Insert certificate here.
					</div>
					<aui:input name="usercert" type="file" label="p12 format Certificate"
						 />
					<!--<aui:input name="userkey" type="file" label="Chiave"
						value="${userkey }" />-->
					
					
					<div class="portlet-msg-error pwd" style="display:none;">
						Insert password of your certificate here.
					</div>
					<aui:input id="keyPass" name="keyPass" type="password"
						label="Password of your certificate" onBlur="printCheck($(this).attr('id'));"/>

				</aui:fieldset>

				<br />
				<br />
			</aui:column>

			<aui:column columnWidth="25" style="margin-left:30px;">

				<aui:fieldset label="Security and Option">
					<br />
					<div class="portlet-msg-error proxyPwd" style="display:none;">
						These password must be the same.
					</div>
					<aui:input id="password" name="password" type="password"
						label="Password"  onBlur="printCheck($(this).attr('id'));"/>
					<div class="portlet-msg-error proxyPwd" style="display:none;">
						These password must be the same.
					</div>
					<aui:input id="passwordVerify" name="passwordVerify"
						type="password" label="Retype Password"
						onkeyup="verifyPassword();"  onBlur="printCheck($(this).attr('id'));"/>

					<aui:input name="primaryCert" type="checkbox" value="${primCert}"
						label="This is default certificate" />
					<br/>
					<div style="float:left; width: 70%;">Insert a password that will be used for proxy retrieval.<br/>
					<strong>In the future we will request you only this password for using the portal.</strong></div>
					<div style="float:left; width: 30%;"><img src="<%=request.getContextPath()%>/images/emblem-important.png"   /></div>
					<div style="clear:left;"></div>
				</aui:fieldset>

				<br />
				<br />
			</aui:column>

			<aui:column columnWidth="25" style="margin-left:30px;">

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
				<aui:button type="submit" value="Update Certificate"  onClick="return validate();"/>
				<aui:button type="cancel" value="Back"
					onClick="location.href='${homeUrl}';" />

			</aui:button-row>

		</aui:fieldset>
	</aui:layout>
</aui:form>