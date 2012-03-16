/**
 * VOMSACLSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.glite.security.voms.service.acl;
@SuppressWarnings({"serial","rawtypes", "unchecked"})
public class VOMSACLSoapBindingSkeleton implements org.glite.security.voms.service.acl.VOMSACL, org.apache.axis.wsdl.Skeleton {
    private org.glite.security.voms.service.acl.VOMSACL impl;
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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getACL", _params, new javax.xml.namespace.QName("", "getACLReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.acl", "ArrayOfACLEntry"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.acl", "getACL"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getACL") == null) {
            _myOperations.put("getACL", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getACL")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.acl", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.acl", "ArrayOfACLEntry"), org.glite.security.voms.service.acl.ACLEntry[].class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("setACL", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.acl", "setACL"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("setACL") == null) {
            _myOperations.put("setACL", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("setACL")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.acl", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.acl", "ACLEntry"), org.glite.security.voms.service.acl.ACLEntry.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("addACLEntry", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.acl", "addACLEntry"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("addACLEntry") == null) {
            _myOperations.put("addACLEntry", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("addACLEntry")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.acl", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.acl", "ACLEntry"), org.glite.security.voms.service.acl.ACLEntry.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("removeACLEntry", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.acl", "removeACLEntry"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("removeACLEntry") == null) {
            _myOperations.put("removeACLEntry", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("removeACLEntry")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.acl", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getDefaultACL", _params, new javax.xml.namespace.QName("", "getDefaultACLReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.acl", "ArrayOfACLEntry"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.acl", "getDefaultACL"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getDefaultACL") == null) {
            _myOperations.put("getDefaultACL", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getDefaultACL")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.acl", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.acl", "ArrayOfACLEntry"), org.glite.security.voms.service.acl.ACLEntry[].class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("setDefaultACL", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.acl", "setDefaultACL"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("setDefaultACL") == null) {
            _myOperations.put("setDefaultACL", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("setDefaultACL")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.acl", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.acl", "ACLEntry"), org.glite.security.voms.service.acl.ACLEntry.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("addDefaultACLEntry", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.acl", "addDefaultACLEntry"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("addDefaultACLEntry") == null) {
            _myOperations.put("addDefaultACLEntry", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("addDefaultACLEntry")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.acl", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.acl", "ACLEntry"), org.glite.security.voms.service.acl.ACLEntry.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("removeDefaultACLEntry", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.acl", "removeDefaultACLEntry"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("removeDefaultACLEntry") == null) {
            _myOperations.put("removeDefaultACLEntry", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("removeDefaultACLEntry")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.acl", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
    }

    public VOMSACLSoapBindingSkeleton() {
        this.impl = new org.glite.security.voms.service.acl.VOMSACLSoapBindingImpl();
    }

    public VOMSACLSoapBindingSkeleton(org.glite.security.voms.service.acl.VOMSACL impl) {
        this.impl = impl;
    }
    public org.glite.security.voms.service.acl.ACLEntry[] getACL(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        org.glite.security.voms.service.acl.ACLEntry[] ret = impl.getACL(in0);
        return ret;
    }

    public void setACL(java.lang.String in0, org.glite.security.voms.service.acl.ACLEntry[] in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.setACL(in0, in1);
    }

    public void addACLEntry(java.lang.String in0, org.glite.security.voms.service.acl.ACLEntry in1, boolean in2) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.addACLEntry(in0, in1, in2);
    }

    public void removeACLEntry(java.lang.String in0, org.glite.security.voms.service.acl.ACLEntry in1, boolean in2) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.removeACLEntry(in0, in1, in2);
    }

    public org.glite.security.voms.service.acl.ACLEntry[] getDefaultACL(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        org.glite.security.voms.service.acl.ACLEntry[] ret = impl.getDefaultACL(in0);
        return ret;
    }

    public void setDefaultACL(java.lang.String in0, org.glite.security.voms.service.acl.ACLEntry[] in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.setDefaultACL(in0, in1);
    }

    public void addDefaultACLEntry(java.lang.String in0, org.glite.security.voms.service.acl.ACLEntry in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.addDefaultACLEntry(in0, in1);
    }

    public void removeDefaultACLEntry(java.lang.String in0, org.glite.security.voms.service.acl.ACLEntry in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.removeDefaultACLEntry(in0, in1);
    }

}
