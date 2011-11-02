/**
 * VOMSACLService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.glite.security.voms.service.acl;

public interface VOMSACLService extends javax.xml.rpc.Service {
    public java.lang.String getVOMSACLAddress();

    public org.glite.security.voms.service.acl.VOMSACL getVOMSACL() throws javax.xml.rpc.ServiceException;

    public org.glite.security.voms.service.acl.VOMSACL getVOMSACL(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
