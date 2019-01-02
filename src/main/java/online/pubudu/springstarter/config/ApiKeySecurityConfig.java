package online.pubudu.springstarter.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import online.pubudu.springstarter.security.CustomAccessDeniedHandler;
import online.pubudu.springstarter.security.CustomAuthenticationEntrypoint;
import online.pubudu.springstarter.security.apikey.ApiKeyAuthenticationFilter;
import online.pubudu.springstarter.security.apikey.ApiKeyAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
* Created by pubudu welagedara on 12/17/18.
* */
@Configuration
@Profile("apikey")
@EnableWebSecurity(debug = true)
public class ApiKeySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ApiKeyAuthenticationProvider apiKeyAuthenticationProvider;

    @Autowired
    private CustomAuthenticationEntrypoint customAuthenticationEntrypoint;

    @Autowired
    private ObjectMapper objectMapper;

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
                    .antMatchers("/v2/api-docs").permitAll() // For Swagger Documents to work
                    .anyRequest().authenticated()
                .and()
                    .addFilterBefore(new ApiKeyAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(apiKeyAuthenticationProvider);
    }
}
