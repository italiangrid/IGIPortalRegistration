<%@ include file="/WEB-INF/jsp/init.jsp"%>

<portlet:actionURL var="sendMailUrl">
	<portlet:param name="myaction" value="sendMail"/>
</portlet:actionURL>

<h1>Send Mail</h1>

<liferay-ui:error key="send-mail-problem"
	message="send-mail-problem" />

<aui:form action="${sendMailUrl }" >
	<aui:input name="subject"  label="Subject"/>
	<aui:input name="text" label="Message (html)" type="textarea" cols="80" rows="15"/>
	
	<aui:button-row>
		<aui:button type="submit" value="Send"/>
	</aui:button-row>

</aui:form>