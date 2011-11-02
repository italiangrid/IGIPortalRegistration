/**
 * VOMSAdminService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.glite.security.voms.service.admin;

public interface VOMSAdminService extends javax.xml.rpc.Service {
    public java.lang.String getVOMSAdminAddress();

    public org.glite.security.voms.service.admin.VOMSAdmin getVOMSAdmin() throws javax.xml.rpc.ServiceException;

    public org.glite.security.voms.service.admin.VOMSAdmin getVOMSAdmin(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
