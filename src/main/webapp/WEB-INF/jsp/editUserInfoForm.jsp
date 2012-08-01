<%@ include file="/WEB-INF/jsp/init.jsp"%>

<script type="text/javascript">
<!--
	//-->
	function mostraModificaUtente() {
		$("#<portlet:namespace/>formOFF").show("slow");
		$("#<portlet:namespace/>formOn").hide("slow");
	}

	function nascondiModificaUtente() {
		$("#<portlet:namespace/>formOFF").hide("slow");
		$("#<portlet:namespace/>formOn").show("slow");
	}

	function mostraCertificatiUtente() {
		$("#<portlet:namespace/>certificatiOFF").show("slow");
		$("#<portlet:namespace/>certificatiOn").hide("slow");
	}

	function nascondiCertificatiUtente() {
		$("#<portlet:namespace/>certificatiOFF").hide("slow");
		$("#<portlet:namespace/>certificatiOn").show("slow");
	}

	function mostraVoUtente() {
		$("#<portlet:namespace/>voOFF").show("slow");
		$("#<portlet:namespace/>voOn").hide("slow");
	}

	function nascondiVoUtente() {
		$("#<portlet:namespace/>voOFF").hide("slow");
		$("#<portlet:namespace/>voOn").show("slow");
	}
	
	function mostraAdvSetUtente() {
		$("#<portlet:namespace/>advSetOff").show("slow");
		$("#<portlet:namespace/>advSetOn").hide("slow");
	}

	function nascondiAdvSetUtente() {
		$("#<portlet:namespace/>advSetOff").hide("slow");
		$("#<portlet:namespace/>advSetOn").show("slow");
	}
	
	function verifyDelete(url){
		var agree=confirm("Sei sicuro di voler eliminare il tuo account?");
		if (agree)
			return location.href=url ;
	}
	
	function mysubmit() {
		//submit form
		
		$("#importTest").submit();
		
		//alert("OK");
		//window.location = url;
	}
	
	function viewTooltip(url){

		$("#userSettings a").tooltip({

			bodyHandler: function() {
				return $(url).html();
			},
			showURL: false


		});
	}
	
	
	$(function() {


		$("#foottipUser a, #foottipCert a, #foottipVO a").tooltip({
			bodyHandler: function() {
				return $($(this).attr("href")).html();
			},
			showURL: false
			
		});

		});
	

	$(document).ready(function() {
		//nascondiCertificatiUtente();
		//nascondiModificaUtente();
		//nascondiVoUtente();
	});
</script>



<style>
div#personalData {
	padding: 1em;
	border: 1px;
	border-color: #C8C9CA;
	border-style: solid;
	background-color: #EFEFEF;
}

div#certificateData {
	margin: 3px 0 3px 0;
	padding: 1em;
	border: 1px;
	border-color: #C8C9CA;
	border-style: solid;
	background-color: #EFEFEF;
}

div#voData {
	margin: 3px 0 3px 0;
	padding: 1em;
	border: 1px;
	border-color: #C8C9CA;
	border-style: solid;
	background-color: #EFEFEF;
}

div#advancedSettings {
	padding: 1em;
	border: 1px;
	border-color: #C8C9CA;
	border-style: solid;
	background-color: #EFEFEF;
}

#closeBox {
	float:right;
	height: 24px;
	line-height:24px;
}

#closeBox img{
	padding-left: 5px;
	padding-right: 10px;
	width: 24px;
	height: 24px;
}

</style>

<c:choose>
<c:when test="<%= request.isUserInRole("administrator") %>">
	<div id="container">
</c:when>
<c:otherwise>
	<div>
</c:otherwise>
</c:choose>

<portlet:actionURL var="editUserInfoActionUrl">
	<portlet:param name="myaction" value="editUserInfo" />
</portlet:actionURL>
<portlet:actionURL var="updateAdvOptsActionUrl">
	<portlet:param name="myaction" value="updateAdvOpts" />
