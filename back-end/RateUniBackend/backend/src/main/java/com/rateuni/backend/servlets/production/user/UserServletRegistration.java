package com.rateuni.backend.servlets.production.user;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServletRegistration {
    @Bean
    ServletRegistrationBean<GetUserInfoServlet> getUserInfoServlet() {
        ServletRegistrationBean<GetUserInfoServlet> srb = new ServletRegistrationBean<>();
        srb.setServlet(new GetUserInfoServlet());
        srb.setUrlMappings(List.of("/api/get-user-info"));
        return srb;
    }
}
