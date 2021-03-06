<%@ include file="/WEB-INF/jsp/init.jsp"%>



<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/themes/base/jquery-ui.css" />

<script type="text/javascript">
<!--

//-->

	function showSearchForm(){
		$(".function").show("slow");
	}

	function hideSearchForm(){
		$(".function").hide("slow");
	}

	var list = new Array();
	var list2 = new Array();

	function viewOrHideDeleteButton(uuid) {
		var i = 0;
		var newlist = new Array();
		var isPresent = false;
		for (i = 0; i < list.length; i++) {
			if (list[i] != uuid) {
				newlist.push(list[i]);
			} else {
				isPresent = true;
			}
		}

		if (isPresent == false)
			list.push(uuid);
		else
			list = newlist;

		if (list.length == 0) {
			$("#addButton").hide("slow");
		} else {
			$("#addButton").show("slow");
		}
	}

	function viewOrHideDeleteButton2(uuid) {
		var i = 0;
		var newlist = new Array();
		var isPresent = false;
		for (i = 0; i < list.length; i++) {
			if (list2[i] != uuid) {
				newlist.push(list2[i]);
			} else {
				isPresent = true;
			}
		}

		if (isPresent == false)
			list2.push(uuid);
		else
			lis2t = newlist;

		if (list2.length == 0) {
			$("#deleteButton").hide();
			$("#roleButton").hide();
		} else {
			$("#deleteButton").show();
			$("#roleButton").show();
		}
	}

	$(function() {
	    var availableTags = [${voList}];
	    $( "#tags" ).autocomplete({

	      source: availableTags
	    });
	  });

	function submit(){

		$("#<portlet:namespace/>addVOForm").submit();
	}

	$(document).ready(function() {
		$(".taglib-text").css("text-decoration","none");
	});

</script>

<style>

.search-results {
    display: none;
}

.portlet-msg-info{
    display: none;
}

div.function {
	padding: 1em 5em 1em 1em;
	border: 1px;
	border-color: #C8C9CA;
	border-style: solid;
	background-color: #D1D6DC;
	margin-top: 30px;
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

#submit{
	float: right;
	margin-right: 20px;

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

.results-row td.disabledVo{
	opacity: .4; 
	filter: alpha(opacity=40);
}

#myAlert div.portlet-msg-success {
    background: #FFC url(https://flyback.cnaf.infn.it/html/themes/classic/images/messages/alert.png) no-repeat 6px 20%;
    border: 1px solid #FC0;
}

</style>

<div>	

<div id="action">

<portlet:actionURL var="addUserActionUrl">
	<portlet:param name="myaction" value="goToAddUserForm" />
</portlet:actionURL>
<portlet:renderURL var="homeUrl">
	<portlet:param name="myaction" value="userInfos" />
</portlet:renderURL>

<jsp:useBean id="vos"
	type="java.util.List<it.italiangrid.portal.dbapi.domain.Vo>"
	scope="request"></jsp:useBean>
