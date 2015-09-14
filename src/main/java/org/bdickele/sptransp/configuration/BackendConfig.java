package org.bdickele.sptransp.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Overall configuration of back-end application
 * Created by Bertrand DICKELE
 */
@Configuration
@Import({PersistenceConfig.class, DomainCacheConfig.class})
@ComponentScan
public class BackendConfig {
}