</portlet:actionURL>
<portlet:renderURL var="homeUrl">
	<portlet:param name="myaction" value="userInfos" />
</portlet:renderURL>

<jsp:useBean id="userInfo" type="it.italiangrid.portal.dbapi.domain.UserInfo"
	scope="request" />
<jsp:useBean id="certList"
	type="java.util.List<it.italiangrid.portal.dbapi.domain.Certificate>"
	scope="request" />
<jsp:useBean id="userToVoList"
	type="java.util.List<it.italiangrid.portal.dbapi.domain.Vo>" scope="request"></jsp:useBean>
	
	<%
			User userLF = (User) request.getAttribute(WebKeys.USER);
			
			String saluto= "Hi " + userLF.getFirstName();
		%>
		<div id="presentation"><aui:fieldset label="<%=saluto %>"></aui:fieldset> </div>
	
	
	<br/><br/>

<div id="personalData">
	<h3 class="header-title">Personal data</h3>

	<liferay-ui:success key="user-updated-successufully"
		message="user-updated-successufully" />

	<liferay-ui:error key="error-updating-user"
		message="error-updating-user" />
	<liferay-ui:error key="problem-idp" message="problem-idp" />
	<liferay-ui:error key="problem-update-user"
		message="problem-update-user" />
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
	<liferay-ui:error key="user-phone-valid" message="user-phone-valid" />
	<liferay-ui:error key="user-mail-must-same"
		message="user-mail-must-same" />
	<liferay-ui:error key="user-username-must-same"
		message="user-username-must-same" />
	<liferay-ui:error key="problem-update-user-liferay"
		message="problem-update-user-liferay" />
	
	<div id="<portlet:namespace/>formOn">
		<!-- <a href="#editUserInfoForm" onclick="mostraModificaUtente();">Modify data</a><br /> <br />  -->
		<aui:layout>

			<aui:fieldset>
				<aui:column columnWidth="80">
					<aui:fieldset>
		
						<table>
							<tr>
								<td align="right" style="margin: 0; padding: 0 1em 0 0;">User:</td>
								<td><strong><c:out value="${userInfo.firstName }" />
								</strong> <strong><c:out value="${userInfo.lastName }" /> </strong></td>
							</tr>
							<c:if test="${!empty  userInfo.institute}">
							<tr>
								<td align="right" style="margin: 0; padding: 0 1em 0 0;">Institute:</td>
								<td><strong><c:out value="${userInfo.institute }" />
								</strong></td>
							</tr>
							</c:if>
							<!-- <tr>
								<td align="right" style="margin: 0; padding: 0 1em 0 0;">Username:</td>
								<td><strong><c:out value="${userInfo.username }" /> </strong>
								</td>
							</tr> -->
							<tr>
								<td align="right" style="margin: 0; padding: 0 1em 0 0;">e-Mail:</td>
								<td><strong><c:out value="${userInfo.mail }" /> </strong></td>
							</tr>
						</table>
					</aui:fieldset>
				</aui:column>
				<aui:column columnWidth="20">
					<aui:fieldset>
						<div id="foottipUser">
							<a href="#footnoteUserOK"><img src="<%=request.getContextPath()%>/images/check.png" width="64" height="64" style="float: right"/></a>
							<div id="footnoteUserOK" style="display:none;">All is OK.</div>
						</div>
					</aui:fieldset>
				</aui:column>	
			</aui:fieldset>
		</aui:layout>
	</div>
	<div id="<portlet:namespace/>formOFF" style="display: none;">

		<a href="#editUserInfoForm" onclick="nascondiModificaUtente();">Hide data</a><br />
		<br />

		<aui:form name="editUserInfoForm" commandName="userInfo"
			action="${editUserInfoActionUrl}">

			<aui:layout>

				<aui:fieldset>
					<aui:column columnWidth="33">
						<aui:fieldset label="Personal data">
							<br></br>

							<aui:input name="userId" type="hidden"
								value="<%=userInfo.getUserId() %>" />
							<liferay-ui:error key="user-first-name-required"
								message="user-first-name-required" />
							<strong>First Name</strong><br/>
							<input name="firstName" type="text"
								value="<%=userInfo.getFirstName() %>" disabled="disabled"/>
							<liferay-ui:error key="user-last-name-required"
								message="user-last-name-required" />
							<br/><br/><strong>Last Name</strong><br/>
							<input name="lastName" type="text"
								value="<%=userInfo.getLastName() %>" disabled="disabled"/>
							<br/> <br/>
						</aui:fieldset>
					</aui:column>

					<aui:column columnWidth="33">
						<aui:fieldset label="Contact">
							<br></br>
							<liferay-ui:error key="user-institute-required"
								message="user-institute-required" />
							<strong>Institute</strong><br/>
							<input name="institute" type="text"
								value="<%=userInfo.getInstitute() %>" disabled="disabled"/>
							<liferay-ui:error key="user-phone-valid"
								message="user-phone-valid" />
							<!-- <br/><br/><strong>Phone Number</strong><br/>  -->
							<input name="phone" type="hidden"
								value="<%=userInfo.getPhone() %>"  />
							<liferay-ui:error key="user-mail-required"
								message="user-mail-required" />
							<liferay-ui:error key="user-valid-mail-required"
								message="user-valid-mail-required" />
							<liferay-ui:error key="user-mail-duplicate"
								message="user-mail-duplicate" />
							<br/><br/><strong>e-Mail Address</strong><br/>
							<input name="mail" disabled="disabled" type="text"
								value="<%=userInfo.getMail() %>" />	
							<input name="username" disabled="disabled" type="hidden"
								value="<%=userInfo.getUsername() %>" />	
							<br/> <br/>
						</aui:fieldset>
					</aui:column>

					<!--<aui:column columnWidth="33">
						<aui:fieldset label="Account data">
							<br></br>
							<liferay-ui:error key="user-username-required"
								message="user-username-required" />
							<liferay-ui:error key="user-username-duplicate"
								message="user-username-duplicate" />
							<strong>Username</strong><br/>
							<input name="username" disabled="disabled" type="text"
								value="<%=userInfo.getUsername() %>" />
							<liferay-ui:error key="user-mail-required"
								message="user-mail-required" />
							<liferay-ui:error key="user-valid-mail-required"
								message="user-valid-mail-required" />
							<liferay-ui:error key="user-mail-duplicate"
								message="user-mail-duplicate" />
							<br/><br/><strong>e-Mail Address</strong><br/>
							<input name="mail" disabled="disabled" type="text"
								value="<%=userInfo.getMail() %>" />
							<br/> <br/>
						</aui:fieldset>
					</aui:column> -->

					<aui:button-row>
						<aui:button type="submit" />
					</aui:button-row>
				</aui:fieldset>
			</aui:layout>
		</aui:form>
	</div>
