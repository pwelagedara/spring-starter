package online.pubudu.springstarter.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import online.pubudu.springstarter.security.CustomAccessDeniedHandler;
import online.pubudu.springstarter.security.CustomAuthenticationEntrypoint;
import online.pubudu.springstarter.security.jwt.JwtAuthenticationFilter;
import online.pubudu.springstarter.security.jwt.JwtAuthenticationProvider;
import online.pubudu.springstarter.security.jwt.login.JwtLoginFilter;
import online.pubudu.springstarter.security.jwt.login.JwtLoginProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
* Created by pubudu welagedara on 12/17/18.
* */
@Configuration
@Profile("jwt")
@EnableWebSecurity(debug = true)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private JwtLoginProvider jwtLoginProvider;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomAuthenticationEntrypoint customAuthenticationEntrypoint;

    /*
    * Protected endpoints need ADMIN Role to work
    * If the Role is wrong a 403 Forbidden Response will be shown
    * Remove ".antMatchers("/protected/**").hasRole("ADMIN")" to allow any Role in
    * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

            http
                .httpBasic().disable()
                .csrf().disable()
                .headers().frameOptions().sameOrigin() // For H2 Console to work
                .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .exceptionHandling().authenticationEntryPoint(customAuthenticationEntrypoint)
                    .accessDeniedHandler(new CustomAccessDeniedHandler(objectMapper))
                .and()
                    .authorizeRequests()
                    .antMatchers("/public/**").permitAll()
                    .antMatchers("/h2-console/**").permitAll() // For H2 Console to work
                    .antMatchers("/auth/login").permitAll()
                    .antMatchers("/protected/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                .and()
                    .addFilterBefore(new JwtLoginFilter("/auth/login", authenticationManager(), authenticationSuccessHandler, authenticationFailureHandler, objectMapper), UsernamePasswordAuthenticationFilter.class)
                    .addFilterBefore(new JwtAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(jwtAuthenticationProvider)
                .authenticationProvider(jwtLoginProvider);
    }
}
