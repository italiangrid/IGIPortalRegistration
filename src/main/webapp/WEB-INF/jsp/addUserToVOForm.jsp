<%@ include file="/WEB-INF/jsp/init.jsp"%>

<script type="text/javascript">
<!--

//-->
function showDescription(discipline){
	
	$("#<portlet:namespace/>voDescription").show();
	
	var totVo = $("#<portlet:namespace/>totVo").val()   ;
	var i=0;
	for(i=0;i<totVo;i++){
		$("#<portlet:namespace/>voDescr"+i).hide();
	}

	var voDescr = $("#<portlet:namespace/>VOselect_"+ discipline).val();
	$("#<portlet:namespace/>voDescr"+voDescr).show();
	
	var output = "";

		       output = "<input name='VOids' type='hidden' value='" + voDescr+ "' />";
		   

		$("#<portlet:namespace/>result").html(output);
	
	
}

function showVoList(){
	
	$("#<portlet:namespace/>voList").show();
	
	var discipline = $("#<portlet:namespace/>disciplineId").val()   ;
	var totDisc = $("#<portlet:namespace/>totDisc").val()   ;
	var i=0;
	for(i=0;i<totDisc;i++){
		$("#<portlet:namespace/>disc"+i).hide();
	}
	
	$("#<portlet:namespace/>voDescription").hide();
	var totVo = $("#<portlet:namespace/>totVo").val()   ;
	var i=0;
	for(i=0;i<totVo;i++){
		$("#<portlet:namespace/>voDescr"+i).hide();
	}
	
	var showId = "#<portlet:namespace/>" + discipline;
	
	$(showId).show();
}

function start(){
	$("#<portlet:namespace/>voList").hide();
	$("#<portlet:namespace/>voDescription").hide();
}


$(document).ready(function(){
	start();
});
</script>

<portlet:actionURL var="addUserToVOActionUrl">
	<portlet:param name="myaction" value="addUserToVO" />
</portlet:actionURL>
<portlet:renderURL var="homeUrl">
	<portlet:param name="myaction" value="userInfos" />
</portlet:renderURL>

<jsp:useBean id="userToVoList" type="java.util.List<portal.registration.domain.Vo>" scope="request"></jsp:useBean>


<liferay-ui:error key="user-vo-list-empty" message="user-vo-list-empty"/>
<liferay-ui:error key="no-user-found-in-VO" message="no-user-found-in-VO"/>
<liferay-ui:error key="no-cert-for-user" message="no-cert-for-user"/>
<liferay-ui:error key="edg-mkgridmap-problem" message="edg-mkgridmap-problem"/>
<liferay-ui:error key="edg-mkgridmap-exception" message="edg-mkgridmap-exception"/>

<jsp:useBean id="disciplines" type="java.util.List<java.lang.String>" scope="request"></jsp:useBean>


<aui:form name="addUserToVOForm" method="post" action="${addUserToVOActionUrl}" >

	<a href="${homeUrl}">Home</a>
	
	<br/><br/>

	<aui:layout>
	
		<h1 class="header-title">Scelta Virtual Organization</h1>
		
		<aui:input name="userId" type="hidden" value="${userId }" />
		<aui:input name="firstReg" type = "hidden" value="${firstReg}"/>
		
		<aui:fieldset>
		
			<aui:column columnWidth="33">
				
					<aui:fieldset label="Discipline">
					
						<br><br/>
						
							<%
							 int i=0;
								pageContext.setAttribute("i",i);
							%>

							<aui:select id="disciplineId" name="disciplineId" label="Discipline" onChange="showVoList();">
								
								<aui:option value="0"><liferay-ui:message key="Seleziona una VO"/></aui:option>
								
								<c:forEach var="discipline" items="${disciplines}">
									
									<aui:option value="disc${i}"><liferay-ui:message key="${discipline}"/></aui:option> 	
									<%
									 i++;
										pageContext.setAttribute("i",i);
									%>
								</c:forEach>
								
							</aui:select>
							<aui:input name="totDisc" id="totDisc" type="hidden" value="${i}"/>
					</aui:fieldset>
				<br/><br/>
			</aui:column>
			<div id="<portlet:namespace/>voList"> 	
			<aui:column columnWidth="33">
				
					<aui:fieldset label="VO">
					
						<%
						 int i=0;
							pageContext.setAttribute("i",i);
						%>
					
						<br><br/>
							<c:forEach var="discipline" items="${disciplines}">
							
								
								<div id="<portlet:namespace/>disc${i}" style="display:none">
									<aui:select id="VOselect_${i}" name="VOselect_${i}" label="${discipline}" onChange="showDescription(${i});">

										<aui:option value="0"><liferay-ui:message key="Seleziona una Disciplina"/></aui:option>
										
										<c:forEach var="vo" items="${vos}">
												<%
												Vo vo = (Vo) pageContext.getAttribute("vo");
												if(vo.getDiscipline().equals(pageContext.getAttribute("discipline"))){
												%>
													<aui:option value="${vo.idVo}"><liferay-ui:message key="${vo.vo}"/></aui:option>
												<%
												}
												%>
										</c:forEach>

									</aui:select> 
									<%
									 i++;
										pageContext.setAttribute("i",i);
									%>	
								
									</div>
							</c:forEach>
						
					</aui:fieldset>
				<br/><br/>
			</aui:column>
			</div>
			
			<div id="<portlet:namespace/>voDescription"> 
			<aui:column columnWidth="33">
				
					<aui:fieldset label="DesctizioneVO">
					
						<br><br/>
							<c:forEach var="vo" items="${vos}">
								<div id="<portlet:namespace/>voDescr${vo.idVo}" style="display:none">
									<c:out value="${vo.description}"/>
								</div>
							</c:forEach>
							<aui:input name="totVo" id="totVo" type="hidden" value="${fn:length(vos)}"/>
					</aui:fieldset>
				<br/><br/>
			</aui:column>
			</div>
			
			<div id="<portlet:namespace/>result">
			</div>
			
		   <aui:button-row>
		   		
		   			<aui:button type="submit" value="Salva"/>
					<portlet:renderURL var="backURL">
						<portlet:param name="myaction" value="editUserInfoForm" />
						<portlet:param name="userId" value="<%= request.getParameter("userId")%>" />
					</portlet:renderURL>
					
					<aui:button type="cancel" value="Indietro"
						onClick="location.href='${backURL}';" />
		   		
		   </aui:button-row>
		   
	   </aui:fieldset>
   </aui:layout>
</aui:form>


