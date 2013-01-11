<%@ include file="/WEB-INF/jsp/init.jsp"%>

<script type="text/javascript">
<!--
	//-->

	var check=false;
	
	function printCheck(element){
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
			//printCheck("<portlet:namespace/>passwordVerify");
			$('#<portlet:namespace/>passwordVerify_img').remove();
			$("#<portlet:namespace/>passwordVerify").after("<img id='<portlet:namespace/>passwordVerify_img' src='<%=request.getContextPath()%>/images/success.png' style='padding-left:5px;'/>");
		} else {
			$("#<portlet:namespace/>password").css("background", "#FDD");
			$("#<portlet:namespace/>passwordVerify").css("background",
					"#FF9999");
			output = "KO";
			check=false;
			$('#<portlet:namespace/>passwordVerify_img').remove();
			$("#<portlet:namespace/>passwordVerify").after("<img id='<portlet:namespace/>passwordVerify_img' src='<%=request.getContextPath()%>/images/close-button2.png' width='16' height='16'  style='padding-left:5px;'/>");
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
<liferay-ui:error key="error-certificate-to-update"
	message="error-certificate-to-update" />
<liferay-ui:error key="no-valid-key"
	message="no-valid-key" />
<liferay-ui:error key="error-password-too-short"
	message="error-password-too-short" />
<liferay-ui:error key="key-password-failure"
	message="key-password-failure" />
	
<portlet:actionURL var="updateCertUrl">
	<portlet:param name="myaction" value="updateCert" />
	<portlet:param name="userId" value="${userId }" />
</portlet:actionURL>

<aui:form name="uploadCertForm" action="${updateCertUrl}"
	enctype="multipart/form-data">
	<aui:layout>

		<h1 class="header-title">Update Certificate</h1>

		<portlet:renderURL var="homeUrl">
			<portlet:param name="myaction" value="userInfos" />
		</portlet:renderURL>

		<br></br>

		<aui:fieldset>

			<aui:column columnWidth="45" style="margin-left:30px;">

				<aui:fieldset>
					<br />
					<%
						if (request.getParameter("userId") != null)
												pageContext.setAttribute("userId",
														request.getParameter("userId"));
						if (request.getParameter("idCert") != null)
							pageContext.setAttribute("idCert",
									request.getParameter("idCert"));
						if (request.getParameter("primCert") != null)
							pageContext.setAttribute("primCert",
									request.getParameter("primCert"));
					%>
					
					<portlet:renderURL var="homeUrl">
						<portlet:param name="myaction" value="editUserInfoForm" />
						<portlet:param name="userId" value="${userId}" />
					</portlet:renderURL>


					<aui:input name="userId" type="hidden" value="${userId}" />
					<aui:input name="idCert" type="hidden" value="${idCert}" />

					<div id="allertDiv2">
					<div class="portlet-msg-error p12" style="display:none;">
						Insert certificate here.
					</div>
					
							<aui:input name="usercert" type="file" label="p12 format Certificate"
								 />
					
					
					<div class="portlet-msg-error pwd" style="display:none;">
						Insert password of your certificate here.
					</div>
					
							<aui:input id="keyPass" name="keyPass" type="password"
								label="Password of your certificate" onBlur="printCheck($(this).attr('id'));"/>
					</div>
					<br />
					
						Please insert below a new password. <br/>
						This password will be asked to use Grid and Cloud resources in a secure way. <br/>
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
									type="password" label="Retype Password" onkeyup="verifyPassword();"/>

						
					</div>
						<br/>
						<strong>Note:</strong> this password will be not saved in the system.
					<aui:input name="primaryCert" type="hidden" value="true"/>
				</aui:fieldset>

				<br />
				<br />
			</aui:column>

			<aui:column columnWidth="30" style="margin-left:30px;">

				<aui:fieldset>
					<br/><br/>
					
					<div id="help">
					<a href="#"><img class="displayed"
													src="<%=request.getContextPath()%>/images/Help.png"
													id="noImg" width="64" /> Certificate Upload Help</a>
													
					</div>
					<br/><br/><br/><br/><br/><br/><br/>
					<div id="help">
					<a href="#"><img class="displayed"
													src="<%=request.getContextPath()%>/images/Information2.png"
													id="noImg" width="64" /> Technical Information</a>
													
													<a href="https://portal.italiangrid.it:8443/moreinfo.html" onclick="$(this).modal({width:800, height:600, message:true}).open(); return false;">More Info</a>
					
													
					</div>
				</aui:fieldset>

				<br />
				<br />
			</aui:column>

			

			

			<aui:button-row>
				<aui:button type="submit" value="Update Certificate"  onClick="return validate();"/>
				<aui:button type="cancel" value="Back"
					onClick="location.href='${homeUrl}';" />

			</aui:button-row>

		</aui:fieldset>
	</aui:layout>
</aui:form>


<div id="footnoteP12" style="display:none;">Upload your Certificate in P12 format.</div>
<div id="footnotePwdP12" style="display:none;">Insert here the password<br/>of your certificate.</div>
<div id="footnotePwdProxy" style="display:none;">Choose a password<br/>for encrypting your proxy<br/>and storing it into MyProxy server.</div>

</div>
