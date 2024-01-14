package com.rateuni.backend.servlets.production.requests;

import com.rateuni.backend.servlets.production.user.GetUserInfoServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RequestServletRegistration {
    @Bean
    ServletRegistrationBean<GetUserRequestServlet> getUserRequestServlet() {
        ServletRegistrationBean<GetUserRequestServlet> srb = new ServletRegistrationBean<>();
        srb.setServlet(new GetUserRequestServlet());
        srb.setUrlMappings(List.of("/api/get-user-requests"));
        return srb;
    }

    @Bean
    ServletRegistrationBean<GetReviewRequestServlet> getReviewRequestServlet() {
        ServletRegistrationBean<GetReviewRequestServlet> srb = new ServletRegistrationBean<>();
        srb.setServlet(new GetReviewRequestServlet());
        srb.setUrlMappings(List.of("/api/get-review-requests"));
        return srb;
    }
}
