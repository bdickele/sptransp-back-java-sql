package org.bdickele.sptransp.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bdickele.sptransp.domain.converter.LocalDateTimeConverter;
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
@SequenceGenerator(name="SEQ_MAIN", sequenceName=DomainConst.SEQUENCE_NAME)
@EqualsAndHashCode(of = "id", doNotUseGetters = true)
@ToString(of = {"id", "employeeId", "status", "rank", "department", "seniority"}, doNotUseGetters = true)
@Getter
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

    @Column(name = "VISA_RANK")
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
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "ID_REQUEST")
    private Request request;


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
    public static RequestAgreementVisa build(Request request, Long id, Long idEmployee, RequestAgreementVisaStatus status,
                                             Integer rank, String comment, Department department,
                                             Seniority seniority, LocalDateTime creationDate) {
        RequestAgreementVisa v = new RequestAgreementVisa();
        v.request = request;
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
}
