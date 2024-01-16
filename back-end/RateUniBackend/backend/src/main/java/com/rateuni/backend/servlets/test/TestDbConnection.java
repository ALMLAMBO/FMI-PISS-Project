package com.rateuni.backend.servlets.test;

import com.google.gson.Gson;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(
        name = "AsyncHelloWorldServlet",
        description = "Simple Async Hello World Servlet in order to understand how servlets are working",
        urlPatterns = {"/test-db-connection"},
        asyncSupported = true
)
public class TestDbConnection extends jakarta.servlet.http.HttpServlet {
    @Autowired
    private TestDataService testDataService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(
            jakarta.servlet.http.HttpServletRequest request,
            jakarta.servlet.http.HttpServletResponse response) throws jakarta.servlet.ServletException, IOException {

        response.setContentType("application/json");
        List<TestData> testData = testDataService.getAllData();
        Gson gson = new Gson();
        String json = gson.toJson(testData);

        PrintWriter writer = response.getWriter();
        writer.println(json);
    }
}
