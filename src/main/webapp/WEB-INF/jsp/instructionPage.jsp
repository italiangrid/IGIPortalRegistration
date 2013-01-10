<%@ include file="/WEB-INF/jsp/init.jsp"%>

			  


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

</style>

<div id="action">

	<h1 class="header-title">Registration - User Data</h1>
	
	<aui:fieldset>
		<aui:column columnWidth="80">
		<div id="userData">
			<br/><br/>
			To use the Grid and Cloud resources some credentials are necessary, during the registration phase we check 
			if you already have all the necessary credentials otherwise we can provide for them.
			<br/><br/>
			<hr/>
			<br/>
			In the first step we are going to retrieve your personal data from your organization.<br/>
			<strong>Please in the next page select your institute, if it is not in the list click on "Other Institutes" button.</strong>
			<br/><br/>
			
		</div>
		</aui:column>
		<aui:column columnWidth="20">
		<br/><br/><br/>
		<img src="<%=request.getContextPath()%>/images/PatientFile.png" width="128" style="margin-right:20px; float: right"/>
		
		</aui:column>
		<portlet:actionURL var="showWAYF">
			<portlet:param name="myaction" value="startRegistration" />
		</portlet:actionURL>
		
		<aui:form name="startRegistration" action="${showWAYF }">
			<aui:button-row id="submit">
				<aui:button type="submit"  value="Continue"></aui:button>
			</aui:button-row>
		</aui:form>
	</aui:fieldset>
</div>