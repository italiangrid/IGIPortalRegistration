package it.italiangrid.portal.registration.model;

import java.util.ArrayList;
import java.util.List;

public class RegistrationModel {
	
	private boolean haveCertificate;
	private String issuer;
	private String subject;
	private String certificateUserId;
	private String vos;
	private String searchVo = null;

	

	/**
	 * 
	 */
	public RegistrationModel() {
		this.haveCertificate = false;
		this.issuer = "";
		this.subject = "";
		this.certificateUserId = "";
		this.vos = "";
	}

	

	/**
	 * @param haveCertificate
	 * @param issuer
	 * @param subject
	 * @param certificateUserId
	 * @param vos
	 */
	public RegistrationModel(boolean haveCertificate, String issuer,
			String subject, String certificateUserId, String vos) {
		
		this.haveCertificate = haveCertificate;
		this.issuer = issuer;
		this.subject = subject;
		this.certificateUserId = certificateUserId;
		this.vos = vos;
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
				+ ", issuer=" + issuer + ", subject=" + subject
				+ ", certificateUserId=" + certificateUserId + ", vos=" + vos
				+ ", searchVo=" + searchVo + "]";
	}
	
}
