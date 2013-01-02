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

</style>

<div id="action">
	<aui:fieldset label="Registration Instruction">
		<br/><br/>
		Per registrarti abbiamo bisogno dei tuoi dati personali, li recupereremo dalla tua organizzazione.
		<br/><br/>
		Ti verrà chiesto di selezionare la tua organizzazione ed effettuare il login, in questo modo recupereremo i tuoi dati personali, sucessivamente ti verrà chiesto di caricare il tuo certificato personale in formato p12 e di specivicare le Virtual Organization (VO) a cui appartieni. 
		<br/><br/>
		Se non fai parte di nessuna organizzazione tra quelle proposte usa l'apposito pulsante in questo modo useremo il tuo certificato personale per recuperare i tuoi dati.
		
		<br/><br/>
		
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