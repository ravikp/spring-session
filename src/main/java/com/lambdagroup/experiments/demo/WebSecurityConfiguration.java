package com.lambdagroup.experiments.demo;

import lombok.val;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        val requestHeaderAuthFilter = new RequestHeaderAuthenticationFilter();
        requestHeaderAuthFilter.setPrincipalRequestHeader("user");
        requestHeaderAuthFilter.setAuthenticationManager(getAuthenticationManager());

        http.csrf().disable()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests().antMatchers("/api/v1/**").permitAll()
            .anyRequest().denyAll()
            .and()
            .addFilter(requestHeaderAuthFilter)
        ;

    }

    private AuthenticationManager getAuthenticationManager() {
        return authentication -> {
            val userName = (String)authentication.getPrincipal();
            authentication.setAuthenticated("power".equalsIgnoreCase(userName));
            return authentication;
        };
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/actuator/**");
    }
}
