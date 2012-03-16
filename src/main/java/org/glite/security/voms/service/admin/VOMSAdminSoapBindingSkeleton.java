/**
 * VOMSAdminSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.glite.security.voms.service.admin;
@SuppressWarnings({"serial","rawtypes", "unchecked"})
public class VOMSAdminSoapBindingSkeleton implements org.glite.security.voms.service.admin.VOMSAdmin, org.apache.axis.wsdl.Skeleton {
    private org.glite.security.voms.service.admin.VOMSAdmin impl;
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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("createGroup", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "createGroup"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("createGroup") == null) {
            _myOperations.put("createGroup", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("createGroup")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getUser", _params, new javax.xml.namespace.QName("", "getUserReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "User"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "getUser"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getUser") == null) {
            _myOperations.put("getUser", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getUser")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "User"), org.glite.security.voms.User.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("setUser", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "setUser"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("setUser") == null) {
            _myOperations.put("setUser", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("setUser")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
        };
        _oper = new org.apache.axis.description.OperationDesc("getMajorVersionNumber", _params, new javax.xml.namespace.QName("", "getMajorVersionNumberReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "getMajorVersionNumber"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getMajorVersionNumber") == null) {
            _myOperations.put("getMajorVersionNumber", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getMajorVersionNumber")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
        };
        _oper = new org.apache.axis.description.OperationDesc("getVOName", _params, new javax.xml.namespace.QName("", "getVONameReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "getVOName"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getVOName") == null) {
            _myOperations.put("getVOName", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getVOName")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "User"), org.glite.security.voms.User.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("createUser", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "createUser"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("createUser") == null) {
            _myOperations.put("createUser", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("createUser")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("deleteUser", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "deleteUser"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("deleteUser") == null) {
            _myOperations.put("deleteUser", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("deleteUser")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("deleteGroup", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "deleteGroup"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("deleteGroup") == null) {
            _myOperations.put("deleteGroup", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("deleteGroup")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("createRole", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "createRole"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("createRole") == null) {
            _myOperations.put("createRole", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("createRole")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("deleteRole", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "deleteRole"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("deleteRole") == null) {
            _myOperations.put("deleteRole", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("deleteRole")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("createCapability", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "createCapability"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("createCapability") == null) {
            _myOperations.put("createCapability", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("createCapability")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("deleteCapability", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "deleteCapability"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("deleteCapability") == null) {
            _myOperations.put("deleteCapability", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("deleteCapability")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("addMember", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "addMember"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("addMember") == null) {
            _myOperations.put("addMember", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("addMember")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("removeMember", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "removeMember"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("removeMember") == null) {
            _myOperations.put("removeMember", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("removeMember")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in3"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("assignRole", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "assignRole"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("assignRole") == null) {
            _myOperations.put("assignRole", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("assignRole")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in3"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("dismissRole", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "dismissRole"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("dismissRole") == null) {
            _myOperations.put("dismissRole", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("dismissRole")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("assignCapability", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "assignCapability"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("assignCapability") == null) {
            _myOperations.put("assignCapability", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("assignCapability")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("dismissCapability", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "dismissCapability"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("dismissCapability") == null) {
            _myOperations.put("dismissCapability", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("dismissCapability")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("listMembers", _params, new javax.xml.namespace.QName("", "listMembersReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "ArrayOf_tns1_User"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "listMembers"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("listMembers") == null) {
            _myOperations.put("listMembers", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("listMembers")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("listUsersWithRole", _params, new javax.xml.namespace.QName("", "listUsersWithRoleReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "ArrayOf_tns1_User"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "listUsersWithRole"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("listUsersWithRole") == null) {
            _myOperations.put("listUsersWithRole", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("listUsersWithRole")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("listUsersWithCapability", _params, new javax.xml.namespace.QName("", "listUsersWithCapabilityReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "ArrayOf_tns1_User"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "listUsersWithCapability"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("listUsersWithCapability") == null) {
            _myOperations.put("listUsersWithCapability", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("listUsersWithCapability")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getGroupPath", _params, new javax.xml.namespace.QName("", "getGroupPathReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "ArrayOf_soapenc_string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "getGroupPath"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getGroupPath") == null) {
            _myOperations.put("getGroupPath", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getGroupPath")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("listSubGroups", _params, new javax.xml.namespace.QName("", "listSubGroupsReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "ArrayOf_soapenc_string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "listSubGroups"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("listSubGroups") == null) {
            _myOperations.put("listSubGroups", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("listSubGroups")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("listGroups", _params, new javax.xml.namespace.QName("", "listGroupsReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "ArrayOf_soapenc_string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "listGroups"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("listGroups") == null) {
            _myOperations.put("listGroups", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("listGroups")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
        };
        _oper = new org.apache.axis.description.OperationDesc("listRoles", _params, new javax.xml.namespace.QName("", "listRolesReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "ArrayOf_soapenc_string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "listRoles"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("listRoles") == null) {
            _myOperations.put("listRoles", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("listRoles")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("listRoles", _params, new javax.xml.namespace.QName("", "listRolesReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "ArrayOf_soapenc_string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "listRoles"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("listRoles") == null) {
            _myOperations.put("listRoles", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("listRoles")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
        };
        _oper = new org.apache.axis.description.OperationDesc("listCapabilities", _params, new javax.xml.namespace.QName("", "listCapabilitiesReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "ArrayOf_soapenc_string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "listCapabilities"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("listCapabilities") == null) {
            _myOperations.put("listCapabilities", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("listCapabilities")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("listCapabilities", _params, new javax.xml.namespace.QName("", "listCapabilitiesReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "ArrayOf_soapenc_string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "listCapabilities"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("listCapabilities") == null) {
            _myOperations.put("listCapabilities", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("listCapabilities")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
        };
        _oper = new org.apache.axis.description.OperationDesc("listCAs", _params, new javax.xml.namespace.QName("", "listCAsReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "ArrayOf_soapenc_string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "listCAs"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("listCAs") == null) {
            _myOperations.put("listCAs", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("listCAs")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getACL", _params, new javax.xml.namespace.QName("", "getACLReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "ArrayOf_tns1_ACLEntry"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "getACL"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getACL") == null) {
            _myOperations.put("getACL", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getACL")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "ArrayOf_tns1_ACLEntry"), org.glite.security.voms.ACLEntry[].class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("setACL", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "setACL"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("setACL") == null) {
            _myOperations.put("setACL", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("setACL")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "ACLEntry"), org.glite.security.voms.ACLEntry.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("addACLEntry", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "addACLEntry"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("addACLEntry") == null) {
            _myOperations.put("addACLEntry", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("addACLEntry")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "ACLEntry"), org.glite.security.voms.ACLEntry.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("removeACLEntry", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "removeACLEntry"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("removeACLEntry") == null) {
            _myOperations.put("removeACLEntry", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("removeACLEntry")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getDefaultACL", _params, new javax.xml.namespace.QName("", "getDefaultACLReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "ArrayOf_tns1_ACLEntry"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "getDefaultACL"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getDefaultACL") == null) {
            _myOperations.put("getDefaultACL", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getDefaultACL")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "ArrayOf_tns1_ACLEntry"), org.glite.security.voms.ACLEntry[].class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("setDefaultACL", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "setDefaultACL"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("setDefaultACL") == null) {
            _myOperations.put("setDefaultACL", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("setDefaultACL")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "ACLEntry"), org.glite.security.voms.ACLEntry.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("addDefaultACLEntry", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "addDefaultACLEntry"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("addDefaultACLEntry") == null) {
            _myOperations.put("addDefaultACLEntry", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("addDefaultACLEntry")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "ACLEntry"), org.glite.security.voms.ACLEntry.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("removeDefaultACLEntry", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "removeDefaultACLEntry"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("removeDefaultACLEntry") == null) {
            _myOperations.put("removeDefaultACLEntry", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("removeDefaultACLEntry")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
        };
        _oper = new org.apache.axis.description.OperationDesc("getMinorVersionNumber", _params, new javax.xml.namespace.QName("", "getMinorVersionNumberReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "getMinorVersionNumber"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getMinorVersionNumber") == null) {
            _myOperations.put("getMinorVersionNumber", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getMinorVersionNumber")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
        };
        _oper = new org.apache.axis.description.OperationDesc("getPatchVersionNumber", _params, new javax.xml.namespace.QName("", "getPatchVersionNumberReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.admin", "getPatchVersionNumber"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getPatchVersionNumber") == null) {
            _myOperations.put("getPatchVersionNumber", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getPatchVersionNumber")).add(_oper);
    }

    public VOMSAdminSoapBindingSkeleton() {
        this.impl = new org.glite.security.voms.service.admin.VOMSAdminSoapBindingImpl();
    }

    public VOMSAdminSoapBindingSkeleton(org.glite.security.voms.service.admin.VOMSAdmin impl) {
        this.impl = impl;
    }
    public void createGroup(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.createGroup(in0, in1);
    }

    public org.glite.security.voms.User getUser(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        org.glite.security.voms.User ret = impl.getUser(in0, in1);
        return ret;
    }

    public void setUser(org.glite.security.voms.User in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.setUser(in0);
    }

    public int getMajorVersionNumber() throws java.rmi.RemoteException
    {
        int ret = impl.getMajorVersionNumber();
        return ret;
    }

    public java.lang.String getVOName() throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        java.lang.String ret = impl.getVOName();
        return ret;
    }

    public void createUser(org.glite.security.voms.User in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.createUser(in0);
    }

    public void deleteUser(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.deleteUser(in0, in1);
    }

    public void deleteGroup(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.deleteGroup(in0);
    }

    public void createRole(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.createRole(in0);
    }

    public void deleteRole(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.deleteRole(in0);
    }

    public void createCapability(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.createCapability(in0);
    }

    public void deleteCapability(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.deleteCapability(in0);
    }

    public void addMember(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.addMember(in0, in1, in2);
    }

    public void removeMember(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.removeMember(in0, in1, in2);
    }

    public void assignRole(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.assignRole(in0, in1, in2, in3);
    }

    public void dismissRole(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.dismissRole(in0, in1, in2, in3);
    }

    public void assignCapability(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.assignCapability(in0, in1, in2);
    }

    public void dismissCapability(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.dismissCapability(in0, in1, in2);
    }

    public org.glite.security.voms.User[] listMembers(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        org.glite.security.voms.User[] ret = impl.listMembers(in0);
        return ret;
    }

    public org.glite.security.voms.User[] listUsersWithRole(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        org.glite.security.voms.User[] ret = impl.listUsersWithRole(in0, in1);
        return ret;
    }

    public org.glite.security.voms.User[] listUsersWithCapability(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        org.glite.security.voms.User[] ret = impl.listUsersWithCapability(in0);
        return ret;
    }

    public java.lang.String[] getGroupPath(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        java.lang.String[] ret = impl.getGroupPath(in0);
        return ret;
    }

    public java.lang.String[] listSubGroups(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        java.lang.String[] ret = impl.listSubGroups(in0);
        return ret;
    }

    public java.lang.String[] listGroups(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        java.lang.String[] ret = impl.listGroups(in0, in1);
        return ret;
    }

    public java.lang.String[] listRoles() throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        java.lang.String[] ret = impl.listRoles();
        return ret;
    }

    public java.lang.String[] listRoles(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        java.lang.String[] ret = impl.listRoles(in0, in1);
        return ret;
    }

    public java.lang.String[] listCapabilities() throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        java.lang.String[] ret = impl.listCapabilities();
        return ret;
    }

    public java.lang.String[] listCapabilities(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        java.lang.String[] ret = impl.listCapabilities(in0, in1);
        return ret;
    }

    public java.lang.String[] listCAs() throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        java.lang.String[] ret = impl.listCAs();
        return ret;
    }

    public org.glite.security.voms.ACLEntry[] getACL(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        org.glite.security.voms.ACLEntry[] ret = impl.getACL(in0);
        return ret;
    }

    public void setACL(java.lang.String in0, org.glite.security.voms.ACLEntry[] in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.setACL(in0, in1);
    }

    public void addACLEntry(java.lang.String in0, org.glite.security.voms.ACLEntry in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.addACLEntry(in0, in1);
    }

    public void removeACLEntry(java.lang.String in0, org.glite.security.voms.ACLEntry in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.removeACLEntry(in0, in1);
    }

    public org.glite.security.voms.ACLEntry[] getDefaultACL(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        org.glite.security.voms.ACLEntry[] ret = impl.getDefaultACL(in0);
        return ret;
    }

    public void setDefaultACL(java.lang.String in0, org.glite.security.voms.ACLEntry[] in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.setDefaultACL(in0, in1);
    }

    public void addDefaultACLEntry(java.lang.String in0, org.glite.security.voms.ACLEntry in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.addDefaultACLEntry(in0, in1);
    }

    public void removeDefaultACLEntry(java.lang.String in0, org.glite.security.voms.ACLEntry in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.removeDefaultACLEntry(in0, in1);
    }

    public int getMinorVersionNumber() throws java.rmi.RemoteException
    {
        int ret = impl.getMinorVersionNumber();
        return ret;
    }

    public int getPatchVersionNumber() throws java.rmi.RemoteException
    {
        int ret = impl.getPatchVersionNumber();
        return ret;
    }

}
