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

<div id="container">

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
	
<%
				if (request.getParameter("userId") != null)
										pageContext.setAttribute("userId",
												request.getParameter("userId"));
									if (request.getParameter("username") != null)
										pageContext.setAttribute("username",
												request.getParameter("username"));
									if (request.getParameter("firstReg") != null)
										pageContext.setAttribute("firstReg",
												request.getParameter("firstReg"));
									else
										pageContext.setAttribute("firstReg","false");
											
			%>

<portlet:actionURL var="uploadCertUrl">
	<portlet:param name="myaction" value="uploadCert" />
	<portlet:param name="userId" value="${userId }" />
	<portlet:param name="firstReg" value="${firstReg }" />
</portlet:actionURL>


<portlet:renderURL var="homeUrl">
	<portlet:param name="myaction" value="editUserInfoForm" />
	<portlet:param name="userId" value="${userId}" />
</portlet:renderURL>

<aui:form name="uploadCertForm" action="${uploadCertUrl}"
	enctype="multipart/form-data" >
	<aui:layout>

		<h1 class="header-title">Upload New Certificate</h1>

		<br></br>

		<aui:fieldset>
			
			
				
			
			<aui:column columnWidth="20">
				
				<aui:fieldset label="Registration">
					<br />
					
					<img src="<%=request.getContextPath()%>/images/step2.png"/>
					<!-- <img src="https://gridlab17.cnaf.infn.it/image/image_gallery?img_id=12347&t=1326102175108" alt="Fase 2" /> -->
					

				</aui:fieldset>
				
				<br />
				<br />
			</aui:column>
			
			<aui:column columnWidth="20" style="margin-left:30px;">

				<aui:fieldset label="Upload Certificate">
					<br />
					


					<aui:input name="userId" type="hidden" value="${userId}" />
					<aui:input name="username" type="hidden" value="${username}" />
					<aui:input name="firstReg" type="hidden" value="${firstReg}" />
					
					<div class="portlet-msg-error p12" style="display:none;">
						Insert certificate here.
					</div>
					<aui:input id="usercert" name="usercert" type="file" label="p12 format certificate"
						value="${usercert }" />
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

			<aui:column columnWidth="20" style="margin-left:30px;">

				<aui:fieldset label="Insert New Password">
					<br />
					
					<div class="portlet-msg-error proxyPwd" style="display:none;">
						These password must be the same.
					</div>
					
					<aui:input id="password" name="password" type="password"
						label="Password" onBlur="printCheck($(this).attr('id'));"/>
					
					<div class="portlet-msg-error proxyPwd" style="display:none;">
						These password must be the same.
					</div>
					
					<aui:input id="passwordVerify" name="passwordVerify"
						type="password" label="Retype Password" onkeyup="verifyPassword();" onBlur="printCheck($(this).attr('id'));"/>
					
					<br />
					
					<div style="float:left; width: 70%;">Insert a password that will be used for proxy retrieval.<br/>
					<strong>In the future we will request you only this password for using the portal.</strong></div>
					<div style="float:left; width: 30%;"><img src="<%=request.getContextPath()%>/images/emblem-important.png"   /></div>
					<div style="clear:left;"></div>

					<aui:input name="primaryCert" type="hidden" value="true"/>

				</aui:fieldset>

				<br />
				<br />
			</aui:column>

			<aui:column columnWidth="20"  style="margin-left:30px;">

				<aui:fieldset label="Note">
					<br />

					<strong>Don't forget these data</strong>
					<br />
					<div id="noteText">
						The <strong>password</strong> that you have insert will 
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
				
				
				<c:if test="${firstReg == false}">
					<aui:button type="submit" value="Upload Certificate" onClick="return validate();"/>
						<aui:button type="cancel" value="Back"
							onClick="location.href='${homeUrl}';" />
				</c:if>
				
				<c:if test="${firstReg == true}">
					<aui:button type="submit" value="Continue" onClick="return validate();"/>
						<aui:button type="cancel" value="Back"
							onClick="location.href='https://halfback.cnaf.infn.it/casshib/shib/app4/login?service=https%3A%2F%2Fgridlab04.cnaf.infn.it%2Fc%2Fportal%2Flogin%3Fp_l_id%3D10671';" />
				</c:if>
					

			</aui:button-row>
			
			

		</aui:fieldset>
	</aui:layout>
</aui:form>

</div>