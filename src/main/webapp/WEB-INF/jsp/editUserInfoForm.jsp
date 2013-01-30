<%@ include file="/WEB-INF/jsp/init.jsp"%>
<script src="<%=request.getContextPath()%>/js/jqxcore.js"></script>
<script src="<%=request.getContextPath()%>/js/jqxswitchbutton.js"></script>

<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/themes/base/jquery-ui.css" />

<script type="text/javascript">
<!--
	//-->
	function mostraModificaUtente() {
		$("#<portlet:namespace/>formOFF").show("slow");
		$("#<portlet:namespace/>formOn").hide("slow");
	}

	function nascondiModificaUtente() {
		$("#<portlet:namespace/>formOFF").hide("slow");
		$("#<portlet:namespace/>formOn").show("slow");
	}

	function mostraCertificatiUtente() {
		$("#<portlet:namespace/>certificatiOFF").show("slow");
		$("#<portlet:namespace/>certificatiOn").hide("slow");
	}

	function nascondiCertificatiUtente() {
		$("#<portlet:namespace/>certificatiOFF").hide("slow");
		$("#<portlet:namespace/>certificatiOn").show("slow");
	}

	function mostraVoUtente() {
		$("#<portlet:namespace/>voOFF").show("slow");
		$("#<portlet:namespace/>voOn").hide("slow");
	}

	function nascondiVoUtente() {
		$("#<portlet:namespace/>voOFF").hide("slow");
		$("#<portlet:namespace/>voOn").show("slow");
	}
	
	function mostraAdvSetUtente() {
		$("#<portlet:namespace/>advSetOff").show("slow");
		$("#<portlet:namespace/>advSetOn").hide("slow");
	}

	function nascondiAdvSetUtente() {
		$("#<portlet:namespace/>advSetOff").hide("slow");
		$("#<portlet:namespace/>advSetOn").show("slow");
	}
	
	function verifyDelete(url){
		var agree=confirm("Do you really want delete your account?");
		if (agree)
			return location.href=url ;
	}
	
	function mysubmit() {
		//submit form
		
		$("#importTest").submit();
		
		//alert("OK");
		//window.location = url;
	}
	
	function viewTooltip(url){

		$("#userSettings a").tooltip({

			bodyHandler: function() {
				return $(url).html();
			},
			showURL: false


		});
	}
	
	
	$(function() {

		var availableTags = [${voList}];
	    $( "#tags" ).autocomplete({
	    	
	      source: availableTags
	    });

		$("#foottipUser a, #foottipCert a, #foottipVO a").tooltip({
			bodyHandler: function() {
				return $($(this).attr("href")).html();
			},
			showURL: false
			
		});

		});
	
	function setHaveCert(value){
		//alert(value);
		$("#<portlet:namespace/>haveCert").attr("value",value);
		
		if(value=='true'){
			//alert(value);
			//opacizza NO
			//opacity:0.4;
			//filter:alpha(opacity=40);
			$("#yesImg").attr("style","opacity:1.0; filter:alpha(opacity=100);");
			$("#noImg").attr("style","opacity:0.4; filter:alpha(opacity=40);");
			$("#<portlet:namespace/>uploadCertForm").submit();
		}else{
			//alert(value);
			//opacizza SI
			$("#noImg").attr("style","opacity:1.0; filter:alpha(opacity=100);");
			$("#yesImg").attr("style","opacity:0.4; filter:alpha(opacity=40);");
		}
	}
	
	$(document).ready(function () {
        // Create Switch Button.
        
        var val = $('#<portlet:namespace/>proxyExpire').val();
        if(val=='true'){
        	$("#jqxButton").jqxSwitchButton({ theme: 'classic', width: '100', height: '25', checked: true});
        }else{
        	$("#jqxButton").jqxSwitchButton({ theme: 'classic', width: '100', height: '25', checked: false});
        }
        $('#jqxButton').bind('checked', function (event) {
            $('#<portlet:namespace/>proxyExpire').val('true');
        });
        $('#jqxButton').bind('unchecked', function (event) {
            $('#<portlet:namespace/>proxyExpire').val('false');
        });
        
        var val2 = $('#<portlet:namespace/>wfchgEnab').val();
        if(val2=='true'){
        	$("#jqxButton2").jqxSwitchButton({ theme: 'classic', width: '100', height: '25', checked: true});
        }else{
        	$("#jqxButton2").jqxSwitchButton({ theme: 'classic', width: '100', height: '25', checked: false});
        }
        $('#jqxButton2').bind('checked', function (event) {
            $('#<portlet:namespace/>wfchgEnab').val('true');
        });
        $('#jqxButton2').bind('unchecked', function (event) {
            $('#<portlet:namespace/>wfchgEnab').val('false');
        });
        
        
        
       
        $("#<portlet:namespace/>advSetOff").hide();
        
        
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
			fadeOutSpeed: 0,
			redirect: null
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
				src: null,
				redirect: null
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
						if(options.redirect!=null){
							$close.click(function () { location.href=options.redirect; });
							$overlay.click(function () { location.href=options.redirect; });
						}else{
							$close.click(function () { jQuery.modal().close()});
							$overlay.click(function () { jQuery.modal().close(); });
						}
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
			},
			redirect: null
		};
	})(jQuery);
	
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
		var check = false;
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
		
		return check;
	}
	
</script>



<style>

