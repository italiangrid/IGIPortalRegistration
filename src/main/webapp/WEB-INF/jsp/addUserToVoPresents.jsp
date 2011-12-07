<%@ include file="/WEB-INF/jsp/init.jsp"%>

<%
if (request.getParameter("userId") != null){
	pageContext.setAttribute("userId",request.getParameter("userId"));
}	
%>


<portlet:renderURL var="addUserToVOActionUrl">
	<portlet:param name="myaction" value="showAddUserToVO" />
	<portlet:param name="userId" value="${userId}" />
	<portlet:param name="firstReg" value="true" />
</portlet:renderURL>
<portlet:renderURL var="homeUrl">
	<portlet:param name="myaction" value="userInfos" />
</portlet:renderURL>

<jsp:useBean id="userToVoList"
	type="java.util.List<portal.registration.domain.Vo>" scope="request"></jsp:useBean>


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


<aui:form name="addUserToVOForm" method="post"
	action="${addUserToVOActionUrl}">

	<aui:input name="userId" type="hidden" value="${userId}" />
	<aui:input name="firstReg" type="hidden" value="true" />

	<aui:layout>

		<h1 class="header-title">Virtual Organization</h1>

		

		<aui:fieldset>
		
			<aui:column columnWidth="25">
				<aui:fieldset label="Registrazione">
					<br />
					<img src="https://flyback.cnaf.infn.it/image/image_gallery?img_id=13471&t=1323074859741" alt="Fase 3" />
				</aui:fieldset>
			</aui:column>

			<aui:column columnWidth="75">

				<aui:fieldset label="Lista VO">

					<br />
					<br />
					
			
					<liferay-ui:search-container
						emptyResultsMessage="Non appartieni a nessuna VO attualmente aggiungile qui in fianco"
						delta="5">
						<liferay-ui:search-container-results>
							<%
								results = ListUtil.subList( userToVoList,
															searchContainer.getStart(),
															searchContainer.getEnd());
								total = userToVoList.size();

								pageContext.setAttribute("results",results);
								pageContext.setAttribute("total", total);
							%>

						</liferay-ui:search-container-results>
						<liferay-ui:search-container-row
							className="portal.registration.domain.Vo" keyProperty="idVo"
							modelVar="Vo">
							<liferay-ui:search-container-column-text name="Nome VO"
								property="vo" />
							<liferay-ui:search-container-column-text name="Descrizione VO"
								property="description" />
							<liferay-ui:search-container-column-jsp
								path="/WEB-INF/jsp/vo-action.jsp" align="right" />
						</liferay-ui:search-container-row>
						<liferay-ui:search-iterator />
					</liferay-ui:search-container>

				</aui:fieldset>
				<br />
				<br />
			</aui:column>
			
			

			
			
			<aui:button type="submit" value="Aggiungi VO" />
			<portlet:renderURL var="voUrl">
				<portlet:param name="myaction" value="showVOList" />
				<portlet:param name="waif" value="showAddUserToVoPresents" />
				<portlet:param name="userId" value="${userId }" />
			</portlet:renderURL>
			<aui:button type="button" value="Richiedi appartenenza VO"
							onClick="location.href='${voUrl}';" />
		
			<c:if test="<%= !themeDisplay.isSignedIn() %>">
			<aui:button type="cancel" value="Fine Registrazione"
				onClick="location.href='https://halfback.cnaf.infn.it/casshib/shib/app1/login?service=https%3A%2F%2Fflyback.cnaf.infn.it%2Fc%2Fportal%2Flogin%3Fp_l_id%3D11722';" />
			</c:if>
			
			<c:if test="<%= themeDisplay.isSignedIn() %>">
			<aui:button type="cancel" value="Fine Registrazione"
				onClick="${homeUrl }" />
			</c:if>
			
		</aui:fieldset>
	</aui:layout>
</aui:form>



