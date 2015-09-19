package org.bdickele.sptransp.controller;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bdickele.sptransp.controller.dto.GoodsDTO;
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
public class GoodsControllerTest extends AbstractControllerTest {

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        mapper = new ObjectMapper();
        reader = mapper.reader(GoodsDTO.class);
    }

    @Test
    public void all_destinations_should_be_returned_as_json() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/goods"))
                .andExpect(status().isOk())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        MappingIterator<GoodsDTO> mappingIterator = reader.readValues(result);
        List<GoodsDTO> dtoList = mappingIterator.readAll();

        assertThat(dtoList).hasSize(5);
        assertThat(dtoList).extracting("code", "name").containsExactly(
                tuple("FOOD", "Food"),
                tuple("MACHINE_TOOL", "Machine tool"),
                tuple("MEDICINE", "Medicine"),
                tuple("OIL", "Oil"),
                tuple("WEAPON", "Weapon"));
    }
}
