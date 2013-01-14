<%@ include file="/WEB-INF/jsp/init.jsp"%>

<portlet:actionURL var="endActionUrl">
	<portlet:param name="myaction" value="endRegistration" />
</portlet:actionURL>

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
	<aui:form name="caForm" action="${endActionUrl}">
		<aui:button type="cancel" value= "Back" onClick="history.back()"/>
		<aui:button type="cancel" value="Registration Completed"
						onClick="location.href='${loginUrl}';" />
	</aui:form>
	
	
</div>
<div style="clear:both;"></div>
</div>