.jqx-switchbutton-label-on
        {
            filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#3065c4', endColorstr='#75adfc',GradientType=0 ); /* IE6-9 */    
            background-image: linear-gradient(bottom, rgb(118,174,252) 20%, rgb(48,103,197) 62%);
            background-image: -o-linear-gradient(bottom, rgb(118,174,252) 20%, rgb(48,103,197) 62%);
            background-image: -moz-linear-gradient(bottom, rgb(118,174,252) 20%, rgb(48,103,197) 62%);
            background-image: -webkit-linear-gradient(bottom, rgb(118,174,252) 20%, rgb(48,103,197) 62%);
            background-image: -ms-linear-gradient(bottom, rgb(118,174,252) 20%, rgb(48,103,197) 62%);
            background-image: -webkit-gradient(
	            linear,
	            left bottom,
	            left top,
	            color-stop(0.2, rgb(118,174,252)),
	            color-stop(0.62, rgb(48,103,197))
            );                    
            color: #fff;
            text-shadow: 0px -1px 1px #000;                                   
        }      
        
        .jqx-switchbutton-label-off
        {
            background: #cfcfcf; /* Old browsers */
            background: -moz-linear-gradient(top,  #cfcfcf 0%, #fefefe 100%); /* FF3.6+ */
            background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#cfcfcf), color-stop(100%,#fefefe)); /* Chrome,Safari4+ */
            background: -webkit-linear-gradient(top,  #cfcfcf 0%,#fefefe 100%); /* Chrome10+,Safari5.1+ */
            background: -o-linear-gradient(top,  #cfcfcf 0%,#fefefe 100%); /* Opera 11.10+ */
            background: -ms-linear-gradient(top,  #cfcfcf 0%,#fefefe 100%); /* IE10+ */
            background: linear-gradient(top,  #cfcfcf 0%,#fefefe 100%); /* W3C */
            filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#cfcfcf', endColorstr='#fefefe',GradientType=0 ); /* IE6-9 */ 
            color: #808080;                 
        }
                
        .jqx-switchbutton-thumb
        {
            background: #bababa; /* Old browsers */
            background: -moz-linear-gradient(top,  #bababa 0%, #fefefe 100%); /* FF3.6+ */
            background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#bababa), color-stop(100%,#fefefe)); /* Chrome,Safari4+ */
            background: -webkit-linear-gradient(top,  #bababa 0%,#fefefe 100%); /* Chrome10+,Safari5.1+ */
            background: -o-linear-gradient(top,  #bababa 0%,#fefefe 100%); /* Opera 11.10+ */
            background: -ms-linear-gradient(top,  #bababa 0%,#fefefe 100%); /* IE10+ */
            background: linear-gradient(top,  #bababa 0%,#fefefe 100%); /* W3C */
            filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#bababa', endColorstr='#fefefe',GradientType=0 ); /* IE6-9 */    
            border: 1px solid #aaa;
            -webkit-box-shadow: -6px 0px 17px 1px #275292;
            -moz-box-shadow: -6px 0px 17px 1px #275292;
            box-shadow: -6px 0px 17px 1px #275292;          
        }
        .jqx-switchbutton
        {
            border: 1px solid #999999;
        }

        
        


div#personalData {
	box-shadow: 10px 10px 5px #888;
	margin: 0 9px 10px 0;
	padding: 0 12px 12px 12px;
	border: 1px;
	border-color: #C8C9CA;
	border-style: solid;
	background-color: #EFEFEF;
}

div#certificateData {
	box-shadow: 10px 10px 5px #888;
	margin: 10px 9px 10px 0;
	padding: 0 12px 12px 12px;
	border: 1px;
	border-color: #C8C9CA;
	border-style: solid;
	background-color: #EFEFEF;
}

div#voData {
	box-shadow: 10px 10px 5px #888;
	margin: 10px 9px 10px 0;
	padding: 0 12px 12px 12px;
	border: 1px;
	border-color: #C8C9CA;
	border-style: solid;
	background-color: #EFEFEF;
}

div#advancedSettings {
	box-shadow: 10px 10px 5px #888;
	margin: 10px 9px 10px 0;
	padding: 0 12px 12px 12px;
	border: 1px;
	border-color: #C8C9CA;
	border-style: solid;
	background-color: #EFEFEF;
}

div.changePassword {
	box-shadow: 10px 10px 5px #888;
	margin: 10px 9px 10px 0;
	padding: 0 12px 12px 12px;
	border: 1px;
	border-color: #C8C9CA;
	border-style: solid;
	background-color: #EFEFEF;
}

div#side {
	box-shadow: 10px 10px 5px #888;
	margin: 0px 0px 0px 0;
	padding: 1em;
	border: 1px;
	border-color: #C8C9CA;
	border-style: solid;
	background-color: #EFEFEF;
}

#closeBox {
	float:right;
	height: 24px;
	line-height:24px;
}

#closeBox img{
	padding-left: 5px;
	padding-right: 10px;
	width: 24px;
	height: 24px;
}

#chooseTable td{
	margin: 5px;
}

#chooseContainer{
	font-size: 14px;
  width: 900px ;
  margin-left: auto ;
  margin-right: auto ;

}

.choose{
	width: 60px;
	height: 80px;
	float: left;
}

.bordered{
	width: 240px;
	height: 80px;
	float: left;
	background-color: #f4fdef;
	border: 1px;
	border-style: solid;
	border-color: #ACDFA7;
	border-radius: 5px;
	-moz-border-radius:5px;
	padding: 8px;
}

.mess{
	height: 80px;
	width: 180px;
	vertical-align: middle;
	display: table-cell;
	float: left;
}

.icon2{
	height: 80px;
	width: 70px;
	vertical-align: middle;
	display: table-cell;
}

.iconContainer{
	height: 80px;
	width: 50px;
	float: left;
}

.reset{
	clear:both;
}

#or{
	text-align: center;
	height: 80px;
	width: 250px;
	vertical-align: middle;
	display: table-cell;
}

.center{
	
	height: 80px;
	width: 200px;
	vertical-align: middle;
	display: table-cell;
	
}

div.function {
	padding: 1em 5em 1em 1em;
	border: 1px;
	border-color: #C8C9CA;
	border-style: solid;
	background-color: #D1D6DC;
}

#<portlet:namespace/>voOFF .search-results {
    display: none;
}

.results-row td.disabledVo{
	opacity: .4; 
	filter: alpha(opacity=40);
}

#myAlert div.portlet-msg-success {
    background: #FFC url(https://flyback.cnaf.infn.it/html/themes/classic/images/messages/alert.png) no-repeat 6px 20%;
    border: 1px solid #FC0;
}

.sideButton{
 background: url("<%=request.getContextPath()%>/images/header_bg.png") repeat-x scroll 0 0 #D4D4D4;
 	-moz-border-bottom-colors: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    border-color: #C8C9CA #9E9E9E #9E9E9E #C8C9CA;
    border-image: none;
    border-style: solid;
    border-width: 1px;
    color: #34404F;
    cursor: pointer;
    font-weight: bold;
    overflow: visible;
    padding: 43px 5px 5px 5px;
    text-shadow: 1px 1px #FFFFFF;
    width: auto;
    border-radius: 4px 4px 4px 4px;
    text-decoration: none;
    margin: 1px;
}

.sideButton:hover {
	background: url("<%=request.getContextPath()%>/images/state_hover_bg.png") repeat-x scroll 0 0 #B9CED9;
    border-color: #627782;
    color: #336699;
}

.large{
	padding: 75px 5px 5px 5px;
}

#presentationLogin {
	color: black;
	font-size:120%;
	font-weight: bold;
	border-bottom: 1px solid #CCC;
	display: block;
	position: absolute;
	width: 90%;
}

#side legend.aui-fieldset-legend{
	margin-bottom: 20px;
}

</style>


<%-- <c:choose>
<c:when test="<%= request.isUserInRole("administrator") %>">
	<div id="container">
