/**
 * VOMSRegistration.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.glite.security.voms.service.registration;

public interface VOMSRegistration extends java.rmi.Remote {
    public void submitRegistrationRequest(org.glite.security.voms.service.registration.RegistrationRequest in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void submitRegistrationRequestForUser(java.lang.String in0, java.lang.String in1, org.glite.security.voms.service.registration.RegistrationRequest in2) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
}
