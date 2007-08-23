/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.internal.identitymaps;

import java.io.*;
import java.util.Arrays;
import java.util.Vector;
import org.eclipse.persistence.internal.helper.*;
import org.eclipse.persistence.sessions.Record;

/**
 * <p><b>Purpose</b>: Container class for storing objects in an IdentityMap.
 * <p><b>Responsibilities</b>:<ul>
 * <li> Hold key and object.
 * <li> Maintain and update the current writeLockValue.
 * </ul>
 * @since TOPLink/Java 1.0
 */
public class CacheKey implements Serializable, Cloneable {

    /** The key holds the vector of primary key values for the object. */
    protected Vector key;

    /** Calculated hash value for CacheKey from primary key values. */
    protected int hash;
    protected Object object;
    
    //used to store a reference to the map this cachkey is in in cases where the
    //cache key is to be removed, prevents us from having to track down the owning
    //map
    protected IdentityMap mapOwner;

    /** The writeLock value is being held as an object so that it might contain a number or timestamp. */
    protected Object writeLockValue;

    /** The cached wrapper for the object, used in EJB. */
    protected Object wrapper;

    /** The cache key hold a reference to the concurrency manager to perform the cache key level locking. */
    protected ConcurrencyManager mutex;

    /** This is used for Document Preservation to cache the record that this object was built from */
    protected Record record;

    /** This attribute is the system time in milli seconds that the object was last refreshed on */

    //CR #4365 
    // CR #2698903 - fix for the previous fix. No longer using millis.
    protected long lastUpdatedQueryId;

    /** Invalidation State can be used to indicate whether this cache key is considered valid */
    protected int invalidationState = CHECK_INVALIDATION_POLICY;

    /** The following constants are used for the invalidationState variable */
    public static final int CHECK_INVALIDATION_POLICY = 0;
    public static final int CACHE_KEY_INVALID = -1;

    /** The read time stores the millisecond value of the last time the object help by
    this cache key was confirmed as up to date. */
    protected long readTime = 0;

    public CacheKey(Vector primaryKey) {
        this.key = primaryKey;
        this.hash = computeHash(primaryKey);
    }

    public CacheKey(Vector primaryKey, Object object, Object lockValue) {
        this.key = primaryKey;
        this.hash = computeHash(primaryKey);
        this.writeLockValue = lockValue;
        //bug4649617  use setter instead of this.object = object to avoid hard reference on object in subclasses
        if (object != null) {
            setObject(object);
        }
    }

    public CacheKey(Vector primaryKey, Object object, Object lockValue, long readTime) {
        this(primaryKey, object, lockValue);
        this.readTime = readTime;
    }

    /**
     * Acquire the lock on the cachek key object.
     */
    public void acquire() {
        getMutex().acquire(false);
    }

    /**
     * Acquire the lock on the cachek key object. For the merge process
     * called with true from the merge process, if true then the refresh will not refresh the object
     */
    public void acquire(boolean forMerge) {
        getMutex().acquire(forMerge);
    }

    /**
     * Acquire the lock on the cache key object. But only if the object has no lock on it
     * Added for CR 2317
     */
    public boolean acquireNoWait() {
        return getMutex().acquireNoWait(false);
    }

    /**
     * Acquire the lock on the cache key object. Only acquire a lock if the cache key's
     * active thread is not set.
     * Added for Bug 5840635
     */

    public boolean acquireIfUnownedNoWait() {
        return getMutex().acquireIfUnownedNoWait(false);
    }

    /**
     * Acquire the lock on the cache key object. But only if the object has no lock on it
     * Added for CR 2317
     * called with true from the merge process, if true then the refresh will not refresh the object
     */
    public boolean acquireNoWait(boolean forMerge) {
        return getMutex().acquireNoWait(forMerge);
    }

    /**
     * Acquire the deferred lcok.
     */
    public void acquireDeferredLock() {
        getMutex().acquireDeferredLock();
    }
    
