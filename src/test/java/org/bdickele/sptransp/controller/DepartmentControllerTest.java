package org.bdickele.sptransp.controller;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.bdickele.sptransp.controller.dto.DepartmentDTO;
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
public class DepartmentControllerTest extends AbstractControllerTest {

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        mapper = new ObjectMapper();
        reader = mapper.reader(DepartmentDTO.class);
    }

    @Test
    public void get_departments_should_work() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/departments"))
                .andExpect(status().isOk())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        MappingIterator<DepartmentDTO> mappingIterator = reader.readValues(result);
        List<DepartmentDTO> dtoList = mappingIterator.readAll();

        assertThat(dtoList.size()).isGreaterThanOrEqualTo(4);
        Assertions.assertThat(dtoList).extracting("code", "name").contains(
                tuple("LAW_COMPLIANCE", "Law compliance"),
                tuple("SHUTTLE_COMPLIANCE", "Shuttle compliance"),
                tuple("GOODS_INSPECTION", "Goods inspection"),
                tuple("JOURNEY_SUPERVISION", "Journey supervision"));
    }
}
