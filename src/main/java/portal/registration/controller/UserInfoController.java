package portal.registration.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;

//import portal.registration.domain.Certificate;
//import portal.registration.domain.Idp;
//import portal.registration.domain.UserInfo;
//import portal.registration.domain.UserToVo;
//import portal.registration.domain.Vo;
//import portal.registration.services.CertificateService;
//import portal.registration.services.IdpService;
//import portal.registration.services.UserInfoService;
//import portal.registration.services.UserToVoService;
import it.italiangrid.portal.dbapi.domain.Certificate;
import it.italiangrid.portal.dbapi.domain.Idp;
import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.dbapi.domain.UserToVo;
import it.italiangrid.portal.dbapi.domain.Vo;
import it.italiangrid.portal.dbapi.services.CertificateService;
import it.italiangrid.portal.dbapi.services.IdpService;
import it.italiangrid.portal.dbapi.services.UserInfoService;
import it.italiangrid.portal.dbapi.services.UserToVoService;

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
	
	@Autowired
	private UserToVoService userToVoService;
	
	@Autowired
	private CertificateService certificateService;
	
	@RenderMapping
	public String showUserInfos(RenderResponse response) {
		return "home";
	}

	@RenderMapping(params = "myaction=home")
	public String showUserInfos2(RenderResponse response) {
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
	
	@ModelAttribute("userId")
	public int getUserId(RenderRequest request) {
		
		User user = (User) request.getAttribute(WebKeys.USER);
		if(user!=null){
			return userInfoService.findByUsername(user.getScreenName()).getUserId();
		}
		return 0;
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
	
	@ModelAttribute("defaultVo")
	public String getDefaultVo(RenderRequest request) {
		
		User user = (User) request.getAttribute(WebKeys.USER);
		if(user!=null){
			int userId = userInfoService.findByUsername(user.getScreenName()).getUserId(); 
			return userToVoService.findDefaultVo(userId);
		}
		return null;
	}

	@ModelAttribute("defaultFqan")
	public String getDefaultFqan(RenderRequest request) {
		User user = (User) request.getAttribute(WebKeys.USER);
		if(user!=null){
			int userId = userInfoService.findByUsername(user.getScreenName()).getUserId();
			return userToVoService.getDefaultFqan(userId);
		}
		return null;
	}

	@ModelAttribute("userInfo")
	public UserInfo getUserInfo(RenderRequest request) {
		User user = (User) request.getAttribute(WebKeys.USER);
		if(user!=null)
			return userInfoService.findByUsername(user.getScreenName());
		return null;
	}

	@ModelAttribute("certList")
	public List<Certificate> getListCert(RenderRequest request) {
		User user = (User) request.getAttribute(WebKeys.USER);
		if(user!=null){
		int userId = userInfoService.findByUsername(user.getScreenName()).getUserId();
			return certificateService.findById(userId);
		}
		return null;
	}

	@ModelAttribute("userToVoList")
	public List<Vo> getUserToVoList(RenderRequest request) {
		User user = (User) request.getAttribute(WebKeys.USER);
		if(user!=null){
			int userId = userInfoService.findByUsername(user.getScreenName()).getUserId();
			return userToVoService.findVoByUserId(userId);
		}
		return null;
	}
	
	/**
	 * Return to the portlet the list of the user's fqans.
	 * @param request: session parameter.
	 * @return the list of the user's fqans.
	 */
	@ModelAttribute("userFqans")
	public Map<Object,Object> getUserFqans(RenderRequest request) {
		
		User user = (User) request.getAttribute(WebKeys.USER);
		if(user!=null){
			UserInfo userInfo = userInfoService.findByUsername(user.getScreenName());
			List<UserToVo> utv = userToVoService.findById(userInfo.getUserId());
			
			Map<Object, Object> x = new Properties();
			
			String toParse = null;
			
			for (Iterator<UserToVo> iterator = utv.iterator(); iterator.hasNext();) {
				UserToVo userToVo = iterator.next();
				toParse = userToVo.getFqans();
				if(toParse != null){
					x.put(userToVo.getId().getIdVo(), toParse);
					
				}else{
					x.put(userToVo.getId().getIdVo(), "No Roles for this VO");
				}
				
			}
			
			return x;
		}
		return null;
	}

}
