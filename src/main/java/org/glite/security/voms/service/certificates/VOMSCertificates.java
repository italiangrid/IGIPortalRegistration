/**
 * VOMSCertificates.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.glite.security.voms.service.certificates;

public interface VOMSCertificates extends java.rmi.Remote {
    public org.glite.security.voms.service.certificates.X509Certificate[] getCertificates(long in0) throws java.rmi.RemoteException;
    public org.glite.security.voms.service.certificates.X509Certificate[] getCertificates(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException;
    public long getUserIdFromDn(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException;
    public void addCertificate(long in0, org.glite.security.voms.service.certificates.X509Certificate in1) throws java.rmi.RemoteException;
    public void addCertificate(java.lang.String in0, java.lang.String in1, org.glite.security.voms.service.certificates.X509Certificate in2) throws java.rmi.RemoteException;
    public void suspendCertificate(org.glite.security.voms.service.certificates.X509Certificate in0, java.lang.String in1) throws java.rmi.RemoteException;
    public void restoreCertificate(org.glite.security.voms.service.certificates.X509Certificate in0) throws java.rmi.RemoteException;
    public void removeCertificate(org.glite.security.voms.service.certificates.X509Certificate in0) throws java.rmi.RemoteException;
}
