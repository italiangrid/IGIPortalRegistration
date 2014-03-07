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

</style>



<portlet:renderURL var="showAskForCertificateUrl">
	<portlet:param name="myaction" value="askForCertificate" />
</portlet:renderURL>

<jsp:useBean id="userInfos"
	type="java.util.List<it.italiangrid.portal.dbapi.domain.UserInfo>"
	scope="request" />
<jsp:useBean id="idps"
	type="java.util.List<it.italiangrid.portal.dbapi.domain.Idp>" scope="request" />


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
					
					<aui:button type="cancel" value="Reset Search"
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
		
		<%
		
		PortalPreferences portalPrefs = PortletPreferencesFactoryUtil.getPortalPreferences(request); 
		String sortByCol = ParamUtil.getString(request, "orderByCol"); 
		String sortByType = ParamUtil.getString(request, "orderByType"); 
		   
		if (Validator.isNotNull(sortByCol ) && Validator.isNotNull(sortByType )) { 
		 
			portalPrefs.setValue("NAME_SPACE", "sort-by-col", sortByCol); 
			portalPrefs.setValue("NAME_SPACE", "sort-by-type", sortByCol); 
		 
		} else { 
		 
			sortByCol = portalPrefs.getValue("NAME_SPACE", "sort-by-col", "lastName");
			sortByType = portalPrefs.getValue("NAME_SPACE", "sort-by-type ", "asc");   
		}
		
		%>
		
		<liferay-ui:search-container
			emptyResultsMessage="No user registred" delta="20" orderByCol="<%= sortByCol %>" orderByType="<%= sortByType %>">
			<liferay-ui:search-container-results>
				<%
					
					OrderByComparator orderByComparator = CustomComparatorUtil.getUserOrderByComparator(sortByCol, sortByType, PortalUtil.getCompanyId(request));         
		  
		           	Collections.sort(userInfos,orderByComparator);
		           
					results = ListUtil.subList(userInfos,
							searchContainer.getStart(),
							searchContainer.getEnd());
					total = userInfos.size();

					
					pageContext.setAttribute("results", results);
					pageContext.setAttribute("total", total);
				%>


			</liferay-ui:search-container-results>
			<liferay-ui:search-container-row
				className="it.italiangrid.portal.dbapi.domain.UserInfo" keyProperty="userId"
				modelVar="UserInfo">
				<liferay-ui:search-container-column-text name="Last Name"
					property="lastName" orderable="<%= true %>" orderableProperty="lastName"/>
				<liferay-ui:search-container-column-text name="First Name"
					property="firstName" orderable="<%= true %>" orderableProperty="firstName"/>
				<liferay-ui:search-container-column-text name="Institute"
					property="institute" orderable="<%= true %>" orderableProperty="institute"/>
				<liferay-ui:search-container-column-text name="e-Mail"
					property="mail" orderable="<%= true %>" orderableProperty="mail"/>

				<%
					UserInfo ui = (UserInfo) row.getObject();
					long companyId = PortalUtil.getCompanyId(request);
					User liferayUser = UserLocalServiceUtil.getUserByEmailAddress(companyId, ui.getMail());
					List<Group> groups = liferayUser.getGroups();
					
					String groupsString = "";
					String groupsStringName = "";
					
					for(Group g : groups){
						if(g.getName().equals("Guest")){
							groupsString+= g.getName() + "|/web" + g.getFriendlyURL() + ",";
						} else {
							groupsString+= g.getName() + "|/group" + g.getFriendlyURL() + ",";
						}
						groupsStringName+= g.getName() + ", ";
					}
					groupsString = groupsString.substring(0, groupsString.length() -1 );
					groupsStringName = groupsStringName.substring(0, groupsStringName.length() -2 );
					
					pageContext.setAttribute("groupList",groupsString);
					pageContext.setAttribute("groupListName",groupsStringName);
					pageContext.setAttribute("liferayUser",liferayUser.getUserId());
					SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
					pageContext.setAttribute("registrationDate",sdf.format(liferayUser.getCreateDate()));
					Boolean test = false;
					if(ui.getUsername().equals(user.getScreenName()))
						test = true;
						
					pageContext.setAttribute("test",test);
					
				%>
				<liferay-ui:search-container-column-text name="Registration Date" orderable="<%= true %>" >${registrationDate }</liferay-ui:search-container-column-text>
				<liferay-ui:search-container-column-text name="Liferay UID" orderable="<%= true %>">${liferayUser }</liferay-ui:search-container-column-text>
				<liferay-ui:search-container-column-text name="Group" orderable="<%= true %>">${groupListName }</liferay-ui:search-container-column-text>
				
				<liferay-ui:search-container-column-text name="Actions">
					<liferay-ui:icon-menu>

						<portlet:renderURL var="editURL">
							<portlet:param name="myaction" value="editUserInfoForm" />
							<portlet:param name="userId" value="${UserInfo.userId }" />
						</portlet:renderURL>
						<liferay-ui:icon image="edit" message="Edit" url="${editURL}" />
						
						<c:forTokens var="group" items="${groupList }" delims=",">
						
							<c:if test="${fn:split(group,'|')[0] == 'Guest' }">
								<liferay-security:doAsURL  doAsUserId="${liferayUser}"  var="impersonateUserURL"/>
								<liferay-ui:icon image="impersonate_user" target="_blank" message="Impersonate User" url="${impersonateUserURL}" />
							</c:if>
							<c:if test="${fn:split(group,'|')[0] != 'Guest' }">
								<liferay-security:doAsURL  doAsUserId="${liferayUser}"  var="impersonateUserURL"/>
								<c:set var="impersonateUserURL" value="${fn:replace(impersonateUserURL, '/web/guest/welcome', fn:split(group,'|')[1])}"/>
								<liferay-ui:icon image="impersonate_user" target="_blank" message="Impersonate User ${fn:split(group,'|')[0]}" url="${impersonateUserURL}" />
							</c:if>
						
						</c:forTokens>
						
						<c:if test="${!test}">
							<portlet:actionURL var="deleteURL">
								<portlet:param name="myaction" value="removeUserInfo" /> 
								<portlet:param name="userId" value="${UserInfo.userId }" />
							</portlet:actionURL>
							<liferay-ui:icon-delete url="${deleteURL}" />
						</c:if>
					
					</liferay-ui:icon-menu>
				</liferay-ui:search-container-column-text>
				
			</liferay-ui:search-container-row>
			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
		</div>
		
		</div>
	</c:when>
</c:choose>

