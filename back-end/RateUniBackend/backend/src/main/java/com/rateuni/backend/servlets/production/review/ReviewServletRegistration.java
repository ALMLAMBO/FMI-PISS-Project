package com.rateuni.backend.servlets.production.review;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReviewServletRegistration {
    @Bean
    ServletRegistrationBean<GetAllReviewsServlet> getAllReviewsServlet() {
        ServletRegistrationBean<GetAllReviewsServlet> srb = new ServletRegistrationBean<>();
        srb.setServlet(new GetAllReviewsServlet());
        srb.setUrlMappings(List.of("/api/get-all-reviews"));
        return srb;
    }
}
