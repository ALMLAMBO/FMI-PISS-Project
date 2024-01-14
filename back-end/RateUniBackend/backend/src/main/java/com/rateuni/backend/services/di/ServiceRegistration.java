package com.rateuni.backend.services.di;

import com.rateuni.backend.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class ServiceRegistration {
    private SimpleDIContainer diContainer;

    public ServiceRegistration() {
        diContainer = new SimpleDIContainer();

        diContainer.register(DegreeService.class, new DegreeService());
        diContainer.register(DisciplineService.class, new DisciplineService());
        diContainer.register(FacultyService.class, new FacultyService());
        diContainer.register(RequestService.class, new RequestService());
        diContainer.register(ReviewService.class, new ReviewService());
        diContainer.register(UniversityService.class, new UniversityService());
        diContainer.register(UserService.class, new UserService());
    }

    @Bean
    @PostConstruct
    public DegreeService degreeService() {
        return diContainer.resolve(DegreeService.class);
    }

    @Bean
    @PostConstruct
    public DisciplineService disciplineService() {
        return diContainer.resolve(DisciplineService.class);
    }

    @Bean
    @PostConstruct
    public FacultyService facultyService() {
        return diContainer.resolve(FacultyService.class);
    }

    @Bean
    @PostConstruct
    public RequestService requestService() {
        return diContainer.resolve(RequestService.class);
    }

    @Bean
    @PostConstruct
    public ReviewService reviewService() {
        return diContainer.resolve(ReviewService.class);
    }

    @Bean
    @PostConstruct
    public UniversityService universityService() {
        return diContainer.resolve(UniversityService.class);
    }

    @Bean
    @PostConstruct
    public UserService userService() {
        return diContainer.resolve(UserService.class);
    }
}
