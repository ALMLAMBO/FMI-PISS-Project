package com.rateuni.backend.servlets.production.requests;

import com.google.gson.Gson;
import com.rateuni.backend.models.link_models.ReviewRequest;
import com.rateuni.backend.models.link_models.UserRequest;
import com.rateuni.backend.services.RequestService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.ExecutionException;

@WebServlet(
        name = "GetAllReviewRequests",
        urlPatterns = {"/api/get-review-requests"}
)
public class GetReviewRequestServlet extends HttpServlet {
    private RequestService requestService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        requestService = new RequestService();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        try {
            List<ReviewRequest> userRequests = requestService.getAllReviewRequests();
            Gson gson = new Gson();
            String json = gson.toJson(userRequests);
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.println(json);
        }
        catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
