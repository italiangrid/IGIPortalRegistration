package it.italiangrid.portal.registration.controller;

import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.dbapi.domain.UserToVo;
import it.italiangrid.portal.dbapi.domain.Vo;
import it.italiangrid.portal.dbapi.services.UserInfoService;
import it.italiangrid.portal.dbapi.services.UserToVoService;
import it.italiangrid.portal.dbapi.services.VoService;
import it.italiangrid.portal.registration.model.RegistrationModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

@Controller(value = "addVOController")
@RequestMapping(value = "VIEW")
public class AddVOController {
	
	@Autowired
	private VoService voService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private UserToVoService userToVoService;
	
	private static final Logger log = Logger.getLogger(AddVOController.class);
	
	@RenderMapping(params = "myaction=showAddVoForm")
	public String showAskForCertificate() {
		log.debug("Show addVoForm.jsp");
		return "addVoForm";
	}
	
	@ModelAttribute("selectedVos")
	public List<Vo> getSelectedVo(RenderRequest request, RenderResponse response){
 
		RegistrationModel registrationModel = (RegistrationModel) request.getAttribute("registrationModel");
		
		List<Vo> result = new ArrayList<Vo>();
		if(!registrationModel.getVos().isEmpty())
			for(String id : registrationModel.getVos().split("#"))
				result.add(voService.findById(Integer.parseInt(id)));
		
		request.setAttribute("registrationModel", registrationModel);
		
		return result;
	}


	@ModelAttribute("vos")
	public List<Vo> getSerchedVo(RenderRequest request){
		
		List<Vo> result = new ArrayList<Vo>();
		RegistrationModel registrationModel = (RegistrationModel) request.getAttribute("registrationModel");
		
		
		if(registrationModel.getSearchVo()!=null){
			result = voService.getAllVoByName(registrationModel.getSearchVo());
		}
		request.setAttribute("registrationModel", registrationModel);
		return result;
	}
	
	@ModelAttribute("voList")
	public String getVoList() {
		
		List<Vo> vos = voService.getAllVo();
		
		String result="";
		
		for(int i=0; i<vos.size()-1; i++){
			result += "\""+vos.get(i).getVo()+"\", ";
		}
		result += "\""+vos.get(vos.size()-1).getVo()+"\"";
		return result;
	}
	
	@ModelAttribute("defaultVo")
	public String getDefaultVo(RenderRequest request) {
		RegistrationModel registrationModel = (RegistrationModel) request.getAttribute("registrationModel");
		UserInfo userInfo = userInfoService.findByMail(registrationModel.getEmail());
		return userToVoService.findDefaultVo(userInfo.getUserId());
	}
	
	/**
	 * Return to the portlet the list of the user's fqans.
	 * 
	 * @param userId
	 *            : the identifier of the user.
	 * @return the list of the user's fqans.
	 */
	@ModelAttribute("userFqans")
	public Map<Object, Object> getUserFqans(RenderRequest request) {

		RegistrationModel registrationModel = (RegistrationModel) request.getAttribute("registrationModel");
		
		UserInfo userInfo = userInfoService.findByMail(registrationModel.getEmail());
		List<UserToVo> utv = userToVoService.findById(userInfo.getUserId());

		Map<Object, Object> x = new Properties();

		String toParse = null;

		for (Iterator<UserToVo> iterator = utv.iterator(); iterator.hasNext();) {
			UserToVo userToVo = iterator.next();
			toParse = userToVo.getFqans();

			if ((toParse != null) && (!toParse.equals(""))) {

				x.put(userToVo.getId().getIdVo(), toParse);

			} else {
				x.put(userToVo.getId().getIdVo(), "No Roles for this VO");
			}

		}

		return x;
	}

}
