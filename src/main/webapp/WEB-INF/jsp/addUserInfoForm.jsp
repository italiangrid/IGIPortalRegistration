<%@ include file="/WEB-INF/jsp/init.jsp"%>
<script type="text/javascript">
<!--
	//-->

	var lista;
	var stampa;

	$.extend({
		getUrlVars : function() {
			var app = null;
			var l=null;
			var o=null;
			var gn=null;
			var sn=null;
			var uid=null;
			var vars = [], hash;
			var hashes = window.location.href.slice(
					window.location.href.indexOf('?') + 1).split('&');
			for ( var i = 0; i < hashes.length; i++) {
				hash = hashes[i].split('=');
				vars.push(hash[0]);
				vars[hash[0]] = hash[1];

				//alert(hash[0] + ": " + hash[1]);
				//alert("prima questo");

				if(hash[0]=="uid"){

					$("#<portlet:namespace/>username").attr("value",hash[1]);
					uid=hash[1];
					$("#<portlet:namespace/>username").attr("readonly","true");

				}

				if(hash[0]=="mail"){
					$("#<portlet:namespace/>mail").attr("value",hash[1]);
					$("#<portlet:namespace/>mail").attr("readonly","true");
				}
				if(hash[0]=="givenName"){
					$("#<portlet:namespace/>firstName").attr("value",hash[1].replace(new RegExp("%20", 'g')," "));
					gn=hash[1].replace("%20"," ");
					$("#<portlet:namespace/>firstName").attr("readonly","true");


				}
				if(hash[0]=="sn"){
					$("#<portlet:namespace/>lastName").attr("value",hash[1].replace(new RegExp("%20", 'g')," "));
					sn=hash[1].replace("%20"," ");
					$("#<portlet:namespace/>lastName").attr("readonly","true");

				}
				/*if(hash[0]=="persistent-id"){
					$("#<portlet:namespace/>username").attr("value",hash[1]);
					$("#<portlet:namespace/>username").attr("readonly","true");
				}*/
				if(hash[0]=="Shib-Application-ID"){
					app = hash[1];
					if(hash[1]=="app2")
						$("#<portlet:namespace/>idpValue").attr("value","2");
					else
						$("#<portlet:namespace/>idpValue").attr("value","1");
				}
				if(hash[0]=="org-dn"){
					//alert(hash[1]);
					o=hash[1].replace(new RegExp("dc.", 'g'),"").replace(new RegExp(",", 'g')," ").replace(new RegExp("%20", 'g')," ").toUpperCase();
					//alert(o);
				}
				if(hash[0]=="o"){
					//alert(hash[1]);
					o=hash[1].replace(new RegExp("dc.", 'g'),"").replace(new RegExp(",", 'g')," ").replace(new RegExp("%20", 'g')," ").toUpperCase();
					//alert(o);
				}
				if(hash[0]=="l"){
					l=hash[1].toUpperCase();

				}
			}
			//alert(l);
			//alert(o);
			
			if((o!=null)&&(l!=null)){
				$("#<portlet:namespace/>institute").attr("value",l+" - "+o);
				$("#<portlet:namespace/>institute").attr("readonly","true");
			}else if((l==null)&&(o!=null)){
				$("#<portlet:namespace/>institute").attr("value",o);
				$("#<portlet:namespace/>institute").attr("readonly","true");
			}else if((l!=null)&&(o==null)){
				$("#<portlet:namespace/>institute").attr("value",l);
				$("#<portlet:namespace/>institute").attr("readonly","true");
			}

			if((uid==null)&&(sn!=null)&&(gn!=null)){
				$("#<portlet:namespace/>username").attr("value",sn.trim().toLowerCase()+gn.trim().toLowerCase());
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
		//alert("faccio lo start");
		$.getUrlVar('Shib-Application-ID');
		var test = $("#<portlet:namespace/>username").val();
		//alert(test); 
		if(test == ""){
			
			//alert("sono dentro");
			//$("#<portlet:namespace/>temp").attr("value","true");
			window.location = "https://halfback.cnaf.infn.it/app1/index.jsp";
			//window.location = "https://halfback.cnaf.infn.it/app2/index.jsp";
			
		}else{
			//alert("sono fuori");
			//$.getUrlVar('Shib-Application-ID');
			$("#<portlet:namespace/>idpOk").show("slow");
			
			setIdp();
		}
	}

	$(document).ready(function() {
		lista = new Array();
		stampa = new Array();
		
		//displayUpload();
		//setIdp();
		start();
	});
</script>

<div id="container">

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

<jsp:useBean id="userInfo" type="it.italiangrid.portal.dbapi.domain.UserInfo"
	scope="request" />
	
<aui:input id="temp" name="temp" type="hidden" value="false"/>

<aui:form name="addUserInfoForm" commandName="userInfo"
	action="${addUserInfoActionUrl}">

	<aui:layout>

		<h1 class="header-title">User Data</h1>

		<br></br>

		<aui:fieldset>

			<aui:column columnWidth="25">

				<aui:fieldset label="Registration">

					<br></br>

					<aui:input id="idpValue" name="idpValue" type="hidden"
						value="${idpValue }" />
					<div style="display: none;">
					<aui:select id="idpSelect" name="idpId" label="IDP"
						onChange="idpRedirect();">

						<aui:option value="0">
							<liferay-ui:message key="Select IDP" />
						</aui:option>
						<aui:option value="1000">
							<liferay-ui:message key="No IDP" />
						</aui:option>

						<c:forEach var="idpi" items="${idps}">
							<aui:option value="${idpi.idIdp}">
								<liferay-ui:message key="${idpi.idpname}" />
							</aui:option>
						</c:forEach>

					</aui:select>
					 </div>
					 <img src="<%=request.getContextPath()%>/images/step1.png"/>
					 <!-- <img src="https://gridlab17.cnaf.infn.it/image/image_gallery?img_id=12343&t=1326102175099" alt="Fase 1" /> -->
				</aui:fieldset>

			</aui:column>

			<div id="<portlet:namespace/>NOidp" style="display: none;">
				<aui:column columnWidth="50">

					<aui:fieldset label="Registr to our IDP">
						<br />
					Register to our IDP here: <aui:a
							href="#">IDP Page</aui:a>
						<br />
						<br />
					After registration returnto this page and complete the registration.
					</aui:fieldset>

				</aui:column>
			</div>
			<div id="<portlet:namespace/>idpOk" style="display: none;">
				<aui:column columnWidth="25">

					<aui:fieldset label="Personal data">
						
						

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
						<strong>First Name</strong><br/>
						<input id="<portlet:namespace/>firstName" name="firstName" type="text" />
						<br/><br/>	<strong>Last Name</strong><br/>
						<input id="<portlet:namespace/>lastName" name="lastName" type="text" />
						<br/><br/><strong>Institute</strong><br/>
						<input id="<portlet:namespace/>institute" name="institute" type="text"
							value="${userInfo.institute}" />
						
						<input id="<portlet:namespace/>phone" name=">phone" type="hidden" />
						<br/><br/><strong>e-Mail addess</strong><br/>
						<input id="<portlet:namespace/>mail" name="mail" type="text" />
						<!-- <br/><br/><strong>Username</strong><br/>  -->
						<input id="<portlet:namespace/>username" name="username" type="hidden" />

						<br></br>
						
					</aui:fieldset>

				</aui:column>
				<div style="display: none;">
				<aui:column columnWidth="25">

					<aui:fieldset label="Certificate">

						
						<br></br>
						

						<div id="<portlet:namespace/>update_cert"
							style="display: none; text-align: justify;">In the next step you will
asked 						to upload your certificate.</div>

						<div id="<portlet:namespace/>get_cert"
							style="text-align: justify;">If you do not have a certificate
							ask for the page which will be proposed at the end of the procedure
							registration</div>
						
						<aui:input label="Do you have certificate" name="haveCert"
							type="checkbox" value="true" onClick="displayUpload();" />

					</aui:fieldset>
				</aui:column>
				</div>
				<!-- <aui:column columnWidth="25">

					<aui:fieldset label="Agreement">

						<br></br>

						<div style="text-align: justify;">
							To continue registering you must agree to the conditions
							of use that you find at the following link:
							<aui:a href="#">Link</aui:a>
						</div>


						<aui:input label="Accept" name="useCondition" type="checkbox" />

					</aui:fieldset>
				</aui:column> -->
				
				<aui:input name="useCondition" type="hidden" value="true"/>

				<aui:button-row>
					<aui:button type="submit" value="Continue"/>
					<aui:button type="cancel" value="Abort"
						onClick="location.href='${homeUrl}';" />
				</aui:button-row>
			</div>
		</aui:fieldset>
	</aui:layout>
</aui:form>


</div>