</c:when>
<c:otherwise>
	<div>
</c:otherwise>
</c:choose> --%>



<div id="container">

<portlet:actionURL var="editUserInfoActionUrl">
	<portlet:param name="myaction" value="editUserInfo" />
</portlet:actionURL>
<portlet:actionURL var="updateAdvOptsActionUrl">
	<portlet:param name="myaction" value="updateAdvOpts" />
</portlet:actionURL>
<portlet:actionURL var="updateGuseNotifyActionUrl">
	<portlet:param name="myaction" value="updateGuseNotify" />
</portlet:actionURL>
<portlet:renderURL var="homeUrl">
	<portlet:param name="myaction" value="selectedUsers" />
</portlet:renderURL>

<jsp:useBean id="selectedUser" type="it.italiangrid.portal.dbapi.domain.UserInfo"
	scope="request" />
<jsp:useBean id="certList"
	type="java.util.List<it.italiangrid.portal.dbapi.domain.Certificate>"
	scope="request" />
<jsp:useBean id="userToVoList"
	type="java.util.List<it.italiangrid.portal.dbapi.domain.Vo>" scope="request"></jsp:useBean>
	
	<liferay-ui:success key="user-deactivate" message="user-deactivate" />
	<liferay-ui:success key="user-activate" message="user-activate" />
	
	
<c:set var="changePasswordNow" value="true"/>	
	
<c:forEach var="c" items="${certList }">
	<c:if test="${c.passwordChanged=='false' }">
		<c:set var="changePasswordNow" value="false"/>
	</c:if>
</c:forEach>

<c:if test="${isUserActive=='false' && fn:length(userToVoList)>0}">
	<div class="portlet-msg-alert">User not active.</div>
</c:if>

<c:if test="${changePasswordNow=='true' }">

<aui:column columnWidth="70">

<div id="personalData">
	<h3 class="header-title">Personal data</h3>

	<liferay-ui:success key="user-updated-successufully"
		message="user-updated-successufully" />

	<liferay-ui:error key="error-updating-user"
		message="error-updating-user" />
	<liferay-ui:error key="problem-idp" message="problem-idp" />
	<liferay-ui:error key="problem-update-user"
		message="problem-update-user" />
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
	<liferay-ui:error key="user-phone-valid" message="user-phone-valid" />
	<liferay-ui:error key="user-mail-must-same"
		message="user-mail-must-same" />
	<liferay-ui:error key="user-username-must-same"
		message="user-username-must-same" />
	<liferay-ui:error key="problem-update-user-liferay"
		message="problem-update-user-liferay" />
		
		
	
		
	
	<div id="<portlet:namespace/>formOn">
		<!-- <a href="#editUserInfoForm" onclick="mostraModificaUtente();">Modify data</a><br /> <br />  -->
		<aui:layout>

			<aui:fieldset>
				<aui:column columnWidth="80">
					<aui:fieldset>
		
						<table>
							<tr>
								<td align="right" style="margin: 0; padding: 0 1em 0 0;">User:</td>
								<td><strong><c:out value="${selectedUser.firstName }" />
								</strong> <strong><c:out value="${selectedUser.lastName }" /> </strong></td>
							</tr>
							<c:if test="${!empty  selectedUser.institute}">
							<tr>
								<td align="right" style="margin: 0; padding: 0 1em 0 0;">Institute:</td>
								<td><strong><c:out value="${selectedUser.institute }" />
								</strong></td>
							</tr>
							</c:if>
							<!-- <tr>
								<td align="right" style="margin: 0; padding: 0 1em 0 0;">Username:</td>
								<td><strong><c:out value="${selectedUser.username }" /> </strong>
								</td>
							</tr> -->
							<tr>
								<td align="right" style="margin: 0; padding: 0 1em 0 0;">e-Mail:</td>
								<td><strong><c:out value="${selectedUser.mail }" /> </strong></td>
							</tr>
						</table>
					</aui:fieldset>
				</aui:column>
				<aui:column columnWidth="20">
					<aui:fieldset>
						<div id="foottipUser">
							<a href="#footnoteUserOK"><img src="<%=request.getContextPath()%>/images/NewCheck.png" width="48" height="48" style="float: right"/></a>
							<div id="footnoteUserOK" style="display:none;">All is OK.</div>
						</div>
					</aui:fieldset>
				</aui:column>	
			</aui:fieldset>
		</aui:layout>
	</div>
	<div id="<portlet:namespace/>formOFF" style="display: none;">

		<a href="#editUserInfoForm" onclick="nascondiModificaUtente();">Hide data</a><br />
		<br />

		<aui:form name="editUserInfoForm" commandName="selectedUser"
			action="${editUserInfoActionUrl}">

			<aui:layout>

				<aui:fieldset>
					<aui:column columnWidth="33">
						<aui:fieldset label="Personal data">
							<br></br>

							<aui:input name="userId" type="hidden"
								value="<%=selectedUser.getUserId() %>" />
							<liferay-ui:error key="user-first-name-required"
								message="user-first-name-required" />
							<strong>First Name</strong><br/>
							<input name="firstName" type="text"
								value="<%=selectedUser.getFirstName() %>" disabled="disabled"/>
							<liferay-ui:error key="user-last-name-required"
								message="user-last-name-required" />
							<br/><br/><strong>Last Name</strong><br/>
							<input name="lastName" type="text"
								value="<%=selectedUser.getLastName() %>" disabled="disabled"/>
							<br/> <br/>
						</aui:fieldset>
					</aui:column>

					<aui:column columnWidth="33">
						<aui:fieldset label="Contact">
							<br></br>
							<liferay-ui:error key="user-institute-required"
								message="user-institute-required" />
							<strong>Institute</strong><br/>
							<input name="institute" type="text"
								value="<%=selectedUser.getInstitute() %>" disabled="disabled"/>
							<liferay-ui:error key="user-phone-valid"
								message="user-phone-valid" />
							<!-- <br/><br/><strong>Phone Number</strong><br/>  -->
							<input name="phone" type="hidden"
								value="<%=selectedUser.getPhone() %>"  />
							<liferay-ui:error key="user-mail-required"
								message="user-mail-required" />
							<liferay-ui:error key="user-valid-mail-required"
								message="user-valid-mail-required" />
							<liferay-ui:error key="user-mail-duplicate"
								message="user-mail-duplicate" />
							<br/><br/><strong>e-Mail Address</strong><br/>
							<input name="mail" disabled="disabled" type="text"
								value="<%=selectedUser.getMail() %>" />	
							<input name="username" disabled="disabled" type="hidden"
								value="<%=selectedUser.getUsername() %>" />	
							<br/> <br/>
						</aui:fieldset>
					</aui:column>

					<!--<aui:column columnWidth="33">
						<aui:fieldset label="Account data">
							<br></br>
							<liferay-ui:error key="user-username-required"
								message="user-username-required" />
							<liferay-ui:error key="user-username-duplicate"
								message="user-username-duplicate" />
							<strong>Username</strong><br/>
							<input name="username" disabled="disabled" type="text"
								value="<%=selectedUser.getUsername() %>" />
							<liferay-ui:error key="user-mail-required"
								message="user-mail-required" />
							<liferay-ui:error key="user-valid-mail-required"
								message="user-valid-mail-required" />
							<liferay-ui:error key="user-mail-duplicate"
								message="user-mail-duplicate" />
							<br/><br/><strong>e-Mail Address</strong><br/>
							<input name="mail" disabled="disabled" type="text"
								value="<%=selectedUser.getMail() %>" />
							<br/> <br/>
						</aui:fieldset>
					</aui:column> -->

					<aui:button-row>
						<aui:button type="submit" />
					</aui:button-row>
				</aui:fieldset>
			</aui:layout>
		</aui:form>
	</div>
