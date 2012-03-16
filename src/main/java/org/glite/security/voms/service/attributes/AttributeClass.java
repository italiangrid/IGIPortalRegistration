/**
 * AttributeClass.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.glite.security.voms.service.attributes;
@SuppressWarnings({"serial","unused"})
public class AttributeClass  implements java.io.Serializable {
    private java.lang.String description;

    private java.lang.String name;

    private boolean uniquenessChecked;

    public AttributeClass() {
    }

    public AttributeClass(
           java.lang.String description,
           java.lang.String name,
           boolean uniquenessChecked) {
           this.description = description;
           this.name = name;
           this.uniquenessChecked = uniquenessChecked;
    }


    /**
     * Gets the description value for this AttributeClass.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this AttributeClass.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the name value for this AttributeClass.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this AttributeClass.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the uniquenessChecked value for this AttributeClass.
     * 
     * @return uniquenessChecked
     */
    public boolean isUniquenessChecked() {
        return uniquenessChecked;
    }


    /**
     * Sets the uniquenessChecked value for this AttributeClass.
     * 
     * @param uniquenessChecked
     */
    public void setUniquenessChecked(boolean uniquenessChecked) {
        this.uniquenessChecked = uniquenessChecked;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AttributeClass)) return false;
        AttributeClass other = (AttributeClass) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            this.uniquenessChecked == other.isUniquenessChecked();
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
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        _hashCode += (isUniquenessChecked() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

}
