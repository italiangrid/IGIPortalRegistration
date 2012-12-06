<%@ include file="/WEB-INF/jsp/init.jsp"%>

<script type="text/javascript">
<!--
	//-->
	function mysubmit() {
		//submit form

		$("#formId").submit();

		//alert("OK");
		//window.location = url;
	}
</script>

<%
	if (request.getParameter("userId") != null) {
		pageContext.setAttribute("userId",
				request.getParameter("userId"));
	}
%>

<div id="container2">

<portlet:renderURL var="addUserToVOActionUrl">
	<portlet:param name="myaction" value="showAddUserToVO" />
	<portlet:param name="userId" value="${userId}" />
	<portlet:param name="firstReg" value="true" />
</portlet:renderURL>
<portlet:renderURL var="homeUrl">
	<portlet:param name="myaction" value="userInfos" />
</portlet:renderURL>

<jsp:useBean id="userToVoList"
	type="java.util.List<it.italiangrid.portal.dbapi.domain.Vo>"
	scope="request"></jsp:useBean>


<liferay-ui:success key="upload-cert-successufully"
	message="upload-cert-successufully" />
<liferay-ui:success key="userToVo-adding-success"
	message="userToVo-adding-success" />
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
<liferay-ui:error key="exception-deactivation-user"
	message="exception-deactivation-user" />


<aui:form name="addUserToVOForm" action="${addUserToVOActionUrl}">

	<aui:input name="userId" type="hidden" value="${userId}" />
	<aui:input name="firstReg" type="hidden" value="true" />

	<aui:layout>

		<h1 class="header-title">Virtual Organization</h1>



		<aui:fieldset>

			<aui:column columnWidth="25">
				<aui:fieldset label="Registartion">
					<br />
					<img src="<%=request.getContextPath()%>/images/step3.png" />
					<!-- <img src="https://gridlab17.cnaf.infn.it/image/image_gallery?img_id=12351&t=1326102175114" alt="Fase 3" /> -->
				</aui:fieldset>
			</aui:column>

			<aui:column columnWidth="75">

				<aui:fieldset label="VO List">

					<br />
					<br />


					<liferay-ui:search-container emptyResultsMessage="No VO selected"
						delta="5">
						<liferay-ui:search-container-results>
							<%
								results = ListUtil.subList(
										userToVoList,
										searchContainer.getStart(),
										searchContainer.getEnd());
								total = userToVoList.size();

								pageContext.setAttribute("results",
										results);
								pageContext
										.setAttribute("total", total);
							%>

						</liferay-ui:search-container-results>
						<liferay-ui:search-container-row
							className="it.italiangrid.portal.dbapi.domain.Vo"
							keyProperty="idVo" modelVar="Vo">
							<liferay-ui:search-container-column-text name="VO name"
								property="vo" />
							<liferay-ui:search-container-column-text name="VO description"
								property="description" />

							<liferay-ui:search-container-column-text name="Roles">
								<c:out value="${fn:replace(userFqansPresent[Vo.idVo],';',' ')}"></c:out>
							</liferay-ui:search-container-column-text>

							<liferay-ui:search-container-column-jsp
								path="/WEB-INF/jsp/vo-action.jsp" align="right" />
						</liferay-ui:search-container-row>
						<liferay-ui:search-iterator />
					</liferay-ui:search-container>

				</aui:fieldset>
				<br />
				<br />
			</aui:column>





			<aui:button type="submit" value="Add VO" />
			<portlet:renderURL var="voUrl">
				<portlet:param name="myaction" value="showVOList" />
				<portlet:param name="waif" value="showAddUserToVoPresents" />
				<portlet:param name="userId" value="${userId }" />
			</portlet:renderURL>
			<!--<aui:button type="button" value="Request VO association"
							onClick="location.href='${voUrl}';" />
			-->
			<c:if test="<%=!themeDisplay.isSignedIn()%>">
				<aui:button type="cancel" value="Registration terminated"
					onClick="location.href='https://halfback.cnaf.infn.it/casshib/shib/app2/login?service=https%3A%2F%2Fportal.italiangrid.it%2Fc%2Fportal%2Flogin%3Fp_l_id%3D11504';" />
			<!-- onClick="location.href='https://halfback.cnaf.infn.it/casshib/shib/app4/login?service=https%3A%2F%2Fgridlab04.cnaf.infn.it%2Fc%2Fportal%2Flogin%3Fp_l_id%3D10671';" />
			 --></c:if>

			<c:if test="<%=themeDisplay.isSignedIn()%>">
				<aui:button type="cancel" value="Registration terminated"
					onClick="mysubmit();${homeUrl }" />
			</c:if>

		</aui:fieldset>
	</aui:layout>
</aui:form>

<div style="display: none">


	<form id="formId"
		action="https://portal.italiangrid.it/web/guest/import?p_auth=<%=AuthTokenUtil.getToken(request)%>&p_p_id=wfimport_WAR_wspgrade&p_p_lifecycle=1&p_p_state=normal&p_p_mode=view&p_p_col_id=column-1&p_p_col_count=1&_wfimport_WAR_wspgrade_guse=doJustDoIt">
		<input type="hidden" value="import" name="impMethode"> <input
			type="hidden" value="appl" name="impWfType"> <input
			type="hidden" value="126" name="impItemId"> <input
			type="hidden"
			value="https://portal.italiangrid.it/web/guest/job-monitor"
			name="returnPath"> <input type="hidden" value="JOB_gridit"
			name="wfimp_newRealName"> <input type="submit" value="Import">
	</form>
</div>

</div>
