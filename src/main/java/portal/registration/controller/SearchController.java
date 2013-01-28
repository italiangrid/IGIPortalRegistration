package portal.registration.controller;

import it.italiangrid.portal.dbapi.domain.Notify;
import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.dbapi.services.NotifyService;
import it.italiangrid.portal.dbapi.services.UserInfoService;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

import portal.registration.utils.GuseNotify;
import portal.registration.utils.GuseNotifyUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

@Controller("searchController")
@RequestMapping(value = "VIEW")
public class SearchController {
	
	private static final Logger log = Logger
			.getLogger(SearchController.class);

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private NotifyService notifyService;

	@ActionMapping(params = "myaction=searchVo")
	public void searchVo(ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus) {

		VOListController.setSearch(request.getParameter("key"));

		String userId = request.getParameter("userId");
		String waif = request.getParameter("waif");


		response.setRenderParameter("myaction", "showVOList");
		response.setRenderParameter("waif", waif);
		response.setRenderParameter("userId", userId);


	}

	@ActionMapping(params = "myaction=searchReset")
	public void searchReset(ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus) {

		VOListController.setSearch("");

		String userId = request.getParameter("userId");
		String waif = request.getParameter("waif");

		response.setRenderParameter("myaction", "showVOList");
		response.setRenderParameter("waif", waif);
		response.setRenderParameter("userId", userId);
	}
	
	/**
	 * Update the user's row of the table notification.
	 * 
	 * @param notify
	 *            : the object that contain the advanced options
	 * @param request
	 *            : the request of the portlet
	 * @param response
	 *            : the response of the portlet
	 * @param sessionStatus
	 *            : the status of the portlet
	 */
	@ActionMapping(params = "myaction=updateGuseNotify")
	public void updateGuseNotify(@ModelAttribute("notification") GuseNotify guseNotify, @ModelAttribute("advOpts") Notify notify, @RequestParam int userId,
			ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus) {
		
		UserInfo userInfo = userInfoService.findById(Integer.valueOf(request.getParameter("userId")));
		userInfoService.save(userInfo);
		String username = userInfo.getUsername();
		long companyId = PortalUtil.getCompanyId(request);
		
		try {
			User user = UserLocalServiceUtil.getUserByScreenName(companyId,
					username);
			
			GuseNotifyUtil guseNotifyUtil = new GuseNotifyUtil();
			
			guseNotify.setWfchgEnab((request.getParameter("wfchgEnab").equals("true")?"1":"0"));
			guseNotify.setEmailEnab((request.getParameter("wfchgEnab").equals("true")?"1":"0"));
			guseNotify.setQuotaEnab((request.getParameter("quotaEnab").equals("true")?"1":"0"));
			
			guseNotifyUtil.writeNotifyXML(user, guseNotify);
			
			
			
			
			Notify n = notifyService.findByUserInfo(userInfo);
			if(n!=null){
				
				log.debug("session id= " + notify.getIdNotify() + " retrived: "
						+ n.getIdNotify());
				n.setProxyExpire(notify.getProxyExpire());
				n.setProxyExpireTime(notify.getProxyExpireTime());
				log.debug("session value= " + notify.getProxyExpire() + " retrived: "
						+ n.getProxyExpire());
				log.error("session value= " + notify.getProxyExpireTime() + " retrived: "
						+ n.getProxyExpireTime());
			}else{
				log.debug("New entry");
				n = new Notify(userInfo, notify.getProxyExpire(), notify.getProxyExpireTime());
				log.debug("session value= " + notify.getProxyExpire() + " retrived: "
						+ n.getProxyExpire());
			}
			notifyService.save(n);
			
		} catch (PortalException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}	
		response.setRenderParameter("myaction", "editUserInfoForm");
		response.setRenderParameter("userId", request.getParameter("userId"));
		
		

	}
	
	@ActionMapping(params = "myaction=uploadComplete")
	public void uploadComplete(ActionResponse response,
			SessionStatus sessionStatus) {
		log.error("passo di qui!!");
		sessionStatus.setComplete();
		response.setRenderParameter("myaction", "home");
		

	}

}
