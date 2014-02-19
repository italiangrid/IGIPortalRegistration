<%@ include file="/WEB-INF/jsp/init.jsp"%>

<portlet:actionURL var="sendMailUrl">
	<portlet:param name="myaction" value="sendMail"/>
</portlet:actionURL>

<h1>Send Mail</h1>

<liferay-ui:error key="send-mail-problem"
	message="send-mail-problem" />

<aui:form action="${sendMailUrl }" >
	
	<aui:input name="subject"  label="Subject"/>
	<br/>
	<aui:input name="sendToAll" label="Send to All" type="checkbox"/>
	<br/>
	<aui:input name="text" label="Message (html)" type="textarea" cols="100" rows="13"/>
	
	<aui:button-row>
		<aui:button type="submit" value="Send"/>
	</aui:button-row>

</aui:form>