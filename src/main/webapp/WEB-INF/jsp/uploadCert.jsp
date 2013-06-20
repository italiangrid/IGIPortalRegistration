
<%@ include file="/WEB-INF/jsp/init.jsp"%>

<script type="text/javascript">
<!--
	//-->
	
	var check=false;

		
	function printCheck(element){
		//alert(element);
		$('#'+element+'_img').remove();
		if(!$('#'+element).val()){
			$('#'+element).after("<img id='"+element+"_img' src='<%=request.getContextPath()%>/images/close-button2.png' width='16' height='16'  style='padding-left:5px;'/>");
		}else{
			$('#'+element).after("<img id='"+element+"_img' src='<%=request.getContextPath()%>/images/success.png' style='padding-left:5px;'/>");
		}
	}
	
	function verifyPassword() {
		var pwd1 = $("#<portlet:namespace/>password").val();
		var pwd2 = $("#<portlet:namespace/>passwordVerify").val();
		var output = "";
		if (pwd1 == pwd2) {
			$("#<portlet:namespace/>password").css("background", "#ACDFA7");
			$("#<portlet:namespace/>passwordVerify").css("background",
					"#ACDFA7");
			check=true;
			printCheck("<portlet:namespace/>passwordVerify");
			
		} else {
			$("#<portlet:namespace/>password").css("background", "#FDD");
			$("#<portlet:namespace/>passwordVerify").css("background",
					"#FF9999");
			output = "KO";
			check=false;
		}
	}
	
	function validate(){
		$(".proxyPwd").hide();
		$(".pwd").hide();
		$(".p12").hide();
		allOK = true;
		//alert(allOK);
		if(check==false){
			allOK = false;
			$(".proxyPwd").show();
			//alert(allOK);
		}
		//alert("valore: " + $("#<portlet:namespace/>keyPass").val());
		//alert("check: " + !$("#<portlet:namespace/>keyPass").val());
		if(!$("#<portlet:namespace/>keyPass").val()){
			allOK = false;
			$(".pwd").show();
			//alert(allOK);
		}
		//alert($("#<portlet:namespace/>usercert").val());
		//alert(!$("#<portlet:namespace/>usercert").val());
		if(!$("#<portlet:namespace/>usercert").val()){
			allOK = false;
			$(".p12").show();
			//alert(allOK);
		}
		//alert(allOK);
		return allOK;
		//return false;
	}
	$(function() {


		$("#foottipPwdP12 div, #foottipPwdProxy div, #foottipPwdReProxy div, #foottipP12 div").tooltip({
			bodyHandler: function() {
				//alert($(this).attr("id"));
				return $($(this).attr("id")).html();
			},
			showURL: false
			
		});

	});

	$(document).ready(function() {

	});
	
	(function ($) {

		/**********************************
		* CUSTOMIZE THE DEFAULT SETTINGS
		* Ex:
		* var _settings = {
		* 	id: 'modal',
		* 	src: function(sender){
		*		return jQuery(sender).attr('href');
		*	},
		* 	width: 800,
		* 	height: 600
		* }
		**********************************/
		var _settings = {
			width: 800, // Use this value if not set in CSS or HTML
			height: 600, // Use this value if not set in CSS or HTML
			overlayOpacity: .85, // Use this value if not set in CSS or HTML
			id: 'modal',
			src: function (sender) {
				return jQuery(sender).attr('href');
			},
			fadeInSpeed: 0,
			fadeOutSpeed: 0
		};

		/**********************************
		* DO NOT CUSTOMIZE BELOW THIS LINE
		**********************************/
		$.modal = function (options) {
			return _modal(this, options);
		};
		$.modal.open = function () {
			_modal.open();
		};
		$.modal.close = function () {
			_modal.close();
		};
		$.fn.modal = function (options) {
			return _modal(this, options);
		};
		_modal = function (sender, params) {
			this.options = {
				parent: null,
				overlayOpacity: null,
				id: null,
				content: null,
				width: null,
				height: null,
				message: false,
				modalClassName: null,
				imageClassName: null,
				closeClassName: null,
				overlayClassName: null,
				src: null
			};
			this.options = $.extend({}, options, _defaults);
			this.options = $.extend({}, options, _settings);
			this.options = $.extend({}, options, params);
			this.close = function () {
				jQuery('.' + options.modalClassName + ', .' + options.overlayClassName).fadeOut(_settings.fadeOutSpeed, function () { jQuery(this).unbind().remove(); });
			};
			this.open = function () {
				if (typeof options.src == 'function') {
					options.src = options.src(sender);
				} else {
					options.src = options.src || _defaults.src(sender);
				}

				var fileExt = /^.+\.((jpg)|(gif)|(jpeg)|(png)|(jpg))$/i;
				var contentHTML = '';
				if (fileExt.test(options.src)) {
					contentHTML = '<div class="' + options.imageClassName + '"><img src="' + options.src + '"/></div>';

				} else {
					contentHTML = '<iframe width="' + options.width + '" height="' + options.height + '" frameborder="0" scrolling="no" allowtransparency="true" src="' + options.src + '"></iframe>';
				}
				options.content = options.content || contentHTML;

				if (jQuery('.' + options.modalClassName).length && jQuery('.' + options.overlayClassName).length) {
					jQuery('.' + options.modalClassName).html(options.content);
				} else {
					$overlay = jQuery((_isIE6()) ? '<iframe src="BLOCKED SCRIPT\'<html></html>\';" scrolling="no" frameborder="0" class="' + options.overlayClassName + '"></iframe><div class="' + options.overlayClassName + '"></div>' : '<div class="' + options.overlayClassName + '"></div>');
					$overlay.hide().appendTo(options.parent);

					$modal = jQuery('<div id="' + options.id + '" class="' + options.modalClassName + '" style="width:' + options.width + 'px; height:' + options.height + 'px; margin-top:-' + (options.height / 2) + 'px; margin-left:-' + (options.width / 2) + 'px;">' + options.content + '</div>');
					$modal.hide().appendTo(options.parent);

					$close = jQuery('<a class="' + options.closeClassName + '"></a>');
					$close.appendTo($modal);

					var overlayOpacity = _getOpacity($overlay.not('iframe')) || options.overlayOpacity;
					$overlay.fadeTo(0, 0).show().not('iframe').fadeTo(_settings.fadeInSpeed, overlayOpacity);
					$modal.fadeIn(_settings.fadeInSpeed);
					
					//alert(options.message)
					if(options.message==false){
					//$close.click(function () { jQuery.modal().close(); location.href='https://halfback.cnaf.infn.it/casshib/shib/app4/login?service=https%3A%2F%2Fgridlab04.cnaf.infn.it%2Fc%2Fportal%2Flogin%3Fp_l_id%3D10671';});
					$close.click(function () { jQuery.modal().close(); location.href='https://halfback.cnaf.infn.it/casshib/shib/app1/login?service=https%3A%2F%2Fflyback.cnaf.infn.it%2Fc%2Fportal%2Flogin%3Fp_l_id%3D10669';});
					}else{
						$close.click(function () { jQuery.modal().close()});
						$overlay.click(function () { jQuery.modal().close(); });
					}
					
				}
			};
			return this;
		};
		_isIE6 = function () {
			if (document.all && document.getElementById) {
				if (document.compatMode && !window.XMLHttpRequest) {
					return true;
				}
			}
			return false;
		};
		_getOpacity = function (sender) {
			$sender = jQuery(sender);
			opacity = $sender.css('opacity');
			filter = $sender.css('filter');

			if (filter.indexOf("opacity=") >= 0) {
				return parseFloat(filter.match(/opacity=([^)]*)/)[1]) / 100;
			}
			else if (opacity != '') {
				return opacity;
			}
			return '';
		};
		_defaults = {
			parent: 'body',
			overlayOpacity: 85,
			id: 'modal',
			content: null,
			width: 800,
			height: 600,
			modalClassName: 'modal-window',
			imageClassName: 'modal-image',
			closeClassName: 'close-window',
			overlayClassName: 'modal-overlay',
			src: function (sender) {
				return jQuery(sender).attr('href');
			}
		};
	})(jQuery);
	
