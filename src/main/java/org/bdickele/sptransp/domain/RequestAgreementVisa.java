package org.bdickele.sptransp.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bdickele.sptransp.domain.converter.LocalDateTImeConverter;
import org.bdickele.sptransp.domain.converter.RequestAgreementStatusConverter;
import org.bdickele.sptransp.domain.converter.SeniorityConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Agreement visas applied to a request : such a visa is Granted or Denied
 * Created by Bertrand DICKELE
 */
@Entity
@Table(name = "ST_REQUEST_AGR_VISA")
@SequenceGenerator(name="SEQ_MAIN", sequenceName="SEQ_MAIN")
public class RequestAgreementVisa implements Serializable {

    private static final long serialVersionUID = -8897770685598265624L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_MAIN")
    private Long id;

    // For now I don't map the full Employee, even is lazy mode
    // I'm not sure I need the full employee here (let's see)
    @Column(name = "ID_EMPLOYEE")
    private Long employeeId;

    @Column(name = "STATUS")
    @Convert(converter = RequestAgreementStatusConverter.class)
    private RequestAgreementVisaStatus status;

    @Column(name = "RANK")
    private Integer rank;

    @Column(name = "VISA_COMMENT")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "ID_DEPARTMENT")
    private Department department;

    @Column(name = "SENIORITY")
    @Convert(converter = SeniorityConverter.class)
    private Seniority seniority;

    @Column(name = "CREATION_DATE")
    @Convert(converter = LocalDateTImeConverter.class)
    private LocalDateTime creationDate;


    /**
     * Build method for a new RequestAgreementVisa
     * @param id
     * @param idEmployee
     * @param status
     * @param rank
     * @param comment
     * @param department
     * @param seniority
     * @param creationDate We can pass creation date so that it can be synchronized with update date of request
     * @return New instance of RequestAgreementVisa
     */
    public static RequestAgreementVisa build(Long id, Long idEmployee, RequestAgreementVisaStatus status,
                                             Integer rank, String comment, Department department,
                                             Seniority seniority, LocalDateTime creationDate) {
        RequestAgreementVisa v = new RequestAgreementVisa();
        v.id = id;
        v.employeeId = idEmployee;
        v.status = status;
        v.rank = rank;
        v.comment = comment;
        v.department = department;
        v.seniority = seniority;
        v.creationDate = creationDate;
        return v;
    }

    public Long getId() {
        return id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public RequestAgreementVisaStatus getStatus() {
        return status;
    }

    public Integer getRank() {
        return rank;
    }

    public String getComment() {
        return comment;
    }

    public Department getDepartment() {
        return department;
    }

    public Seniority getSeniority() {
        return seniority;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
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
        RequestAgreementVisa other = (RequestAgreementVisa) obj;
        return new EqualsBuilder()
                .append(this.id, other.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(7, 11)
                .append(id)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("employee", employeeId)
                .append("status", status)
                .append("rank", rank)
                .append("department", department.getName())
                .append("seniority", seniority.getValue())
                .toString();
    }
}
