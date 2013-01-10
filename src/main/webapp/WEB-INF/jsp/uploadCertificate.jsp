<%@ include file="/WEB-INF/jsp/init.jsp"%>

<script type="text/javascript">
<!--
	//-->
	
	var check=false;

		
	function printCheck(element){
		$('#'+element+'_img').remove();
		if(!$('#'+element).val()){
			$('#'+element).after("<img id='"+element+"_img' src='<%=request.getContextPath()%>/images/close-button2.png' width='16' height='16'  style='padding-left:5px;'/>");
		}else{
			$('#'+element).after("<img id='"+element+"_img' src='<%=request.getContextPath()%>/images/success.png' style='padding-left:5px;'/>");
		}
	}
	
	function verifyPassword() {
		var pwd1 = $("#<portlet:namespace/>password").val();
		var pwd2 = $("#<portlet:namespace/>passwordVerify").val();
		var output = "";
		if (pwd1 == pwd2) {
			$("#<portlet:namespace/>password").css("background", "#ACDFA7");
			$("#<portlet:namespace/>passwordVerify").css("background",
					"#ACDFA7");
			check=true;
			printCheck("<portlet:namespace/>passwordVerify");
			
		} else {
			$("#<portlet:namespace/>password").css("background", "#FDD");
			$("#<portlet:namespace/>passwordVerify").css("background",
					"#FF9999");
			output = "KO";
			check=false;
		}
	}
	
	function validate(){
		$(".proxyPwd").hide();
		$(".pwd").hide();
		$(".p12").hide();
		allOK = true;
		if(check==false){
			allOK = false;
			$(".proxyPwd").show();
		}
		if(!$("#<portlet:namespace/>keyPass").val()){
			allOK = false;
			$(".pwd").show();
			//alert(allOK);
		}
		if(!$("#<portlet:namespace/>usercert").val()){
			allOK = false;
			$(".p12").show();
		}
		return allOK;
	}
	

	$(document).ready(function() {

	});
</script>


<style type="text/css">
span .<portlet:namespace />pwd {
	float: right;
}

div#noteText {
	margin-top: 10px;
	text-align: justify;
	font-size: 12px;
}

h5#usernameAlert {
	margin-top: 10px;
}

#allertDiv{
	border-color: #FFCC66;
	border-width: 1px;
	border-style: solid;
	background-color: #FFFFDD;
	padding: 5px;
}

#submit{
	float:right; 
	margin-right:20px;
}

#help{
	height: 64px;
	vertical-align: middle;
	display: table-cell;
}


#action{
		width: 74%;
		box-shadow: 10px 10px 5px #888;
		border: 1px;
		border-color: #C8C9CA;
		border-style: solid;
		background-color: #EFEFEF;
		border-radius: 5px;
		padding: 10px;
		margin-right: 9px;
		margin-left: 10px;
		float: left;
	}

</style>

<div>
		<%@ include file="/WEB-INF/jsp/summary.jsp" %>	
		
		
<div id="action">

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
	<portlet:param name="myaction" value="uploadCertificate" />
</portlet:actionURL>


<portlet:renderURL var="homeUrl">
	<portlet:param name="myaction" value="editUserInfoForm" />
	<portlet:param name="userId" value="${userId}" />
</portlet:renderURL>