</div>

<div id="certificateData">
	<h3 class="header-title">My certificates</h3>

	<liferay-ui:success key="certificate-updated-successufully"
		message="certificate-updated-successufully" />
	<liferay-ui:success key="certificate-deleted-successufully"
		message="certificate-deleted-successufully" />
	<liferay-ui:success key="upload-cert-successufully"
		message="upload-cert-successufully" />

	<liferay-ui:error key="error-deleting-certificate"
		message="error-deleting-certificate" />
	<liferay-ui:error key="error-updating-certificate"
		message="error-updating-certificate" />
	<liferay-ui:error key="error-default-certificate"
		message="error-default-certificate" />
	<liferay-ui:error key="error-deleting-certificate-proxy-not-exists"
		message="error-deleting-certificate-proxy-not-exists" />
	<liferay-ui:error key="error-deleting-certificate-proxy-expired"
		message="error-deleting-certificate-proxy-expired" />
	<liferay-ui:error key="error-deleting-certificate-proxy-expired"
		message="error-deleting-certificate-proxy-expired" />
	<liferay-ui:error key="error-deleting-certificate-wrong-proxy"
		message="error-deleting-certificate-wrong-proxy" />


	<div id="<portlet:namespace/>certificatiOn">
		<aui:layout>

			<aui:fieldset>
				<aui:column columnWidth="70">
					<aui:fieldset>
					<a href="#apriCert" ></a>   
							
					<c:if test="${fn:length(certList)==0}" >
					<div class="portlet-msg-error">You don't have a certificate</div>
					</c:if>
					
					<jsp:useBean id="now" class="java.util.Date" scope="request" />
					<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today" />
							
					<c:forEach var="cert" items="${certList}">
						<c:if test="${cert.primaryCert == 'true'}">
						
							<fmt:formatDate value="${cert.expirationDate}" pattern="yyyy-MM-dd" var="certLifeTime2" />
							<c:if test="${certLifeTime2 < today}">
							    <div class="portlet-msg-error">Your certificate is expired. Update it.</div>
							</c:if>

						</c:if>
					</c:forEach>
					
					<c:if test="${fn:length(certList) > 0}">
					My certificate is: <strong> <c:forEach
								var="cert" items="${certList}">
								<c:if test="${cert.primaryCert == 'true'}">
									<c:out value="${cert.subject}" />
								</c:if>
							</c:forEach> </strong>
						<br />
					Expiration Date: <strong> <c:forEach
								var="cert" items="${certList}">
								<c:if test="${cert.primaryCert == 'true'}">
								
									<fmt:formatDate value="${cert.expirationDate}" pattern="yyyy-MM-dd" var="certLifeTime" />
									<c:if test="${certLifeTime >= today}">
									    <c:out value="${cert.expirationDate}" />
									</c:if>
									<c:if test="${certLifeTime < today}">
									    <span style="color:red; font-weight:bold;"><c:out value="${cert.expirationDate}" /></span>
									</c:if>

								</c:if>
							</c:forEach> </strong>
				
					</c:if>
			
					
					</aui:fieldset>
				</aui:column>
				<aui:column columnWidth="30">
					<aui:fieldset>
					<div id="foottipCert">
						<c:choose>
							<c:when test="${fn:length(certList)==0}" >
								<a href="#footnoteCertKO"><img src="<%=request.getContextPath()%>/images/NewDelete.png" width="48" height="48" style="float: right"/></a>
							</c:when>
							<c:otherwise>
								<c:forEach var="cert" items="${certList}">
									<c:if test="${cert.primaryCert == 'true'}">
									
										<fmt:formatDate value="${cert.expirationDate}" pattern="yyyy-MM-dd" var="certLifeTime2" />
										<c:if test="${certLifeTime2 < today}">
										    <a href="#footnoteCertKO"><img src="<%=request.getContextPath()%>/images/NewDelete.png" width="48" height="48" style="float: right"/></a>
										</c:if>
										<c:if test="${certLifeTime2 >= today}">
										    <a href="#footnoteCertOK"><img src="<%=request.getContextPath()%>/images/NewCheck.png" width="48" height="48" style="float: right"/></a>
										</c:if>
			
									</c:if>
								</c:forEach>
								
							</c:otherwise>
						</c:choose>
						<div id="footnoteCertOK" style="display:none;">All is OK.</div>
						<div id="footnoteCertKO" style="display:none;">Add your Certificate.</div>
					</div>
					<div id="userSettings"><a href="#apriCert" onclick="mostraCertificatiUtente();" onmouseover="viewTooltip('#settingsButtonCert');"><img src="<%=request.getContextPath()%>/images/advancedsettings.png" width="48" height="48" style="float: right; padding-right:10px;"/></a></div>
					</aui:fieldset>
				</aui:column>	
			</aui:fieldset>
		</aui:layout>


	</div>

	<div id="<portlet:namespace/>certificatiOFF" style="display: none;">

		<div id="closeBox"><a href="#cert" onclick="nascondiCertificatiUtente();"><img src="<%=request.getContextPath()%>/images/close-button2.png"/></a></div>
		<div id="closeBox"><a href="#cert" onclick="nascondiCertificatiUtente();">Hide Certificate</a></div>
		<br /> <br /> <br />
		
		<%
		    PortletURL itURL = renderResponse.createRenderURL();
			itURL.setParameter("myaction","editUserInfoForm");
			itURL.setParameter("userId",Integer.toString(selectedUser.getUserId()));
			
		%>

		<liferay-ui:search-container
			delta="5" iteratorURL="<%= itURL %>">
			<liferay-ui:search-container-results>
				<%
					results = ListUtil.subList(certList,
									searchContainer.getStart(),
									searchContainer.getEnd());
							total = certList.size();

							pageContext.setAttribute("results", results);
							pageContext.setAttribute("total", total);
				%>

			</liferay-ui:search-container-results>
			<liferay-ui:search-container-row
				className="it.italiangrid.portal.dbapi.domain.Certificate"
				keyProperty="idCert" modelVar="Certificate">
				<liferay-ui:search-container-column-text name="Subject"
					property="subject" />
				<liferay-ui:search-container-column-text name="Issuer"
					property="issuer" />
					<liferay-ui:search-container-column-text name="Expiration data">
					<%
						Certificate cert = (Certificate) row.getObject();

						GregorianCalendar c = new GregorianCalendar();
						Date oggi = c.getTime();
	
						if (cert.getExpirationDate().before(oggi)) {
							%>
							<span style="color:red; font-weight:bold;"><c:out value="<%=cert.getExpirationDate().toString() %>"/></span>
							<%
							
						} else {
							%>
							<c:out value="<%=cert.getExpirationDate().toString() %>"/>
							<%
						}
					%>
					</liferay-ui:search-container-column-text>
				
				<!-- <c:if test="${certCAonline == 'false' }">
					
				</c:if> -->
				
					<liferay-ui:search-container-column-jsp
					path="/WEB-INF/jsp/admin-cert-action.jsp" align="right" />
				
			</liferay-ui:search-container-row>
			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
		<div id="apriCert"></div>

		<portlet:renderURL var="uploadCertUrl">
			<portlet:param name="myaction" value="showUploadCert" />

		</portlet:renderURL>

		<aui:form id="uploadCertForm" name="uploadCertForm" commandName="selectedUser" action="${uploadCertUrl}">
			<c:if test="${fn:length(certList) == 0}">
			<aui:input name="userId" type="hidden" value="${selectedUser.userId }" />
			<aui:input name="username" type="hidden"
				value="${selectedUser.username }" />
			<aui:input name="firstReg" type="hidden" value="false" />
			
			
			
					
					
					
					<div id="chooseContainer">
						<a href="" onclick="setHaveCert('true'); return false;">
						<div class="bordered">
						
							<div class="mess">
								<div class="center">
								Upload your personal certificate.
								</div>
							</div>
							<div class="iconContainer">
							<div class="icon2">
								<img class="displayed" src="<%=request.getContextPath()%>/images/cert-upload.png" id="yesImg" width="64" />							</div>
							</div>
							<div class="reset"></div>
						</div>
						</a>
						<c:if test="${caEnabled=='true' }">
						<div class="choose">
							<div id="or"><strong>OR</strong></div>
						</div>
						<a href="https://openlab03.cnaf.infn.it/CAOnlineBridge/home?t1=${tokens[0]}&t2=${tokens[1]}" onclick="setHaveCert('false'); $(this).modal({width:800, height:400}).open(); return false;">
						<div class="bordered">
							<div class="mess">
								<div class="center">
								Request a new certificate<br/>by our on-Line CA.
								</div>
							</div>
							<div class="iconContainer">
							<div class="icon2">
								<img class="displayed" src="<%=request.getContextPath()%>/images/cert-download.png" id="noImg" width="64"/>
							</div>
							</div>
							<div class="reset"></div>
						</div>
						</a>
						</c:if>
						<c:if test="${proxyEnabled=='true' }">
						<div class="choose">
							<div id="or"><strong>OR</strong></div>
						</div>
						<a href="https://openlab03.cnaf.infn.it/CAOnlineBridge/home?t1=${tokens[0]}&t2=${tokens[1]}" onclick="setHaveCert('false'); $(this).modal({width:800, height:400}).open(); return false;">
						<div class="bordered">
							<div class="mess">
								<div class="center">
								Request a new certificate<br/>by our on-Line CA.
								</div>
							</div>
							<div class="iconContainer">
							<div class="icon2">
								<img class="displayed" src="<%=request.getContextPath()%>/images/cert-download.png" id="noImg" width="64"/>
							</div>
							</div>
							<div class="reset"></div>
						</div>
						</a>
						</c:if>
					</div>	
						
						
			
			

			<aui:button-row >
				
					<aui:button type="submit" value="Upload certificate" style="display:none;"/>
				
			</aui:button-row>
			
			</c:if>
		</aui:form>

	</div>
