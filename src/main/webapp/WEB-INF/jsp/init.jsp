<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>


<%@ page import="portal.registration.domain.UserInfo" %>
<%@ page import="portal.registration.domain.Idp" %>
<%@ page import="portal.registration.domain.Vo" %>
<%@ page import="portal.registration.domain.UserToVo" %>
<%@ page import="portal.registration.domain.Certificate" %>
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
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %> 
<%@ page import="com.liferay.portal.kernel.util.CalendarFactoryUtil" %> 
<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %> 
<%@ page import="com.liferay.portal.kernel.dao.search.SearchEntry" %> 
<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %> 
<%@ page import="com.liferay.portal.kernel.exception.SystemException" %> 
<%@ page import="com.liferay.portal.kernel.util.WebKeys" %> 
<%@ page import="com.liferay.portal.security.permission.ActionKeys" %> 
<%@ page import="com.liferay.portal.kernel.util.ListUtil" %> 
<%@ page import="com.liferay.portal.service.permission.PortalPermissionUtil" %>
<%@ page import="com.liferay.portal.service.permission.PortletPermissionUtil"%> 
<%@ page import="com.liferay.portal.model.User"%>
<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="com.liferay.portal.security.auth.AuthTokenUtil" %>

<portlet:defineObjects />	
<liferay-theme:defineObjects />