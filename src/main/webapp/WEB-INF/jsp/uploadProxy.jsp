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
		
		return allOK;
	}
	

	function submit(){
		if(validate()){
		$("#<portlet:namespace/>uploadProxyForm").submit();
		}
	}

	$(document).ready(function() {
		$(".taglib-text").css("text-decoration","none");
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


.button{
	margin: 5px;
	text-decoration: none;

}

.button a{
    -moz-border-bottom-colors: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    background: url("<%=request.getContextPath()%>/images/header_bg.png") repeat-x scroll 0 0 #D4D4D4;
    border-color: #C8C9CA #9E9E9E #9E9E9E #C8C9CA;
    border-image: none;
    border-style: solid;
    border-width: 1px;
    color: #34404F;
    cursor: pointer;
    font-weight: bold;
    overflow: visible;
    padding: 5px;
    text-shadow: 1px 1px #FFFFFF;
    width: auto;
    border-radius: 4px 4px 4px 4px;
    text-decoration: none;
    margin: 1px;
}

.button:hover a{
    background: url("<%=request.getContextPath()%>/images/state_hover_bg.png") repeat-x scroll 0 0 #B9CED9;
    border-color: #627782;
    color: #336699;
    
}

.button img{
	text-decoration: none;
	margin-top: -1px;
}
</style>

<div>
		<%@ include file="/WEB-INF/jsp/summary.jsp" %>	
		
		
<div id="action">

<liferay-ui:error key="myproxy-exception" message="myproxy-exception" />


<liferay-ui:error key="no-valid-cert" message="no-valid-cert" />
<liferay-ui:error key="no-valid-key"
	message="no-valid-key" />
<liferay-ui:error key="key-password-failure"
	message="key-password-failure" />
<liferay-ui:error key="password-not-changed"
	message="password-not-changed" />
<liferay-ui:error key="error-password-mismatch"
	message="error-password-mismatch" />
<liferay-ui:error key="error-password-too-short"
	message="error-password-too-short" />


<portlet:actionURL var="uploadCertUrl">
	<portlet:param name="myaction" value="uploadProxy" />
</portlet:actionURL>


<portlet:renderURL var="homeUrl">
	<portlet:param name="myaction" value="editUserInfoForm" />
	<portlet:param name="userId" value="${userId}" />
</portlet:renderURL>

<aui:form name="uploadProxyForm" id="uploadPorxyForm" action="${uploadCertUrl}" commandName="registrationModel">
	<aui:input name="subject" type="hidden" value="${registrationModel.subject }"></aui:input>
	<aui:input name="issuer" type="hidden" value="${registrationModel.issuer }"></aui:input>
	<aui:input name="expiration" type="hidden" value="${registrationModel.expiration }"></aui:input>
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
	<aui:input name="certificateStatus" type="hidden" value="${registrationModel.certificateStatus }"/>
	<aui:input name="voStatus" type="hidden" value="${registrationModel.voStatus }"/>
	<aui:layout>

		

		<aui:fieldset label="Registration">
		
		<br/><br/>
		<img src="<%=request.getContextPath()%>/images/registration_step4.png"/>
		<br/><br/><br/>
			
			<aui:fieldset>
			
			<div class="portlet-msg-alert">Please choose a password to encrypt your credentials.<br/>
			You have to insert this password every time you need to retrieve your credentials, don't forget it because we don't conserve it !!</div>
			
					
			
			<aui:column columnWidth="40" style="margin-left:30px;">
					
					<aui:fieldset>
						
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
									type="password" label="Confirm Password" onkeyup="verifyPassword();"/>

						
					</div>
						
						
						
					
				</aui:fieldset>

				
				
			</aui:column>

			<aui:column columnWidth="50" style="margin-left:30px;">

				<aui:fieldset>
					
					<br/><br/><br/>
					
					<div id="help">
					<a href="https://portal.italiangrid.it:8443/info/certificate-upload-technical-info.html"  onclick="$(this).modal({width:800, height:600, message:true}).open(); return false;"><img class="displayed"
													src="<%=request.getContextPath()%>/images/Information2.png"
													id="noImg" width="48" />Technical Information</a>
					 							
			
													
					</div>
				</aui:fieldset>

				
			</aui:column>
			
			</aui:fieldset>

			<aui:button-row>
				
				<div class="button" style="float: left;">
				<liferay-ui:icon-menu>
				<liferay-ui:icon image="close" message="Abort Registration" url="#"
					onClick="alert('You are now registrated in the portal, please log into the portal to complete the registraion.');location.href='${loginUrl }';" />
				</liferay-ui:icon-menu>
				</div>
				<div class="button" style="float: right;">
				<liferay-ui:icon-menu>
				<liferay-ui:icon image="forward" message="Continue" url="#" onClick="submit();" />
				</liferay-ui:icon-menu>
				</div>
			
				<aui:button type="cancel" value= "Back" onClick="history.back()"  style="display:none;"/>
				<aui:button type="cancel" value="Abort Registration"  style="display:none;"
					onClick="alert('You are now registrated in the portal, please log into the portal to complete the registraion.');location.href='${loginUrl }';" />
				<div style="float: right;">
				<aui:button type="submit" value="Continue" onClick="return validate();"  style="display:none;"/>
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
