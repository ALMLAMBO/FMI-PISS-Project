package com.rateuni.backend.servlets.production.university;

import com.google.gson.Gson;
import com.rateuni.backend.models.base_models.University;
import com.rateuni.backend.services.business_logic.UniversityService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.ExecutionException;

@WebServlet(
        name = "GetAllUniversities",
        urlPatterns = {"/api/get-all-universities"}
)
public class GetAllUniversitiesServlet extends HttpServlet {
    private UniversityService universityService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        universityService = new UniversityService();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        try {
            List<University> universities = universityService.getAllUniversities();
            Gson gson = new Gson();
            String json = gson.toJson(universities);
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.println(json);
        }
        catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
