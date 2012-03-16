/**
 * VOMSRegistrationServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.glite.security.voms.service.registration;

@SuppressWarnings("serial")
public class VOMSRegistrationServiceLocator extends org.apache.axis.client.Service implements org.glite.security.voms.service.registration.VOMSRegistrationService {

    public VOMSRegistrationServiceLocator() {
    }


    public VOMSRegistrationServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public VOMSRegistrationServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for VOMSRegistration
    private java.lang.String VOMSRegistration_address = "https://localhost:8443/glite-security-voms-admin-interface/VOMSRegistration";

    public java.lang.String getVOMSRegistrationAddress() {
        return VOMSRegistration_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String VOMSRegistrationWSDDServiceName = "VOMSRegistration";

    public java.lang.String getVOMSRegistrationWSDDServiceName() {
        return VOMSRegistrationWSDDServiceName;
    }

    public void setVOMSRegistrationWSDDServiceName(java.lang.String name) {
        VOMSRegistrationWSDDServiceName = name;
    }

    public org.glite.security.voms.service.registration.VOMSRegistration getVOMSRegistration() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(VOMSRegistration_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getVOMSRegistration(endpoint);
    }

    public org.glite.security.voms.service.registration.VOMSRegistration getVOMSRegistration(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.glite.security.voms.service.registration.VOMSRegistrationSoapBindingStub _stub = new org.glite.security.voms.service.registration.VOMSRegistrationSoapBindingStub(portAddress, this);
            _stub.setPortName(getVOMSRegistrationWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setVOMSRegistrationEndpointAddress(java.lang.String address) {
        VOMSRegistration_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    @SuppressWarnings("rawtypes")
	public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.glite.security.voms.service.registration.VOMSRegistration.class.isAssignableFrom(serviceEndpointInterface)) {
                org.glite.security.voms.service.registration.VOMSRegistrationSoapBindingStub _stub = new org.glite.security.voms.service.registration.VOMSRegistrationSoapBindingStub(new java.net.URL(VOMSRegistration_address), this);
                _stub.setPortName(getVOMSRegistrationWSDDServiceName());
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
    @SuppressWarnings("rawtypes")
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("VOMSRegistration".equals(inputPortName)) {
            return getVOMSRegistration();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.registration", "VOMSRegistrationService");
    }

    @SuppressWarnings("rawtypes")
	private java.util.HashSet ports = null;

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.registration", "VOMSRegistration"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("VOMSRegistration".equals(portName)) {
            setVOMSRegistrationEndpointAddress(address);
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
