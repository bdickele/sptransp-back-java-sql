package org.bdickele.sptransp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Bertrand DICKELE
 */
@SpringBootApplication
public class SpaceTransportBackEnd {

    public static void main(String[] args) {
        //System.setProperty("spring.profiles.active", "dev");
        SpringApplication.run(SpaceTransportBackEnd.class, args);
    }
}
