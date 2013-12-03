<%@ include file="/WEB-INF/jsp/init.jsp"%>

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
	
	function verifyDelete(url){
		var agree=confirm("Sei sicuro di voler eliminare il tuo account?");
		if (agree)
			return location.href=url ;
	}
	
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
				redirect: null,
				myurl: null
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
				
				if(options.myurl != null){
					options.src=options.myurl;
				}

				var fileExt = /^.+\.((jpg)|(gif)|(jpeg)|(png)|(jpg))$/i;
				var contentHTML = '';
				if (fileExt.test(options.src)) {
					contentHTML = '<div class="' + options.imageClassName + '"><img src="' + options.src + '"/></div>';

				} else {
					contentHTML = '<iframe width="' + options.width + '" height="' + options.height + '" frameborder="0" scrolling="yes" allowtransparency="true" src="' + options.src + '"></iframe>';
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
			redirect: null,
			myurl: null
		};
	})(jQuery);
	

	$(document).ready(function() {
		//nascondiCertificatiUtente();
		//nascondiModificaUtente();
		//nascondiVoUtente();
		
		document.closeFrame = function(){  
			//window.location.href='${usePortalURL}';
			$(this).modal().close();
		};
	});
</script>

<style>
div.function {
	padding: 1em 5em 1em 1em;
	border: 1px;
	border-color: #C8C9CA;
	border-style: solid;
	background-color: #D1D6DC;
}
#tabella {
	margin: 3px 0 3px 0;
	padding: 1em;
/* 	border: 1px; */
/* 	border-color: #C8C9CA; */
/* 	border-style: solid; */
}
#search{float:left;}
#addVo{float:left;}
#clear{clear:both;}

div#personalData {
	padding: 1em;
	border: 1px;
	border-color: #C8C9CA;
	border-style: solid;
	background-color: #EFEFEF;
}

div#certificateData {
	margin: 3px 0 3px 0;
	padding: 1em;
	border: 1px;
	border-color: #C8C9CA;
	border-style: solid;
	background-color: #EFEFEF;
}

div#voData {
	padding: 1em;
	border: 1px;
	border-color: #C8C9CA;
	border-style: solid;
	background-color: #EFEFEF;
}

</style>



<portlet:renderURL var="showAskForCertificateUrl">
	<portlet:param name="myaction" value="askForCertificate" />
</portlet:renderURL>

<jsp:useBean id="userInfos"
	type="java.util.List<it.italiangrid.portal.dbapi.domain.UserInfo>"
	scope="request" />
<jsp:useBean id="idps"
	type="java.util.List<it.italiangrid.portal.dbapi.domain.Idp>" scope="request" />
<jsp:useBean id="idpsName" type="java.util.Map" scope="request" />


<liferay-ui:success key="user-delated-successufully"
	message="user-delated-successufully" />
<liferay-ui:success key="user-registration-success"
	message="user-registration-success" />

<liferay-ui:error key="use-condition-not-acepted"
	message="use-condition-not-acepted" />
<liferay-ui:error key="exception-activation-user"
	message="exception-activation-user" />

<c:choose>
	<c:when test="<%= !themeDisplay.isSignedIn() %>"> 
		
		<div>
			
		<%@ include file="/WEB-INF/jsp/instructionPage.jsp" %>
		<%@ include file="/WEB-INF/jsp/summary.jsp" %>
		<div style="clear:both;"></div>
		</div>
	</c:when>
	<c:when test="<%= (themeDisplay.isSignedIn()) && (request.isUserInRole("administrator")) %>">
		<div id="container2">
	  	<div id="presentation">
		
			<%
					User userLF = (User) request.getAttribute(WebKeys.USER);

					String saluto= "Hi " + userLF.getFirstName();
				%>
		  	<aui:fieldset label="<%=saluto %>"></aui:fieldset>
			<br />
			<br />
		</div>
		
		<div class="function">
			<aui:fieldset>
			<aui:column columnWidth="50">
			<portlet:actionURL var="searchVOActionUrl">
				<portlet:param name="myaction" value="searchUser" />
			</portlet:actionURL>
			
			
			<aui:form name="searchUserInfo" 
				action="${searchVOActionUrl}">
				<aui:layout>
					<aui:button-row>
					<aui:input name="key" label="Cerca Utente" type="text" inlineField="true" inlineLabel="true"/>
					<aui:button type="submit" value="Search" inlineField="true"/>
					<portlet:actionURL var="backURL">
						<portlet:param name="myaction" value="searchResetUser" />
					</portlet:actionURL>
					
					<aui:button type="cancel" value="Erase search"
						onClick="location.href='${backURL}';" />
					<liferay-portlet:renderURL  windowState="<%= LiferayWindowState.POP_UP.toString() %>" var="downloadProxy">
						<portlet:param name="myaction" value="showSendMail" />
					</liferay-portlet:renderURL>
					<aui:button type="button" value="Send Email" onClick="$(this).modal({width:600, height:450, message:true, src:'${downloadProxy }'}).open();"/>
					</aui:button-row>
				</aui:layout>
			</aui:form>
			
			<c:if test="${!empty search }">
				<br/>
				Search: <strong><c:out value="${search}" /></strong>	
			</c:if>
			</aui:column>
			</aui:fieldset>
		</div>
		<div id="tabella">
		<liferay-ui:search-container
			emptyResultsMessage="No user registred" delta="20">
			<liferay-ui:search-container-results>
				<%
					results = ListUtil.subList(userInfos,
							searchContainer.getStart(),
							searchContainer.getEnd());
					total = userInfos.size();

					pageContext.setAttribute("idps", idps);
					pageContext.setAttribute("results", results);
					pageContext.setAttribute("total", total);
				%>


			</liferay-ui:search-container-results>
			<liferay-ui:search-container-row
				className="it.italiangrid.portal.dbapi.domain.UserInfo" keyProperty="userId"
				modelVar="UserInfo">
				<liferay-ui:search-container-column-text name="Last Name"
					property="lastName" />
				<liferay-ui:search-container-column-text name="First Name"
					property="firstName" />
				<liferay-ui:search-container-column-text name="Institute"
					property="institute" />
				<liferay-ui:search-container-column-text name="e-Mail"
					property="mail" />

				<%
					UserInfo ui = (UserInfo) row.getObject();
									String res = (String) idpsName.get(ui.getUserId());
				%>
				<liferay-ui:search-container-column-jsp
					path="/WEB-INF/jsp/admin-action.jsp" align="right" />
			</liferay-ui:search-container-row>
			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
		</div>
		
		</div>
	</c:when>
	<c:otherwise>
	
		
		
		<portlet:renderURL var="editURL">
			<portlet:param name="myaction" value="editUserInfoForm" />
			<portlet:param name="userId" value="${userId }" />
		</portlet:renderURL>
		
		<script>
		
		/*location.href="${editURL}";*/
		
		</script>
		
		non dovresti essere qui.
		

	</c:otherwise>
</c:choose>


