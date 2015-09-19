package org.bdickele.sptransp.controller;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bdickele.sptransp.controller.dto.EmployeeDTO;
import org.bdickele.sptransp.domain.Employee;
import org.bdickele.sptransp.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.Principal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.tuple;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Bertrand DICKELE
 */
public class EmployeeControllerTest extends AbstractControllerTest {

    @Autowired
    private EmployeeRepository repository;

    @Mock
    private Principal mockPrincipal;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        mapper = new ObjectMapper();
        reader = mapper.reader(EmployeeDTO.class);

        when(mockPrincipal.getName()).thenReturn("testuser");
    }

    @Test
    public void all_employees_should_be_returned_as_json() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        MappingIterator<EmployeeDTO> mappingIterator = reader.readValues(result);
        List<EmployeeDTO> dtoList = mappingIterator.readAll();

        assertThat(dtoList).hasSize(1);
        assertThat(dtoList)
                .hasSize(1)
                .extracting("uid", "fullName", "departmentCode", "seniority").containsExactly(
                    tuple("doejoh01", "John DOE", "LAW_COMPLIANCE", 60));
    }

    @Test
    public void update_of_employee_should_work() throws Exception {
        String uid = "doejoh01";
        Employee employee = repository.findByUid(uid);

        assertThat(employee.getDepartment().getCode()).isEqualTo("LAW_COMPLIANCE");
        assertThat(employee.getSeniority().getValue()).isEqualTo(60);

        MvcResult mvcResult = mvc.perform(put("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"uid\": \"doejoh01\", \"fullName\": \"John DOE2\", " +
                    "\"departmentCode\": \"SHUTTLE_COMPLIANCE\", \"seniority\": 80}")
                .principal(mockPrincipal))
                .andExpect(status().isOk())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        EmployeeDTO dto = reader.readValue(result);
        assertThat(dto.getFullName()).isEqualTo("John DOE2");
        assertThat(dto.getDepartmentCode()).isEqualTo("SHUTTLE_COMPLIANCE");
        assertThat(dto.getSeniority()).isEqualTo(80);
    }
}