<jsp:useBean id="selectedVos"
	type="java.util.List<it.italiangrid.portal.dbapi.domain.Vo>"
	scope="request"></jsp:useBean>	





	<aui:layout>


	<aui:fieldset label="Registration">
	<br/><br/>
	<img src="<%=request.getContextPath()%>/images/registration_step3.png"/>
	<br/><br/>
	
	
	<liferay-ui:success key="upload-cert-successufully"
		message="upload-cert-successufully" />
	<liferay-ui:success key="userToVo-adding-success"
		message="userToVo-adding-success" />
	<liferay-ui:success key="userToVo-removed-success"
		message="userToVo-removed-success" />
	<liferay-ui:success key="userToVo-updated-successufully"
		message="userToVo-updated-successufully" />
	<liferay-ui:success key="userToVo-default-successufully"
		message="userToVo-default-successufully" />
	<div id="myAlert">
		<liferay-ui:success key="vo-not-configurated"
			message="vo-not-configurated" />
		</div>
	
	<liferay-ui:error key="user-vo-list-empty" message="user-vo-list-empty" />
	<liferay-ui:error key="no-user-found-in-VO"
		message="no-user-found-in-VO" />
	<liferay-ui:error key="no-VO-found"
		message="no-VO-found" />
	<liferay-ui:error key="no-cert-for-user" message="no-cert-for-user" />
	<liferay-ui:error key="edg-mkgridmap-problem"
		message="edg-mkgridmap-problem" />
	<liferay-ui:error key="edg-mkgridmap-exception"
		message="edg-mkgridmap-exception" />
	<liferay-ui:error key="exception-deactivation-user"
		message="exception-deactivation-user" />
	
	<p style="font-size: 15px; padding-top:10px;">Digit the name of your VOs and click "<strong>Add</strong>" button.</p>
	<p style="font-size: 15px;">Than you can also set your VO Group and Role clicking on "<strong>Actions</strong>" button.</p>

	<div class="function">
		<aui:fieldset>
		<aui:column columnWidth="50">
		<div id="searchForm" >

		<portlet:actionURL var="searchVOActionUrl">
			<portlet:param name="myaction" value="searchVo2" />
		</portlet:actionURL>

		<aui:form name="searchVo"
			action="${searchVOActionUrl}"  commandName="registrationModel">

			<aui:input name="subject" type="hidden" value="${registrationModel.subject }"></aui:input>
			<aui:input name="issuer" type="hidden" value="${registrationModel.issuer }"></aui:input>
			<aui:input name="expiration" type="hidden" value="${registrationModel.expiration }"></aui:input>
			<aui:input name="haveCertificate" type="hidden" value="${registrationModel.haveCertificate }"></aui:input>
			<aui:input name="certificateUserId" type="hidden" value="${registrationModel.certificateUserId }"></aui:input>
			<aui:input name="vos" type="hidden" value="${registrationModel.vos }"></aui:input>
			<aui:input name="mail" type="hidden" value="${registrationModel.mail }"></aui:input>
			<aui:input name="haveIDP" type="hidden" value="${registrationModel.haveIDP }"></aui:input>
			<aui:input name="firstName" type="hidden" value="${registrationModel.firstName }"/>
			<aui:input name="lastName" type="hidden" value="${registrationModel.lastName }"/>
			<aui:input name="institute" type="hidden" value="${registrationModel.institute }"/>
			<aui:input name="email" type="hidden" value="${registrationModel.email }"/>
			<aui:input name="userStatus" type="hidden" value="${registrationModel.userStatus }"/>
			<aui:input name="certificateStatus" type="hidden" value="${registrationModel.certificateStatus }"/>
			<aui:input name="voStatus" type="hidden" value="${registrationModel.voStatus }"/>
			<aui:input name="verifyUser" type="hidden" value="${registrationModel.verifyUser }"/>
			<aui:input name="searchVo" id="searchVo" type="hidden" value=""/>
			<aui:layout>
				<aui:button-row>

				<div class="ui-widget" style="float:left;">
				  Enter your VO's name <input id="tags" name="tags" type="text" />
				</div>


				<aui:button type="submit" value="Add" inlineField="true"/>

				</aui:button-row>
			</aui:layout>
		</aui:form>

		</div>
		</aui:column>
		</aui:fieldset>

</div>


