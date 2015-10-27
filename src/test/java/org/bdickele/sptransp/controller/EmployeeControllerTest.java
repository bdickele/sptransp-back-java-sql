package org.bdickele.sptransp.controller;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import org.bdickele.sptransp.controller.dto.EmployeeDTO;
import org.bdickele.sptransp.service.EmployeeServiceTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.sql.DataSource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.tuple;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Bertrand DICKELE
 */
public class EmployeeControllerTest extends AbstractControllerTest {

    @Autowired
    private DataSource dataSource;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        mapper = new ObjectMapper();
        reader = mapper.reader(EmployeeDTO.class);
    }

    @After
    public void after() {
        new DbSetup(new DataSourceDestination(dataSource), EmployeeServiceTest.TEST_EMPLOYEE_DELETE).launch();
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
                tuple("kvcquz31", "Kathleen Carpenter", "JOURNEY_SUPERVISION", 80),
                tuple("whlofu42", "Helen Cox", "LAW_COMPLIANCE", 60));
    }

    @Test
    public void insertion_and_update_of_employee_should_work() throws Exception {
        new DbSetup(new DataSourceDestination(dataSource), EmployeeServiceTest.TEST_EMPLOYEE_DELETE).launch();

        // ==== INSERTION ====

        MvcResult mvcResult = mvc.perform(post("/employees/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(
                        "{\"fullName\": \"EMPLOYEE_NAME\", \"profileCode\": \"ADMIN_ALL\", " +
                        "\"departmentCode\": \"LAW_COMPLIANCE\", \"seniority\": 60}"))
                .andExpect(status().isCreated())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        EmployeeDTO employee = reader.readValue(result);
        assertThat(employee).isNotNull();
        assertThat(employee.getDepartmentCode()).isEqualTo("LAW_COMPLIANCE");
        assertThat(employee.getSeniority()).isEqualTo(60);

        String uid = employee.getUid();
        assertThat(uid).isEqualTo("employ1");

        // ==== UPDATE ====

        mvcResult = mvc.perform(put("/employees/employ1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(
                        "{\"uid\": \"employ1\", \"fullName\": \"EMPLOYEE_NAME 2\", " +
                                "\"profileCode\": \"ADMIN_READER\", \"departmentCode\": \"SHUTTLE_COMPLIANCE\", " +
                                "\"seniority\": 80}"))
                .andExpect(status().isOk())
                .andReturn();

        result = mvcResult.getResponse().getContentAsString();

        EmployeeDTO dto = reader.readValue(result);
        assertThat(dto.getFullName()).isEqualTo("EMPLOYEE_NAME 2");
        assertThat(dto.getProfileCode()).isEqualTo("ADMIN_READER");
        assertThat(dto.getDepartmentCode()).isEqualTo("SHUTTLE_COMPLIANCE");
        assertThat(dto.getSeniority()).isEqualTo(80);
    }

}