</div>

<div id="voData">
	<h3 class="header-title">My Virtual Organization</h3>

	<liferay-ui:success key="userToVo-adding-success"
		message="userToVo-adding-success" />
	<liferay-ui:success key="userToVo-updated-successufully"
		message="userToVo-updated-successufully" />
	<liferay-ui:success key="userToVo-default-successufully"
		message="userToVo-default-successufully" />
	<liferay-ui:success key="userToVo-deleted-successufully"
		message="userToVo-deleted-successufully" />
	<div id="myAlert">
	<liferay-ui:success key="vo-not-configurated"
		message="vo-not-configurated" />
	</div>
	
	

	<liferay-ui:error key="user-vo-list-empty" message="no-VO-found" />
	<liferay-ui:error key="no-user-found-in-VO"
		message="no-user-found-in-VO" />
	<liferay-ui:error key="no-cert-for-user" message="no-cert-for-user" />
	<liferay-ui:error key="edg-mkgridmap-problem"
		message="edg-mkgridmap-problem" />
	<liferay-ui:error key="edg-mkgridmap-exception"
		message="edg-mkgridmap-exception" />
	<liferay-ui:error key="error-deleting-userToVo"
		message="error-deleting-userToVo" />
	<liferay-ui:error key="error-default-userToVo"
		message="error-default-userToVo" />
	<liferay-ui:error key="userToVo-already-exists"
		message="userToVo-already-exists" />
	<liferay-ui:error key="exception-deactivation-user"
		message="exception-deactivation-user" />

	<div id="<portlet:namespace/>voOn">
		<aui:layout>

			<aui:fieldset>
				<aui:column columnWidth="70">
					<aui:fieldset>
						<a href="#apriVo"/></a>
						
						
						<c:choose>
						<c:when test="${fn:length(userToVoList)==0 }">
						
							<div class="portlet-msg-error"> You don't have any VO membership.</div>
						</c:when>
						<c:otherwise>
						<c:if test="${fn:length(userToVoList)==1 }">
							My VO membership is: 
						</c:if>
						<c:if test="${fn:length(userToVoList)>1 }">
							My VO membership are: 
						</c:if>
						<strong>
						<c:forEach var="vo" items="${userToVoList}" varStatus="count">
							&nbsp ${vo.vo }
							<c:if test="${defaultVo==vo.vo}">
								(default)
							</c:if>
							<c:if test="${count.count < fn:length(userToVoList)}">,</c:if>
						</c:forEach>
						</strong>
						</c:otherwise>
						</c:choose>
						<br /> <br />
					</aui:fieldset>
				</aui:column>
				<aui:column columnWidth="30">
					<aui:fieldset>
					<div id="foottipVO">
						<c:choose>
							<c:when test="${fn:length(userToVoList)==0}" >
								<a href="#footnoteVOKO"><img src="<%=request.getContextPath()%>/images/NewDelete.png" width="48" height="48" style="float: right"/></a>
							</c:when>
							<c:otherwise>
								<a href="#footnoteVOOK"><img src="<%=request.getContextPath()%>/images/NewCheck.png" width="48" height="48" style="float: right"/></a>
							</c:otherwise>
						</c:choose>
						<div id="footnoteVOOK" style="display:none;">All is OK.</div>
						<div id="footnoteVOKO" style="display:none;">Add a new VO.</div>
					</div>
					<div id="userSettings"><a href="#apriVo" onclick="mostraVoUtente();" onmouseover="viewTooltip('#settingsButtonVO');"><img src="<%=request.getContextPath()%>/images/advancedsettings.png" width="48" height="48" style="float: right; padding-right:10px;"/></a></div>
					</aui:fieldset>
				</aui:column>	
			</aui:fieldset>
		</aui:layout>

	</div>

	<div id="<portlet:namespace/>voOFF" style="display: none;">

		<div id="closeBox"><a href="#vo" onclick="nascondiVoUtente();"><img src="<%=request.getContextPath()%>/images/close-button2.png"/></a></div>
		<div id="closeBox"><a href="#vo" onclick="nascondiVoUtente();">Hide VO</a></div>
		<br /> <br /> <br />
		<c:if test="${fn:length(certList) != 0}">
		<div class="function">
		<aui:fieldset>
		<aui:column columnWidth="80">
		<div id="searchForm" >
	
		<portlet:actionURL var="searchVOActionUrl">
			<portlet:param name="myaction" value="searchVo3" />
			<portlet:param name="userId" value="${selectedUser.userId }" />
		</portlet:actionURL>
		
		<aui:form name="searchVo"
			action="${searchVOActionUrl}"  commandName="registrationModel">
			
			<aui:layout>
				<aui:button-row>
				
				<div class="ui-widget" style="float:left;">
				  Enter your VO's name <input id="tags" name="tags" type="text" />
				</div>

				<aui:button type="submit" value="Add" inlineField="true"/>
				
				</aui:button-row>
			</aui:layout>
		</aui:form>
		
		</div>
		</aui:column>
		</aui:fieldset>
	
		</div>

		<br/>

		<liferay-ui:search-container
			delta="5" iteratorURL="<%= itURL %>">
			<liferay-ui:search-container-results>
				<%
					results = ListUtil.subList(userToVoList,
									searchContainer.getStart(),
									searchContainer.getEnd());
							total = userToVoList.size();

							pageContext.setAttribute("results", results);
							pageContext.setAttribute("total", total);
				%>

			</liferay-ui:search-container-results>
			<liferay-ui:search-container-row
				className="it.italiangrid.portal.dbapi.domain.Vo" keyProperty="idVo"
				modelVar="Vo">
					<c:if test="${Vo.configured=='false' }">	
						<liferay-ui:search-container-column-text name="VO name" 
							property="vo"  cssClass="disabledVo"/>
						<liferay-ui:search-container-column-text name="Default VO" cssClass="disabledVo">
						<c:if test="${defaultVo==Vo.vo}">
							
							<img src="<%=request.getContextPath()%>/images/NewCheck.png" width="16" height="16"/>	
							
						</c:if>
						</liferay-ui:search-container-column-text>
						
						<liferay-ui:search-container-column-text name="Roles" cssClass="disabledVo"> 
							
							<c:out value="${fn:replace(userFqans[Vo.idVo],';',' ')}"></c:out>
							
						</liferay-ui:search-container-column-text>
						
						<liferay-ui:search-container-column-jsp cssClass="disabledVo"
							path="/WEB-INF/jsp/admin-vo-action.jsp" align="right" />
					</c:if>
					<c:if test="${Vo.configured=='true' }">	
						<liferay-ui:search-container-column-text name="VO name" 
							property="vo" />
						<liferay-ui:search-container-column-text name="Default VO">
						<c:if test="${defaultVo==Vo.vo}">
							
							<img src="<%=request.getContextPath()%>/images/NewCheck.png" width="16" height="16"/>	
							
						</c:if>
						</liferay-ui:search-container-column-text>
						
						<liferay-ui:search-container-column-text name="Roles"> 
							
							<c:out value="${fn:replace(userFqans[Vo.idVo],';',' ')}"></c:out>
							
						</liferay-ui:search-container-column-text>
						
						<liferay-ui:search-container-column-jsp 
							path="/WEB-INF/jsp/admin-vo-action.jsp" align="right" />
					</c:if>
					
			</liferay-ui:search-container-row>
			<liferay-ui:search-iterator />
		</liferay-ui:search-container>

		<portlet:renderURL var="addUserToVOActionUrl">
			<portlet:param name="myaction" value="showAddUserToVO" />
			<portlet:param name="userId" value="${selectedUser.userId}" />
			<portlet:param name="firstReg" value="false" />
		</portlet:renderURL>
		
		<portlet:renderURL var="voUrl">
			<portlet:param name="myaction" value="showVOList" />
			<portlet:param name="waif" value="editUserInfoForm" />
			<portlet:param name="userId" value="${selectedUser.userId}"/>
		</portlet:renderURL>
		
		</c:if>
		
		<c:if test="${fn:length(certList) == 0}">
			
			<div class="portlet-msg-error"> You haven't a certificate yet. Request one or upload your Personal Certificate.</div>
			
			
		</c:if>

		<div id="apriVo"></div>

	</div>
