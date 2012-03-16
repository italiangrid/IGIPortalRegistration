/**
 * VOMSCompatibilitySoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.glite.security.voms.service.compatibility;

@SuppressWarnings({"serial","rawtypes", "unchecked"})
public class VOMSCompatibilitySoapBindingSkeleton implements org.glite.security.voms.service.compatibility.VOMSCompatibility, org.apache.axis.wsdl.Skeleton {
    private org.glite.security.voms.service.compatibility.VOMSCompatibility impl;
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
        };
        _oper = new org.apache.axis.description.OperationDesc("getMajorVersionNumber", _params, new javax.xml.namespace.QName("", "getMajorVersionNumberReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.compatibility", "getMajorVersionNumber"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getMajorVersionNumber") == null) {
            _myOperations.put("getMajorVersionNumber", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getMajorVersionNumber")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
        };
        _oper = new org.apache.axis.description.OperationDesc("getMinorVersionNumber", _params, new javax.xml.namespace.QName("", "getMinorVersionNumberReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.compatibility", "getMinorVersionNumber"));
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
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.compatibility", "getPatchVersionNumber"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getPatchVersionNumber") == null) {
            _myOperations.put("getPatchVersionNumber", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getPatchVersionNumber")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
        };
        _oper = new org.apache.axis.description.OperationDesc("getGridmapUsers", _params, new javax.xml.namespace.QName("", "getGridmapUsersReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.compatibility", "ArrayOf_soapenc_string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.compatibility", "getGridmapUsers"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getGridmapUsers") == null) {
            _myOperations.put("getGridmapUsers", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getGridmapUsers")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.compatibility", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getGridmapUsers", _params, new javax.xml.namespace.QName("", "getGridmapUsersReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.compatibility", "ArrayOf_soapenc_string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.compatibility", "getGridmapUsers"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getGridmapUsers") == null) {
            _myOperations.put("getGridmapUsers", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getGridmapUsers")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("VOMSException");
        _fault.setQName(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms.service.compatibility", "fault"));
        _fault.setClassName("org.glite.security.voms.VOMSException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://glite.org/wsdl/services/org.glite.security.voms", "VOMSException"));
        _oper.addFault(_fault);
    }

    public VOMSCompatibilitySoapBindingSkeleton() {
        this.impl = new org.glite.security.voms.service.compatibility.VOMSCompatibilitySoapBindingImpl();
    }

    public VOMSCompatibilitySoapBindingSkeleton(org.glite.security.voms.service.compatibility.VOMSCompatibility impl) {
        this.impl = impl;
    }
    public int getMajorVersionNumber() throws java.rmi.RemoteException
    {
        int ret = impl.getMajorVersionNumber();
        return ret;
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

    public java.lang.String[] getGridmapUsers() throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        java.lang.String[] ret = impl.getGridmapUsers();
        return ret;
    }

    public java.lang.String[] getGridmapUsers(java.lang.String in0) throws java.rmi.RemoteException, org.glite.security.voms.VOMSException
    {
        java.lang.String[] ret = impl.getGridmapUsers(in0);
        return ret;
    }

}
