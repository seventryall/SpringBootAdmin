package com.sj.springbootadmin;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import java.util.Collections;


@SpringBootApplication
public class SpringBootAdminApplication{

//@Bean
//public FilterRegistrationBean jwtFilter() {
//    final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//    JwtAuthenticationFilter filter = new JwtAuthenticationFilter();
//    registrationBean.setFilter(filter);
//    return registrationBean;
//}

    public static void main(String[] args) {
       SpringApplication.run(SpringBootAdminApplication.class, args);
    }

}
