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

    @Bean
    public CacheManager cacheManager() {
        return new GuavaCacheManager();
    }
}
