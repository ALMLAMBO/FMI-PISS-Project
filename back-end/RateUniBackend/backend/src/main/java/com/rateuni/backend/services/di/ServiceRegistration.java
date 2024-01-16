package com.rateuni.backend.services.di;

import com.rateuni.backend.services.business_logic.*;
import com.rateuni.backend.services.jwt.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ServiceRegistration {
    @Bean
    public DegreeService degreeService() {
        return new DegreeService();
    }

    @Bean
    public DisciplineService disciplineService() {
        return new DisciplineService();
    }

    @Bean
    public FacultyService facultyService() {
        return new FacultyService();
    }

    @Bean
    public RequestService requestService() {
        return new RequestService();
    }

    @Bean
    public ReviewService reviewService() {
        return new ReviewService();
    }

    @Bean
    public UniversityService universityService() {
        return new UniversityService();
    }

    @Bean
    public UserService userService() {
        return new UserService();
    }

    @Bean
    public JwtService jwtService() {
        return new JwtService();
    }
}
