package org.bdickele.sptransp.controller;

import org.bdickele.sptransp.SpaceTransportBackEnd;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Base test for integration test cases of REST controllers
 * Created by Bertrand DICKELE
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpaceTransportBackEnd.class)
@WebAppConfiguration
public abstract class AbstractControllerTest {
    //
}
