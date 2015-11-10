package org.bdickele.sptransp.service;

import org.bdickele.sptransp.domain.*;
import org.bdickele.sptransp.domain.audit.AgreementRuleAud;
import org.bdickele.sptransp.exception.SpTranspBizError;
import org.bdickele.sptransp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by Bertrand DICKELE
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class RequestService extends AbstractService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private AgreementRuleAudRepository ruleAudRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    @Transactional(propagation = Propagation.REQUIRED)
    public Request create(String goodsCode, String departureCode, String arrivalCode, String customerUid) {
        Customer customer = customerRepository.findByUid(customerUid);
        Goods goods = goodsRepository.findByCode(goodsCode);
        Destination departure = destinationRepository.findByCode(departureCode);
        Destination arrival = destinationRepository.findByCode(arrivalCode);
        return create(goods, departure, arrival, customer);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Request create(Goods goods, Destination departure, Destination arrival, Customer customer) {
        AgreementRuleAud ruleAud = ruleAudRepository.findTopByDestinationIdAndGoodsIdOrderByPkVersionDesc(arrival.getId(), goods.getId());
        Request request = Request.build(null, generateRequestReference(), customer, goods, departure, arrival, ruleAud);
        em().persist(request);
        return request;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Request update(String requestReference, String employeeUid, RequestAgreementVisaStatus visaStatus, String comment) {
        Request request = requestRepository.findByReference(requestReference);
        if (request==null) throw SpTranspBizError.REQUEST_NOT_FOUND_FOR_REFERENCE.exception(requestReference);

        Employee employee = employeeRepository.findByUid(employeeUid);
        if (employee==null) throw SpTranspBizError.EMPLOYEE_NOT_FOUND.exception(employeeUid);

        return update(request, employee, visaStatus, comment);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Request update(Request request, Employee employee, RequestAgreementVisaStatus visaStatus, String comment) {
        request.applyAgreementVisa(employee, visaStatus, comment);
        return request;
    }

    public static String generateRequestReference() {
        Random random = new Random();
        StringBuilder word = new StringBuilder(10);
        IntStream.range(0, 6).forEach(i -> word.append((char)('a' + random.nextInt(26))));
        IntStream.range(0, 4).forEach(i -> word.append(random.nextInt(10)));
        return word.toString().toUpperCase();
    }
}
