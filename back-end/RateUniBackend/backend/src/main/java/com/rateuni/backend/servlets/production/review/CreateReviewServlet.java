package com.rateuni.backend.servlets.production.review;

import com.google.gson.Gson;
import com.rateuni.backend.models.base_models.Review;
import com.rateuni.backend.services.business_logic.ReviewService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "CreateReviewServlet",
        urlPatterns = {"/api/create-review"}
)
public class CreateReviewServlet extends HttpServlet {
    private ReviewService reviewService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        reviewService = new ReviewService();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String requestBody = new String(request.getInputStream().readAllBytes());
        Gson gson = new Gson();
        Review review = gson.fromJson(requestBody, Review.class);
        reviewService.createReview(review);
        String reviewResponse = gson.toJson("Your review will be verified by an admin");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.println(reviewResponse);
    }
}