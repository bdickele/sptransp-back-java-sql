package org.bdickele.sptransp.configuration;


import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebMvcSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //.antMatchers("/").permitAll().and()
                .anyRequest().permitAll()
                .and().authorizeRequests().antMatchers("/console/**").permitAll();

        // Because of H2 database
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
        /*
        http.formLogin()
                .and().logout().and().authorizeRequests()
                .antMatchers("/index.html", "/home.html", "/login.html", "/").permitAll().anyRequest()
                .authenticated().and().csrf()
                .csrfTokenRepository(csrfTokenRepository()).and()
                .addFilterAfter(new CrossScriptRequestForgeryFilter(), CsrfFilter.class);
                */

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }

    /*
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("pass").roles("USER");
    }
    */
}
