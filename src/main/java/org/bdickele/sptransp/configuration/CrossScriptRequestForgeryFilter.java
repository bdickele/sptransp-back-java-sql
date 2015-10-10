package org.bdickele.sptransp.configuration;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CrossScriptRequestForgeryFilter extends OncePerRequestFilter {

    public static final String COOKIE_NAME = "XSRF-TOKEN";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        CsrfToken csrf = getCrossSiteRequestForgeryToken(request);
        if (csrf != null) {
            Cookie cookie = WebUtils.getCookie(request, COOKIE_NAME);
            String token = csrf.getToken();
            if (isCookieIncorrectlySet(cookie, token)) {
                cookie = new Cookie(COOKIE_NAME, token);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
        filterChain.doFilter(request, response);

    }

    private boolean isCookieIncorrectlySet(Cookie cookie, String token) {
        return cookie == null || token != null
                && !token.equals(cookie.getValue());
    }

    private CsrfToken getCrossSiteRequestForgeryToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    }
}
