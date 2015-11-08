package org.bdickele.sptransp.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.bdickele.sptransp.controller.dto.AgreementRuleDTO;
import org.bdickele.sptransp.controller.dto.AgreementRuleVisaDTO;
import org.bdickele.sptransp.domain.AgreementRule;
import org.bdickele.sptransp.domain.Department;
import org.bdickele.sptransp.domain.Seniority;
import org.bdickele.sptransp.exception.SpTranspBizError;
import org.bdickele.sptransp.repository.AgreementRuleRepository;
import org.bdickele.sptransp.repository.DepartmentRepository;
import org.bdickele.sptransp.service.AgreementRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Bertrand DICKELE
 */
@RestController
@RequestMapping("/agreementRules")
public class AgreementRuleController extends AbstractController {

    @Autowired
    private AgreementRuleRepository repository;

    @Autowired
    private AgreementRuleService service;

    @Autowired
    private DepartmentRepository departmentRepository;


    @RequestMapping(
            method= RequestMethod.GET,
            produces="application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<AgreementRuleDTO> findRules(@RequestParam(value = "d", required = false) String destinationCode,
                                            @RequestParam(value = "g", required = false) String goodsCode) {
        if (!StringUtils.isEmpty(destinationCode)) {
            return findByDestinationCode(destinationCode);
        } else if (!StringUtils.isEmpty(goodsCode)) {
            return findByGoodsCode(goodsCode);
        } else {
            return findAll();
        }
    }

    private List<AgreementRuleDTO> findAll() {
        List<AgreementRule> rules = repository.findAll();
        return AgreementRuleDTO.build(rules);
    }

    private List<AgreementRuleDTO> findByDestinationCode(String destinationCode) {
        List<AgreementRule> rules = repository.findByDestinationCode(destinationCode.trim().toUpperCase());
        return AgreementRuleDTO.build(rules);
    }

    private List<AgreementRuleDTO> findByGoodsCode(String goodsCode) {
        List<AgreementRule> rules = repository.findByGoodsCode(goodsCode.trim().toUpperCase());
        return AgreementRuleDTO.build(rules);
    }

    @RequestMapping(
            value="/{destinationCode}/{goodsCode}",
            method= RequestMethod.GET,
            produces="application/json")
    @ResponseStatus(HttpStatus.OK)
    public AgreementRuleDTO findByDestinationCodeAndGoodsCode(@PathVariable String destinationCode,
                                                              @PathVariable String goodsCode) {
        AgreementRule rule = repository.findByDestinationCodeAndGoodsCode(
                destinationCode.trim().toUpperCase(), goodsCode.trim().toUpperCase());

        if (rule==null) {
            throw SpTranspBizError.AGR_RULE_DOESNT_EXIST.exception(destinationCode, goodsCode);
        }

        return AgreementRuleDTO.build(rule);
    }

    @RequestMapping(
            method= RequestMethod.POST,
            consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public AgreementRuleDTO create(@RequestBody AgreementRuleDTO dto) {
        AgreementRule rule = service.create(dto.getDestinationCode(), dto.getGoodsCode(), dto.isReqAllowed(),
                createVisasForService(dto), TEMP_USER_UID);
        return AgreementRuleDTO.build(rule);
    }

    @RequestMapping(
            method= RequestMethod.PUT,
            consumes="application/json")
    @ResponseStatus(HttpStatus.OK)
    public AgreementRuleDTO update(@RequestBody AgreementRuleDTO dto) {
        AgreementRule rule = service.update(dto.getDestinationCode(), dto.getGoodsCode(), dto.isReqAllowed(),
                createVisasForService(dto), TEMP_USER_UID);
        return AgreementRuleDTO.build(rule);
    }

    private List<Pair<Department, Seniority>> createVisasForService(AgreementRuleDTO dto) {
        List<AgreementRuleVisaDTO> visaDTOs = dto.getAgreementVisas();

        if (visaDTOs==null || visaDTOs.isEmpty()) return new ArrayList<>();

        // AgreementRuleDTO provides only department code
        List<Department> departments = departmentRepository.findAll();
        // Key = department code, Value = department
        Map<String, Department> mapDepartment = departments.stream()
                .collect(Collectors.toMap(Department::getCode, d -> d));

        return visaDTOs.stream()
                .map(v -> Pair.of(mapDepartment.get(v.getDepartmentCode()), Seniority.of(v.getSeniority())))
                .collect(Collectors.toList());
    }
}