    /**
     * Check the read lock on the cachek key object.
     * This can be called to ensure the cache key has a valid built object.
     * It does not hold a lock, so the object could be refreshed afterwards.
     */
    public void checkReadLock() {
        getMutex().checkReadLock();
    }
        
    /**
     * Check the deferred lock on the cachek key object.
     * This can be called to ensure the cache key has a valid built object.
     * It does not hold a lock, so the object could be refreshed afterwards.
     */
    public void checkDeferredLock() {
        getMutex().checkDeferredLock();
    }
    
    /**
     * Acquire the read lock on the cachek key object.
     */
    public void acquireReadLock() {
        getMutex().acquireReadLock();
    }

    /**
     * Acquire the read lock on the cache key object.
     */
    public boolean acquireReadLockNoWait() {
        return getMutex().acquireReadLockNoWait();
    }

    /**
     * INTERNAL:
     * Clones itself.
     */
    public Object clone() {
        Object object = null;

        try {
            object = super.clone();
        } catch (Exception exception) {
            throw new InternalError(exception.toString());
        }

        return object;
    }

    /**
     * Compute a hash value for the CacheKey dependent upon the values of the primary key
     * instead of the identity of the receiver.
     * This method is intended for use by constructors only.
     */
    protected int computeHash(Vector primaryKey) {
        int computedHashValue = 0;
        int size = primaryKey.size();
        for (int index = 0; index < size; index++) {
            Object value = primaryKey.get(index);
            if (value != null) {
            	//bug5709489, gf bug 1193: fix to handle array hashcodes properly
            	if (value.getClass().isArray()) {
            		computedHashValue = computedHashValue ^ computeArrayHashCode(value);
            	} else {
            		computedHashValue = computedHashValue ^ (value.hashCode());
            	}
            }
        }
        return computedHashValue;
    }

    /**
     * Compute the hashcode for supported array types
     * @param obj - an array
     * @return hashCode value
     */
    private int computeArrayHashCode(Object obj) {
        if (obj.getClass() == ClassConstants.APBYTE) {
            return Arrays.hashCode((byte []) obj);
        } else if (obj.getClass() == ClassConstants.APCHAR) {
            return Arrays.hashCode((char []) obj);
        } else {
            return Arrays.hashCode((Object []) obj);
        }
    }

    /**
     * Determine if the receiver is equal to anObject.
     * If anObject is a CacheKey, do further comparison, otherwise, return false.
     * @see CacheKey#equals(CacheKey)
     */
    public boolean equals(Object object) {
        try {
            return equals((CacheKey)object);
        } catch (ClassCastException incorrectType) {
            return false;
        }
    }

