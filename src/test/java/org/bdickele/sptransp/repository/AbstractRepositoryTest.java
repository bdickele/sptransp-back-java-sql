package org.bdickele.sptransp.repository;

import org.bdickele.sptransp.configuration.IntegrationTestConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Bertrand DICKELE
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = IntegrationTestConfig.class)
public abstract class AbstractRepositoryTest {
    //
}
