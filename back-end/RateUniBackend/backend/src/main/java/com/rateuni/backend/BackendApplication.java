package com.rateuni.backend;

import com.rateuni.backend.servlets.test.TestDbConnection;
import com.rateuni.backend.servlets.test.HelloWorldServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    ServletRegistrationBean<HelloWorldServlet> helloWorldServlet() {
        ServletRegistrationBean<HelloWorldServlet> srb = new ServletRegistrationBean<>();
        srb.setServlet(new HelloWorldServlet());
        srb.setUrlMappings(List.of("/hello-world-servlet"));
        return srb;
    }

    @Bean
    ServletRegistrationBean<TestDbConnection> asyncHelloWorldServlet() {
        ServletRegistrationBean<TestDbConnection> srb = new ServletRegistrationBean<>();
        srb.setServlet(new TestDbConnection());
        srb.setUrlMappings(List.of("/test-db-connection"));
        return srb;
    }
}
