package com.cleancoder.base.common.util;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Created by Leonid on 16.12.2014.
 */
public class ValueWrapper<V> {
    private V value;

    public ValueWrapper(V value) {
        this.value = value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ValueWrapper<?>)) {
            return false;
        }
        ValueWrapper other = (ValueWrapper) obj;
        return Objects.equal(value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("value", value)
                .toString();
    }

}