</script>


<style>
span .<portlet:namespace />pwd {
	float: right;
}

div#noteText {
	margin-top: 10px;
	text-align: justify;
	font-size: 12px;
}

h5#usernameAlert {
	margin-top: 10px;
}

#allertDiv{
	border-color: #FFCC66;
	border-width: 1px;
	border-style: solid;
	background-color: #FFFFDD;
	padding: 5px;
}

#submit{
	float:right; 
	margin-right:20px;
}

#help{
	height: 64px;
	vertical-align: middle;
	display: table-cell;
}


</style>

<div id="container2">

<liferay-ui:success key="user-saved-successufully"
	message="user-saved-successufully" />
<liferay-ui:error key="error-uploading-certificate"
	message="error-uploading-certificate" />
<liferay-ui:error key="myproxy-exception" message="myproxy-exception" />
<liferay-ui:error key="cert-password-incorrect"
	message="cert-password-incorrect" />
<liferay-ui:error key="cert-pass1-required"
	message="cert-pass1-required" />
<liferay-ui:error key="cert-pass2-required"
	message="cert-pass2-required" />
<liferay-ui:error key="no-valid-cert" message="no-valid-cert" />
<liferay-ui:error key="key-pass-required" message="key-pass-required" />
<liferay-ui:error key="no-valid-cert-subject"
	message="no-valid-cert-subject" />
