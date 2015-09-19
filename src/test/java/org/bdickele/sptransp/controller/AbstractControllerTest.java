package org.bdickele.sptransp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.bdickele.sptransp.SpaceTransportBackEnd;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

/**
 * Base test for integration test cases of REST controllers
 * Created by Bertrand DICKELE
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpaceTransportBackEnd.class)
@WebAppConfiguration
public abstract class AbstractControllerTest {

    @Autowired
    protected WebApplicationContext context;

    protected MockMvc mvc;

    protected ObjectMapper mapper;

    protected ObjectReader reader;
}
