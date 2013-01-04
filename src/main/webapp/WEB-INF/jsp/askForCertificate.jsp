<%@ include file="/WEB-INF/jsp/init.jsp"%>


<script type="text/javascript">
<!--

//-->


function setHaveCert(value){
	
	$("#<portlet:namespace/>haveCertificate").attr("value",value);
	
	if(value=='true'){
		
		$("#yesImg").attr("style","opacity:1.0; filter:alpha(opacity=100);");
		$("#noImg").attr("style","opacity:0.4; filter:alpha(opacity=40);");
		$("#<portlet:namespace/>haveCertificate").val("true");
		$("#<portlet:namespace/>askForCertificate").submit();
	}else{
		
		$("#noImg").attr("style","opacity:1.0; filter:alpha(opacity=100);");
		$("#yesImg").attr("style","opacity:0.4; filter:alpha(opacity=40);");
		$("#<portlet:namespace/>askForCertificate").submit();
	}
}


</script>

<style>
<!--

-->

#chooseTable td{
	margin: 5px;
}

#chooseContainer{
	font-size: 14px;
  width: 790px ;
  margin-left: auto ;
  margin-right: auto ;

}

.choose{
	width: 250px;
	height: 80px;
	float: left;
}

.bordered{
	background-color: #f4fdef;
	border: 1px;
	border-style: solid;
	border-color: #ACDFA7;
	border-radius: 5px;
	-moz-border-radius:5px;
	padding: 8px;
}

.mess{
	height: 80px;
	width: 180px;
	vertical-align: middle;
	display: table-cell;
	float: left;
}

.icon{
	height: 80px;
	width: 70px;
	vertical-align: middle;
	display: table-cell;
}

.iconContainer{
	height: 80px;
	width: 70px;
	float: left;
}

.reset{
	clear:both;
}

#or{
	text-align: center;
	height: 80px;
	width: 250px;
	vertical-align: middle;
	display: table-cell;
}

.center{
	
	height: 80px;
	width: 200px;
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

<portlet:actionURL var="askForCertificateUrl">
	<portlet:param name="myaction" value="askForCertificateRedirect" />
</portlet:actionURL>

<portlet:renderURL var="homeUrl">
	<portlet:param name="myaction" value="userInfos" />
</portlet:renderURL>

<!--  -->

<aui:form name="askForCertificate" id="askForCertificate" action="${askForCertificateUrl}" commandName="registrationModel">
	<aui:layout>

		<aui:fieldset label="Do you have a personal certificate?">
			<aui:column columnWidth="25">

				<aui:fieldset>
					<br /><br /><br /><br /><br /><br /><br /><br />
					
					<div id="chooseContainer">
						<a href="" onclick="setHaveCert('true'); return false;">
						<div class="choose bordered">
						
							<div class="mess">
								<div class="center">
								I have a personal certificate.
								</div>
							</div>
							<div class="iconContainer">
							<div class="icon">
								<img class="displayed" src="<%=request.getContextPath()%>/images/cert-upload.png" id="yesImg" width="64" />							</div>
							</div>
							<div class="reset"></div>
						</div>
						</a>

						<div class="choose">
							<div id="or"><strong>OR</strong></div>
						</div>
						<a href="" onclick="setHaveCert('false'); return false;">
						<div class="choose bordered">
							<div class="mess">
								<div class="center">
								I haven't a personal certificate.
								</div>
							</div>
							<div class="iconContainer">
							<div class="icon">
								<img class="displayed" src="<%=request.getContextPath()%>/images/cert-download.png" id="noImg" width="64"/>
							</div>
							</div>
							<div class="reset"></div>
						</div>
						</a>
					</div>
							
					
					<aui:input name="haveCertificate" id="haveCertificate" type="hidden" value="false"/>
					<aui:input name="haveIDP" type="hidden" value="${registrationModel.haveIDP }"/>
					<aui:input name="firstName" type="hidden" value="${registrationModel.firstName }"/>
					<aui:input name="lastName" type="hidden" value="${registrationModel.lastName }"/>
					<aui:input name="institute" type="hidden" value="${registrationModel.institute }"/>
					<aui:input name="email" type="hidden" value="${registrationModel.email }"/>
					<aui:input name="userStatus" type="hidden" value="${registrationModel.userStatus }"/>

				</aui:fieldset>

				<br />
				<br />
			</aui:column>

			

			<aui:button-row>
				<aui:button type="submit" value="Continue"  style="display:none"/>
				
						<aui:button type="cancel" value="Terminate Registration" />

			</aui:button-row>

		</aui:fieldset>
	</aui:layout>
</aui:form>





</div>

<div style="clear:both;"></div>
</div>