    /**
     * Determine if the receiver is equal to key.
     * Use an index compare, because it is much faster than enumerations.
     */
    public boolean equals(CacheKey key) {
        if (this == key) {
            return true;
        }
        // PERF: Using direct variable access.
        int size = this.key.size();
        if (size == key.key.size()) {
            for (int index = 0; index < size; index++) {
                Object myValue = this.key.get(index);
                Object comparisionValue = key.key.get(index);

                if (myValue == null) {
                    if (comparisionValue != null) {
                        return false;
                    }
                } else if (myValue.getClass().isArray()) {
                    //gf bug 1193: fix array comparison logic to exit if they don't match, and continue the loop if they do
                    if (((myValue.getClass() == ClassConstants.APBYTE) && (comparisionValue.getClass() == ClassConstants.APBYTE)) ) {
                        if (!Helper.compareByteArrays((byte[])myValue, (byte[])comparisionValue)){
                            return false;
                        }
                    } else if (((myValue.getClass() == ClassConstants.APCHAR) && (comparisionValue.getClass() == ClassConstants.APCHAR)) ) {
                        if (!Helper.compareCharArrays((char[])myValue, (char[])comparisionValue)){
                            return false;
                        }
                    } else {
                        if (!Helper.compareArrays((Object[])myValue, (Object[])comparisionValue)) {
                            return false;
                        }
                    }
                } else {
                    if (!(myValue.equals(comparisionValue))) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * INTERNAL:
     * This method returns the system time in millis seconds at which this object was last refreshed
     * CR #4365
     * CR #2698903 ... instead of using millis we will now use id's instead. Method
     * renamed appropriately.
     */
    public long getLastUpdatedQueryId() {
        return this.lastUpdatedQueryId;
    }

    public Vector getKey() {
        return key;
    }

    /**
     * Return the concurrency manager.
     */
    public ConcurrencyManager getMutex() {
        if (mutex == null) {
            synchronized (this) {
                if (mutex == null) {
                    mutex = new ConcurrencyManager(this);
                }
            }
        }
        return mutex;
    }

    public Object getObject() {
        return object;
    }

    public IdentityMap getOwningMap(){
        return this.mapOwner;
    }
    
    /**
     * INTERNAL:
     * Return the current value of the Read Time variable
     */
    public long getReadTime() {
        return readTime;
    }

    public Record getRecord() {
        return record;
    }

    public Object getWrapper() {
        return wrapper;
    }

    public Object getWriteLockValue() {
        return writeLockValue;
    }

    /**
     * Overrides hashCode() in Object to use the primaryKey's hashCode for storage in data structures.
     */
    public int hashCode() {
        return hash;
    }

    /**
     * Return if the lock is acquired
     */
    public boolean isAcquired() {
        return getMutex().isAcquired();
    }

    /**
     * INTERNAL:
     * Return the value of the invalidationState Variable
     * The return value will be a constant
     * CHECK_INVALIDATION_POLICY - The Invalidation policy is must be checked for this cache key's sate
     * CACHE_KEY_INVALID - This cache key has been labeled invalid.
     */
    public int getInvalidationState() {
        return invalidationState;
    }

    /**
     * Release the lock on the cachek key object.
     */
    public void release() {
        getMutex().release();
    }

    /**
     * Release the deferred lock
     */
    public void releaseDeferredLock() {
        getMutex().releaseDeferredLock();
    }

    /**
     * Release the read lock on the cachek key object.
     */
    public void releaseReadLock() {
        getMutex().releaseReadLock();
    }

    /**
     * INTERNAL:
     * Set the value of the invalidationState Variable
     * The possible values are from an enumeration of constants
     * CHECK_INVALIDATION_POLICY - The invalidation policy is must be checked for this cache key's sate
     * CACHE_KEY_INVALID - This cache key has been labeled invalid.
     */
    public void setInvalidationState(int invalidationState) {
        this.invalidationState = invalidationState;
    }

    /**
     * INTERNAL:
     * This method sets the system time in millis seconds at which this object was last refreshed
     * CR #4365
     * CR #2698903 ... instead of using millis we will now use ids instead. Method
     * renamed appropriately.
     */
    public void setLastUpdatedQueryId(long id) {
        this.lastUpdatedQueryId = id;
    }

    public void setKey(Vector key) {
        this.key = key;
        this.hash = computeHash(key);
    }

    /**
     * Set the concurrency manager.
     */
    public void setMutex(ConcurrencyManager mutex) {
        this.mutex = mutex;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public void setOwningMap(IdentityMap map){
        this.mapOwner = map;
    }
    
    /**
     * INTERNAL:
     * Set the read time of this cache key
     */
    public void setReadTime(long readTime) {
        this.readTime = readTime;
        invalidationState = CHECK_INVALIDATION_POLICY;
    }

    public void setRecord(Record newRecord) {
        this.record = newRecord;
    }

    public void setWrapper(Object wrapper) {
        this.wrapper = wrapper;
    }

    public void setWriteLockValue(Object writeLockValue) {
        this.writeLockValue = writeLockValue;
    }

    public String toString() {
        int hashCode = 0;
        if (getObject() != null) {
            hashCode = getObject().hashCode();
        }

        return "[" + getKey() + ": " + hashCode + ": " + getWriteLockValue() + ": " + getReadTime() + ": " + getObject() + "]";
    }

    /**
     * Notifies that cache key that it has been accessed.
     * Allows the LRU sub-cache to be maintained.
     */
    public void updateAccess() {
        // Nothing required by default.
    }
}