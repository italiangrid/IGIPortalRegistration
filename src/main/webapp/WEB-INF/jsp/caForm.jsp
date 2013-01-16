<%@ include file="/WEB-INF/jsp/init.jsp"%>

<portlet:actionURL var="endActionUrl">
	<portlet:param name="myaction" value="endRegistration" />
</portlet:actionURL>

<script type="text/javascript">
<!--

//-->

function submit(){
	
	$("#<portlet:namespace/>caForm").submit();
}

$(document).ready(function() {
	$(".taglib-text").css("text-decoration","none");
});

</script>

<style>

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
	float: right;
	margin-right: 20px;
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

<div>
		<%@ include file="/WEB-INF/jsp/summary.jsp" %>	

<div id="action">

	
	<aui:fieldset label="Registration">
	
	<br/><br/>
	<img src="<%=request.getContextPath()%>/images/registration_step3_request.png"/>
	<br/>
	<aui:column columnWidth="70">
	<iframe frameborder="0" scrolling="no" allowtransparency="true" src="${caUrl }" width="100%" height="340"
	 ></iframe>
	<br/><br/>
	</aui:column>
	<aui:column columnWidth="25">
	<br/><br/><br/><br/><br/><br/><br/><br/>
	<a href="https://portal.italiangrid.it:8443/info/certificate-request-technical-info.html"  onclick="$(this).modal({width:800, height:600, message:true}).open(); return false;"><img class="displayed"
													src="<%=request.getContextPath()%>/images/Information2.png"
													id="noImg" width="64" />Technical Information</a>
	</aui:column>
	</aui:fieldset>
	<aui:form name="caForm" id="caForm" action="${endActionUrl}">
	
		<aui:button-row>
			<div class="button" style="float: left;">
			<liferay-ui:icon-menu>
			<liferay-ui:icon image="back" message="Back" url="#" onClick="history.back()" />
			</liferay-ui:icon-menu>
			</div>
			<div class="button" style="float: right;">
			<liferay-ui:icon-menu>
			<liferay-ui:icon image="forward" message="Registration Completed" url="#" onClick="location.href='${loginUrl}';" />
			</liferay-ui:icon-menu>
			</div>
		</aui:button-row>
	
		<aui:button type="cancel" value= "Back" style="display: none;" onClick="history.back()"/>
		<div id="submit">
		<aui:button type="cancel" value="Registration Completed"
						onClick="location.href='${loginUrl}';" style="display: none;"/>
		</div>
	</aui:form>
	
	
</div>
<div style="clear:both;"></div>
</div>