</div>
<div id="advancedSettings">

	<h3 class="header-title">Advanced Settings</h3>
	
	<div id="<portlet:namespace/>advSetOn">
	
		<aui:layout>

			<aui:fieldset>
				<aui:column columnWidth="80">
					<aui:fieldset>
					
						Proxy Notification: <strong><c:if test="${advOpts.proxyExpire=='true' }">ON</c:if><c:if test="${advOpts.proxyExpire=='false' }">OFF</c:if></strong>.<br/>
						Proxy Lifetime: <strong><c:forEach var="option" items="${expirationTime}"><c:if test="${advOpts.proxyExpireTime==fn:trim(fn:split(option,'/')[0]) }">${fn:split(option,'/')[1] }</c:if></c:forEach></strong>.<br/>
						Job Notification: <strong><c:if test="${notification.wfchgEnab=='true' }">ON</c:if><c:if test="${notification.wfchgEnab=='false' }">OFF</c:if></strong>.<br/>
						
					</aui:fieldset>
				</aui:column>
				<aui:column columnWidth="20">
					<aui:fieldset>
						<div id="userSettings"><a href="#apriAdvSet" onclick="mostraAdvSetUtente();" onmouseover="viewTooltip('#settingsButtonAdv');"><img src="<%=request.getContextPath()%>/images/advancedsettings.png" width="48" height="48" style="float: right; padding-right:10px;"/></a></div>
					</aui:fieldset>
				</aui:column>	
			</aui:fieldset>
		</aui:layout>
		
	</div>
	<div id="<portlet:namespace/>advSetOff" >
		<div id="closeBox"><a href="#advSet" onclick="nascondiAdvSetUtente();"><img src="<%=request.getContextPath()%>/images/close-button2.png"/></a></div>
		<div id="closeBox"><a href="#advSet" onclick="nascondiAdvSetUtente();">Hide Settings</a></div>
		<br /> <br />
		
			<aui:layout>

				<aui:fieldset>
					<aui:form name="guseNotifyForm" commandName="notification"
								action="${updateGuseNotifyActionUrl}">
					<aui:column columnWidth="50">
					
						<aui:fieldset label="Proxy Notification">
							
								<br></br>
								
								<aui:input name="idNotify" type="hidden"
									value="${advOpts.idNotify }" />
								
								<aui:input id="proxyExpire" name="proxyExpire" type="hidden" value="${advOpts.proxyExpire }" />
								<strong>Enable the switch before if you want to be notified by mail 1 hour before the proxy expiration</strong>
								<br/><br/><div id="jqxButton"></div>
								
									
								
						</aui:fieldset>
						
						<aui:fieldset label="Proxy Lifetime">		
								
								<aui:select name="proxyExpireTime" inlineLabel="true" label="Expiration Time">
								
									<c:forEach var="option" items="${expirationTime}">
										
										<aui:option value="${fn:trim(fn:split(option,'/')[0]) }" selected="${advOpts.proxyExpireTime==fn:trim(fn:split(option,'/')[0]) }">${fn:split(option,'/')[1] }</aui:option>
																	
									</c:forEach>
								
								</aui:select>
								
								
						</aui:fieldset>
						
					</aui:column>
					
					<aui:column columnWidth="50">
						
							
								<aui:fieldset label="Job Notification">
								<br></br>
								<strong>Enable the switch before if you want to be notified by mail on workflow status changing</strong> <br/><br/>
								<aui:input name="wfchgEnab" id="wfchgEnab" type="hidden"
									value="${notification.wfchgEnab }" />
								<div id="jqxButton2"></div>
								</aui:fieldset>
								<aui:fieldset label="e-Mail configuration"> 
								<aui:input name="emailAddr" type="hidden"
									value="${notification.emailAddr }" />
									
								<aui:input name="userId" type="hidden"
								value="<%=selectedUser.getUserId() %>" />
								
							 	<aui:input name="emailEnab" type="hidden"
									value="${notification.emailEnab }" />
									
								<aui:input name="emailSubj" type="text"
									label="Email Subject:" value="${notification.emailSubj }" />
									
								<aui:fieldset>
									<aui:column columnWidth="70">
										<aui:input name="wfchgMess" type="textarea" rows="7" cols="40"
											label="Insert your message here" value="${notification.wfchgMess }" />
									</aui:column>
									<aui:column columnWidth="30">
										<br/> <strong>key list:</strong> <br/>
										#now# <br/>
										#portal# <br/>
										#workflow# <br/>
										#instance# <br/>
										#oldstatus# <br/>
										#newstatus# 
									</aui:column>
								</aui:fieldset>
									
								<aui:input name="quotaMess" type="hidden"
									value="${notification.quotaMess }" />
								
								<aui:input name="quotaEnab" type="hidden"
									value="${notification.quotaEnab }" />
								
								
							</aui:fieldset>
						
					</aui:column>

					<aui:button-row style="float: right; margin-right: 20px;">
						<aui:button type="submit" />
					</aui:button-row>
					
				</aui:form>
					
				</aui:fieldset>
			</aui:layout>
		
		
		<div id="apriAdvSet"></div>

	</div>

