package com.ucompensar.facialrecognition.view.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ucompensar.facialrecognition.view.filter.LoginFilter;

@Configuration
public class FilterConfig {

   @Bean
   public FilterRegistrationBean loginFilter() {
      final FilterRegistrationBean registration = new FilterRegistrationBean();
      registration.setFilter(new LoginFilter());
      registration.addUrlPatterns("/facial-recognition/pages/*", "/pages/*");
      return registration;
   }

}
