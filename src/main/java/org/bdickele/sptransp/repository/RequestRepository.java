package org.bdickele.sptransp.repository;

import org.bdickele.sptransp.domain.Request;
import org.bdickele.sptransp.domain.RequestAgreementStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * Created by bdickele
 */
public interface RequestRepository extends PagingAndSortingRepository<Request, Long> {

    /**
     * @param reference
     * @return Request for passed reference
     */
    Request findByReference(String reference);

    Page<Request> findByAgreementStatusIn(@Param("agreementStatus") Collection<RequestAgreementStatus> agreementStatus,
                                          Pageable pageRequest);

    List<Request> findByCustomerUidAndAgreementStatusInOrderByCreationDate(String customerUid, RequestAgreementStatus... agreementStatus);
}
