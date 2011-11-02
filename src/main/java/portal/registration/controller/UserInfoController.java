package portal.registration.controller;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.portlet.RenderResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import portal.registration.domain.Idp;
import portal.registration.domain.UserInfo;
import portal.registration.services.IdpService;
import portal.registration.services.UserInfoService;

@Controller("userInfoController")
@RequestMapping(value = "VIEW")
public class UserInfoController {
	
	private static String search = null;
	
	public static void setSearch(String search2){
		search = search2;
	}

	@ModelAttribute("search")
	public String getSearch() {
		
		return search;
		
		
	}

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private IdpService idpService;

	@RenderMapping
	public String showUserInfos(RenderResponse response) {
		return "home";
	}

	@ModelAttribute("userInfos")
	public List<UserInfo> getUserInfos() {
		
		
		if(search==null||search.equals(""))
			return userInfoService.getAllUserInfo();
		else
			return userInfoService.getAllUserInfoByName(search);
	}

	@ModelAttribute("idps")
	public List<Idp> getIdps() {
		return idpService.getAllIdp();
	}

	@ModelAttribute("idpsName")
	public Map<Object, Object> getIdpsName() {

		Map<Object, Object> x = new Properties();

		List<UserInfo> users = userInfoService.getAllUserInfo();

		for (int i = 0; i < users.size(); i++) {

			x.put(users.get(i).getUserId(),
					idpService.findByIdp(users.get(i).getIdp()));
		}
		return x;
	}

}
