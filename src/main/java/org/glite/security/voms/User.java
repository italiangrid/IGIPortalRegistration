/**
 * User.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.glite.security.voms;

public class User  implements java.io.Serializable {
    private java.lang.String CA;

    private java.lang.String CN;

    private java.lang.String DN;

    private java.lang.String certUri;

    private java.lang.String mail;

    public User() {
    }

    public User(
           java.lang.String CA,
           java.lang.String CN,
           java.lang.String DN,
           java.lang.String certUri,
           java.lang.String mail) {
           this.CA = CA;
           this.CN = CN;
           this.DN = DN;
           this.certUri = certUri;
           this.mail = mail;
    }


    /**
     * Gets the CA value for this User.
     * 
     * @return CA
     */
    public java.lang.String getCA() {
        return CA;
    }


    /**
     * Sets the CA value for this User.
     * 
     * @param CA
     */
    public void setCA(java.lang.String CA) {
        this.CA = CA;
    }


    /**
     * Gets the CN value for this User.
     * 
     * @return CN
     */
    public java.lang.String getCN() {
        return CN;
    }


    /**
     * Sets the CN value for this User.
     * 
     * @param CN
     */
    public void setCN(java.lang.String CN) {
        this.CN = CN;
    }


    /**
     * Gets the DN value for this User.
     * 
     * @return DN
     */
    public java.lang.String getDN() {
        return DN;
    }


    /**
     * Sets the DN value for this User.
     * 
     * @param DN
     */
    public void setDN(java.lang.String DN) {
        this.DN = DN;
    }


    /**
     * Gets the certUri value for this User.
     * 
     * @return certUri
     */
    public java.lang.String getCertUri() {
        return certUri;
    }


    /**
     * Sets the certUri value for this User.
     * 
     * @param certUri
     */
    public void setCertUri(java.lang.String certUri) {
        this.certUri = certUri;
    }


    /**
     * Gets the mail value for this User.
     * 
     * @return mail
     */
    public java.lang.String getMail() {
        return mail;
    }


    /**
     * Sets the mail value for this User.
     * 
     * @param mail
     */
    public void setMail(java.lang.String mail) {
        this.mail = mail;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof User)) return false;
        User other = (User) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CA==null && other.getCA()==null) || 
             (this.CA!=null &&
              this.CA.equals(other.getCA()))) &&
            ((this.CN==null && other.getCN()==null) || 
             (this.CN!=null &&
              this.CN.equals(other.getCN()))) &&
            ((this.DN==null && other.getDN()==null) || 
             (this.DN!=null &&
              this.DN.equals(other.getDN()))) &&
            ((this.certUri==null && other.getCertUri()==null) || 
             (this.certUri!=null &&
              this.certUri.equals(other.getCertUri()))) &&
            ((this.mail==null && other.getMail()==null) || 
             (this.mail!=null &&
              this.mail.equals(other.getMail())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getCA() != null) {
            _hashCode += getCA().hashCode();
        }
        if (getCN() != null) {
            _hashCode += getCN().hashCode();
        }
        if (getDN() != null) {
            _hashCode += getDN().hashCode();
        }
        if (getCertUri() != null) {
            _hashCode += getCertUri().hashCode();
        }
        if (getMail() != null) {
            _hashCode += getMail().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