<aui:form name="uploadCertForm" action="${uploadCertUrl}"
	enctype="multipart/form-data" >
	<aui:input name="subject" type="hidden" value="${registrationModel.subject }"></aui:input>
	<aui:input name="issuer" type="hidden" value="${registrationModel.issuer }"></aui:input>
	<aui:input name="haveCertificate" type="hidden" value="${registrationModel.haveCertificate }"></aui:input>
	<aui:input name="certificateUserId" type="hidden" value="${registrationModel.certificateUserId }"></aui:input>
	<aui:input name="vos" type="hidden" value="${registrationModel.vos }"></aui:input>
	<aui:input name="searchVo" type="hidden" value="${registrationModel.searchVo }"></aui:input>
	<aui:input name="mail" type="hidden" value="${registrationModel.mail }"></aui:input>
	<aui:input name="haveIDP" type="hidden" value="${registrationModel.haveIDP }"></aui:input>
	<aui:input name="firstName" type="hidden" value="${registrationModel.firstName }"/>
	<aui:input name="lastName" type="hidden" value="${registrationModel.lastName }"/>
	<aui:input name="institute" type="hidden" value="${registrationModel.institute }"/>
	<aui:input name="email" type="hidden" value="${registrationModel.email }"/>
	<aui:input name="userStatus" type="hidden" value="${registrationModel.userStatus }"/>
	<aui:input name="verifyUser" type="hidden" value="${registrationModel.verifyUser }"/>
	<aui:layout>

		<h1 class="header-title">Registration - Certificate Upload</h1>

		<br></br>

		<aui:fieldset>
			
			<aui:column columnWidth="45" style="margin-left:30px;">

				<aui:fieldset>
					<div id="allertDiv2">
					<div class="portlet-msg-error p12" style="display:none;">
						Insert certificate here.
					</div>
							<aui:input id="usercert" name="usercert" type="file" label="Import certificate in P12 format"
								value="${usercert }" />

					
					<div class="portlet-msg-error pwd" style="display:none;">
						Insert password of your certificate here.
					</div>
							<aui:input id="keyPass" name="keyPass" type="password"
								label="Import certificate password" onBlur="printCheck($(this).attr('id'));"/> 
					</div>
					<br />
					
						Please insert below a new password. <br/>
						This password will be asked to use Grid and Cloud resources in a secure way. <br/>
					<div id="allertDiv2">
				
						<br/>
						<div class="portlet-msg-error proxyPwd" style="display:none;">
							These password must be the same.
						</div>

								<aui:input id="password" name="password" type="password"
									label="Insert Password" onBlur="printCheck($(this).attr('id'));"/>

						<div class="portlet-msg-error proxyPwd" style="display:none;">
							These password must be the same.
						</div>

								<aui:input id="passwordVerify" name="passwordVerify"
									type="password" label="Retype Password" onkeyup="verifyPassword();"/>

						
					</div>
						<br/>
						<strong>Note:</strong> this password will be not saved in the system.
						
					<aui:input name="primaryCert" type="hidden" value="true"/>
				</aui:fieldset>

				<br />
				<br />
			</aui:column>

			<aui:column columnWidth="40" style="margin-left:30px;">

				<aui:fieldset>
					
					<br/><br/>
					
					<div id="help">
					<a href="#"><img class="displayed"
													src="<%=request.getContextPath()%>/images/Help.png"
													id="noImg" width="64" /> Certificate Upload Help</a>
													
					</div>
					<br/><br/><br/><br/><br/><br/><br/>
					<div id="help">
					<a href="#"><img class="displayed"
													src="<%=request.getContextPath()%>/images/Information2.png"
													id="noImg" width="64" /> Technical Information</a>
													
					</div>
				</aui:fieldset>

				<br />
				<br />
			</aui:column>

			<aui:button-row>
				<aui:button type="cancel" value= "Back" onClick="history.back()"/>
				<aui:button type="submit" value="Continue" onClick="return validate();" />
				<div style="float: right;">
				<aui:button type="cancel" value="Abort Registration"
					onClick="alert('You are now registrated in the portal, please log into the portal to complete the registraion.');location.href='${loginUrl }';" />
				</div>
			</aui:button-row>
			
			

		</aui:fieldset>
	</aui:layout>
</aui:form>

<div id="footnoteP12" style="display:none;">Upload your Certificate in P12 format.</div>
<div id="footnotePwdP12" style="display:none;">Insert here the password<br/>of your certificate.</div>
<div id="footnotePwdProxy" style="display:none;">Choose a password<br/>for encrypting your proxy<br/>and storing it into MyProxy server.</div>


</div>

<div style="clear:both;"></div>
</div>
