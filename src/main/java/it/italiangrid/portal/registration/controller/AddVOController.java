package it.italiangrid.portal.registration.controller;

import it.italiangrid.portal.dbapi.domain.Vo;
import it.italiangrid.portal.dbapi.services.VoService;
import it.italiangrid.portal.registration.model.RegistrationModel;
import java.util.ArrayList;
import java.util.List;

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

}
