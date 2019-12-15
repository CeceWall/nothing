package com.example.configure;

import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  @Override
  protected UserDetailsService userDetailsService() {
    return new UserService();
  }

  @Autowired
  protected MySavedRequestAwareAuthenticationFailureHandler failureHandler;

  @Autowired
  protected  MySavedRequestAwareAuthenticationSuccessHandler successHandler;

  @Autowired
  protected RestAuthenticationEntryPoint restAuthenticationEntryPoint;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(this.userDetailsService()).passwordEncoder(new CustomPasswordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .exceptionHandling()
        .authenticationEntryPoint(restAuthenticationEntryPoint)
        .and()
        .authorizeRequests()
//        .antMatchers("/", "/home", "/**/*").permitAll()
        .anyRequest().authenticated()
        .and().formLogin()
              .loginPage("/login")
              .failureUrl("/login?error")
              .successHandler(successHandler)
        .failureHandler(failureHandler)
        .permitAll()
        .and()
        .csrf()
        .disable()
        .logout().permitAll();
  }
}
