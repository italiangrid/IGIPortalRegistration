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
	<aui:fieldset label="CA Online - Request new certificate">
	
	
	<iframe frameborder="0" scrolling="no" allowtransparency="true" src="${caUrl }" width="900" height="600"
	 ></iframe>
	<br/><br/>
	<aui:form name="caForm" action="${endActionUrl}">
		<aui:button type="cancel" value= "Back" onClick="history.back()"/>
		<aui:button type="cancel" value="Registration Completed"
						onClick="location.href='${loginUrl}';" />
	</aui:form>
	
	</aui:fieldset>
</div>
<div style="clear:both;"></div>
</div>