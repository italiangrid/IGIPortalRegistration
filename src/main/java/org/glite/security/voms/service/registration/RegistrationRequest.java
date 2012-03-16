/**
 * RegistrationRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.glite.security.voms.service.registration;

@SuppressWarnings("serial")
public class RegistrationRequest  implements java.io.Serializable {
    private boolean aupAccepted;

    private java.lang.String comments;

    private java.lang.String emailAddress;

    private java.lang.String institute;

    private java.lang.String phoneNumber;

    public RegistrationRequest() {
    }

    public RegistrationRequest(
           boolean aupAccepted,
           java.lang.String comments,
           java.lang.String emailAddress,
           java.lang.String institute,
           java.lang.String phoneNumber) {
           this.aupAccepted = aupAccepted;
           this.comments = comments;
           this.emailAddress = emailAddress;
           this.institute = institute;
           this.phoneNumber = phoneNumber;
    }


    /**
     * Gets the aupAccepted value for this RegistrationRequest.
     * 
     * @return aupAccepted
     */
    public boolean isAupAccepted() {
        return aupAccepted;
    }


    /**
     * Sets the aupAccepted value for this RegistrationRequest.
     * 
     * @param aupAccepted
     */
    public void setAupAccepted(boolean aupAccepted) {
        this.aupAccepted = aupAccepted;
    }


    /**
     * Gets the comments value for this RegistrationRequest.
     * 
     * @return comments
     */
    public java.lang.String getComments() {
        return comments;
    }


    /**
     * Sets the comments value for this RegistrationRequest.
     * 
     * @param comments
     */
    public void setComments(java.lang.String comments) {
        this.comments = comments;
    }


    /**
     * Gets the emailAddress value for this RegistrationRequest.
     * 
     * @return emailAddress
     */
    public java.lang.String getEmailAddress() {
        return emailAddress;
    }


    /**
     * Sets the emailAddress value for this RegistrationRequest.
     * 
     * @param emailAddress
     */
    public void setEmailAddress(java.lang.String emailAddress) {
        this.emailAddress = emailAddress;
    }


    /**
     * Gets the institute value for this RegistrationRequest.
     * 
     * @return institute
     */
    public java.lang.String getInstitute() {
        return institute;
    }


    /**
     * Sets the institute value for this RegistrationRequest.
     * 
     * @param institute
     */
    public void setInstitute(java.lang.String institute) {
        this.institute = institute;
    }


    /**
     * Gets the phoneNumber value for this RegistrationRequest.
     * 
     * @return phoneNumber
     */
    public java.lang.String getPhoneNumber() {
        return phoneNumber;
    }


    /**
     * Sets the phoneNumber value for this RegistrationRequest.
     * 
     * @param phoneNumber
     */
    public void setPhoneNumber(java.lang.String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private java.lang.Object __equalsCalc = null;
    @SuppressWarnings("unused")
	public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RegistrationRequest)) return false;
        RegistrationRequest other = (RegistrationRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.aupAccepted == other.isAupAccepted() &&
            ((this.comments==null && other.getComments()==null) || 
             (this.comments!=null &&
              this.comments.equals(other.getComments()))) &&
            ((this.emailAddress==null && other.getEmailAddress()==null) || 
             (this.emailAddress!=null &&
              this.emailAddress.equals(other.getEmailAddress()))) &&
            ((this.institute==null && other.getInstitute()==null) || 
             (this.institute!=null &&
              this.institute.equals(other.getInstitute()))) &&
            ((this.phoneNumber==null && other.getPhoneNumber()==null) || 
             (this.phoneNumber!=null &&
              this.phoneNumber.equals(other.getPhoneNumber())));
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
        _hashCode += (isAupAccepted() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getComments() != null) {
            _hashCode += getComments().hashCode();
        }
        if (getEmailAddress() != null) {
            _hashCode += getEmailAddress().hashCode();
        }
        if (getInstitute() != null) {
            _hashCode += getInstitute().hashCode();
        }
        if (getPhoneNumber() != null) {
            _hashCode += getPhoneNumber().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
