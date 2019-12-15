package com.example.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class MySavedRequestAwareAuthenticationFailureHandler
    extends SimpleUrlAuthenticationFailureHandler {
  @Bean
  @Primary
  public MySavedRequestAwareAuthenticationFailureHandler failureHandler() {
    return new MySavedRequestAwareAuthenticationFailureHandler();
  }
}
