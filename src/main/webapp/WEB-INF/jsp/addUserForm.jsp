<%@ include file="/WEB-INF/jsp/init.jsp"%>

<script type="text/javascript">

	function validate(input, target){
		
		if(input!=""){
			
			$("#no"+target).hide();
			$("#ok"+target).show();
			
		} else {
			$("#ok"+target).hide();
			$("#no"+target).show();
		}
		
		
	}
	
	function submit(){
		
		$("#<portlet:namespace/>addUserForm").submit();
	}

	$(document).ready(function() {
		$(".taglib-text").css("text-decoration","none");
	});

</script>

<style type="text/css">
<!--

-->

.input{
	float: left;
}

.icon{
	padding-top: 25px; 
	margin-left: 8px; 
	float: left;
}

.iconHide{
	padding-top: 25px; 
	margin-left: 8px; 
	float: left;
	display: none;
}

#form{
	margin-left: 20px;
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
    

<portlet:actionURL var="addUserActionUrl">
	<portlet:param name="myaction" value="addUser" />
</portlet:actionURL>
<portlet:renderURL var="homeUrl">
	<portlet:param name="myaction" value="userInfos" />
</portlet:renderURL>

<liferay-ui:error key="error-saving-registration"
	message="error-saving-registration" />
<liferay-ui:error key="user-first-name-required"
	message="user-first-name-required" />
<liferay-ui:error key="user-last-name-required"
	message="user-last-name-required" />
<liferay-ui:error key="user-institute-required"
	message="user-institute-required" />
<liferay-ui:error key="user-mail-required" message="user-mail-required" />
<liferay-ui:error key="user-valid-mail-required"
	message="user-valid-mail-required" />
<liferay-ui:error key="user-username-required"
	message="user-username-required" />
<liferay-ui:error key="user-username-duplicate"
	message="user-username-duplicate" />
<liferay-ui:error key="user-mail-duplicate"
	message="user-mail-duplicate" />
<liferay-ui:error key="user-phone-valid" message="user-phone-valid" />

<jsp:useBean id="userInfo" type="it.italiangrid.portal.dbapi.domain.UserInfo"
	scope="request" />
	


