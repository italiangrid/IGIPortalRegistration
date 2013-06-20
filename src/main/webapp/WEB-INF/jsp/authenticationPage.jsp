<%@ include file="/WEB-INF/jsp/init.jsp"%>

<style type="text/css">

#action {
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
.text{
	font-size: 14px;
}

</style>

<div>


	<div id="action">
	<aui:fieldset label="Registration">
    <p class="text">You have been automatically registered in the Portal Identity provider. </br> The next times to log into the portal choose the <b>IGI Portal IDP</b> and  click on <b>Certificate Login</b> button.</p>
    <p></p>
 	<div>
    <div style="float:left; border: 5px solid black; "><img src="<%=request.getContextPath()%>/images/idp-login1.png" height="290px" alt="Portal idp choose" /></div>
    <div style="float:left; margin-left:10px; margin-right:10px; margin-top:150px;"><img src="<%=request.getContextPath()%>/images/Arrow_Right.png" height="56px" alt="idp logo" /></div>
    <div style="float:left; border: 5px solid black; margin-bottom:10px"><img src="<%=request.getContextPath()%>/images/idp-login2.png" height="290px" alt="Portal idp login"/></div>
   </div>
	<aui:form>
	<aui:button-row>
				<portlet:actionURL var="abortUrl">
					<portlet:param name="myaction" value="abortRegistration"/>
				</portlet:actionURL>
				<div class="button" style="float: right;">
				<liferay-ui:icon-menu>
				<liferay-ui:icon image="forward" message="Registration Completed" url="${loginUrl }"/>
				</liferay-ui:icon-menu>
				</div>
				
				
	</aui:button-row>
	</aui:form>
	</aui:fieldset>
	</div>
	<%@ include file="/WEB-INF/jsp/summary.jsp" %>
	<div style="clear: both;"></div>
	
</div>