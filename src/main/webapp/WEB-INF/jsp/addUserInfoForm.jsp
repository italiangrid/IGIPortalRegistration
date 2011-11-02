<%@ include file="/WEB-INF/jsp/init.jsp"%>
<script type="text/javascript">
<!--
	//-->

	var lista;
	var stampa;

	$.extend({
		getUrlVars : function() {
			var vars = [], hash;
			var hashes = window.location.href.slice(
					window.location.href.indexOf('?') + 1).split('&');
			for ( var i = 0; i < hashes.length; i++) {
				hash = hashes[i].split('=');
				vars.push(hash[0]);
				vars[hash[0]] = hash[1];
			}
			return vars;
		},
		getUrlVar : function(name) {
			return $.getUrlVars()[name];
		}
	});

	function addVorole() {
		var idVorole = $("#<portlet:namespace/>VOrole").val();
		var tmp = new Object();
		tmp["idVorole"] = idVorole;
		tmp["testo"] = $("#<portlet:namespace/>VOrole option:selected").text();
		stampa.push(tmp);
		lista.push(idVorole);
		preview();
		onchangeVO();

	}

	function previewVO() {
		$("#listaVo").html("");
		var output = "";
		var i = 0;
		while (i < stampa.length) {

			output = output
					+ stampa[i].testo
					+ "<a href='#add' onClick=\"removeVo("
					+ stampa[i].idVo
					+ ");\"> Delete</a> "
					+ "<input name='idVoList' type='hidden' value='" + stampa[i].idVo+ "' />"
					+ "<br/>";
			i++;
		}

		$("#listaVo").html(output);
	}

	function addVo() {
		var idVo = $("#<portlet:namespace/>VOids").val();
		if (idVo != 0) {
			var tmp = new Object();
			tmp["idVo"] = idVo;
			tmp["testo"] = $("#<portlet:namespace/>VOids option:selected")
					.text();
			$("#<portlet:namespace/>VOids option[value = '" + idVo + "']")
					.remove();
			stampa.push(tmp);
			previewVO();
		} else {
			alert("Seleziona una VO valida");
		}

	}

	function removeVo(idVo) {
		var i = 0;
		while (i < stampa.length) {
			if (stampa[i].idVo == idVo) {
				$("#<portlet:namespace/>VOids").append(
						"<option value='"+idVo+"'>" + stampa[i].testo
								+ "</option>");
				stampa.splice(i, 1);
				break;
			}
			i++;
		}
		previewVO();
	}

	function displayUpload() {
		var cb = $("#<portlet:namespace/>haveCert").val();
		if (cb == "true") {
			$("#<portlet:namespace/>get_cert").hide("slow");
			$("#<portlet:namespace/>update_cert").show("slow");
		} else {
			$("#<portlet:namespace/>get_cert").show("slow");
			$("#<portlet:namespace/>update_cert").hide("slow");
		}
	}

	function verifyPassword() {
		var pwd1 = $("#<portlet:namespace/>password").val();
		var pwd2 = $("#<portlet:namespace/>passwordVerify").val();
		var output = "";
		if (pwd1 == pwd2) {
			output = "OK";
		} else {
			output = "KO";
		}
		$(".<portlet:namespace/>pwd").html(output);
	}

	function setIdp() {

		var appname = $.getUrlVar('Shib-Application-ID');
		if (appname == "app1") {
			$("#<portlet:namespace/>idpSelect option[value = '1']").attr(
					"selected", "selected");
			$("#<portlet:namespace/>idpOk").show("slow");
		} else {
			if (appname == "app2") {
				$("#<portlet:namespace/>idpSelect option[value = '2']").attr(
						"selected", "selected");
				$("#<portlet:namespace/>idpOk").show("slow");
			} else {
				var value = $("#<portlet:namespace/>idpValue").val();
				if (value != "") {
					$(
							"#<portlet:namespace/>idpSelect option[value = '"
									+ value + "']")
							.attr("selected", "selected");
					if (value == 1000) {
						$("#<portlet:namespace/>NOidp").show("slow");
					} else {
						$("#<portlet:namespace/>idpOk").show("slow");
					}
				}

			}
		}
	}

	function idpRedirect() {
		var Idp = $("#<portlet:namespace/>idpSelect").val();
		if (Idp == "1") {
			window.location = "https://halfback.cnaf.infn.it/app1/index.jsp";
		}
		if (Idp == "2") {
			window.location = "https://halfback.cnaf.infn.it/app2/index.jsp";
		}
		if (Idp == "0") {
			$("#<portlet:namespace/>NOidp").hide("slow");
			$("#<portlet:namespace/>idpOk").hide("slow");
		}
		if (Idp == "1000") {
			$("#<portlet:namespace/>NOidp").show("slow");
			$("#<portlet:namespace/>idpOk").hide("slow");
		}
	}

	function start() {
		$("#<portlet:namespace/>idpOk").hide("slow");
		$("#<portlet:namespace/>NOidp").hide("slow");
	}

	$(document).ready(function() {
		lista = new Array();
		stampa = new Array();
		//start();
		//displayUpload();
		setIdp();
	});
</script>

<portlet:actionURL var="addUserInfoActionUrl">
	<portlet:param name="myaction" value="addUserInfo" />
</portlet:actionURL>
<portlet:renderURL var="homeUrl">
	<portlet:param name="myaction" value="userInfos" />
</portlet:renderURL>