</div>


<div id="settingsButtonAdv" style="display:none;">Edit your Advanced Settings.</div>
<div id="settingsButtonCert" style="display:none;">Edit your certficate.</div>
<div id="settingsButtonVO" style="display:none;">Edit your VO.</div>

</aui:column>

<aui:column columnWidth="30">

<div id="side">

	
<c:set var="check" value="<%= request.isUserInRole("administrator") %>"/>
	
<c:if test="${check == 'false' }">
<aui:fieldset label="Hi ${selectedUser.firstName}">
<table style="width: 100%;">
	<tbody>
		<c:if test="${fn:length(userToVoList)>0&&isUserActive=='true'}">
		<tr>
			<td colspan="3">
			
				<c:if test="${proxyDownloaded=='false' }">				
				<liferay-portlet:renderURL plid="11914" portletName="Login_WAR_Login11_INSTANCE_OI71Ar1eqW4Y" windowState="<%= LiferayWindowState.POP_UP.toString() %>" var="downloadProxy">
					<portlet:param name="myaction" value="downloadCertificate" />
				</liferay-portlet:renderURL>
				<p style="text-align: center;">
					<a href="#" onclick="$(this).modal({width:400, height:300, message:true, src: '${downloadProxy }', redirect: '${usePortalURL }'}).open(); return false;" class="sideButton">
					<img alt="" src="<%=request.getContextPath()%>/images/Use_Portal2.png" style="width: 137px; height: 48px;" /></a></p>
				
				</c:if>
				<c:if test="${proxyDownloaded=='true' }">	
				<p style="text-align: center;">
					<a href="#" onclick="location.href='${usePortalURL }';" class="sideButton">
					<img alt="" src="<%=request.getContextPath()%>/images/Use_Portal2.png" style="width: 137px; height: 48px;" /></a></p>
				
				</c:if>
				<p style="text-align: center;">
					<img alt="" src="<%=request.getContextPath()%>/images/separatore-giu.png" style="width: 237px; height: 15px;" /></p>
			</td>
		</tr>
		</c:if>
		<tr>
			<td colspan="3">
				
				<p style="text-align: center;">
				<portlet:actionURL var="deleteURL">
					<portlet:param name="myaction" value="deleteByUser" /> 
					<portlet:param name="userId" value="${selectedUser.userId }" /> 
				</portlet:actionURL>
					<a href="#" onClick="verifyDelete('${deleteURL}')" class="sideButton">
					<img alt="" src="<%=request.getContextPath()%>/images/Delete_user3.png" style="width: 130px; height: 48px;" /></a></p>
				<p style="text-align: center;">
					<img alt="" src="<%=request.getContextPath()%>/images/separatore-giu.png" style="width: 237px; height: 15px;" /></p>
				
			</td>
		</tr>
		<tr>
			<td style="width: 30%; text-align: center; vertical-align: middle;">
				<p>
					<a href="#" onclick="location.href='https://flyback.cnaf.infn.it/web/guest/contact-us';" class="sideButton large">
					<img alt="" src="<%=request.getContextPath()%>/images/Documentation_guide.png" style="width: 80px; height: 80px;" /></a></p>
				<p>
					<img alt="" src="<%=request.getContextPath()%>/images/separatore-giu.png" style="line-height: 1.4; width: 80px; height: 10px;" /></p>
			</td>
			<td style="width: 30%; text-align: center; vertical-align: middle;">
				<p>
					<a href="#" onclick="location.href='https://flyback.cnaf.infn.it/web/guest/contact-us';" class="sideButton large">
					<img alt="" src="<%=request.getContextPath()%>/images/Video_guide.png" style="width: 80px; height: 80px;" /></a></p>
				<p>
					<img alt="" src="<%=request.getContextPath()%>/images/separatore-giu.png" style="width: 80px; height: 10px;" /></p>
			</td>
			<td style="width: 30%; text-align: center; vertical-align: middle;">
				<p><a href="#" onclick="location.href='https://flyback.cnaf.infn.it/web/guest/contact-us';" class="sideButton large">
				<img alt="" src="<%=request.getContextPath()%>/images/HelpSide.png" style="width: 80px; height: 80px;" /></a><br />
				</p><p>
					<img alt="" src="<%=request.getContextPath()%>/images/separatore-giu.png" style="width: 80px; height: 10px;" /></p>
			</td>
		</tr>
	</tbody>
