package com.rateuni.backend.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestDataService {
    @Autowired
    private final TestDataRepository testDataRepository;

    public TestDataService(TestDataRepository testDataRepository) {
        this.testDataRepository = testDataRepository;
    }

    public List<TestData> getAllData() {
        return testDataRepository.findAll();
    }
}
