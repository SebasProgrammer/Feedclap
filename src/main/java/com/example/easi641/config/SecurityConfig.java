package com.example.easi641.config;


import com.example.easi641.security.RestAuthenticationEntryPoint;
import com.example.easi641.security.TokenAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public TokenAuthenticationFilter createTokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder createPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.cors()
                //.and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf()
                .disable()
                .formLogin()
                .disable()
                .httpBasic()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .and()
                .authorizeRequests()
                .antMatchers("/",
                        "/error",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/**/*.woff2"
                )
                .permitAll()

                .antMatchers(
                        "/login",
                        "/signup",
                        "/games",
                        "/games/search/{gameName}",
                        "/games/searches/{keyword}",
                        "/games/genre/{genre}",
                        "/users/cant_followers/{username}",
                        "/users/cant_following/{username}",
                        "/users/followers/{username}",
                        "/users/following/{username}",
                        "/users/cant_games/{username}",
                        "/users/follow",
                        "/users",
                        "/users/{username}",
                        "/review",
                        "/review/cant_reviews/{id_user}",
                        "/review/reviews/{id_user}",

                        "/v2/api-docs",
                        "/webjars/**",
                        "/swagger-resources/**"
                )
                .permitAll()
                .anyRequest()
                .authenticated()
        ;


        http.addFilterBefore(createTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}
