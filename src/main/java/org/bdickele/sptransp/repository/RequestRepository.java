package org.bdickele.sptransp.repository;

import org.bdickele.sptransp.domain.Customer;
import org.bdickele.sptransp.domain.Request;
import org.bdickele.sptransp.domain.RequestAgreementStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by bdickele
 */
public interface RequestRepository extends JpaRepository<Request, Long> {

    /**
     * @param reference
     * @return Request for passed reference
     */
    Request findByReference(String reference);


    List<Request> findByAgreementStatus(RequestAgreementStatus agreementStatus);

    List<Request> findByCustomerAndAgreementStatus(Customer customer, RequestAgreementStatus agreementStatus);
}