</div>

<div id="certificateData">
	<h3 class="header-title">My certificates</h3>

	<liferay-ui:success key="certificate-updated-successufully"
		message="certificate-updated-successufully" />
	<liferay-ui:success key="certificate-deleted-successufully"
		message="certificate-deleted-successufully" />
	<liferay-ui:success key="upload-cert-successufully"
		message="upload-cert-successufully" />

	<liferay-ui:error key="error-deleting-certificate"
		message="error-deleting-certificate" />
	<liferay-ui:error key="error-updating-certificate"
		message="error-updating-certificate" />
	<liferay-ui:error key="error-default-certificate"
		message="error-default-certificate" />
	<liferay-ui:error key="error-deleting-certificate-proxy-not-exists"
		message="error-deleting-certificate-proxy-not-exists" />
	<liferay-ui:error key="error-deleting-certificate-proxy-expired"
		message="error-deleting-certificate-proxy-expired" />
	<liferay-ui:error key="error-deleting-certificate-proxy-expired"
		message="error-deleting-certificate-proxy-expired" />
	<liferay-ui:error key="error-deleting-certificate-wrong-proxy"
		message="error-deleting-certificate-wrong-proxy" />


	<div id="<portlet:namespace/>certificatiOn">
		<aui:layout>

			<aui:fieldset>
				<aui:column columnWidth="80">
					<aui:fieldset>
					<a href="#apriCert" ></a> You have uploaded  
							<c:choose>
							<c:when test="${fn:length(certList)==0}" >
							<span style="color:red"><strong>#<c:out value="${fn:length(certList)}" /></strong></span>
							certificates<br />
							</c:when>
							<c:when test="${fn:length(certList)==1}" >
							<strong>#<c:out value="${fn:length(certList)}" />
							</strong>certificate<br />
							</c:when>
							<c:otherwise>
							<strong>#<c:out value="${fn:length(certList)}" />
							</strong>certificates<br />
							</c:otherwise>
							</c:choose>
					
					<c:if test="${fn:length(certList) > 0}">
					Your defaul certificate is: <strong> <c:forEach
								var="cert" items="${certList}">
								<c:if test="${cert.primaryCert == 'true'}">
									<c:out value="${cert.subject}" />
								</c:if>
							</c:forEach> </strong>
						<br />
					</c:if>
			
					<br />
					</aui:fieldset>
				</aui:column>
				<aui:column columnWidth="20">
					<aui:fieldset>
					<div id="foottipCert">
						<c:choose>
							<c:when test="${fn:length(certList)==0}" >
								<a href="#footnoteCertKO"><img src="<%=request.getContextPath()%>/images/missed_calls.png" width="64" height="64" style="float: right"/></a>
							</c:when>
							<c:otherwise>
								<a href="#footnoteCertOK"><img src="<%=request.getContextPath()%>/images/check.png" width="64" height="64" style="float: right"/></a>
							</c:otherwise>
						</c:choose>
						<div id="footnoteCertOK" style="display:none;">All is OK.</div>
						<div id="footnoteCertKO" style="display:none;">Add your Certificate.</div>
					</div>
					<div id="userSettings"><a href="#apriCert" onclick="mostraCertificatiUtente();" onmouseover="viewTooltip('#settingsButtonCert');"><img src="<%=request.getContextPath()%>/images/advancedsettings.png" width="64" height="64" style="float: right; padding-right:10px;"/></a></div>
					</aui:fieldset>
				</aui:column>	
			</aui:fieldset>
		</aui:layout>


	</div>

	<div id="<portlet:namespace/>certificatiOFF" style="display: none;">

		<div id="closeBox"><a href="#cert" onclick="nascondiCertificatiUtente();"><img src="<%=request.getContextPath()%>/images/close-button2.png"/></a></div>
		<div id="closeBox"><a href="#cert" onclick="nascondiCertificatiUtente();">Hide Certificate</a></div>
		<br /> <br /> <br />
		
		<%
		    PortletURL itURL = renderResponse.createRenderURL();
			itURL.setParameter("myaction","editUserInfoForm");
			itURL.setParameter("userId",Integer.toString(userInfo.getUserId()));
			
		%>

		<liferay-ui:search-container
			emptyResultsMessage="No certificates uploaded"
			delta="5" iteratorURL="<%= itURL %>">
			<liferay-ui:search-container-results>
				<%
					results = ListUtil.subList(certList,
									searchContainer.getStart(),
									searchContainer.getEnd());
							total = certList.size();

							pageContext.setAttribute("results", results);
							pageContext.setAttribute("total", total);
				%>

			</liferay-ui:search-container-results>
			<liferay-ui:search-container-row
				className="it.italiangrid.portal.dbapi.domain.Certificate"
				keyProperty="idCert" modelVar="Certificate">
				<liferay-ui:search-container-column-text name="Subject"
					property="subject" />
				<liferay-ui:search-container-column-text name="Issuer"
					property="issuer" />
					<liferay-ui:search-container-column-text name="Expiration data">
					<%
						Certificate cert = (Certificate) row.getObject();

						GregorianCalendar c = new GregorianCalendar();
						Date oggi = c.getTime();
	
						if (cert.getExpirationDate().before(oggi)) {
							%>
							<span style="color:red; font-weight:bold;"><c:out value="<%=cert.getExpirationDate().toString() %>"/></span>
							<%
							
						} else {
							%>
							<c:out value="<%=cert.getExpirationDate().toString() %>"/>
							<%
						}
					%>
					</liferay-ui:search-container-column-text>
				
				<c:if test="${certCAonline == 'false' }">
					<liferay-ui:search-container-column-jsp
					path="/WEB-INF/jsp/admin-cert-action.jsp" align="right" />
				</c:if>
				
			</liferay-ui:search-container-row>
			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
		<div id="apriCert"></div>

		<portlet:renderURL var="uploadCertUrl">
			<portlet:param name="myaction" value="showUploadCert" />

		</portlet:renderURL>

		<aui:form name="catalogForm" commandName="userInfo" action="${uploadCertUrl}">

			<aui:input name="userId" type="hidden" value="${userInfo.userId }" />
			<aui:input name="username" type="hidden"
				value="${userInfo.username }" />
			<aui:input name="firstReg" type="hidden" value="false" />

			<aui:button-row>
				<c:if test="${fn:length(certList) == 0}">
					<aui:button type="submit" value="Upload certificate" />
				</c:if>
			</aui:button-row>
		</aui:form>

	</div>
