/**
 * VOMSAttributesSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.glite.security.voms.service.attributes;

public class VOMSAttributesSoapBindingSkeleton implements org.glite.security.voms.service.attributes.VOMSAttributes, org.apache.axis.wsdl.Skeleton {
    private org.glite.security.voms.service.attributes.VOMSAttributes impl;
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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("createAttributeClass", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "createAttributeClass"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("createAttributeClass") == null) {
            _myOperations.put("createAttributeClass", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("createAttributeClass")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("createAttributeClass", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "createAttributeClass"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("createAttributeClass") == null) {
            _myOperations.put("createAttributeClass", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("createAttributeClass")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("createAttributeClass", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "createAttributeClass"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("createAttributeClass") == null) {
            _myOperations.put("createAttributeClass", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("createAttributeClass")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getAttributeClass", _params, new javax.xml.namespace.QName("", "getAttributeClassReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "AttributeClass"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "getAttributeClass"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getAttributeClass") == null) {
            _myOperations.put("getAttributeClass", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getAttributeClass")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "AttributeClass"), org.glite.security.voms.service.attributes.AttributeClass.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("saveAttributeClass", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "saveAttributeClass"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("saveAttributeClass") == null) {
            _myOperations.put("saveAttributeClass", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("saveAttributeClass")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("deleteAttributeClass", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "deleteAttributeClass"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("deleteAttributeClass") == null) {
            _myOperations.put("deleteAttributeClass", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("deleteAttributeClass")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "AttributeClass"), org.glite.security.voms.service.attributes.AttributeClass.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("deleteAttributeClass", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "deleteAttributeClass"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("deleteAttributeClass") == null) {
            _myOperations.put("deleteAttributeClass", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("deleteAttributeClass")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
        };
        _oper = new org.apache.axis.description.OperationDesc("listAttributeClasses", _params, new javax.xml.namespace.QName("", "listAttributeClassesReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "ArrayOfAttributeClass"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "listAttributeClasses"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("listAttributeClasses") == null) {
            _myOperations.put("listAttributeClasses", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("listAttributeClasses")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "User"), org.glite.security.voms.User.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("listUserAttributes", _params, new javax.xml.namespace.QName("", "listUserAttributesReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "ArrayOfAttributeValue"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "listUserAttributes"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("listUserAttributes") == null) {
            _myOperations.put("listUserAttributes", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("listUserAttributes")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "User"), org.glite.security.voms.User.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "AttributeValue"), org.glite.security.voms.service.attributes.AttributeValue.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("setUserAttribute", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "setUserAttribute"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("setUserAttribute") == null) {
            _myOperations.put("setUserAttribute", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("setUserAttribute")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "User"), org.glite.security.voms.User.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("deleteUserAttribute", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "deleteUserAttribute"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("deleteUserAttribute") == null) {
            _myOperations.put("deleteUserAttribute", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("deleteUserAttribute")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "User"), org.glite.security.voms.User.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "AttributeValue"), org.glite.security.voms.service.attributes.AttributeValue.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("deleteUserAttribute", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "deleteUserAttribute"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("deleteUserAttribute") == null) {
            _myOperations.put("deleteUserAttribute", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("deleteUserAttribute")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "AttributeValue"), org.glite.security.voms.service.attributes.AttributeValue.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("setGroupAttribute", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "setGroupAttribute"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("setGroupAttribute") == null) {
            _myOperations.put("setGroupAttribute", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("setGroupAttribute")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("deleteGroupAttribute", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "deleteGroupAttribute"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("deleteGroupAttribute") == null) {
            _myOperations.put("deleteGroupAttribute", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("deleteGroupAttribute")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "AttributeValue"), org.glite.security.voms.service.attributes.AttributeValue.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("deleteGroupAttribute", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "deleteGroupAttribute"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("deleteGroupAttribute") == null) {
            _myOperations.put("deleteGroupAttribute", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("deleteGroupAttribute")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("listGroupAttributes", _params, new javax.xml.namespace.QName("", "listGroupAttributesReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "ArrayOfAttributeValue"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "listGroupAttributes"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("listGroupAttributes") == null) {
            _myOperations.put("listGroupAttributes", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("listGroupAttributes")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "AttributeValue"), org.glite.security.voms.service.attributes.AttributeValue.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("setRoleAttribute", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "setRoleAttribute"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("setRoleAttribute") == null) {
            _myOperations.put("setRoleAttribute", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("setRoleAttribute")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("deleteRoleAttribute", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "deleteRoleAttribute"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("deleteRoleAttribute") == null) {
            _myOperations.put("deleteRoleAttribute", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("deleteRoleAttribute")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "AttributeValue"), org.glite.security.voms.service.attributes.AttributeValue.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("deleteRoleAttribute", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "deleteRoleAttribute"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("deleteRoleAttribute") == null) {
            _myOperations.put("deleteRoleAttribute", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("deleteRoleAttribute")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("listRoleAttributes", _params, new javax.xml.namespace.QName("", "listRoleAttributesReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "ArrayOfAttributeValue"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "listRoleAttributes"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("listRoleAttributes") == null) {
            _myOperations.put("listRoleAttributes", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("listRoleAttributes")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.attributes", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
    }

    public VOMSAttributesSoapBindingSkeleton() {
        this.impl = new org.glite.security.voms.service.attributes.VOMSAttributesSoapBindingImpl();
    }

    public VOMSAttributesSoapBindingSkeleton(org.glite.security.voms.service.attributes.VOMSAttributes impl) {
        this.impl = impl;
    }
    public void createAttributeClass(java.lang.String in0, java.lang.String in1, boolean in2) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.createAttributeClass(in0, in1, in2);
    }

    public void createAttributeClass(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.createAttributeClass(in0, in1);
    }

    public void createAttributeClass(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.createAttributeClass(in0);
    }

    public org.glite.security.voms.service.attributes.AttributeClass getAttributeClass(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        org.glite.security.voms.service.attributes.AttributeClass ret = impl.getAttributeClass(in0);
        return ret;
    }

    public void saveAttributeClass(org.glite.security.voms.service.attributes.AttributeClass in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.saveAttributeClass(in0);
    }

    public void deleteAttributeClass(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.deleteAttributeClass(in0);
    }

    public void deleteAttributeClass(org.glite.security.voms.service.attributes.AttributeClass in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.deleteAttributeClass(in0);
    }

    public org.glite.security.voms.service.attributes.AttributeClass[] listAttributeClasses() throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        org.glite.security.voms.service.attributes.AttributeClass[] ret = impl.listAttributeClasses();
        return ret;
    }

    public org.glite.security.voms.service.attributes.AttributeValue[] listUserAttributes(org.glite.security.voms.User in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        org.glite.security.voms.service.attributes.AttributeValue[] ret = impl.listUserAttributes(in0);
        return ret;
    }

    public void setUserAttribute(org.glite.security.voms.User in0, org.glite.security.voms.service.attributes.AttributeValue in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.setUserAttribute(in0, in1);
    }

    public void deleteUserAttribute(org.glite.security.voms.User in0, java.lang.String in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.deleteUserAttribute(in0, in1);
    }

    public void deleteUserAttribute(org.glite.security.voms.User in0, org.glite.security.voms.service.attributes.AttributeValue in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.deleteUserAttribute(in0, in1);
    }

    public void setGroupAttribute(java.lang.String in0, org.glite.security.voms.service.attributes.AttributeValue in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.setGroupAttribute(in0, in1);
    }

    public void deleteGroupAttribute(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.deleteGroupAttribute(in0, in1);
    }

    public void deleteGroupAttribute(java.lang.String in0, org.glite.security.voms.service.attributes.AttributeValue in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.deleteGroupAttribute(in0, in1);
    }

    public org.glite.security.voms.service.attributes.AttributeValue[] listGroupAttributes(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        org.glite.security.voms.service.attributes.AttributeValue[] ret = impl.listGroupAttributes(in0);
        return ret;
    }

    public void setRoleAttribute(java.lang.String in0, java.lang.String in1, org.glite.security.voms.service.attributes.AttributeValue in2) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.setRoleAttribute(in0, in1, in2);
    }

    public void deleteRoleAttribute(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.deleteRoleAttribute(in0, in1, in2);
    }

    public void deleteRoleAttribute(java.lang.String in0, java.lang.String in1, org.glite.security.voms.service.attributes.AttributeValue in2) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        impl.deleteRoleAttribute(in0, in1, in2);
    }

    public org.glite.security.voms.service.attributes.AttributeValue[] listRoleAttributes(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        org.glite.security.voms.service.attributes.AttributeValue[] ret = impl.listRoleAttributes(in0, in1);
        return ret;
    }

}
