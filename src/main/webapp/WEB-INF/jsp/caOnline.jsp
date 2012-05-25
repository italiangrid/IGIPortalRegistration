<%@ include file="/WEB-INF/jsp/init.jsp"%>


<script type="text/javascript">
<!--

//-->


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
	}

	/**********************************
	* DO NOT CUSTOMIZE BELOW THIS LINE
	**********************************/
	$.modal = function (options) {
		return _modal(this, options);
	}
	$.modal.open = function () {
		_modal.open();
	}
	$.modal.close = function () {
		_modal.close();
	}
	$.fn.modal = function (options) {
		return _modal(this, options);
	}
	_modal = function (sender, params) {
		this.options = {
			parent: null,
			overlayOpacity: null,
			id: null,
			content: null,
			width: null,
			height: null,
			modalClassName: null,
			imageClassName: null,
			closeClassName: null,
			overlayClassName: null,
			src: null
		}
		this.options = $.extend({}, options, _defaults);
		this.options = $.extend({}, options, _settings);
		this.options = $.extend({}, options, params);
		this.close = function () {
			jQuery('.' + options.modalClassName + ', .' + options.overlayClassName).fadeOut(_settings.fadeOutSpeed, function () { jQuery(this).unbind().remove(); });
		}
		this.open = function () {
			if (typeof options.src == 'function') {
				options.src = options.src(sender)
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

				$close.click(function () { jQuery.modal().close(); });
				$overlay.click(function () { jQuery.modal().close(); });
			}
		}
		return this;
	}
	_isIE6 = function () {
		if (document.all && document.getElementById) {
			if (document.compatMode && !window.XMLHttpRequest) {
				return true;
			}
		}
		return false;
	}
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
	}
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
	}
})(jQuery);

function checkChoice(){
	//alert("Sono dentro");
	//alert($("#<portlet:namespace/>haveCert").val());
	if($("#<portlet:namespace/>haveCert").val() == "true"){
		//alert("checked");
		openMyModal('https://131.154.4.63:8443/test/index.jsp');
	}
}


</script>

<style>
<!--

-->
.modal-overlay {
	position: fixed;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	height: 100%;
	width: 100%;
	margin: 0;
	padding: 0;
	background: #131313;
	opacity: .85;
	filter: alpha(opacity=85);
	z-index: 101;
}
.modal-window {
	position: fixed;
	top: 50%;
	left: 50%;
	margin: 0;
	padding: 0;
	z-index: 102;
	background: #fff;
	border: solid 8px #000;
	-moz-border-radius: 8px;
	-webkit-border-radius: 8px;
}
.close-window {
	position: absolute;
	width: 47px;
	height: 47px;
	right: -23px;
	top: -23px;
	background: transparent url(<%=request.getContextPath()%>/images/close-button2.png) no-repeat scroll right top;
	text-indent: -99999px;
	overflow: hidden;
	cursor: pointer;
}


</style>



<liferay-ui:success key="user-saved-successufully"
	message="user-saved-successufully" />

	
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
	<portlet:param name="myaction" value="haveCert" />
</portlet:actionURL>

<portlet:renderURL var="homeUrl">
	<portlet:param name="myaction" value="userInfos" />
</portlet:renderURL>

<!--  -->

<aui:form name="formCA" id="formCA" action="${uploadCertUrl}">
	<aui:layout>

		<h1 class="header-title">Have a Personal Certificate?</h1>

		<br></br>

		<aui:fieldset>
			
			
				
			<c:if test="${firstReg == true}">
			<aui:column columnWidth="25">
				
				<aui:fieldset label="Registration">
					<br />
					
					<img src="<%=request.getContextPath()%>/images/step2.png"/>
					
					

				</aui:fieldset>
				
				<br />
				<br />
			</aui:column>
			</c:if>
			<aui:column columnWidth="25">

				<aui:fieldset label="Have a Certificate?">
					<br />
					
					${userId}<br/>
					${username}<br/>
					${firstReg}<br/><br/>

					<aui:input name="userId" type="hidden" value="${userId}" />
					<aui:input name="username" type="hidden" value="${username}" />
					<aui:input name="firstReg" type="hidden" value="${firstReg}" />
					<aui:input label="Do you have certificate" name="haveCert"
							type="checkbox"  onClick="checkChoice();"/>
					

				</aui:fieldset>

				<br />
				<br />
			</aui:column>

			

			<aui:button-row>
				<aui:button type="submit" value="Continue"/>
				

						<aui:button type="cancel" value="Back"
							onClick="location.href='${homeUrl}';" />
					

			</aui:button-row>

		</aui:fieldset>
	</aui:layout>
</aui:form>


<a href="https://131.154.4.63:8443/test/index.jsp" onclick="$(this).modal({width:800, height:600}).open(); return false;">Modal iFrame</a>