</div>

<div id="voData">
	<h3 class="header-title">My VO</h3>

	<liferay-ui:success key="userToVo-adding-success"
		message="userToVo-adding-success" />
	<liferay-ui:success key="userToVo-updated-successufully"
		message="userToVo-updated-successufully" />
	<liferay-ui:success key="userToVo-deleted-successufully"
		message="userToVo-deleted-successufully" />
	<liferay-ui:success key="user-deactivate" message="user-deactivate" />
	<liferay-ui:success key="user-activate" message="user-activate" />

	<liferay-ui:error key="user-vo-list-empty" message="user-vo-list-empty" />
	<liferay-ui:error key="no-user-found-in-VO"
		message="no-user-found-in-VO" />
	<liferay-ui:error key="no-cert-for-user" message="no-cert-for-user" />
	<liferay-ui:error key="edg-mkgridmap-problem"
		message="edg-mkgridmap-problem" />
	<liferay-ui:error key="edg-mkgridmap-exception"
		message="edg-mkgridmap-exception" />
	<liferay-ui:error key="error-deleting-userToVo"
		message="error-deleting-userToVo" />
	<liferay-ui:error key="error-default-userToVo"
		message="error-default-userToVo" />
	<liferay-ui:error key="userToVo-already-exists"
		message="userToVo-already-exists" />
	<liferay-ui:error key="exception-deactivation-user"
		message="exception-deactivation-user" />

	<div id="<portlet:namespace/>voOn">
		<aui:layout>

			<aui:fieldset>
				<aui:column columnWidth="80">
					<aui:fieldset>
						<a href="#apriVo"/></a>
						At the moment you have <strong>#<c:out
								value="<%= Integer.toString(userToVoList.size()) %>"></c:out> </strong> VO associations.<br />
						<c:if test="${!empty defaultVo}">
					Your default VO is: <strong><c:out value="${defaultVo}" />
							</strong>
						</c:if>
						<c:if test="${!empty defaultFqan}">
							<br />Selected roles for your default VO: <strong><c:out
									value="${fn:replace(defaultFqan,';',' ')}" /> </strong>
						</c:if>
				
						<br /> <br />
					</aui:fieldset>
				</aui:column>
				<aui:column columnWidth="20">
					<aui:fieldset>
					<div id="foottipVO">
						<c:choose>
							<c:when test="${fn:length(userToVoList)==0}" >
								<a href="#footnoteVOKO"><img src="<%=request.getContextPath()%>/images/missed_calls.png" width="64" height="64" style="float: right"/></a>
							</c:when>
							<c:otherwise>
								<a href="#footnoteVOOK"><img src="<%=request.getContextPath()%>/images/check.png" width="64" height="64" style="float: right"/></a>
							</c:otherwise>
						</c:choose>
						<div id="footnoteVOOK" style="display:none;">All is OK.</div>
						<div id="footnoteVOKO" style="display:none;">Add a new VO.</div>
					</div>
					<div id="userSettings"><a href="#apriVo" onclick="mostraVoUtente();" onmouseover="viewTooltip('#settingsButtonVO');"><img src="<%=request.getContextPath()%>/images/advancedsettings.png" width="64" height="64" style="float: right; padding-right:10px;"/></a></div>
					</aui:fieldset>
				</aui:column>	
			</aui:fieldset>
		</aui:layout>

	</div>

	<div id="<portlet:namespace/>voOFF" style="display: none;">

		<div id="closeBox"><a href="#vo" onclick="nascondiVoUtente();"><img src="<%=request.getContextPath()%>/images/close-button2.png"/></a></div>
		<div id="closeBox"><a href="#vo" onclick="nascondiVoUtente();">Hide VO</a></div>
		<br /> <br /> <br />

		

		<liferay-ui:search-container
			emptyResultsMessage="No VO selected"
			delta="5" iteratorURL="<%= itURL %>">
			<liferay-ui:search-container-results>
				<%
					results = ListUtil.subList(userToVoList,
									searchContainer.getStart(),
									searchContainer.getEnd());
							total = userToVoList.size();

							pageContext.setAttribute("results", results);
							pageContext.setAttribute("total", total);
				%>

			</liferay-ui:search-container-results>
			<liferay-ui:search-container-row
				className="it.italiangrid.portal.dbapi.domain.Vo" keyProperty="idVo"
				modelVar="Vo">
				
						<liferay-ui:search-container-column-text name="VO name"
							property="vo" />
						<liferay-ui:search-container-column-text name="Default VO">
						<c:if test="${defaultVo==Vo.vo}">
							
							<img src="<%=request.getContextPath()%>/images/success.png"/>	
							
						</c:if>
						</liferay-ui:search-container-column-text>
						<liferay-ui:search-container-column-text name="Description"
							property="description" />
						<liferay-ui:search-container-column-text name="Roles"> 
							<c:out value="${fn:replace(userFqans[Vo.idVo],';',' ')}"></c:out>
						</liferay-ui:search-container-column-text>
						<c:if test="${certCAonline == 'false' }">
							<liferay-ui:search-container-column-jsp
							path="/WEB-INF/jsp/admin-vo-action.jsp" align="right" />
						</c:if>
						
					
			</liferay-ui:search-container-row>
			<liferay-ui:search-iterator />
		</liferay-ui:search-container>

		<portlet:renderURL var="addUserToVOActionUrl">
			<portlet:param name="myaction" value="showAddUserToVO" />
			<portlet:param name="userId" value="${userInfo.userId}" />
			<portlet:param name="firstReg" value="false" />
		</portlet:renderURL>
		
		<portlet:renderURL var="voUrl">
			<portlet:param name="myaction" value="showVOList" />
			<portlet:param name="waif" value="editUserInfoForm" />
			<portlet:param name="userId" value="${userInfo.userId}"/>
		</portlet:renderURL>

		<aui:form name="addUserToVOForm"
			action="${addUserToVOActionUrl}">

			
			<aui:button-row>
				<aui:button type="submit" value="Add VO" />
				
				<!--<aui:button type="cancel" value="Request VO association"
								onClick="location.href='${voUrl}';" />-->
			</aui:button-row>
		</aui:form>
		<div id="apriVo"></div>

	</div>
