package org.bdickele.sptransp.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Class that wraps the notion of Seniority
 * Created by Bertrand DICKELE
 */
public class Seniority {

    private final Integer value;


    public Seniority() {
        this(0);
    }

    public Seniority(int value) {
        this.value = Integer.valueOf(value);
    }

    /**
     * @param other
     * @return True if seniority is equal or greater than passed seniority
     */
    public boolean ge(Seniority other) {
        return value.compareTo(other.value) >= 0;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Seniority other = (Seniority) obj;
        return new EqualsBuilder()
                .append(this.value, other.value)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(7, 11)
                .append(value)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("value", value)
                .toString();
    }
}
