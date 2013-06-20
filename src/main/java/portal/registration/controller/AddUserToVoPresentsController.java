package portal.registration.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;

import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.dbapi.domain.UserToVo;
import it.italiangrid.portal.dbapi.domain.Vo;
import it.italiangrid.portal.dbapi.services.UserInfoService;
import it.italiangrid.portal.dbapi.services.UserToVoService;
import it.italiangrid.portal.dbapi.services.VoService;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

@Controller(value = "addUserToVoPresentsController")
@RequestMapping(value = "VIEW")
public class AddUserToVoPresentsController {

	private static final Logger log = Logger
			.getLogger(AddUserToVoPresentsController.class);


	@Autowired
	private VoService voService;

	@Autowired
	private UserToVoService userToVoService;

	@Autowired
	private UserInfoService userInfoService;

	@ModelAttribute("userInfo")
	public UserInfo getCommandObject() {
		return new UserInfo();
	}

	@ModelAttribute("userToVoList")
	public List<Vo> getUserToVoList(@RequestParam int userId) {

		return userToVoService.findVoByUserId(userId);
	}

	@RenderMapping(params = "myaction=showAddUserToVoPresents")
	public String showUserInfoForm(@RequestParam int userId,
			RenderRequest request) {

		if (userToVoService.findById(userId).size() == 0) {
			deactivateUser(userId, request);
			SessionMessages.add(request, "user-deactivate");
		}
		return "addUserToVoPresents";
	}

	private void deactivateUser(int userId, RenderRequest request) {

		UserInfo userInfo = userInfoService.findById(userId);

		if (userInfo.getRegistrationComplete().equals("true")) {
			String username = userInfo.getUsername();

			long companyId = PortalUtil.getCompanyId(request);

			User user;
			try {
				user = UserLocalServiceUtil.getUserByScreenName(companyId,
						username);

				Role rolePowerUser = RoleLocalServiceUtil.getRole(companyId, "Power User");

				UserLocalServiceUtil.deleteRoleUser(rolePowerUser.getRoleId(),
						user.getUserId());

				userInfo.setRegistrationComplete("false");

				userInfoService.edit(userInfo);

			} catch (PortalException e) {
				PortletConfig portletConfig = (PortletConfig)request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
				SessionMessages.add(request, portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
				SessionErrors.add(request, "exception-deactivation-user");
				e.printStackTrace();
			} catch (SystemException e) {
				PortletConfig portletConfig = (PortletConfig)request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
				SessionMessages.add(request, portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
				SessionErrors.add(request, "exception-deactivation-user");
				e.printStackTrace();
			}
		}

	}

	@ActionMapping(params = "myaction=goToAddUserToVOForm")
	public void goToAddUserToVOForm(ActionRequest request,
			ActionResponse response) {
		int userId = 0;
		if(request.getParameter("userId")==null){
			log.info("siamo rovinati");
			User user = (User) request.getAttribute(WebKeys.USER);
			if(user!=null)
				userId = userInfoService.findByUsername(user.getScreenName()).getUserId();
		}else{
			userId = Integer.parseInt(request.getParameter("userId"));
		}

		log.info("UserID = " + request.getParameter("userId"));

		response.setRenderParameter("myaction", "showAddUserToVO");
		response.setRenderParameter("userId", Integer.toString(userId));
		response.setRenderParameter("firstReg", request.getParameter("firstReg"));


	}

	@ModelAttribute("vos")
	public List<Vo> getVos() {
		return voService.getAllVo();
	}

	@ActionMapping(params = "myaction=userToVoComplete")
	public void userToVoComplete(ActionResponse response) {

		response.setRenderParameter("myaction", "userInfos");

	}
	
	/**
	 * Return to the portlet the list of the user's fqans.
	 * @param request: session parameter.
	 * @return the list of the user's fqans.
	 */
	@ModelAttribute("userFqansPresent")
	public Map<Object,Object> getUserFqans(@RequestParam int userId) {
		
		UserInfo userInfo = userInfoService.findById(userId);
		List<UserToVo> utv = userToVoService.findById(userInfo.getUserId());
		
		Map<Object, Object> x = new Properties();
		
		String toParse = null;
		
		for (Iterator<UserToVo> iterator = utv.iterator(); iterator.hasNext();) {
			UserToVo userToVo = iterator.next();
			toParse = userToVo.getFqans();
			if((toParse != null)&&(!toParse.equals(""))){
				x.put(userToVo.getId().getIdVo(), toParse);
				
			}else{
				x.put(userToVo.getId().getIdVo(), "No Roles for this VO");
			}
			
		}
		
		return x;
	}

}
