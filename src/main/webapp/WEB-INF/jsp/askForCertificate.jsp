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
</script>

<style>
<!--
-->
#chooseTable td {
	margin: 5px;
}

#chooseContainer {
	font-size: 12px;
	width: 900px;
	margin-left: auto;
	margin-right: auto;
}

.type{
	width: 200px;
	height: 155px;
	float: left;
	margin: 0px 30px;
}

.choose {
	width: 200px;
	height: 150px;
	
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

.icon {
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
		
		<h1 class="header-title">Registration - Select the User Type</h1>

		<aui:form name="askForCertificate" id="askForCertificate"
			action="${askForCertificateUrl}" commandName="registrationModel">
			<aui:layout>

				<aui:fieldset>
					

						<aui:fieldset>
							<br />
							<br />
							<br />
							<br />

							<div id="chooseContainer">
								<c:if test="${registrationModel.haveIDP }">
									<div class="type">
									<a href="" onclick="setHaveCert('false'); return false;">
										<div class="choose bordered">
											<div class="title">
												<strong>New User</strong>
											</div>
											<div class="iconContainer">
												<div class="icon">
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
										<a href="">More info</a>
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
											<div class="icon">
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
										<a href="">More info</a>
									</div>
								</div>
								<div class="type">
								<a href="" onclick="return false;">
									<div class="choose bordered">
										<div class="title">
											<strong>Expert User</strong>
										</div>
										<div class="iconContainer">
											<div class="icon">
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
										<a href="">More info</a>
									</div>
								</div>
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

						</aui:fieldset>

						<br />
						<br />
						<br />
						<br />
					


					<aui:button-row>
						<aui:button type="submit" value="Continue" style="display:none" />
						<div style="float: right;">
						<aui:button type="cancel" value="Abort Registration"
							onClick="alert('You are now registrated in the portal, please log into the portal to complete the registraion.');location.href='${loginUrl }';" />
						</div>
					</aui:button-row>

				</aui:fieldset>
			</aui:layout>
		</aui:form>





	</div>

	<div style="clear: both;"></div>
</div>