<%@ include file="/WEB-INF/jsp/init.jsp"%>

<portlet:actionURL var="endActionUrl">
	<portlet:param name="myaction" value="endRegistration" />
</portlet:actionURL>

<div id="container2">
	<aui:fieldset label="CA Online - Request new certificate">
	<aui:column columnWidth="20"><br/></aui:column>
	<aui:column columnWidth="60">
	<iframe frameborder="0" scrolling="no" allowtransparency="true"src="https://openlab03.cnaf.infn.it/CAOnlineBridge/home?t1=${tokens[0]}&t2=${tokens[1]}" width="800" height="600" ></iframe>
	<br/><br/>
	<aui:form name="caForm" action="${endActionUrl}">
	
		<aui:button type="cancel" value="Terminate Registration"
						onClick="location.href='${loginUrl}';" />
	</aui:form>
	</aui:column>
	<aui:column columnWidth="20"><br/></aui:column>
	</aui:fieldset>
</div>