<%@ include file="/WEB-INF/jsp/init.jsp"%>


<script type="text/javascript">
<!--
	//-->

	function setHaveCert(value) {

		$("#<portlet:namespace/>haveCertificate").attr("value", value);

		if (value == 'true') {

			$("#noImg").attr("style", "opacity:1.0; filter:alpha(opacity=100);");
			$("#yesImg").attr("style", "opacity:0.4; filter:alpha(opacity=40);");
			$("#<portlet:namespace/>haveCertificate").val("true");
			$("#<portlet:namespace/>askForCertificate").submit();
		} else {

			$("#yesImg").attr("style", "opacity:1.0; filter:alpha(opacity=100);");
			$("#noImg").attr("style", "opacity:0.4; filter:alpha(opacity=40);");
			$("#<portlet:namespace/>askForCertificate").submit();
		}
	}
	
	function submit(){
		
		$("#<portlet:namespace/>askForCertificate").submit();
	}

	$(document).ready(function() {
		$(".taglib-text").css("text-decoration","none");
	});
	
	
	
</script>

<style>
<!--
-->
#chooseTable td {
	margin: 5px;
}

#chooseContainer {
	font-size: 12px;
	width: 100%;
	margin-left: auto;
	margin-right: auto;
}

.type{
	width: 200px;
	height: 155px;
	float: left;
	margin: 0px 0px 0px 15%;
}

.choose {
	width: 200px;
	height: 150px;
	margin
}

.bordered {
	background-color: #f4fdef;
	border: 1px;
	border-style: solid;
	border-color: #ACDFA7;
	border-radius: 5px;
	-moz-border-radius: 5px;
	padding: 8px;
}

.bordered-disabled {
	border: 1px;
	border-style: solid;
	border-color: grey;
	border-radius: 5px;
	-moz-border-radius: 5px;
	padding: 8px;
	opacity: .4; 
	filter: alpha(opacity=40);
}

.mess {
	height: 65px;
	width: 200px;
	vertical-align: middle;
	float: left;
}

.title {
	height: 20px;
	width: 200px;
	vertical-align: middle;
	text-align: center;
	float: left;
	font-size: 18px;
}

.myIcon {
	width: 64px;
	margin: auto;
	vertical-align: middle;
}

.iconContainer {
	height: 70px;
	width: 200px;
	float: left;
	padding-top: 14px;
}

.reset {
	clear: both;
}

#or {
	text-align: center;
	height: 80px;
	width: 250px;
	vertical-align: middle;
	display: table-cell;
}

.center {
	height: 65px;
	width: 200px;
	vertical-align: middle;
	text-align: center;
	display: table-cell;
}

.moreInfo{
	text-align: right;
	font-size: 9px;
}

