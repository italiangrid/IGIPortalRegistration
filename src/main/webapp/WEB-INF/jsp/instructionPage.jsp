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
			To use the Grid and Cloud resources some credentials are necessary, during the registration phase we check 
			if you already have all the necessary credentials otherwise we can provide you for them.
			<br/><br/>
			<hr/>
			<br/>
			In the first step we are going to retrieve your personal data from your organization.<br/>
			<strong>Please in the next page select your institute, if it is not in the list click on "Other Institutes" button.</strong>
			<br/><br/><br/>
			
		</div>
		</aui:column>
		<aui:column columnWidth="20">
		<br/><br/><br/>
		<img src="<%=request.getContextPath()%>/images/PatientFile.png" width="128" style="margin-right:20px; float: right"/>
		
		</aui:column>
		<portlet:actionURL var="showWAYF">
			<portlet:param name="myaction" value="startRegistration" />
		</portlet:actionURL>
		
		<aui:form id="startRegistration" name="startRegistration" action="${showWAYF }">
		
			
			<aui:button-row>
				<div class="button" style="float: left;">
				<liferay-ui:icon-menu>
				<liferay-ui:icon image="close" message="Abort Registration" url="#" onClick="location.href='https://flyback.cnaf.infn.it';" />
				</liferay-ui:icon-menu>
				</div>
				<div class="button" style="float: right;">
				<liferay-ui:icon-menu>
				<liferay-ui:icon image="forward" message="Continue" url="#" onClick="submit();" />
				</liferay-ui:icon-menu>
				</div>
				<aui:button type="cancel"  value="Abort Registration" onClick="location.href='https://flyback.cnaf.infn.it'" style="display:none;"></aui:button>
				
				<aui:button type="submit"  value="Continue" style="display:none;"></aui:button>
			</aui:button-row>
			
		</aui:form>
	</aui:fieldset>
</div>

