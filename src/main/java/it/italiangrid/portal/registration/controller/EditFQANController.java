package it.italiangrid.portal.registration.controller;

import javax.portlet.RenderRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import it.italiangrid.portal.dbapi.domain.Certificate;
import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.dbapi.domain.UserToVo;
import it.italiangrid.portal.dbapi.domain.Vo;
import it.italiangrid.portal.dbapi.services.CertificateService;
import it.italiangrid.portal.dbapi.services.UserInfoService;
import it.italiangrid.portal.dbapi.services.UserToVoService;
import it.italiangrid.portal.dbapi.services.VoService;
import it.italiangrid.portal.registration.model.RegistrationModel;
import portal.registration.utils.VOMSAdminCallOut;

@Controller(value = "editFQANController")
@RequestMapping("view")
public class EditFQANController {

	private static final Logger log = Logger
			.getLogger(EditFQANController.class);

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private VoService voService;

	@Autowired
	private UserToVoService userToVoService;

	@Autowired
	private CertificateService certificateService;

	@RenderMapping(params = "myaction=showEditFQAN")
	public String showEditUserInfoForm() {
		return "editFQANForm";
	}	

	@ModelAttribute("userToVo")
	public UserToVo getUserToVo(RenderRequest request) {
		RegistrationModel registrationModel = getRegistrationModelFromRequest(request);
		UserInfo userinfo = userInfoService.findByMail(registrationModel.getEmail());
		return userToVoService.findById(userinfo.getUserId(), Integer.parseInt(request.getParameter("idVo")));
	}
	
	@ModelAttribute("registrationModel")
	public RegistrationModel getRegistrationModel(RenderRequest request) {
		RegistrationModel registrationModel = getRegistrationModelFromRequest(request);
		return registrationModel;
	}
	
	@ModelAttribute("vo")
	public Vo getVO(RenderRequest request) {
		return voService.findById(Integer.parseInt(request.getParameter("idVo")));
	}

	@ModelAttribute("fqans")
	public String[] getFqans(RenderRequest request) {
		
		RegistrationModel registrationModel = getRegistrationModelFromRequest(request);

		UserInfo userinfo = userInfoService.findByMail(registrationModel.getEmail());
		int userId = userinfo.getUserId();

		Vo vo = voService.findById(Integer.parseInt(request.getParameter("idVo")));

		UserToVo utv = userToVoService.findById(userId, Integer.parseInt(request.getParameter("idVo")));

		Certificate cert = certificateService
				.findByIdCert(utv.getCertificate());

		String[] fqans = null;

		fqans = VOMSAdminCallOut.getUserFQANs(cert.getSubject(),
				cert.getIssuer(), vo.getHost());
		
		return fqans;
	}
	
	
	
	private RegistrationModel getRegistrationModelFromRequest(
			RenderRequest request) {
		RegistrationModel result = new RegistrationModel();
		
		result.setCertificateStatus(Boolean.parseBoolean(request.getParameter("certificateStatus")));
		result.setCertificateUserId(request.getParameter("certificateUserId"));
		result.setEmail(request.getParameter("email"));
		result.setExpiration(request.getParameter("expiration"));
		result.setFirstName(request.getParameter("firstName"));
		result.setHaveCertificate(Boolean.parseBoolean(request.getParameter("haveCertificate")));
		result.setHaveIDP(Boolean.parseBoolean(request.getParameter("haveIDP")));
		result.setInstitute(request.getParameter("institute"));
		result.setIssuer(request.getParameter("issuer"));
		result.setLastName(request.getParameter("lastName"));
		result.setMail(request.getParameter("mail"));
		result.setSearchVo(request.getParameter("searchVo"));
		result.setSubject(request.getParameter("subject"));
		result.setUserStatus(Boolean.parseBoolean(request.getParameter("userStatus")));
		result.setVerifyUser(Boolean.parseBoolean(request.getParameter("verifyUser")));
		result.setVos(request.getParameter("vos"));
		result.setVoStatus(Boolean.parseBoolean(request.getParameter("voStatus")));
		
		return result;
	}

}
