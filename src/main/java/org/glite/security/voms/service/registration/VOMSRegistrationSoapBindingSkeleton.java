/**
 * VOMSRegistrationSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.glite.security.voms.service.registration;

@SuppressWarnings({ "serial", "unchecked", "rawtypes" })
public class VOMSRegistrationSoapBindingSkeleton implements org.glite.security.voms.service.registration.VOMSRegistration, org.apache.axis.wsdl.Skeleton {
    private org.glite.security.voms.service.registration.VOMSRegistration impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.registration", "RegistrationRequest"), org.glite.security.voms.service.registration.RegistrationRequest.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("submitRegistrationRequest", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.registration", "submitRegistrationRequest"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("submitRegistrationRequest") == null) {
            _myOperations.put("submitRegistrationRequest", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("submitRegistrationRequest")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.registration", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.registration", "RegistrationRequest"), org.glite.security.voms.service.registration.RegistrationRequest.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("submitRegistrationRequestForUser", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.registration", "submitRegistrationRequestForUser"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("submitRegistrationRequestForUser") == null) {
            _myOperations.put("submitRegistrationRequestForUser", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("submitRegistrationRequestForUser")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.registration", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
    }

    public VOMSRegistrationSoapBindingSkeleton() {
        this.impl = new org.glite.security.voms.service.registration.VOMSRegistrationSoapBindingImpl();
    }

    public VOMSRegistrationSoapBindingSkeleton(org.glite.security.voms.service.registration.VOMSRegistration impl) {
        this.impl = impl;
    }
    public void submitRegistrationRequest(org.glite.security.voms.service.registration.RegistrationRequest in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.submitRegistrationRequest(in0);
    }

    public void submitRegistrationRequestForUser(java.lang.String in0, java.lang.String in1, org.glite.security.voms.service.registration.RegistrationRequest in2) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.submitRegistrationRequestForUser(in0, in1, in2);
    }

}