</table>
</aui:fieldset>
</c:if>
<c:if test="<%= request.isUserInRole("administrator") %>">
<aui:fieldset label="Hi Administrator">
<table style="width: 100%;">
	<tbody>
		<c:if test="${fn:length(userToVoList)>0&&isUserActive=='true'}">
		<tr>
			<td colspan="3">
			
				<portlet:actionURL var="homeUrl">
					<portlet:param name="myaction" value="uploadComplete" />
				</portlet:actionURL>
				<p style="text-align: center;">
					<a href="#" onclick="location.href='${homeUrl }';" class="sideButton">
					<img alt="" src="<%=request.getContextPath()%>/images/Changes_completed.png" style="width: 137px; height: 48px;" /></a></p>
				
				
				<p style="text-align: center;">
					<img alt="" src="<%=request.getContextPath()%>/images/separatore-giu.png" style="width: 237px; height: 15px;" /></p>
			</td>
		</tr>
		</c:if>
		<tr>
			<td colspan="3">
				
				<p style="text-align: center;">
				<portlet:actionURL var="deleteURL">
					<portlet:param name="myaction" value="removeUserInfo" /> 
					<portlet:param name="userId" value="${selectedUser.userId }" /> 
				</portlet:actionURL>
					<a href="#" onClick="verifyDelete('${deleteURL}')" class="sideButton">
					<img alt="" src="<%=request.getContextPath()%>/images/Delete_user3.png" style="width: 130px; height: 48px;" /></a></p>
				<p style="text-align: center;">
					<img alt="" src="<%=request.getContextPath()%>/images/separatore-giu.png" style="width: 237px; height: 15px;" /></p>
				
			</td>
		</tr>
	</tbody>
</table>
</aui:fieldset>
</c:if>

</div>
	
</aui:column>


</c:if>



<c:if test="${changePasswordNow=='false' }">
<liferay-ui:error key="myproxy-exception" message="myproxy-exception" />


<liferay-ui:error key="no-valid-cert" message="no-valid-cert" />
<liferay-ui:error key="no-valid-key"
	message="no-valid-key" />
<liferay-ui:error key="key-password-failure"
	message="key-password-failure" />
<liferay-ui:error key="password-not-changed"
	message="password-not-changed" />
<liferay-ui:error key="error-password-mismatch"
	message="error-password-mismatch" />
<liferay-ui:error key="myproxy-exception"
	message="myproxy-exception" />
<liferay-ui:error key="error-password-too-short"
	message="error-password-too-short" />


<c:forEach var="crt" items="${certList }">
	
	
	<div class="changePassword">
	<aui:fieldset>
	<div class="portlet-msg-alert">Please choose a password to encrypt your credentials.<br/>
			You have to insert this password every time you need to retrieve your credentials, don't forget it because we don't conserve it !!</div>
	
	
	<portlet:actionURL var="changePwdURL">
		<portlet:param name="myaction" value="changePwd2"/>
		<portlet:param name="userId" value="<%= request.getParameter("userId") %>"/>
		<portlet:param name="idCert" value="${crt.idCert }"/>
	</portlet:actionURL>
	<aui:column columnWidth="40" style="margin-left:30px;">
	<aui:form name="changePwd_${crt.idCert }" action="${changePwdURL}">
		<div class="portlet-msg-error proxyPwd" style="display:none;">
			These password must be the same.
		</div>
		<aui:input id="password" name="pwd" type="password" label="Insert Password" onBlur="printCheck($(this).attr('id'));"/>
		<div class="portlet-msg-error proxyPwd" style="display:none;">
			These password must be the same.
		</div>
		<aui:input  id="passwordVerify" name="confirmpwd" type="password" label="Confirm Password" onkeyup="verifyPassword();"/>
		<aui:button-row>
		<aui:button type="submit" value="Save"/>
		</aui:button-row>
	</aui:form>
	</aui:column>
	<aui:column columnWidth="40" style="margin-left:30px;">

				<aui:fieldset>
					
					<br/><br/>
					
					<div id="help">
					<a href="https://portal.italiangrid.it:8443/info/certificate-upload-technical-info.html"  onclick="$(this).modal({width:800, height:600, message:true}).open(); return false;"><img class="displayed"
													src="<%=request.getContextPath()%>/images/Information2.png"
													id="noImg" width="48" />Technical Information</a>
					 							
			
													
					</div>
				</aui:fieldset>

				
			</aui:column>
	
	</aui:fieldset>
	</div>
	
	
	
	
</c:forEach>

</c:if>

</div>





