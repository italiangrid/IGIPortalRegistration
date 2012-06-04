<%@ include file="/WEB-INF/jsp/init.jsp"%>


<script type="text/javascript">
<!--

//-->


function setHaveCert(value){
	//alert(value);
	$("#<portlet:namespace/>haveCert").attr("value",value);
	
	if(value=='true'){
		//alert(value);
		//opacizza NO
		//opacity:0.4;
		//filter:alpha(opacity=40);
		$("#yesImg").attr("style","opacity:1.0; filter:alpha(opacity=100);");
		$("#noImg").attr("style","opacity:0.4; filter:alpha(opacity=40);");
	}else{
		//alert(value);
		//opacizza SI
		$("#noImg").attr("style","opacity:1.0; filter:alpha(opacity=100);");
		$("#yesImg").attr("style","opacity:0.4; filter:alpha(opacity=40);");
	}
}


</script>

<style>
<!--

-->



</style>



<liferay-ui:success key="user-saved-successufully"
	message="user-saved-successufully" />

	
<%
				if (request.getParameter("userId") != null)
										pageContext.setAttribute("userId",
												request.getParameter("userId"));
									if (request.getParameter("username") != null)
										pageContext.setAttribute("username",
												request.getParameter("username"));
									if (request.getParameter("firstReg") != null)
										pageContext.setAttribute("firstReg",
												request.getParameter("firstReg"));
									else
										pageContext.setAttribute("firstReg","false");
											
			%>

<portlet:actionURL var="uploadCertUrl">
	<portlet:param name="myaction" value="haveCert" />
</portlet:actionURL>

<portlet:renderURL var="homeUrl">
	<portlet:param name="myaction" value="userInfos" />
</portlet:renderURL>

<!--  -->

<aui:form name="formCA" id="formCA" action="${uploadCertUrl}">
	<aui:layout>

		<h1 class="header-title">Have a Personal Certificate?</h1>

		<br></br>

		<aui:fieldset>
			
			
				
			<c:if test="${firstReg == true}">
			<aui:column columnWidth="25">
				
				<aui:fieldset label="Registration">
					<br />
					
					<img src="<%=request.getContextPath()%>/images/step2.png"/>
					
					

				</aui:fieldset>
				
				<br />
				<br />
			</aui:column>
			</c:if>
			<aui:column columnWidth="25">

				<aui:fieldset label="Have a Certificate?">
					<br />
					
					

					<aui:input name="userId" type="hidden" value="${userId}" />
					<aui:input name="username" type="hidden" value="${username}" />
					<aui:input name="firstReg" type="hidden" value="${firstReg}" />
							
					<aui:input name="haveCert" id="haveCert" type="hidden" value="false"/>
					
					<a href="" onclick="setHaveCert('true'); return false;"><img src="<%=request.getContextPath()%>/images/check.png" id="yesImg" width="64" height="64"/></a>
					<a href="https://localhost:8443/CAOnlineBridge" onclick="setHaveCert('false'); $(this).modal({width:800, height:600}).open(); return false;"><img src="<%=request.getContextPath()%>/images/missed_calls.png" id="noImg" width="64" height="64"/></a>
					

				</aui:fieldset>

				<br />
				<br />
			</aui:column>

			

			<aui:button-row>
				<aui:button type="submit" value="Continue"/>
				

						<aui:button type="cancel" value="Back"
							onClick="location.href='${homeUrl}';" />
					

			</aui:button-row>

		</aui:fieldset>
	</aui:layout>
</aui:form>





