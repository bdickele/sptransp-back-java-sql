package org.bdickele.sptransp.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Bertrand DICKELE
 */
@Configuration
@EnableCaching
public class DomainCacheConfig {

    public static final String CUSTOMER = "customer";
    public static final String DEPARTMENT = "department";
    public static final String DESTINATION = "destination";
    public static final String EMPLOYEE = "employee";
    public static final String GOOD = "good";

    @Bean
    public CacheManager cacheManager() {
        return new GuavaCacheManager();
    }
}
