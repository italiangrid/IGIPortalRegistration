<%@ include file="/WEB-INF/jsp/init.jsp"%>

<portlet:actionURL var="sendMailUrl">
	<portlet:param name="myaction" value="sendMail"/>
</portlet:actionURL>

<h1>Send Mail</h1>

<liferay-ui:error key="send-mail-problem"
	message="send-mail-problem" />
<liferay-ui:error key="send-mail-empty-list"
	message="send-mail-empty-list" />	
	

<aui:form action="${sendMailUrl }" >
	
	<aui:fieldset>
	<aui:column columnWidth="40">
		<aui:input name="subject"  label="Subject"/>
	</aui:column>
	<aui:column columnWidth="60">
		<aui:input name="sendToAll" label="Send to All" type="radio" value="true"/>
		<aui:input id="select" name="sendToAll" label="Only valid user that was loged in ${monthEarly } months." type="radio" value="false" checked="checked"/>
	</aui:column>
	</aui:fieldset>
	<aui:input name="text" label="Message (html)" type="textarea" cols="100" rows="13"/>
	
	<aui:button-row>
		<aui:button type="submit" value="Send"/>
	</aui:button-row>

</aui:form>


<script type="text/javascript">

	$("#<portlet:namespace/>select").prop('checked',true);
	
</script>