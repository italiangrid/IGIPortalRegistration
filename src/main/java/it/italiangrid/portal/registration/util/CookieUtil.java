package it.italiangrid.portal.registration.util;

import java.util.Arrays;
import java.util.List;

import it.italiangrid.portal.registration.model.RegistrationModel;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderResponse;
import javax.servlet.http.Cookie;

public class CookieUtil {
	
	public static void setCookie(RegistrationModel registrationModel, ActionResponse response) {
		setCookie("haveCertificate", Boolean.toString(registrationModel.isHaveCertificate()), response);
		setCookie("issuer",  registrationModel.getIssuer(), response);
		setCookie("subject",  registrationModel.getSubject(), response);
		setCookie("certificateUserId",  registrationModel.getCertificateUserId(), response);
		setCookie("vos",  registrationModel.getVos(), response);
		setCookie("searchVo", registrationModel.getSearchVo(), response);
	}
	
	public static void setCookie(String name, String value, ActionResponse response) {
		Cookie cookie = new Cookie(name, value);
		cookie.setSecure(true);
		cookie.setPath("/");
		cookie.setMaxAge(3600);
		response.addProperty(cookie);
		
	}
	
	public static void delCookie(RenderResponse response){
		delCookie("haveCertificate", response);
		delCookie("issuer",  response);
		delCookie("subject",  response);
		delCookie("certificateUserId",  response);
		delCookie("vos",  response);
		delCookie("searchVo", response);
	}
	
	public static void delCookie(String name, RenderResponse response){
		Cookie cookie = new Cookie(name, "none");
		cookie.setSecure(true);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addProperty(cookie);
	}

	public static RegistrationModel getCookie(ActionRequest request) {
		RegistrationModel rm = new RegistrationModel();
		
		String[] array = {"haveCertificate", "issuer", "subject", "certificateUserId", "vos", "searchVo"};
		List<String> cookieNames = Arrays.asList(array);
		
		for(Cookie c: request.getCookies()){
			switch(cookieNames.indexOf(c.getName())){
			case 0:
				//haveCertificate
				rm.setHaveCertificate(Boolean.parseBoolean(c.getValue()));
				break;
			case 1:
				//issuer
				rm.setIssuer(c.getValue());
				break;
			case 2:
				//subject
				rm.setSubject(c.getValue());
				break;
			case 3:
				//certificateUserId
				rm.setCertificateUserId(c.getValue());
				break;
			case 4:
				//vos
				rm.setVos(c.getValue());
				break;
			case 5:
				//searchVo
				rm.setSearchVo(c.getValue());
				break;
			}
		}
		
		return rm;
	}
}
