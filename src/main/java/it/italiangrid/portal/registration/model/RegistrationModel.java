package it.italiangrid.portal.registration.model;

public class RegistrationModel {
	
	private boolean haveCertificate;
	private boolean haveIDP;
	private boolean userStatus;
	private boolean certificateStatus;
	private boolean voStatus;
	private String firstName;
	private String lastName;
	private String institute;
	private String email;
	private String issuer;
	private String subject;
	private String expiration;
	private String certificateUserId;
	private String vos;
	private String searchVo = null;
	private String mail;

	/**
	 * 
	 */
	public RegistrationModel() {
		this.haveCertificate = false;
		this.haveIDP = false;
		this.userStatus = false;
		this.certificateStatus = false;
		this.voStatus = false;
		this.firstName = "";
		this.lastName = "";
		this.institute = "";
		this.email = "";
		this.issuer = "";
		this.subject = "";
		this.expiration = "";
		this.certificateUserId = "";
		this.vos = "";
		this.mail = "";
	}
	
	/**
	 * @param haveCertificate
	 * @param haveIDP
	 * @param userStatus
	 * @param certificateStatus
	 * @param voStatus
	 * @param firstName
	 * @param lastName
	 * @param institute
	 * @param email
	 * @param issuer
	 * @param subject
	 * @param expiration
	 * @param certificateUserId
	 * @param vos
	 * @param searchVo
	 * @param mail
	 */
	public RegistrationModel(boolean haveCertificate, boolean haveIDP,
			boolean userStatus, boolean certificateStatus, boolean voStatus,
			String firstName, String lastName, String institute, String email,
			String issuer, String subject, String expiration,
			String certificateUserId, String vos, String searchVo, String mail) {
		super();
		this.haveCertificate = haveCertificate;
		this.haveIDP = haveIDP;
		this.userStatus = userStatus;
		this.certificateStatus = certificateStatus;
		this.voStatus = voStatus;
		this.firstName = firstName;
		this.lastName = lastName;
		this.institute = institute;
		this.email = email;
		this.issuer = issuer;
		this.subject = subject;
		this.expiration = expiration;
		this.certificateUserId = certificateUserId;
		this.vos = vos;
		this.searchVo = searchVo;
		this.mail = mail;
	}
	
	public boolean isUserStatus() {
		return userStatus;
	}

	public void setUserStatus(boolean userStatus) {
		this.userStatus = userStatus;
	}

	public boolean isCertificateStatus() {
		return certificateStatus;
	}

	public void setCertificateStatus(boolean certificateStatus) {
		this.certificateStatus = certificateStatus;
	}

	public boolean isVoStatus() {
		return voStatus;
	}

	public void setVoStatus(boolean voStatus) {
		this.voStatus = voStatus;
	}
	
	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getInstitute() {
		return institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
				+ ", haveIDP=" + haveIDP + ", userStatus=" + userStatus
				+ ", certificateStatus=" + certificateStatus + ", voStatus="
				+ voStatus + ", firstName=" + firstName + ", lastName="
				+ lastName + ", institute=" + institute + ", email=" + email
				+ ", issuer=" + issuer + ", subject=" + subject
				+ ", expiration=" + expiration + ", certificateUserId="
				+ certificateUserId + ", vos=" + vos + ", searchVo=" + searchVo
				+ ", mail=" + mail + "]";
	}

	

	
	
}
