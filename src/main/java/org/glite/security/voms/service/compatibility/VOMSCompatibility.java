/**
 * VOMSCompatibility.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.glite.security.voms.service.compatibility;

public interface VOMSCompatibility extends java.rmi.Remote {
    public int getMajorVersionNumber() throws java.rmi.RemoteException;
    public int getMinorVersionNumber() throws java.rmi.RemoteException;
    public int getPatchVersionNumber() throws java.rmi.RemoteException;
    public java.lang.String[] getGridmapUsers() throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public java.lang.String[] getGridmapUsers(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
}
