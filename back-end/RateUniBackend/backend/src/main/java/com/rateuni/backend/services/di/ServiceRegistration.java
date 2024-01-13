package com.rateuni.backend.services.di;

import com.rateuni.backend.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceRegistration {
    private SimpleDIContainer diContainer;

    public ServiceRegistration() {
        diContainer = new SimpleDIContainer();

        diContainer.register(DegreeService.class, new DegreeService());
        diContainer.register(FacultyService.class, new FacultyService());
        diContainer.register(RequestService.class, new RequestService());
        diContainer.register(ReviewService.class, new ReviewService());
        diContainer.register(UniversityService.class, new UniversityService());
        diContainer.register(UserService.class, new UserService());
    }

    @Bean
    public DegreeService degreeService() {
        return diContainer.resolve(DegreeService.class);
    }

    @Bean
    public FacultyService facultyService() {
        return diContainer.resolve(FacultyService.class);
    }

    @Bean
    public RequestService requestService() {
        return diContainer.resolve(RequestService.class);
    }

    @Bean
    public ReviewService reviewService() {
        return diContainer.resolve(ReviewService.class);
    }

    public UniversityService universityService() {
        return diContainer.resolve(UniversityService.class);
    }

    public UserService userService() {
        return diContainer.resolve(UserService.class);
    }
}
