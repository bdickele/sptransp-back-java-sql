package org.bdickele.sptransp.controller;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bdickele.sptransp.controller.dto.UserProfileDTO;
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
public class EmployeeProfileControllerTest extends AbstractControllerTest {

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        mapper = new ObjectMapper();
        reader = mapper.reader(UserProfileDTO.class);
    }

    @Test
    public void get_employee_profiles_should_work() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/employeeProfiles"))
                .andExpect(status().isOk())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        MappingIterator<UserProfileDTO> mappingIterator = reader.readValues(result);
        List<UserProfileDTO> dtoList = mappingIterator.readAll();

        assertThat(dtoList.size()).isEqualTo(6);
        assertThat(dtoList).extracting("code", "label").contains(
                tuple("ADMIN_ALL", "Admin all rights"),
                tuple("ADMIN_READER", "Admin reader"),
                tuple("AGR_VISA_APPLIER", "Agreement visa applier"),
                tuple("RULE_WRITER", "Rule writer"),
                tuple("HR_READER", "Human resources - Reader"),
                tuple("HR_WRITER", "Human resources - Writer"));
    }
}