</div>
<div id="advancedSettings">

	<h3 class="header-title">Advanced Settings</h3>
	
	<div id="<portlet:namespace/>advSetOn">
	
		<aui:layout>

			<aui:fieldset>
				<aui:column columnWidth="80">
					<aui:fieldset>
					
						Notification settings.
				
						<br /> <br />
					</aui:fieldset>
				</aui:column>
				<aui:column columnWidth="20">
					<aui:fieldset>
						<div id="userSettings"><a href="#apriAdvSet" onclick="mostraAdvSetUtente();" onmouseover="viewTooltip('#settingsButtonAdv');"><img src="<%=request.getContextPath()%>/images/advancedsettings.png" width="24" height="24" style="float: right; padding-right:10px;"/></a></div>
					</aui:fieldset>
				</aui:column>	
			</aui:fieldset>
		</aui:layout>
		
	</div>
	<div id="<portlet:namespace/>advSetOff" style="display: none;">
		<div id="closeBox"><a href="#advSet" onclick="nascondiAdvSetUtente();"><img src="<%=request.getContextPath()%>/images/close-button2.png"/></a></div>
		<div id="closeBox"><a href="#advSet" onclick="nascondiAdvSetUtente();">Hide Settings</a></div>
		<br /> <br />
		
			<aui:layout>

				<aui:fieldset>
					<aui:column columnWidth="50">
						<aui:fieldset label="Notification">
							<aui:form name="editUserInfoForm" commandName="advOpts"
								action="${updateAdvOptsActionUrl}">
								<br></br>
	
								<aui:input name="idNotify" type="hidden"
									value="${advOpts.idNotify }" />
									
								<aui:input name="userId" type="hidden"
								value="<%=userInfo.getUserId() %>" />
								
								<aui:input name="proxyExpire" type="checkbox"
									label="Check if you want a notify mail befor proxy expiration" checked="${advOpts.proxyExpire }" />
								
								<br/> <br/>
								<aui:button-row>
									<aui:button type="submit" />
								</aui:button-row>
							</aui:form>
						</aui:fieldset>
					</aui:column>

					<aui:column columnWidth="50">
						<aui:fieldset label="Job Notification">
							<br></br>
							
							Under costruction.
							
						</aui:fieldset>
					</aui:column>

					
				</aui:fieldset>
			</aui:layout>
		
		
		<div id="apriAdvSet"></div>
	</div>
