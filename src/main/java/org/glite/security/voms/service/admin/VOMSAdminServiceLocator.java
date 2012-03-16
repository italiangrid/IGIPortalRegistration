/**
 * VOMSAdminServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.glite.security.voms.service.admin;
@SuppressWarnings({"serial","rawtypes", "unchecked"})
public class VOMSAdminServiceLocator extends org.apache.axis.client.Service implements org.glite.security.voms.service.admin.VOMSAdminService {

    public VOMSAdminServiceLocator() {
    }


    public VOMSAdminServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public VOMSAdminServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for VOMSAdmin
    private java.lang.String VOMSAdmin_address = "https://localhost:8443/glite-security-voms-admin-interface/VOMSAdmin";

    public java.lang.String getVOMSAdminAddress() {
        return VOMSAdmin_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String VOMSAdminWSDDServiceName = "VOMSAdmin";

    public java.lang.String getVOMSAdminWSDDServiceName() {
        return VOMSAdminWSDDServiceName;
    }

    public void setVOMSAdminWSDDServiceName(java.lang.String name) {
        VOMSAdminWSDDServiceName = name;
    }

    public org.glite.security.voms.service.admin.VOMSAdmin getVOMSAdmin() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(VOMSAdmin_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getVOMSAdmin(endpoint);
    }

    public org.glite.security.voms.service.admin.VOMSAdmin getVOMSAdmin(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.glite.security.voms.service.admin.VOMSAdminSoapBindingStub _stub = new org.glite.security.voms.service.admin.VOMSAdminSoapBindingStub(portAddress, this);
            _stub.setPortName(getVOMSAdminWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setVOMSAdminEndpointAddress(java.lang.String address) {
        VOMSAdmin_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.glite.security.voms.service.admin.VOMSAdmin.class.isAssignableFrom(serviceEndpointInterface)) {
                org.glite.security.voms.service.admin.VOMSAdminSoapBindingStub _stub = new org.glite.security.voms.service.admin.VOMSAdminSoapBindingStub(new java.net.URL(VOMSAdmin_address), this);
                _stub.setPortName(getVOMSAdminWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("VOMSAdmin".equals(inputPortName)) {
            return getVOMSAdmin();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "VOMSAdminService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "VOMSAdmin"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("VOMSAdmin".equals(portName)) {
            setVOMSAdminEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