<liferay-ui:error key="error-saving-registration"
	message="error-saving-registration" />
<liferay-ui:error key="user-first-name-required"
	message="user-first-name-required" />
<liferay-ui:error key="user-last-name-required"
	message="user-last-name-required" />
<liferay-ui:error key="user-institute-required"
	message="user-institute-required" />
<liferay-ui:error key="user-mail-required" message="user-mail-required" />
<liferay-ui:error key="user-valid-mail-required"
	message="user-valid-mail-required" />
<liferay-ui:error key="user-username-required"
	message="user-username-required" />
<liferay-ui:error key="user-username-duplicate"
	message="user-username-duplicate" />
<liferay-ui:error key="user-mail-duplicate"
	message="user-mail-duplicate" />
<liferay-ui:error key="user-phone-valid" message="user-phone-valid" />

<jsp:useBean id="userInfo" type="portal.registration.domain.UserInfo"
	scope="request" />

<aui:form name="addUserInfoForm" commandName="userInfo" method="post"
	action="${addUserInfoActionUrl}">

	<aui:layout>

		<h1 class="header-title">Dati Utente</h1>

		<a href="${homeUrl}">Home</a>

		<br></br>

		<aui:fieldset>

			<aui:column columnWidth="20">

				<aui:fieldset label="Identity Provider">

					<br></br>

					<aui:input id="idpValue" name="idpValue" type="hidden"
						value="${idpValue }" />
					<!--  -->
					<aui:select id="idpSelect" name="idpId" label="IDP"
						onChange="idpRedirect();">

						<aui:option value="0">
							<liferay-ui:message key="Seleziona un Idp" />
						</aui:option>
						<aui:option value="1000">
							<liferay-ui:message key="Nessun IDP" />
						</aui:option>

						<c:forEach var="idpi" items="${idps}">
							<aui:option value="${idpi.idIdp}">
								<liferay-ui:message key="${idpi.idpname}" />
							</aui:option>
						</c:forEach>

					</aui:select>

				</aui:fieldset>

			</aui:column>

			<div id="<portlet:namespace/>NOidp" style="display: none;">
				<aui:column columnWidth="50">

					<aui:fieldset label="Iscriviti al nostro IDP">
						<br />
					Richiedi l'appartenenza all'idp sequando il seguente link: <aui:a
							href="#">IDP Page</aui:a>
						<br />
						<br />
					Dopo aver seguito tutto le indicazione e dopo aver ricevuto la conferma di appartenenza all'IDP torna su questa pagina per proseguire nella registrazione del portale.
					
					</aui:fieldset>

				</aui:column>
			</div>
			<div id="<portlet:namespace/>idpOk" style="display: none;">
				<aui:column columnWidth="25">

					<aui:fieldset label="Dati Personali">

						<br></br>
						<%
							if (request.getParameter("uid") != null)
								userInfo.setUsername(request
										.getParameter("uid"));
							if (request.getParameter("mail") != null)
								userInfo.setMail(request
										.getParameter("mail"));
							if (request.getParameter("givenName") != null)
								userInfo.setFirstName(request
										.getParameter("givenName"));
							if (request.getParameter("sn") != null)
								userInfo.setLastName(request
										.getParameter("sn"));
							if (request.getParameter("l") != null) {
								if (request.getParameter(
										"Shib-Application-ID").equals(
										"app2"))
									userInfo.setInstitute("INFN-"
											+ request.getParameter("l")
													.toUpperCase());
							}
						%>

						<aui:input name="firstName" type="text" label="First Name"
							value="${userInfo.firstName}" />
						<aui:input name="lastName" type="text" label="Last Name"
							value="${userInfo.lastName}" />
						<aui:input name="institute" type="text" label="Institute"
							value="${userInfo.institute}" />
						<aui:input name="phone" type="text" label="Phone Number"
							value="${userInfo.phone}" />


					</aui:fieldset>

				</aui:column>

				<aui:column columnWidth="25">

					<aui:fieldset label="Certificato">

						<br></br>

						<aui:input name="mail" type="text" label="e-Mail Address"
							value="${userInfo.mail}" />

						<aui:input id="username" name="username" type="text"
							label="Username" value="${userInfo.username}" />

						<aui:input label="Possiedi un Certificato" name="haveCert"
							type="checkbox" value="${checked}" onClick="displayUpload();" />

						<div id="<portlet:namespace/>update_cert"
							style="display: none; text-align: justify;">L'username
							inserito ti servir� per recuperare il tuo certificato dal MyProxy
							server</div>

						<div id="<portlet:namespace/>get_cert"
							style="text-align: justify;">Se non hai un certificato puoi
							richiederlo nella pagina che proposta al termine della procedura
							di registrazione</div>

					</aui:fieldset>
				</aui:column>

				<aui:column columnWidth="25">

					<aui:fieldset label="Condizioni d'uso">

						<br></br>

						<div style="text-align: justify;">
							Per proseguire con la registrazione devi accettare le condizioni
							d'uso che trovi al seguente link:
							<aui:a href="#">Link</aui:a>
						</div>


						<aui:input label="Accetto" name="useCondition" type="checkbox" />

					</aui:fieldset>
				</aui:column>

				<aui:button-row>
					<aui:button type="submit" value="Salva"/>
					<aui:button type="cancel" value="Home"
						onClick="location.href='${homeUrl}';" />
				</aui:button-row>
			</div>
		</aui:fieldset>
	</aui:layout>
</aui:form>


