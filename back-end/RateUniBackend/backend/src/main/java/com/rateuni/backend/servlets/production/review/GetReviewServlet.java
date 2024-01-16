package com.rateuni.backend.servlets.production.review;

import com.google.gson.Gson;
import com.rateuni.backend.models.base_models.Review;
import com.rateuni.backend.models.request_response.request.UniversityIdRequest;
import com.rateuni.backend.services.business_logic.ReviewService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;

@WebServlet(
        name = "GetReviewServlet",
        urlPatterns = {"/api/get-review"}
)
public class GetReviewServlet extends HttpServlet {
    private ReviewService reviewService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        reviewService = new ReviewService();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        String input = new String(request.getInputStream().readAllBytes());
        Gson gson = new Gson();
        UniversityIdRequest universityIdRequest = gson.fromJson(input, UniversityIdRequest.class);
        response.setContentType("application/json");
        try {
            Review review = reviewService.getReview(universityIdRequest.getId());
            String json = gson.toJson(review);
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.println(json);
        }
        catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
