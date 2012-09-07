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
		$("#<portlet:namespace/>formCA").submit();
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

</style>

<div id="container">

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

				<aui:fieldset>
					<br /><br /><br /><br /><br /><br /><br /><br />
					
					<div id="chooseContainer">
						<a href="" onclick="setHaveCert('true'); return false;">
						<div class="choose bordered">
						
							<div class="mess">
								<div class="center">
								Upload your personal certificate.
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
						<a href="https://openlab03.cnaf.infn.it/CAOnlineBridge/home?t1=${tokens[0]}&t2=${tokens[1]}" onclick="setHaveCert('false'); $(this).modal({width:800, height:600}).open(); return false;">
						<div class="choose bordered">
							<div class="mess">
								<div class="center">
								Request a new certificate<br/>by our on-Line CA.
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

					<aui:input name="userId" type="hidden" value="${userId}" />
					<aui:input name="username" type="hidden" value="${username}" />
					<aui:input name="firstReg" type="hidden" value="${firstReg}" />
							
					<aui:input name="haveCert" id="haveCert" type="hidden" value="false"/>
					
					<!-- 
					<a href="" onclick="setHaveCert('true'); return false;"><img src="<%=request.getContextPath()%>/images/check.png" id="yesImg" width="64" height="64"/></a>
					<a href="https://openlab03.cnaf.infn.it/CAOnlineBridge/home?t1=${tokens[0]}&t2=${tokens[1]}" onclick="setHaveCert('false'); $(this).modal({width:800, height:600}).open(); return false;"><img src="<%=request.getContextPath()%>/images/missed_calls.png" id="noImg" width="64" height="64"/></a>
					 -->

				</aui:fieldset>

				<br />
				<br />
			</aui:column>

			

			<aui:button-row>
				<aui:button type="submit" value="Continue"  style="display:none"/>
				

						<aui:button type="cancel" value="Back"
							onClick="location.href='https://halfback.cnaf.infn.it/casshib/shib/app1/login?service=https%3A%2F%2Fflyback.cnaf.infn.it%2Fc%2Fportal%2Flogin%3Fp_l_id%3D10669';" />
					

			</aui:button-row>

		</aui:fieldset>
	</aui:layout>
</aui:form>





</div>