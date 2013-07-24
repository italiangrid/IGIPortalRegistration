package it.italiangrid.portal.diracregistration.dirac;

/**
 * The class that contains the user's information for the task.
 * 
 * @author dmichelotto
 * 
 */
public class DiracTask {

	/**
	 * User's certificate path.
	 */
	private String userCert;

	/**
	 * User's private key path.
	 */
	private String userKey;

	/**
	 * Private key password.
	 */
	private String password;

	/**
	 * User's e-mail.
	 */
	private String email;

	/**
	 * User's DN.
	 */
	private String dn;

	/**
	 * User's username.
	 */
	private String username;

	/**
	 * Constructor.
	 * 
	 * @param userCert
	 *            - User's certificate path.
	 * @param userKey
	 *            - User's private key path.
	 * @param password
	 *            - Private key password.
	 * @param email
	 *            - User's e-mail.
	 * @param dn
	 *            - User's DN.
	 * @param username
	 *            - User's username.
	 */
	public DiracTask(String userCert, String userKey, String password,
			String email, String dn, String username) {
		super();
		this.userCert = userCert;
		this.userKey = userKey;
		this.password = password;
		this.email = email;
		this.dn = dn;
		this.username = username;
	}

	/**
	 * Default constructor.
	 */
	public DiracTask() {

	}

	/**
	 * Getter method.
	 * 
	 * @return the userCert
	 */
	public String getUserCert() {
		return userCert;
	}

	/**
	 * Setter method.
	 * 
	 * @param userCert
	 *            the userCert to set
	 */
	public void setUserCert(String userCert) {
		this.userCert = userCert;
	}

	/**
	 * Getter method.
	 * 
	 * @return the userKey
	 */
	public String getUserKey() {
		return userKey;
	}

	/**
	 * Setter method.
	 * 
	 * @param userKey
	 *            the userKey to set
	 */
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	/**
	 * Getter method.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter method.
	 * 
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Getter method.
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter method.
	 * 
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Getter method.
	 * 
	 * @return the dn
	 */
	public String getDn() {
		return dn;
	}

	/**
	 * Setter method.
	 * 
	 * @param dn
	 *            the dn to set
	 */
	public void setDn(String dn) {
		this.dn = dn;
	}

	/**
	 ** Getter method.
	 * 
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Setter method.
	 * 
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DiracTask [userCert=" + userCert + ", userKey=" + userKey
				+ ", password=" + password + ", email=" + email + ", dn=" + dn
				+ ", username=" + username + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dn == null) ? 0 : dn.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((userCert == null) ? 0 : userCert.hashCode());
		result = prime * result + ((userKey == null) ? 0 : userKey.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DiracTask other = (DiracTask) obj;
		if (dn == null) {
			if (other.dn != null)
				return false;
		} else if (!dn.equals(other.dn))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userCert == null) {
			if (other.userCert != null)
				return false;
		} else if (!userCert.equals(other.userCert))
			return false;
		if (userKey == null) {
			if (other.userKey != null)
				return false;
		} else if (!userKey.equals(other.userKey))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
