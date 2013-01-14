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
	


<aui:form name="addUserForm" commandName="userInfo"
	action="${addUserActionUrl}">

	<aui:layout>

		

		<br>
		<aui:fieldset label="Registration">
		
			<br/><br/>
				<img src="<%=request.getContextPath()%>/images/registration_step1.png"/>
			<br/>
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
					<aui:button type="submit" value="Continue"/>
					<div style="float: right;">
					<aui:button type="cancel" value="Abort Registration"
						onClick="location.href='${homeUrl}';" />
					</div>
				</aui:button-row>
			</div>
		</aui:fieldset>
	</aui:layout>
</aui:form>



</div>
<div style="clear:both;"></div>
</div>