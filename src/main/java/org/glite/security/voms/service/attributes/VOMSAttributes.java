/**
 * VOMSAttributes.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.glite.security.voms.service.attributes;

public interface VOMSAttributes extends java.rmi.Remote {
    public void createAttributeClass(java.lang.String in0, java.lang.String in1, boolean in2) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void createAttributeClass(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void createAttributeClass(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public org.glite.security.voms.service.attributes.AttributeClass getAttributeClass(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void saveAttributeClass(org.glite.security.voms.service.attributes.AttributeClass in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void deleteAttributeClass(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void deleteAttributeClass(org.glite.security.voms.service.attributes.AttributeClass in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public org.glite.security.voms.service.attributes.AttributeClass[] listAttributeClasses() throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public org.glite.security.voms.service.attributes.AttributeValue[] listUserAttributes(org.glite.security.voms.User in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void setUserAttribute(org.glite.security.voms.User in0, org.glite.security.voms.service.attributes.AttributeValue in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void deleteUserAttribute(org.glite.security.voms.User in0, java.lang.String in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void deleteUserAttribute(org.glite.security.voms.User in0, org.glite.security.voms.service.attributes.AttributeValue in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void setGroupAttribute(java.lang.String in0, org.glite.security.voms.service.attributes.AttributeValue in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void deleteGroupAttribute(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void deleteGroupAttribute(java.lang.String in0, org.glite.security.voms.service.attributes.AttributeValue in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public org.glite.security.voms.service.attributes.AttributeValue[] listGroupAttributes(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void setRoleAttribute(java.lang.String in0, java.lang.String in1, org.glite.security.voms.service.attributes.AttributeValue in2) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void deleteRoleAttribute(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public void deleteRoleAttribute(java.lang.String in0, java.lang.String in1, org.glite.security.voms.service.attributes.AttributeValue in2) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
    public org.glite.security.voms.service.attributes.AttributeValue[] listRoleAttributes(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException;
}
