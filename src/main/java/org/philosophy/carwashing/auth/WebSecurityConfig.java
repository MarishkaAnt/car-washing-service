package org.philosophy.carwashing.auth;

import lombok.RequiredArgsConstructor;
import org.philosophy.carwashing.auth.filter.CustomAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
/*
        CustomAuthenticationFilter customAuthenticationFilter =
                new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/v1/login");
*/
        http.cors().disable();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().anyRequest().permitAll();
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
/*
        http.authorizeRequests().antMatchers("/api/v1/login/**").permitAll();
        http.authorizeRequests().antMatchers("/api/v1/**").permitAll();
        http.addFilter(customAuthenticationFilter);
        http.authorizeRequests().antMatchers("/api/v1/**").authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/api/v1/wash-types", true)
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
                .logout()
                .deleteCookies("JSESSIONID");
*/

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}