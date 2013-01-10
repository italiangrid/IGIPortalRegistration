<%@ include file="/WEB-INF/jsp/init.jsp"%>


<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>


<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />

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
		} else {
			$("#deleteButton").show();
		}
	}
	
	$(function() {
	    var availableTags = [${voList}];
	    $( "#tags" ).autocomplete({
	    	
	      source: availableTags
	    });
	  });

</script>

<style>

.search-results {
    display: none;
}

div.function {
	padding: 1em 5em 1em 1em;
	border: 1px;
	border-color: #C8C9CA;
	border-style: solid;
	background-color: #D1D6DC;
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
	


<liferay-ui:success key="upload-cert-successufully"
	message="upload-cert-successufully" />
<liferay-ui:success key="userToVo-adding-success"
	message="userToVo-adding-success" />
<liferay-ui:success key="userToVo-removed-success"
	message="userToVo-removed-success" />
<liferay-ui:success key="user-deactivate" message="user-deactivate" />
<liferay-ui:success key="user-activate" message="user-activate" />

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
	
	<aui:layout>

		<h1 class="header-title">Registration - Virtual Organization</h1>
	
	<aui:fieldset>
	
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
				  Select your VO <input id="tags" name="tags" type="text" />
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
					<aui:form name="delVo"
						action="${delVOActionUrl}"  commandName="registrationModel">
						
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
					
					<liferay-ui:search-container emptyResultsMessage="No VO selected"
						delta="5">
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
							
							<liferay-ui:search-container-column-text name="Select">
							<input name="voToDel" type="radio"
										value="${Vo.idVo }"
										onchange="viewOrHideDeleteButton2('${Vo.vo }');"></input>
										
							</liferay-ui:search-container-column-text>
							
							<liferay-ui:search-container-column-text name="VO name"
								property="vo" />
							<liferay-ui:search-container-column-text name="VO description"
								property="description" />

							
						</liferay-ui:search-container-row>
						<liferay-ui:search-iterator />
					</liferay-ui:search-container>
					
					<aui:button-row>
						
						<aui:button id="deleteButton" type="submit" value="Delete VO" style="display:none;" />
						
					</aui:button-row>
					
					</aui:form>
				</aui:fieldset>
				<br />
				<br />
			

		<aui:form name="addVOForm" action="${addUserActionUrl}" commandName="registrationModel">
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
				<aui:button type="submit" value="Registration Completed" />
			</aui:button-row>
		
		</aui:form>
		
		</aui:fieldset>
	</aui:layout>


	


</div>
<div style="clear:both;"></div>
</div>
