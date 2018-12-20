package online.pubudu.springstarter.config;

import online.pubudu.springstarter.security.CustomAccessDeniedHandler;
import online.pubudu.springstarter.security.CustomAuthenticationEntrypoint;
import online.pubudu.springstarter.security.apikey.ApiKeyAuthenticationFilter;
import online.pubudu.springstarter.security.apikey.ApiKeyAuthenticationProvider;
import online.pubudu.springstarter.security.jwt.JwtAuthenticationFilter;
import online.pubudu.springstarter.security.jwt.JwtAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by pubudu welagedara on 12/17/18.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ApiKeyAuthenticationProvider apiKeyAuthenticationProvider;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private CustomAuthenticationEntrypoint customAuthenticationEntrypoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                httpBasic().disable().
                csrf().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().
                exceptionHandling().authenticationEntryPoint(customAuthenticationEntrypoint).
                accessDeniedHandler(new CustomAccessDeniedHandler());

        http.
                antMatcher("/public/**").
                authorizeRequests().
                anyRequest().permitAll();

        http.
                antMatcher("/protected/api-key/**").
                authorizeRequests().
                anyRequest().authenticated().
                and().
                addFilterBefore(new ApiKeyAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);

        http.
                antMatcher("/protected/jwt/**").
                authorizeRequests().
                anyRequest().authenticated().
                and().
                addFilterBefore(new JwtAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(apiKeyAuthenticationProvider)
                .authenticationProvider(jwtAuthenticationProvider);
    }
}
