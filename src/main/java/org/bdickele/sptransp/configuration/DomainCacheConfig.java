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

    public static final String CUSTOMERS = "customers";
    public static final String DEPARTMENTS = "departments";
    public static final String DESTINATIONS = "destinations";
    public static final String EMPLOYEES = "employees";
    public static final String GOODS = "goods";

    @Bean
    public CacheManager cacheManager() {
        return new GuavaCacheManager();
    }
}