<aui:form name="addUserForm" id="addUserForm" commandName="userInfo"
	action="${addUserActionUrl}">

	<aui:layout>

		

		<br>
		<aui:fieldset label="Registration">
		
			<br/><br/>
				<img src="<%=request.getContextPath()%>/images/registration_step1.png"/>
			<br/><br/>
			<div id="form">
				<aui:column columnWidth="30">
				
					<aui:fieldset>

								<c:if test="${!empty  userInfo.firstName}">
									<div class="input">
											<aui:input label="First Name" name="firstName" type="input"
												value="${userInfo.firstName}" readonly="readonly" />
										</div>
										<div class="icon">
											<img src="<%=request.getContextPath()%>/images/NewCheck.png"
												width="16" height="16" />
										</div>
										<div style="clear: both;"></div>
									
								</c:if>
								<c:if test="${empty  userInfo.firstName}">
									
										<div class="input">
											<aui:input label="First Name" name="firstName" type="input"
												value="${userInfo.firstName}"  onkeyup="validate($(this).val(), 'FirstName');"/>
										</div>
										<div id="noFirstName" class="icon">
											<img src="<%=request.getContextPath()%>/images/NewDelete.png"
												width="16" height="16" />
										</div>
										<div id="okFirstName" class="iconHide">
											<img src="<%=request.getContextPath()%>/images/NewCheck.png"
												width="16" height="16" />
										</div>
										<div style="clear: both;"></div>
									
								</c:if>

								<c:if test="${!empty  userInfo.lastName}">
									
										<div class="input">
											<aui:input label="Last Name" name="lastName" type="input"
												value="${userInfo.lastName}" readonly="readonly" />
										</div>
										<div class="icon">
											<img src="<%=request.getContextPath()%>/images/NewCheck.png"
												width="16" height="16" />
										</div>
										<div style="clear: both;"></div>
									
								</c:if>
								<c:if test="${empty  userInfo.lastName}">
									
										<div class="input">
											<aui:input label="Last Name" name="lastName" type="input"
												value="${userInfo.lastName}"   onkeyup="validate($(this).val(), 'LastName');"/>
										</div>
										<div id="noLastName" class="icon">
											<img src="<%=request.getContextPath()%>/images/NewDelete.png"
												width="16" height="16" />
										</div>
										<div id="okLastName" class="iconHide">
											<img src="<%=request.getContextPath()%>/images/NewCheck.png"
												width="16" height="16" />
										</div>
										<div style="clear: both;"></div>
									
								</c:if>

								<c:if test="${!empty  userInfo.institute}">
								
										<div class="input">
											<aui:input label="Institute" name="institute" type="input"
												value="${userInfo.institute}" readonly="readonly" />
										</div>
										<div class="icon">
											<img src="<%=request.getContextPath()%>/images/NewCheck.png"
												width="16" height="16" />
										</div>
										<div style="clear: both;"></div>
									
								</c:if>
								<c:if test="${empty  userInfo.institute}">
									
										<div class="input">
											<aui:input label="Institute" name="institute" type="input"
												value="${userInfo.institute}"  onkeyup="validate($(this).val(), 'Institute');"/>
										</div>
										<div id="noInstitute" class="icon">
											<img src="<%=request.getContextPath()%>/images/NewDelete.png"
												width="16" height="16" />
										</div>
										<div id="okInstitute" class="iconHide">
											<img src="<%=request.getContextPath()%>/images/NewCheck.png"
												width="16" height="16" />
										</div>
										<div style="clear: both;"></div>
									
								</c:if>

								<aui:input name="phone" type="hidden" />

								<c:if test="${!empty  userInfo.mail}">
									
										<div class="input">
											<aui:input label="e-Mail addess" name="mail" type="input"
												value="${userInfo.mail }" readonly="readonly" />
										</div>
										<div class="icon">
											<img src="<%=request.getContextPath()%>/images/NewCheck.png"
												width="16" height="16" />
										</div>
										<div style="clear: both;"></div>
									
								</c:if>
								<c:if test="${empty  userInfo.mail}">
									
										<div class="input">
											<aui:input label="e-Mail addess" name="mail" type="input"
												value="${userInfo.mail }" onkeyup="validate(this.val(), 'Mail');"/>
										</div>
										<div id="noMail" class="icon">
											<img src="<%=request.getContextPath()%>/images/NewDelete.png"
												width="16" height="16" />
										</div>
										<div id="okMail" class="iconHide">
											<img src="<%=request.getContextPath()%>/images/NewCheck.png"
												width="16" height="16" />
										</div>
										<div style="clear: both;"></div>
									
								</c:if>

								<aui:input  name="username" type="hidden" value="${userInfo.username }"/>
						<aui:input  name="fromIDP" type="hidden" value="${fromIDP }"/>
						
					</aui:fieldset>
					
					<br/>

				</aui:column>
				
				<aui:column columnWidth="20">
				<br/><br/>
				<img src="<%=request.getContextPath()%>/images/PatientFile.png" width="128" />
				
				</aui:column>
				

				<aui:button-row>
				
					<div class="button" style="float: left;">
					<liferay-ui:icon-menu>
					<liferay-ui:icon image="close" message="Abort Registration" url="#" onClick="location.href='${homeUrl}';" />
					</liferay-ui:icon-menu>
					</div>
					<div class="button" style="float: right;">
					<liferay-ui:icon-menu>
					<liferay-ui:icon image="forward" message="Continue" url="#" onClick="submit();" />
					</liferay-ui:icon-menu>
					</div>
					
					<aui:button type="cancel" value="Abort Registration"
						onClick="location.href='${homeUrl}';" style="display: none;"/>
					<div style="float: right; display: none;">
					<aui:button type="submit" value="Continue"/>
					</div>
				</aui:button-row>
			</div>
		</aui:fieldset>
	</aui:layout>
</aui:form>



</div>
<div style="clear:both;"></div>
</div>