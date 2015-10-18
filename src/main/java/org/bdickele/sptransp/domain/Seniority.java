package org.bdickele.sptransp.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Class that wraps the notion of Seniority
 * Created by Bertrand DICKELE
 */
public class Seniority implements Serializable {

    private static final long serialVersionUID = -5289664116290695740L;

    private final Integer value;


    private Seniority(Integer value) {
        this.value = value==null ? 0 : Integer.valueOf(value);
    }

    public static Seniority of(Integer value) {
        return new Seniority(value);
    }

    public boolean eq(Seniority other) {
        return value.equals(other.value);
    }

    public boolean ge(Seniority other) {
        return value.compareTo(other.value) >= 0;
    }

    public boolean gt(Seniority other) {
        return value.compareTo(other.value) > 0;
    }

    public boolean le(Seniority other) {
        return value.compareTo(other.value) <= 0;
    }

    public boolean lt(Seniority other) {
        return value.compareTo(other.value) < 0;
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
