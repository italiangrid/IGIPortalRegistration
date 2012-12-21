package it.italiangrid.portal.registration.model;

public class RegistrationModel {
	
	private boolean haveCertificate;
	private boolean haveIDP;
	private String issuer;
	private String subject;
	private String certificateUserId;
	private String vos;
	private String searchVo = null;
	private String mail;

	/**
	 * 
	 */
	public RegistrationModel() {
		this.haveCertificate = false;
		this.haveIDP = true;
		this.issuer = "";
		this.subject = "";
		this.certificateUserId = "";
		this.vos = "";
		this.mail = "";
	}
	
	/**
	 * @param haveCertificate
	 * @param haveIDP
	 * @param issuer
	 * @param subject
	 * @param certificateUserId
	 * @param vos
	 * @param searchVo
	 * @param mail
	 */
	public RegistrationModel(boolean haveCertificate, boolean haveIDP, String issuer,
			String subject, String certificateUserId, String vos,
			String searchVo, String mail) {
		super();
		this.haveCertificate = haveCertificate;
		this.haveIDP = haveIDP;
		this.issuer = issuer;
		this.subject = subject;
		this.certificateUserId = certificateUserId;
		this.vos = vos;
		this.searchVo = searchVo;
		this.mail = mail;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}

	public boolean isHaveIDP() {
		return haveIDP;
	}

	public void setHaveIDP(boolean haveIDP) {
		this.haveIDP = haveIDP;
	}

	public boolean isHaveCertificate() {
		return haveCertificate;
	}

	public void setHaveCertificate(boolean haveCertificate) {
		this.haveCertificate = haveCertificate;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCertificateUserId() {
		return certificateUserId;
	}

	public void setCertificateUserId(String certificateUserId) {
		this.certificateUserId = certificateUserId;
	}

	public String getVos() {
		return vos;
	}

	public void setVos(String vos) {
		this.vos = vos;
	}

	public String getSearchVo() {
		return searchVo;
	}

	public void setSearchVo(String searchVo) {
		this.searchVo = searchVo;
	}
	
	@Override
	public String toString() {
		return "RegistrationModel [haveCertificate=" + haveCertificate
				+ ", haveIDP=" + haveIDP + ", issuer=" + issuer + ", subject="
				+ subject + ", certificateUserId=" + certificateUserId
				+ ", vos=" + vos + ", searchVo=" + searchVo + ", mail=" + mail
				+ "]";
	}
	
}
