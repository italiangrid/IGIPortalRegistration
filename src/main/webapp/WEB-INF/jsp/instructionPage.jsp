<%@ include file="/WEB-INF/jsp/init.jsp"%> 

<script type="text/javascript">
<!--

//-->

function submit(){
	
	$("#<portlet:namespace/>startRegistration").submit();
}

$(document).ready(function() {
	$(".taglib-text").css("text-decoration","none");
});

</script>

<style>
<!--

-->

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
	float:right; 
	margin-right:20px;
}

#userData{
	font-size: 14px;
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

</style>

<div id="action">

	
	
	<aui:fieldset label="Registration">
	
		<br/><br/>
			<img src="<%=request.getContextPath()%>/images/registration_step1.png"/>
		<br/>
		<aui:column columnWidth="80">
		<div id="userData">
			<br/><br/>
			We are going to retrieve your personal data from your organization.<br/><br/>
			<div style="float: left; color: red; font-size: 16px; height: 32px; margin: 0 10px 15px;"><img src="<%=request.getContextPath()%>/images/arrow_right2.png" width="32" /></div>
			<div style="float: left; font-weight: bold; width: 75% ;height: 25px; padding-top:7px; margin: 0 0px 15px; color: #215285;">Please in the next page select your Organization<c:if test="${idpEnabled=='true' }"> or click on "Other Institute" button.</c:if></div>
			<div style="float: left; color: red; font-size: 16px; height: 32px; margin: 0 10px 15px;"><img src="<%=request.getContextPath()%>/images/arrow_left.png" width="32" /></div>
			<div style="clear: both;"></div>
			
			<hr/>
			<br/>
			In case of problems please delete cache and cookies from your browser or restart your browser.
			<br/><br/><br/>
			
		</div>
		</aui:column>
		<aui:column columnWidth="20">
		<br/><br/><br/>
		<img src="<%=request.getContextPath()%>/images/PatientFile.png" width="128" style="margin-right:20px; float: right"/>
		
		</aui:column>
		
			<aui:button-row>
				<div class="button" style="float: left;">
				<liferay-ui:icon-menu>
				<liferay-ui:icon image="close" message="Abort Registration" url="#" onClick="location.href='${goToHome}';" />
				</liferay-ui:icon-menu>
				</div>
				<div class="button" style="float: right;">
				<liferay-ui:icon-menu>
				<liferay-ui:icon image="forward" message="Continue" url="#" onClick="location.href='${goToWAYF}';" />
				</liferay-ui:icon-menu>
				</div>
				
			</aui:button-row>
			
		
	</aui:fieldset>
</div>