</aui:fieldset>

		<br/>


		<aui:fieldset>



				<aui:fieldset>

					<portlet:actionURL var="delVOActionUrl">
						<portlet:param name="myaction" value="delVo" />
					</portlet:actionURL>
					<portlet:actionURL var="editRoleActionUrl">
						<portlet:param name="myaction" value="editRole" />
					</portlet:actionURL>


					<liferay-ui:search-container delta="5">
						<liferay-ui:search-container-results>
							<%
								results = ListUtil.subList(
										selectedVos,
										searchContainer.getStart(),
										searchContainer.getEnd());
								total = selectedVos.size();

								pageContext.setAttribute("results",
										results);
								pageContext
										.setAttribute("total", total);
							%>

						</liferay-ui:search-container-results>
						<liferay-ui:search-container-row
							className="it.italiangrid.portal.dbapi.domain.Vo"
							keyProperty="idVo" modelVar="Vo">

							
							<c:if test="${Vo.configured=='false' }">	
								<liferay-ui:search-container-column-text name="VO name" 
									property="vo"  cssClass="disabledVo"/>
								<liferay-ui:search-container-column-text name="Default VO" cssClass="disabledVo">
								<c:if test="${defaultVo==Vo.vo}">
									
									<img src="<%=request.getContextPath()%>/images/NewCheck.png" width="16" height="16"/>	
									
								</c:if>
								</liferay-ui:search-container-column-text>
								
								<liferay-ui:search-container-column-text name="Roles" cssClass="disabledVo"> 
									
									<c:out value="${fn:replace(userFqans[Vo.idVo],';',' ')}"></c:out>
									
								</liferay-ui:search-container-column-text>
								
								<liferay-ui:search-container-column-jsp cssClass="disabledVo"
									path="/WEB-INF/jsp/addvo-action.jsp" align="right" />
							</c:if>
							<c:if test="${Vo.configured=='true' }">	
								<liferay-ui:search-container-column-text name="VO name" 
									property="vo" />
								<liferay-ui:search-container-column-text name="Default VO">
								<c:if test="${defaultVo==Vo.vo}">
									
									<img src="<%=request.getContextPath()%>/images/NewCheck.png" width="16" height="16"/>	
									
								</c:if>
								</liferay-ui:search-container-column-text>
								
								<liferay-ui:search-container-column-text name="Roles"> 
									
									<c:out value="${fn:replace(userFqans[Vo.idVo],';',' ')}"></c:out>
									
								</liferay-ui:search-container-column-text>
								
								<liferay-ui:search-container-column-jsp 
									path="/WEB-INF/jsp/addvo-action.jsp" align="right" />
							</c:if>

						</liferay-ui:search-container-row>
						<liferay-ui:search-iterator />
					</liferay-ui:search-container>


				</aui:fieldset>
				<br />
				<br />


		<aui:form name="addVOForm" id="addVOForm" action="${addUserActionUrl}" commandName="registrationModel">
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
			<aui:input name="certificateStatus" type="hidden" value="${registrationModel.certificateStatus }"/>
			<aui:input name="voStatus" type="hidden" value="${registrationModel.voStatus }"/>
			<aui:input name="verifyUser" type="hidden" value="${registrationModel.verifyUser }"/>

			<aui:button-row>
			
				<portlet:actionURL var="abortUrl">
						<portlet:param name="myaction" value="abortRegistration"/>
						<portlet:param name="subject" value="${registrationModel.subject }"/>
						<portlet:param name="issuer" value="${registrationModel.issuer }"/>
						<portlet:param name="expiration" value="${registrationModel.expiration }"/>
						<portlet:param name="haveCertificate" value="${registrationModel.haveCertificate }"/>
						<portlet:param name="certificateUserId" value="${registrationModel.certificateUserId }"/>
						<portlet:param name="vos" value="${registrationModel.vos }"/>
						<portlet:param name="searchVo" value="${registrationModel.searchVo }"/>
						<portlet:param name="mail" value="${registrationModel.mail }"/>
						<portlet:param name="haveIDP" value="${registrationModel.haveIDP }"/>
						<portlet:param name="firstName" value="${registrationModel.firstName }"/>
						<portlet:param name="lastName" value="${registrationModel.lastName }"/>
						<portlet:param name="institute" value="${registrationModel.institute }"/>
						<portlet:param name="email" value="${registrationModel.email }"/>
						<portlet:param name="userStatus" value="${registrationModel.userStatus }"/>
						<portlet:param name="certificateStatus" value="${registrationModel.certificateStatus }"/>
						<portlet:param name="voStatus" value="${registrationModel.voStatus }"/>
						<portlet:param name="verifyUser" value="${registrationModel.verifyUser }"/>
					</portlet:actionURL>
				
				<div class="button" style="float: left;">
				<liferay-ui:icon-menu>
				<liferay-ui:icon image="close" message="Abort Registration" url="${abortUrl }"
					onClick="alert('You are now registrated in the portal, please log into the portal to complete the registraion.');" />
				</liferay-ui:icon-menu>
				</div>
			
				<div class="button" style="float: right;">
				<liferay-ui:icon-menu>
				<liferay-ui:icon image="forward" message="Continue" url="#" onClick="submit();" />
				</liferay-ui:icon-menu>
				</div>

				<aui:button type="submit" value="Registration Completed" style="display:none;"/>
			</aui:button-row>

		</aui:form>

		</aui:fieldset>
	</aui:layout>





</div>
<%@ include file="/WEB-INF/jsp/summary.jsp" %>
<div style="clear:both;"></div>
</div>