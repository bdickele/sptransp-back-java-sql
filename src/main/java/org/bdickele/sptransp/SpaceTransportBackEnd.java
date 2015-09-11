package org.bdickele.sptransp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Bertrand DICKELE
 */
@ComponentScan
@EnableAutoConfiguration
public class SpaceTransportBackEnd {

    public static void main(String[] args) {
        SpringApplication.run(SpaceTransportBackEnd.class, args);
    }
}
