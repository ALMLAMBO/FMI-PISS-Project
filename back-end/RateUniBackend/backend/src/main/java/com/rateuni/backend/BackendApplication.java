package com.rateuni.backend;

import com.rateuni.backend.servlets.test.TestDbConnection;
import com.rateuni.backend.servlets.test.HelloWorldServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
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

//    @Bean
//    public DataSourceInitializer dataSourceInitializer(DataSource ds) {
//        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
//        resourceDatabasePopulator.addScript(new ClassPathResource("/seed_data.sql"));
//        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
//        dataSourceInitializer.setDataSource(ds);
//        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
//
//        return dataSourceInitializer;
//    }
}
