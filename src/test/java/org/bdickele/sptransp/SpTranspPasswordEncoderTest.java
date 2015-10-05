package org.bdickele.sptransp;

import org.bdickele.sptransp.security.SpTranspPasswordEncoder;
import org.junit.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Bertrand DICKELE
 */
public class SpTranspPasswordEncoderTest {

    @Test
    public void password_encoder_should_encode_and_decode() {
        String pwd = "changeme";
        PasswordEncoder ourEncoder = SpTranspPasswordEncoder.getInstance();

        String encodedPwd = ourEncoder.encode(pwd);

        assertThat(ourEncoder.matches(pwd, encodedPwd)).isTrue();
    }
}
