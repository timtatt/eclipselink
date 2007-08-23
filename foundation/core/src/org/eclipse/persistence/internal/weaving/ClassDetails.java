// Copyright (c) 2005, 2007, Oracle. All rights reserved.  
package org.eclipse.persistence.internal.weaving;

import java.util.*;

/**
 * Internal helper class that holds details of a persistent class.
 * Used by {@link PersistenceWeaver}
 */

public class ClassDetails {
    
    protected Class describedClass;    
    /** Name of this class. */
    protected String className;
    /** Superclass' name. */
    protected String superClassName;
    /** Superclass' ClassDetails - only populated if superclass is also persistent. */
    protected ClassDetails superClassDetails;
    /** Define if lazy value holders should be weaved in this class. */
    protected boolean shouldWeaveValueHolders = false;
    /** Define if change tracking should be weaved in this class. */    
    protected boolean shouldWeaveChangeTracking = false;
    /** Define if fetch groups should be weaved in this class. */    
    protected boolean shouldWeaveFetchGroups = false;
    /** Map of this class' persistent attributes where the key is the Attribute name. */
    protected Map<String, AttributeDetails> attributesMap;
    /** Map of this class' persistent get methods where the key is the getMethod name. */
    protected Map<String, AttributeDetails> getterMethodToAttributeDetails;
    /** Map of this class' persistent set methods where the key is the setMethod name. */    
    protected Map<String, AttributeDetails> setterMethodToAttributeDetails;
    /** Set of mappings using value holder indirection. */
    protected List lazyMappings;
    /** Determine if a JPA "mapped superclass". */
    protected boolean isMappedSuperClass = false;
    /** Determine if a JPA "embedable" (aggregate). */
    protected boolean isEmbedable = false;
    /** Determine if class uses attribute access, lazily initialized. */
    protected Boolean usesAttributeAccess = null;
    
    public ClassDetails() {
    }
    
    public Class getDescribedClass(){
        return describedClass;
    }
    
    public String getClassName() {
        return className;
    }
    
    public void setDescribedClass(Class describedClass){
        this.describedClass = describedClass;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }

    public String getSuperClassName() {
        return superClassName;
    }
    
    public void setSuperClassName(String superClassName) {
        this.superClassName = superClassName;
    }

    public ClassDetails getSuperClassDetails() {
        return superClassDetails;
    }
    
    public void setSuperClassDetails(ClassDetails superClassDetails) {
        this.superClassDetails = superClassDetails;
    }

    public boolean shouldWeaveValueHolders() {
        return shouldWeaveValueHolders;
    }
    
    public void setShouldWeaveValueHolders(boolean shouldWeaveValueHolders) {
        this.shouldWeaveValueHolders = shouldWeaveValueHolders;
    }
    
    public boolean shouldWeaveChangeTracking() {
        return shouldWeaveChangeTracking;
    }
    
    public void setShouldWeaveChangeTracking(boolean shouldWeaveChangeTracking) {
        this.shouldWeaveChangeTracking = shouldWeaveChangeTracking;
    }
    
    public boolean shouldWeaveFetchGroups() {
        return shouldWeaveFetchGroups;
    }
    
    public void setShouldWeaveFetchGroups(boolean shouldWeaveFetchGroups) {
        this.shouldWeaveFetchGroups = shouldWeaveFetchGroups;
    }
    
    public Map getAttributesMap() {
        return attributesMap;
    }

    public Map getGetterMethodToAttributeDetails(){
        return getterMethodToAttributeDetails;
    }
    
    public List getLazyMappings(){
        return lazyMappings;
    }
    
    public Map getSetterMethodToAttributeDetails(){
        return setterMethodToAttributeDetails;
    }
    
    public void setAttributesMap(Map attributesMap) {
        this.attributesMap = attributesMap;
    }
    
    public void setGetterMethodToAttributeDetails(Map map){
        this.getterMethodToAttributeDetails = map;
    }
    
    public void setLazyMappings(List lazyMappings){
        this.lazyMappings = lazyMappings;
    }
    
    public boolean isMappedSuperClass(){
        return isMappedSuperClass;
    }
    
    public void setIsMappedSuperClass(boolean isMappedSuperClass){
        this.isMappedSuperClass = isMappedSuperClass;
    }
    
    public boolean isEmbedable(){
        return isEmbedable;
    }
    
    public void setIsEmbedable(boolean isEmbedable){
        this.isEmbedable = isEmbedable;
    }
    
    public void setSetterMethodToAttributeDetails(Map map){
        this.setterMethodToAttributeDetails = map;
    }
    
    /**
     * If one attribute of this class uses attribute access, by the JPA specification, all
     * attributes must use attribute access
     * 
     * This method assumes it is called when this class details is completely initialized.
     */
    public boolean usesAttributeAccess(){
        if (this.usesAttributeAccess != null){
            return this.usesAttributeAccess.booleanValue();
        } else {
            Iterator i = this.attributesMap.values().iterator();
            while (i.hasNext()){
                AttributeDetails details = (AttributeDetails)i.next();
                if (details.isMappedWithAttributeAccess()){
                    this.usesAttributeAccess = Boolean.TRUE;
                    return true;
                }
            }
            if (getSuperClassDetails() != null){
                return getSuperClassDetails().usesAttributeAccess();
            } else {
                this.usesAttributeAccess = Boolean.FALSE;
                return false;
            }
        }
    }
    
    public AttributeDetails getAttributeDetailsFromClassOrSuperClass(String attributeName){
        AttributeDetails attribute = attributesMap.get(attributeName);
        if (attribute == null && superClassDetails != null){
            return superClassDetails.getAttributeDetailsFromClassOrSuperClass(attributeName);
        }
       return attribute; 
    }
    
    public boolean doesSuperclassWeaveChangeTracking(){
        if (getSuperClassDetails() == null){
            return false;
        }
        if (getSuperClassDetails().shouldWeaveChangeTracking()) {
            return true;
        }
        
        return getSuperClassDetails().doesSuperclassWeaveChangeTracking();
    }
    
    public boolean canWeaveChangeTracking(){
        if ((getSuperClassDetails() == null) || (!shouldWeaveChangeTracking())) {
            return shouldWeaveChangeTracking();
        }
        
        return getSuperClassDetails().canWeaveChangeTracking();
    }
}
