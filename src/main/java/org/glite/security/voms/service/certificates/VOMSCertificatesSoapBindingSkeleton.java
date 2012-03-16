/**
 * VOMSCertificatesSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.glite.security.voms.service.certificates;
@SuppressWarnings({"serial","rawtypes", "unchecked", "unused"})
public class VOMSCertificatesSoapBindingSkeleton implements org.glite.security.voms.service.certificates.VOMSCertificates, org.apache.axis.wsdl.Skeleton {
    private org.glite.security.voms.service.certificates.VOMSCertificates impl;
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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getCertificates", _params, new javax.xml.namespace.QName("", "getCertificatesReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.certificates", "ArrayOfX509Certificate"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.certificates", "getCertificates"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getCertificates") == null) {
            _myOperations.put("getCertificates", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getCertificates")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getCertificates", _params, new javax.xml.namespace.QName("", "getCertificatesReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.certificates", "ArrayOfX509Certificate"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.certificates", "getCertificates"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getCertificates") == null) {
            _myOperations.put("getCertificates", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getCertificates")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getUserIdFromDn", _params, new javax.xml.namespace.QName("", "getUserIdFromDnReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.certificates", "getUserIdFromDn"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getUserIdFromDn") == null) {
            _myOperations.put("getUserIdFromDn", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getUserIdFromDn")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.certificates", "X509Certificate"), org.glite.security.voms.service.certificates.X509Certificate.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("addCertificate", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.certificates", "addCertificate"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("addCertificate") == null) {
            _myOperations.put("addCertificate", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("addCertificate")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.certificates", "X509Certificate"), org.glite.security.voms.service.certificates.X509Certificate.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("addCertificate", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.certificates", "addCertificate"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("addCertificate") == null) {
            _myOperations.put("addCertificate", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("addCertificate")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.certificates", "X509Certificate"), org.glite.security.voms.service.certificates.X509Certificate.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("suspendCertificate", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.certificates", "suspendCertificate"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("suspendCertificate") == null) {
            _myOperations.put("suspendCertificate", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("suspendCertificate")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.certificates", "X509Certificate"), org.glite.security.voms.service.certificates.X509Certificate.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("restoreCertificate", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.certificates", "restoreCertificate"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("restoreCertificate") == null) {
            _myOperations.put("restoreCertificate", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("restoreCertificate")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.certificates", "X509Certificate"), org.glite.security.voms.service.certificates.X509Certificate.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("removeCertificate", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.certificates", "removeCertificate"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("removeCertificate") == null) {
            _myOperations.put("removeCertificate", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("removeCertificate")).add(_oper);
    }

    public VOMSCertificatesSoapBindingSkeleton() {
        this.impl = new org.glite.security.voms.service.certificates.VOMSCertificatesSoapBindingImpl();
    }

    public VOMSCertificatesSoapBindingSkeleton(org.glite.security.voms.service.certificates.VOMSCertificates impl) {
        this.impl = impl;
    }
    public org.glite.security.voms.service.certificates.X509Certificate[] getCertificates(long in0) throws java.rmi.RemoteException
    {
        org.glite.security.voms.service.certificates.X509Certificate[] ret = impl.getCertificates(in0);
        return ret;
    }

    public org.glite.security.voms.service.certificates.X509Certificate[] getCertificates(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException
    {
        org.glite.security.voms.service.certificates.X509Certificate[] ret = impl.getCertificates(in0, in1);
        return ret;
    }

    public long getUserIdFromDn(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException
    {
        long ret = impl.getUserIdFromDn(in0, in1);
        return ret;
    }

    public void addCertificate(long in0, org.glite.security.voms.service.certificates.X509Certificate in1) throws java.rmi.RemoteException
    {
        impl.addCertificate(in0, in1);
    }

    public void addCertificate(java.lang.String in0, java.lang.String in1, org.glite.security.voms.service.certificates.X509Certificate in2) throws java.rmi.RemoteException
    {
        impl.addCertificate(in0, in1, in2);
    }

    public void suspendCertificate(org.glite.security.voms.service.certificates.X509Certificate in0, java.lang.String in1) throws java.rmi.RemoteException
    {
        impl.suspendCertificate(in0, in1);
    }

    public void restoreCertificate(org.glite.security.voms.service.certificates.X509Certificate in0) throws java.rmi.RemoteException
    {
        impl.restoreCertificate(in0);
    }

    public void removeCertificate(org.glite.security.voms.service.certificates.X509Certificate in0) throws java.rmi.RemoteException
    {
        impl.removeCertificate(in0);
    }

}