</div>


<br />
<br />

<c:choose>
	<c:when test="<%= request.isUserInRole("administrator") %>">
	
		<portlet:actionURL var="homeUrl">
			<portlet:param name="myaction" value="uploadComplete" />
			<portlet:param name="userId"
				value="<%= request.getParameter("userId") %>" />
		</portlet:actionURL>
		
		
		<aui:form name="catalogForm" action="${homeUrl}">
			<aui:button-row>
				<aui:button type="submit" value="Changes completed" />
			</aui:button-row>
		</aui:form>

	</c:when>
	<c:otherwise>
		
	
		<portlet:actionURL var="deleteURL">
			<portlet:param name="myaction" value="deleteByUser" /> 
			<portlet:param name="userId" value="${userInfo.userId }" /> 
		</portlet:actionURL>
		
		<aui:form name="catalogForm" action="${deleteUrl}">
			<aui:button-row>
				<aui:button type="cancel" value="Delete Account"
								onClick="verifyDelete('${deleteURL}')" />
				
			</aui:button-row>
		</aui:form>
	
	</c:otherwise>

</c:choose>

<div id="settingsButtonAdv" style="display:none;">Edit your Advanced Settings.</div>
<div id="settingsButtonCert" style="display:none;">Edit your certficate.</div>
<div id="settingsButtonVO" style="display:none;">Edit your VO.</div>

</div>

