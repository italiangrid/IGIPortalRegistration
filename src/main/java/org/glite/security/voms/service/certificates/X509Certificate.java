/**
 * X509Certificate.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.glite.security.voms.service.certificates;

@SuppressWarnings("serial")
public class X509Certificate  implements java.io.Serializable {
    private byte[] bytes;

    private long id;

    private java.lang.String issuer;

    private java.lang.String notAfter;

    private java.lang.String subject;

    public X509Certificate() {
    }

    public X509Certificate(
           byte[] bytes,
           long id,
           java.lang.String issuer,
           java.lang.String notAfter,
           java.lang.String subject) {
           this.bytes = bytes;
           this.id = id;
           this.issuer = issuer;
           this.notAfter = notAfter;
           this.subject = subject;
    }


    /**
     * Gets the bytes value for this X509Certificate.
     * 
     * @return bytes
     */
    public byte[] getBytes() {
        return bytes;
    }


    /**
     * Sets the bytes value for this X509Certificate.
     * 
     * @param bytes
     */
    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }


    /**
     * Gets the id value for this X509Certificate.
     * 
     * @return id
     */
    public long getId() {
        return id;
    }


    /**
     * Sets the id value for this X509Certificate.
     * 
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }


    /**
     * Gets the issuer value for this X509Certificate.
     * 
     * @return issuer
     */
    public java.lang.String getIssuer() {
        return issuer;
    }


    /**
     * Sets the issuer value for this X509Certificate.
     * 
     * @param issuer
     */
    public void setIssuer(java.lang.String issuer) {
        this.issuer = issuer;
    }


    /**
     * Gets the notAfter value for this X509Certificate.
     * 
     * @return notAfter
     */
    public java.lang.String getNotAfter() {
        return notAfter;
    }


    /**
     * Sets the notAfter value for this X509Certificate.
     * 
     * @param notAfter
     */
    public void setNotAfter(java.lang.String notAfter) {
        this.notAfter = notAfter;
    }


    /**
     * Gets the subject value for this X509Certificate.
     * 
     * @return subject
     */
    public java.lang.String getSubject() {
        return subject;
    }


    /**
     * Sets the subject value for this X509Certificate.
     * 
     * @param subject
     */
    public void setSubject(java.lang.String subject) {
        this.subject = subject;
    }

    private java.lang.Object __equalsCalc = null;
    @SuppressWarnings("unused")
	public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof X509Certificate)) return false;
        X509Certificate other = (X509Certificate) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.bytes==null && other.getBytes()==null) || 
             (this.bytes!=null &&
              java.util.Arrays.equals(this.bytes, other.getBytes()))) &&
            this.id == other.getId() &&
            ((this.issuer==null && other.getIssuer()==null) || 
             (this.issuer!=null &&
              this.issuer.equals(other.getIssuer()))) &&
            ((this.notAfter==null && other.getNotAfter()==null) || 
             (this.notAfter!=null &&
              this.notAfter.equals(other.getNotAfter()))) &&
            ((this.subject==null && other.getSubject()==null) || 
             (this.subject!=null &&
              this.subject.equals(other.getSubject())));
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
        if (getBytes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getBytes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getBytes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += new Long(getId()).hashCode();
        if (getIssuer() != null) {
            _hashCode += getIssuer().hashCode();
        }
        if (getNotAfter() != null) {
            _hashCode += getNotAfter().hashCode();
        }
        if (getSubject() != null) {
            _hashCode += getSubject().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
