/**
 * AttributeValue.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.glite.security.voms.service.attributes;

public class AttributeValue  implements java.io.Serializable {
    private org.glite.security.voms.service.attributes.AttributeClass attributeClass;

    private java.lang.String context;

    private java.lang.String value;

    public AttributeValue() {
    }

    public AttributeValue(
           org.glite.security.voms.service.attributes.AttributeClass attributeClass,
           java.lang.String context,
           java.lang.String value) {
           this.attributeClass = attributeClass;
           this.context = context;
           this.value = value;
    }


    /**
     * Gets the attributeClass value for this AttributeValue.
     * 
     * @return attributeClass
     */
    public org.glite.security.voms.service.attributes.AttributeClass getAttributeClass() {
        return attributeClass;
    }


    /**
     * Sets the attributeClass value for this AttributeValue.
     * 
     * @param attributeClass
     */
    public void setAttributeClass(org.glite.security.voms.service.attributes.AttributeClass attributeClass) {
        this.attributeClass = attributeClass;
    }


    /**
     * Gets the context value for this AttributeValue.
     * 
     * @return context
     */
    public java.lang.String getContext() {
        return context;
    }


    /**
     * Sets the context value for this AttributeValue.
     * 
     * @param context
     */
    public void setContext(java.lang.String context) {
        this.context = context;
    }


    /**
     * Gets the value value for this AttributeValue.
     * 
     * @return value
     */
    public java.lang.String getValue() {
        return value;
    }


    /**
     * Sets the value value for this AttributeValue.
     * 
     * @param value
     */
    public void setValue(java.lang.String value) {
        this.value = value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AttributeValue)) return false;
        AttributeValue other = (AttributeValue) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.attributeClass==null && other.getAttributeClass()==null) || 
             (this.attributeClass!=null &&
              this.attributeClass.equals(other.getAttributeClass()))) &&
            ((this.context==null && other.getContext()==null) || 
             (this.context!=null &&
              this.context.equals(other.getContext()))) &&
            ((this.value==null && other.getValue()==null) || 
             (this.value!=null &&
              this.value.equals(other.getValue())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAttributeClass() != null) {
            _hashCode += getAttributeClass().hashCode();
        }
        if (getContext() != null) {
            _hashCode += getContext().hashCode();
        }
        if (getValue() != null) {
            _hashCode += getValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
