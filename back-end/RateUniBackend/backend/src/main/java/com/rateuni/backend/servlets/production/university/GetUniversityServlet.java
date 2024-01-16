package com.rateuni.backend.servlets.production.university;

import com.google.gson.Gson;
import com.rateuni.backend.models.base_models.University;
import com.rateuni.backend.models.request_response.request.UniversityIdRequest;
import com.rateuni.backend.services.business_logic.UniversityService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;

@WebServlet(
        name = "GetAllUniversities",
        urlPatterns = {"/api/get-university"}
)
public class GetUniversityServlet extends HttpServlet {
    private UniversityService universityService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        universityService = new UniversityService();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        String input = new String(request.getInputStream().readAllBytes());
        Gson gson = new Gson();
        UniversityIdRequest universityIdRequest = gson.fromJson(input, UniversityIdRequest.class);
        response.setContentType("application/json");
        try {
            University university = universityService.getUniversity(universityIdRequest.getId());
            String json = gson.toJson(university);
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.println(json);
        }
        catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}