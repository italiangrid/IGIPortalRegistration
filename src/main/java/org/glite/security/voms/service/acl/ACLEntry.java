/**
 * ACLEntry.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.glite.security.voms.service.acl;
@SuppressWarnings({"serial","unused"})
public class ACLEntry  implements java.io.Serializable {
    private java.lang.String adminIssuer;

    private java.lang.String adminSubject;

    private int vomsPermissionBits;

    public ACLEntry() {
    }

    public ACLEntry(
           java.lang.String adminIssuer,
           java.lang.String adminSubject,
           int vomsPermissionBits) {
           this.adminIssuer = adminIssuer;
           this.adminSubject = adminSubject;
           this.vomsPermissionBits = vomsPermissionBits;
    }


    /**
     * Gets the adminIssuer value for this ACLEntry.
     * 
     * @return adminIssuer
     */
    public java.lang.String getAdminIssuer() {
        return adminIssuer;
    }


    /**
     * Sets the adminIssuer value for this ACLEntry.
     * 
     * @param adminIssuer
     */
    public void setAdminIssuer(java.lang.String adminIssuer) {
        this.adminIssuer = adminIssuer;
    }


    /**
     * Gets the adminSubject value for this ACLEntry.
     * 
     * @return adminSubject
     */
    public java.lang.String getAdminSubject() {
        return adminSubject;
    }


    /**
     * Sets the adminSubject value for this ACLEntry.
     * 
     * @param adminSubject
     */
    public void setAdminSubject(java.lang.String adminSubject) {
        this.adminSubject = adminSubject;
    }


    /**
     * Gets the vomsPermissionBits value for this ACLEntry.
     * 
     * @return vomsPermissionBits
     */
    public int getVomsPermissionBits() {
        return vomsPermissionBits;
    }


    /**
     * Sets the vomsPermissionBits value for this ACLEntry.
     * 
     * @param vomsPermissionBits
     */
    public void setVomsPermissionBits(int vomsPermissionBits) {
        this.vomsPermissionBits = vomsPermissionBits;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ACLEntry)) return false;
        ACLEntry other = (ACLEntry) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.adminIssuer==null && other.getAdminIssuer()==null) || 
             (this.adminIssuer!=null &&
              this.adminIssuer.equals(other.getAdminIssuer()))) &&
            ((this.adminSubject==null && other.getAdminSubject()==null) || 
             (this.adminSubject!=null &&
              this.adminSubject.equals(other.getAdminSubject()))) &&
            this.vomsPermissionBits == other.getVomsPermissionBits();
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
        if (getAdminIssuer() != null) {
            _hashCode += getAdminIssuer().hashCode();
        }
        if (getAdminSubject() != null) {
            _hashCode += getAdminSubject().hashCode();
        }
        _hashCode += getVomsPermissionBits();
        __hashCodeCalc = false;
        return _hashCode;
    }

}
