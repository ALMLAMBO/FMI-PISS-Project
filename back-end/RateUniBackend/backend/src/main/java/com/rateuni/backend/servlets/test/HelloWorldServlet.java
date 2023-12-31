package com.rateuni.backend.servlets.test;

import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "HelloWorldServlet",
        description = "Simple Hello World Servlet in order to understand how servlets are working",
        urlPatterns = {"/hello-world-servlet"}
)
public class HelloWorldServlet extends jakarta.servlet.http.HttpServlet {
    @Override
    protected void doGet(
            jakarta.servlet.http.HttpServletRequest request,
            jakarta.servlet.http.HttpServletResponse response) throws jakarta.servlet.ServletException, IOException {

        response.setContentType("application/json");
        String content = "Hello World from java servlets!";
        TestHelloWorld testHelloWorld = new TestHelloWorld();
        testHelloWorld.setData(content);
        Gson gson = new Gson();
//        String json = gson.toJson(content);
        //Test serialization of class
        String json = gson.toJson(testHelloWorld);
        PrintWriter writer = response.getWriter();
        writer.println(json);
    }
}
