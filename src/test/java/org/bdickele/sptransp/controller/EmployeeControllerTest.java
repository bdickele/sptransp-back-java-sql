package org.bdickele.sptransp.controller;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bdickele.sptransp.controller.dto.EmployeeDTO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.tuple;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Bertrand DICKELE
 */
public class EmployeeControllerTest extends AbstractControllerTest {

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        mapper = new ObjectMapper();
        reader = mapper.reader(EmployeeDTO.class);
    }

    @Test
    public void all_destinations_should_be_returned_as_json() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        MappingIterator<EmployeeDTO> mappingIterator = reader.readValues(result);
        List<EmployeeDTO> dtoList = mappingIterator.readAll();

        assertThat(dtoList).hasSize(1);
        assertThat(dtoList)
                .hasSize(1)
                .extracting("uid", "fullName", "department.code", "seniority.value").containsExactly(
                    tuple("doejoh01", "John DOE", "LAW_COMPLIANCE", 60));
    }
}
