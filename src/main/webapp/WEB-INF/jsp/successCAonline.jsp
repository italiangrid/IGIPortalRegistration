<%@ include file="/WEB-INF/jsp/init.jsp"%>
<div id="container">
<aui:form>
	<aui:layout>
		<aui:fieldset>		
			<strong>Registration completed.</strong> <img src="<%=request.getContextPath()%>/images/check.png" width="64" height="64" style="float: right"/>
			<aui:button-row>
				<aui:button type="cancel" value="Registration terminated"
							onClick="location.href='https://halfback.cnaf.infn.it/casshib/shib/app4/login?service=https%3A%2F%2Fgridlab04.cnaf.infn.it%2Fc%2Fportal%2Flogin%3Fp_l_id%3D10671';" />
			</aui:button-row>
		</aui:fieldset>
	</aui:layout>
</aui:form>
</div>