<liferay-ui:error key="no-valid-cert-issuer"
	message="no-valid-cert-issuer" />
<liferay-ui:error key="no-valid-cert-enddate"
	message="no-valid-cert-enddate" />
<liferay-ui:error key="user-certificate-expired"
	message="user-certificate-expired" />
<liferay-ui:error key="certificate-duplicate"
	message="certificate-duplicate" />
<liferay-ui:error key="no-valid-key"
	message="no-valid-key" />
<liferay-ui:error key="key-password-failure"
	message="key-password-failure" />
	
<%
				if (request.getParameter("userId") != null)
										pageContext.setAttribute("userId",
												request.getParameter("userId"));
									if (request.getParameter("username") != null)
										pageContext.setAttribute("username",
												request.getParameter("username"));
									if (request.getParameter("firstReg") != null)
										pageContext.setAttribute("firstReg",
												request.getParameter("firstReg"));
									else
										pageContext.setAttribute("firstReg","false");
											
			%>

<portlet:actionURL var="uploadCertUrl">
	<portlet:param name="myaction" value="uploadCert" />
	<portlet:param name="userId" value="${userId }" />
	<portlet:param name="firstReg" value="${firstReg }" />
</portlet:actionURL>


<portlet:renderURL var="homeUrl">
	<portlet:param name="myaction" value="editUserInfoForm" />
	<portlet:param name="userId" value="${userId}" />
</portlet:renderURL>

