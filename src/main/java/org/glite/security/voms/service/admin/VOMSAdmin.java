/**
 * VOMSAdmin.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.glite.security.voms.service.admin;

public interface VOMSAdmin extends java.rmi.Remote {
    public void createGroup(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public org.glite.security.voms.User getUser(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void setUser(org.glite.security.voms.User in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public int getMajorVersionNumber() throws java.rmi.RemoteException;
    public java.lang.String getVOName() throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void createUser(org.glite.security.voms.User in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void deleteUser(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void deleteGroup(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void createRole(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void deleteRole(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void createCapability(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void deleteCapability(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void addMember(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void removeMember(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void assignRole(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void dismissRole(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void assignCapability(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void dismissCapability(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public org.glite.security.voms.User[] listMembers(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public org.glite.security.voms.User[] listUsersWithRole(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public org.glite.security.voms.User[] listUsersWithCapability(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public java.lang.String[] getGroupPath(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public java.lang.String[] listSubGroups(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public java.lang.String[] listGroups(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public java.lang.String[] listRoles() throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public java.lang.String[] listRoles(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public java.lang.String[] listCapabilities() throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public java.lang.String[] listCapabilities(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public java.lang.String[] listCAs() throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public org.glite.security.voms.ACLEntry[] getACL(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void setACL(java.lang.String in0, org.glite.security.voms.ACLEntry[] in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void addACLEntry(java.lang.String in0, org.glite.security.voms.ACLEntry in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void removeACLEntry(java.lang.String in0, org.glite.security.voms.ACLEntry in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public org.glite.security.voms.ACLEntry[] getDefaultACL(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void setDefaultACL(java.lang.String in0, org.glite.security.voms.ACLEntry[] in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void addDefaultACLEntry(java.lang.String in0, org.glite.security.voms.ACLEntry in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void removeDefaultACLEntry(java.lang.String in0, org.glite.security.voms.ACLEntry in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public int getMinorVersionNumber() throws java.rmi.RemoteException;
    public int getPatchVersionNumber() throws java.rmi.RemoteException;
}
