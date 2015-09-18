package org.bdickele.sptransp.controller;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.bdickele.sptransp.controller.dto.DestinationDTO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.tuple;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration test for DestinationController
 * Created by Bertrand DICKELE
 */
public class DestinationControllerTest extends AbstractControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    private ObjectMapper mapper;

    private ObjectReader reader;


    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        mapper = new ObjectMapper();
        reader = mapper.reader(DestinationDTO.class);
    }

    @Test
    public void all_destinations_should_be_returned_as_json() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/destinations"))
                .andExpect(status().isOk())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        MappingIterator<DestinationDTO> mappingIterator = reader.readValues(result);
        List<DestinationDTO> dtoList = mappingIterator.readAll();
        //dtoList.forEach(d -> System.out.println("> " + d));

        assertThat(dtoList).hasSize(3);
        assertThat(dtoList).extracting("code", "name").contains(
                tuple("MOON", "Moon"),
                tuple("MARS", "Mars"),
                tuple("TITAN", "Titan"));
    }
}
