package com.rateuni.backend.servlets.production.university;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UniversityServletRegistration {
    @Bean
    ServletRegistrationBean<GetAllUniversitiesServlet> getAllUniversitiesServlet() {
        ServletRegistrationBean<GetAllUniversitiesServlet> srb = new ServletRegistrationBean<>();
        srb.setServlet(new GetAllUniversitiesServlet());
        srb.setUrlMappings(List.of("/api/get-all-universities"));
        return srb;
    }

    @Bean
    ServletRegistrationBean<GetUniversityServlet> getUniversityServlet() {
        ServletRegistrationBean<GetUniversityServlet> srb = new ServletRegistrationBean<>();
        srb.setServlet(new GetUniversityServlet());
        srb.setUrlMappings(List.of("/api/get-university"));
        return srb;
    }
}
