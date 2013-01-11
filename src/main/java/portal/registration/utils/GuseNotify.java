package portal.registration.utils;

public class GuseNotify {

	private String wfchgMess;
	private String wfchgEnab;
	private String quotaMess;
	private String quotaEnab;
	private String emailAddr;
	private String emailEnab;
	private String emailSubj;
	
	public GuseNotify() {
		this.wfchgMess = "Dear user,\n\n[EXAMPLE] The istance #instance# of the workflow #workflow# change status from #oldstatus# to #newstatus# at #now#.  [EXAMPLE] \n\n[ANSYS EXAMPLE]#instance#.[ANSYS EXAMPLE]\n\nRegards.";
		this.wfchgEnab = "false";
		this.quotaMess = "";
		this.quotaEnab = "false";
		this.emailAddr = "";
		this.emailEnab = "false";
		this.emailSubj = "Insert your subject here";
	}
	
	public GuseNotify(String wfchgMess, String wfchgEnab, String quotaMess,
			String quotaEnab, String emailAddr, String emailEnab,
			String emailSubj) {
		this.wfchgMess = wfchgMess;
		this.wfchgEnab = wfchgEnab;
		this.quotaMess = quotaMess;
		this.quotaEnab = quotaEnab;
		this.emailAddr = emailAddr;
		this.emailEnab = emailEnab;
		this.emailSubj = emailSubj;
	}

	public String getWfchgMess() {
		return wfchgMess;
	}

	public void setWfchgMess(String wfchgMess) {
		this.wfchgMess = wfchgMess;
	}

	public String getWfchgEnab() {
		return wfchgEnab;
	}

	public void setWfchgEnab(String wfchgEnab) {
		if(wfchgEnab.equals("1"))
			this.wfchgEnab = "true";
		else
			this.wfchgEnab = "false";
	}

	public String getQuotaMess() {
		return quotaMess;
	}

	public void setQuotaMess(String quotaMess) {
		this.quotaMess = quotaMess;
	}

	public String getQuotaEnab() {
		return quotaEnab;
	}

	public void setQuotaEnab(String quotaEnab) {
		if(quotaEnab.equals("1"))
			this.quotaEnab = "true";
		else
			this.quotaEnab = "false";
	}

	public String getEmailAddr() {
		return emailAddr;
	}

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	public String getEmailEnab() {
		return emailEnab;
	}

	public void setEmailEnab(String emailEnab) {
		if(emailEnab.equals("1"))
			this.emailEnab = "true";
		else
			this.emailEnab = "false";
	}

	public String getEmailSubj() {
		return emailSubj;
	}

	public void setEmailSubj(String emailSubj) {
		this.emailSubj = emailSubj;
	}

}