<aui:form name="uploadCertForm" action="${uploadCertUrl}"
	enctype="multipart/form-data" >
	<aui:layout>

		<h1 class="header-title">Upload New Certificate</h1>

		<br></br>

		<aui:fieldset>
			
			
				
			<c:if test="${firstReg == true}">
				<aui:column columnWidth="20">
					
					<aui:fieldset label="Registration">
						<br />
						
						<img src="<%=request.getContextPath()%>/images/step2.png"/>
						<!-- <img src="https://gridlab17.cnaf.infn.it/image/image_gallery?img_id=12347&t=1326102175108" alt="Fase 2" /> -->
						
	
					</aui:fieldset>
					
					<br />
					<br />
				</aui:column>
			</c:if>
			
			<aui:column columnWidth="45" style="margin-left:30px;">

				<aui:fieldset>
					<br />
					
					<div class="portlet-msg-alert" >Please upload your certificate (.p12 or pfx format) and insert the password used to encrypt it.</div>


					<aui:input name="userId" type="hidden" value="${userId}" />
					<aui:input name="username" type="hidden" value="${username}" />
					<aui:input name="firstReg" type="hidden" value="${firstReg}" />
					<div id="allertDiv2">
					<div class="portlet-msg-error p12" style="display:none;">
						Insert certificate here.
					</div>
					
							<aui:input id="usercert" name="usercert" type="file" label="Import certificate"
								value="${usercert }" />
					
					
					<div class="portlet-msg-error pwd" style="display:none;">
						Insert password of your certificate here.
					</div>
					
							<aui:input id="keyPass" name="keyPass" type="password"
								label="Import certificate password" onBlur="printCheck($(this).attr('id'));"/> 
					</div>
					<br />
					
						<div class="portlet-msg-alert"><p>Please choose a password to encrypt your credentials. This is the only password you have to edit to retrieve your credentials.
						</p><span>Don't forget it because we don't conserve it!!</span></div>
					<div id="allertDiv2">
				
						<br/>
						<div class="portlet-msg-error proxyPwd" style="display:none;">
							These password must be the same.
						</div>

								<aui:input id="password" name="password" type="password"
									label="Insert Password" onBlur="printCheck($(this).attr('id'));"/>

						<div class="portlet-msg-error proxyPwd" style="display:none;">
							These password must be the same.
						</div>

								<aui:input id="passwordVerify" name="passwordVerify"
									type="password" label="Confim Password" onkeyup="verifyPassword();"/>

						
					</div>
						<br/>
						

				</aui:fieldset>

				<br />
				<br />
			</aui:column>

			<aui:column columnWidth="30" style="margin-left:30px;">

				<aui:fieldset>
					<br/><br/><br/><br/><br/>
					
					<div id="help">
					<script  type="text/javascript">
					 
					 function openNewWindow() {
					 popupWin = window.open('https://portal.italiangrid.it:8443/info/upload-certificate-help.html',
					 'open_window',
					 'scrollbars, resizable, dependent, width=640, height=480, left=0, top=0')
					 }
					 
					 </script>
					<a href="javascript:openNewWindow();" ><img class="displayed"
													src="<%=request.getContextPath()%>/images/Help.png"
													id="noImg" width="64" />Certificate Upload Help</a>
													
					</div>
					<br/><br/><br/><br/><br/><br/><br/>
					<div id="help">
					<a href="https://portal.italiangrid.it:8443/info/certificate-upload-technical-info.html"  onclick="$(this).modal({width:800, height:600, message:true}).open(); return false;"><img class="displayed"
													src="<%=request.getContextPath()%>/images/Information2.png"
													id="noImg" width="64" />Technical Information</a>
													
										
					</div>
					<aui:input name="primaryCert" type="hidden" value="true"/>

				</aui:fieldset>

				<br />
				<br />
			</aui:column>

			

			<aui:button-row>
				
				
				<c:if test="${firstReg == false}">
					<aui:button type="submit" value="Upload Certificate" onClick="return validate();"/>
						<aui:button type="cancel" value="Back"
							onClick="location.href='${homeUrl}';" />
				</c:if>
				
				<c:if test="${firstReg == true}">
					<aui:button type="submit" value="Continue" onClick="return validate();"/>
						<!--<aui:button type="cancel" value="Terminate Registration"
							onClick="alert('You are registrated in the portal, log into the portal for complete the registraion.');location.href='https://halfback.cnaf.infn.it/casshib/shib/app2/login?service=https%3A%2F%2Fportal.italiangrid.it%2Fc%2Fportal%2Flogin%3Fp_l_id%3D11504';" />
							-->
						<aui:button type="cancel" value="Terminate Registration"
							onClick="alert('You are registrated in the portal, log into the portal for complete the registraion.');location.href='https://halfback.cnaf.infn.it/casshib/shib/app1/login?service=https%3A%2F%2Fflyback.cnaf.infn.it%2Fc%2Fportal%2Flogin%3Fp_l_id%3D10669';" />
							
				</c:if>
					

			</aui:button-row>
			
			

		</aui:fieldset>
	</aui:layout>
</aui:form>

<div id="footnoteP12" style="display:none;">Upload your Certificate in P12 format.</div>
<div id="footnotePwdP12" style="display:none;">Insert here the password<br/>of your certificate.</div>
<div id="footnotePwdProxy" style="display:none;">Choose a password<br/>for encrypting your proxy<br/>and storing it into MyProxy server.</div>


</div>
