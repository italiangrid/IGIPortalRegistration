/**
 * VOMSACL.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.glite.security.voms.service.acl;

public interface VOMSACL extends java.rmi.Remote {
    public org.glite.security.voms.service.acl.ACLEntry[] getACL(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void setACL(java.lang.String in0, org.glite.security.voms.service.acl.ACLEntry[] in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void addACLEntry(java.lang.String in0, org.glite.security.voms.service.acl.ACLEntry in1, boolean in2) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void removeACLEntry(java.lang.String in0, org.glite.security.voms.service.acl.ACLEntry in1, boolean in2) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public org.glite.security.voms.service.acl.ACLEntry[] getDefaultACL(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void setDefaultACL(java.lang.String in0, org.glite.security.voms.service.acl.ACLEntry[] in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void addDefaultACLEntry(java.lang.String in0, org.glite.security.voms.service.acl.ACLEntry in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void removeDefaultACLEntry(java.lang.String in0, org.glite.security.voms.service.acl.ACLEntry in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
}