#action {
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
	<%@ include file="/WEB-INF/jsp/summary.jsp"%>


	<div id="action">

		<liferay-ui:success key="user-saved-successufully"
			message="user-saved-successufully" />

		<portlet:actionURL var="askForCertificateUrl">
			<portlet:param name="myaction" value="askForCertificateRedirect" />
		</portlet:actionURL>

		<portlet:renderURL var="homeUrl">
			<portlet:param name="myaction" value="userInfos" />
		</portlet:renderURL>

		<!--  -->
		
		

		<aui:form name="askForCertificate" id="askForCertificate"
			action="${askForCertificateUrl}" commandName="registrationModel">
			<aui:layout>

				<aui:fieldset label="Registration">
				<br/><br/>
				<img src="<%=request.getContextPath()%>/images/registration_step2.png"/>
				<br/>

						<aui:fieldset>
							<br />
							<br />
							
							<c:if test="${!registrationModel.haveIDP }" >
							<div class="portlet-msg-alert">
							Depending on the the data collected in the previous step some profiles could be not selectable.
							</div>
							
							</c:if>
							<br />
							<br />
							<div id="chooseContainer">
									<c:if test="${registrationModel.haveIDP }" >
									<div class="type">
									
									<a href="" onclick="setHaveCert('false'); return false;">
									
										<div class="choose bordered">
											<div class="title">
												<strong>New User</strong>
											</div>
											<div class="iconContainer">
												<div class="myIcon">
													
													<img class="displayed"
														src="<%=request.getContextPath()%>/images/NewUser.png"
														id="yesImg" width="64" />
													
												</div>
											</div>
											<div class="reset"></div>
											<div class="mess">
												<div class="center">I don't have Grid credentials and I'd like to have them now.</div>
											</div>
											<div class="reset"></div>
											
										</div>
									
									</a>
									
									<div class="reset"></div>
									<div class="moreInfo">
										<a href="https://portal.italiangrid.it:8443/info/user-profile-new-user.html"  onclick="$(this).modal({width:800, height:250, message:true}).open(); return false;">More Info</a>
									</div>
									</div>
								</c:if>
								
								<c:if test="${!registrationModel.haveIDP }" >
									<div class="type">
									
									
									
										<div class="choose bordered-disabled">
											<div class="title">
												<strong>New User</strong>
											</div>
											<div class="iconContainer">
												<div class="myIcon">
													
													<img class="displayed"
														src="<%=request.getContextPath()%>/images/NewUser.png"
														id="yesImg" width="64" style="opacity:0.4; filter:alpha(opacity=40);"/>
													
												</div>
											</div>
											<div class="reset"></div>
											<div class="mess">
												<div class="center">I don't have Grid credentials and I'd like to have them now.</div>
											</div>
											<div class="reset"></div>
											
										</div>
									
									
									
									<div class="reset"></div>
									<div class="moreInfo">
										<a href="https://portal.italiangrid.it:8443/info/user-profile-new-user.html"  onclick="$(this).modal({width:800, height:250, message:true}).open(); return false;">More Info</a>
									</div>
									</div>
								</c:if>
								
								<div class="type">
								<a href="" onclick="setHaveCert('true'); return false;">
									<div class="choose bordered">
										<div class="title">
											<strong>Classic User</strong>
										</div>
										<div class="iconContainer">
											<div class="myIcon">
												<img class="displayed"
													src="<%=request.getContextPath()%>/images/NormalUser.png"
													id="noImg" width="64" />
											</div>
										</div>
										<div class="reset"></div>
										<div class="mess">
											<div class="center">I already have Grid credentials and I want to use my certificate.</div>
										</div>
										<div class="reset"></div>
									</div>
								</a>
								<div class="reset"></div>
									<div class="moreInfo">
										<a href="https://portal.italiangrid.it:8443/info/user-profile-classic-user.html"  onclick="$(this).modal({width:800, height:250, message:true}).open(); return false;">More Info</a>
									</div>
								</div>
								<c:if test="${false }">
									<div class="type">
									<a href="" onclick="return false;">
										<div class="choose bordered">
											<div class="title">
												<strong>Expert User</strong>
											</div>
											<div class="iconContainer">
												<div class="myIcon">
													<img class="displayed"
														src="<%=request.getContextPath()%>/images/ExpertUser.png"
														id="noImg" width="64" />
												</div>
											</div>
											<div class="reset"></div>
											<div class="mess">
												<div class="center">I already have Grid credentials and I want to use my proxy.</div>
											</div>
											<div class="reset"></div>
										</div>
									</a>
									<div class="reset"></div>
										<div class="moreInfo">
											<a href="https://portal.italiangrid.it:8443/info/user-profile-classic-user.html"  onclick="$(this).modal({width:800, height:600, message:true}).open(); return false;">More Info</a>
										</div>
									</div>
								</c:if>
								<div class="reset"></div>
							</div>


							<aui:input name="haveCertificate" id="haveCertificate"
								type="hidden" value="false" />
							<aui:input name="haveIDP" type="hidden"
								value="${registrationModel.haveIDP }" />
							<aui:input name="firstName" type="hidden"
								value="${registrationModel.firstName }" />
							<aui:input name="lastName" type="hidden"
								value="${registrationModel.lastName }" />
							<aui:input name="institute" type="hidden"
								value="${registrationModel.institute }" />
							<aui:input name="email" type="hidden"
								value="${registrationModel.email }" />
							<aui:input name="userStatus" type="hidden"
								value="${registrationModel.userStatus }" />
							<aui:input name="verifyUser" type="hidden" 
								value="${registrationModel.verifyUser }"/>

						</aui:fieldset>

						<br />
						<br />
						<br />
						<br />
					


					<aui:button-row>
					
						<div class="button" style="float: left;">
						<liferay-ui:icon-menu>
						<liferay-ui:icon image="close" message="Abort Registration" url="#" onClick="alert('You are now registrated in the portal, please log into the portal to complete the registraion.');location.href='${loginUrl }';" />
						</liferay-ui:icon-menu>
						</div>
						
						<aui:button type="submit" value="Continue" style="display:none" />
						
						
						<aui:button type="cancel" value="Abort Registration" style="display:none"
							onClick="alert('You are now registrated in the portal, please log into the portal to complete the registraion.');location.href='${loginUrl }';"  />
						
					</aui:button-row>

				</aui:fieldset>
			</aui:layout>
		</aui:form>





	</div>

	<div style="clear: both;"></div>
</div>

