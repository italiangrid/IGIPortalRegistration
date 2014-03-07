<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="it.italiangrid.portal.dbapi.domain.*" %>
<%@ page import="it.italiangrid.portal.registration.model.*" %>
<%@ page import="portal.registration.controller.AddUserToVOController" %>
<%@ page import="java.security.cert.X509Certificate" %>
<%@ page import="java.net.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.GregorianCalendar" %>

<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %> 
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %> 
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Calendar" %> 
<%@ page import="java.util.Collections" %>

<%@ page import="javax.portlet.PortletURL" %>
 
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %> 
<%@ page import="com.liferay.portal.kernel.util.CalendarFactoryUtil" %> 
<%@ page import="com.liferay.portal.kernel.util.WebKeys" %> 
<%@ page import="com.liferay.portal.kernel.util.ListUtil" %>
<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %> 
<%@ page import="com.liferay.portal.kernel.dao.search.SearchEntry" %> 
<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %> 
<%@ page import="com.liferay.portal.kernel.exception.SystemException" %> 
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %>

<%@ page import="com.liferay.portal.service.permission.PortalPermissionUtil" %>
<%@ page import="com.liferay.portal.service.permission.PortletPermissionUtil"%> 
<%@ page import="com.liferay.portal.service.UserLocalServiceUtil" %>

<%@ page import="com.liferay.portal.security.auth.AuthTokenUtil" %>
<%@ page import="com.liferay.portal.security.permission.ActionKeys" %> 

<%@ page import="com.liferay.portal.model.User"%>
<%@ page import="com.liferay.portal.model.Group"%>
<%@ page import="com.liferay.portal.model.Layout" %>

<%@ page import="com.liferay.portal.theme.ThemeDisplay" %>

<%@ page import="com.liferay.portal.util.PortalUtil" %>

<%@ page import="com.liferay.portal.kernel.util.OrderByComparator" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.liferay.portlet.PortalPreferences" %>
<%@ page import="com.liferay.portlet.PortletPreferencesFactoryUtil" %>
<%@ page import="it.italiangrid.portal.registration.util.CustomComparatorUtil" %>


<portlet:defineObjects />	
<liferay-theme:defineObjects />