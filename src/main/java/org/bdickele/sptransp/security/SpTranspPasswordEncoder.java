package org.bdickele.sptransp.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * Created by Bertrand DICKELE
 */
public class SpTranspPasswordEncoder implements PasswordEncoder {

    private static final String SECRET = "SpTr@nspS3cr3t";

    private StandardPasswordEncoder delegateEncoder;

    private static final PasswordEncoder INSTANCE = new SpTranspPasswordEncoder();


    /** Constructor */
    private SpTranspPasswordEncoder() {
        delegateEncoder = new StandardPasswordEncoder(SECRET);
    }

    public static PasswordEncoder getInstance() {
        return INSTANCE;
    }

    @Override
    public String encode(CharSequence charSequence) {
        return delegateEncoder.encode(charSequence);
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return delegateEncoder.matches(charSequence, s);
    }
}
