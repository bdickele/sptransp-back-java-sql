package org.bdickele.sptransp.controller;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import org.assertj.core.api.StrictAssertions;
import org.bdickele.sptransp.controller.dto.EmployeeDTO;
import org.bdickele.sptransp.domain.Employee;
import org.bdickele.sptransp.repository.EmployeeRepository;
import org.bdickele.sptransp.service.EmployeeServiceTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.sql.DataSource;
import java.security.Principal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.tuple;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Bertrand DICKELE
 */
public class EmployeeControllerTest extends AbstractControllerTest {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private DataSource dataSource;

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

        // I check only one user (and not the modified one)
        assertThat(dtoList.size()).isGreaterThanOrEqualTo(5);
        assertThat(dtoList).extracting("uid", "fullName", "departmentCode", "seniority").contains(
                tuple("kvcquz31", "Kathleen Carpenter", "JOURNEY_SUPERVISION", 20),
                tuple("whlofu42", "Helen Cox", "LAW_COMPLIANCE", 20));
    }

    @Test
    public void update_of_employee_should_work() throws Exception {
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource),
                EmployeeServiceTest.TEST_EMPLOYEE_DELETE);
        dbSetup.launch();

        String uid = "EMPLOYEE1";
        Employee employee = repository.findByUid(uid);
        StrictAssertions.assertThat(employee).isNull();

        dbSetup = new DbSetup(new DataSourceDestination(dataSource),
                EmployeeServiceTest.TEST_EMPLOYEE_INSERT);
        dbSetup.launch();

        employee = repository.findByUid(uid);
        assertThat(employee).isNotNull();

        assertThat(employee.getDepartment().getCode()).isEqualTo("LAW_COMPLIANCE");
        assertThat(employee.getSeniority().getValue()).isEqualTo(60);

        MvcResult mvcResult = mvc.perform(put("/employees/EMPLOYEE1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(
                        "{\"uid\": \"EMPLOYEE1\", \"fullName\": \"EMPLOYEE1_NAME 2\", " +
                        "\"profileCode\": \"ADMIN_READER\", \"departmentCode\": \"SHUTTLE_COMPLIANCE\", " +
                        "\"seniority\": 80}")
                .principal(mockPrincipal))
                .andExpect(status().isOk())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        EmployeeDTO dto = reader.readValue(result);
        assertThat(dto.getFullName()).isEqualTo("EMPLOYEE1_NAME 2");
        assertThat(dto.getProfileCode()).isEqualTo("ADMIN_READER");
        assertThat(dto.getDepartmentCode()).isEqualTo("SHUTTLE_COMPLIANCE");
        assertThat(dto.getSeniority()).isEqualTo(80);
    }

}
