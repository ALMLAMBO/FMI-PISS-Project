package com.rateuni.backend.servlets.production.review;

import com.google.gson.Gson;
import com.rateuni.backend.models.base_models.Review;
import com.rateuni.backend.models.base_models.University;
import com.rateuni.backend.services.ReviewService;
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
        urlPatterns = {"/api/get-all-reviews"}
)
public class GetAllReviewsServlet extends HttpServlet {
    private ReviewService reviewService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        reviewService = new ReviewService();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        try {
            List<Review> universities = reviewService.getAllReviews();
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
