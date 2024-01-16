package com.rateuni.backend.servlets.production.user;

import com.google.gson.Gson;
import com.rateuni.backend.models.base_models.*;
import com.rateuni.backend.models.request_response.request.UserInfoRequest;
import com.rateuni.backend.models.request_response.response.UniUserInfo;
import com.rateuni.backend.services.business_logic.DegreeService;
import com.rateuni.backend.services.business_logic.FacultyService;
import com.rateuni.backend.services.business_logic.UniversityService;
import com.rateuni.backend.services.business_logic.UserService;
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
        name = "GetAllUniversities",
        urlPatterns = {"/api/get-user-info"}
)
public class GetUserInfoServlet extends HttpServlet {
    private UniversityService universityService;
    private FacultyService facultyService;
    private UserService userService;
    private DegreeService degreeService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        universityService = new UniversityService();
        facultyService = new FacultyService();
        userService = new UserService();
        degreeService = new DegreeService();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        String input = new String(request.getInputStream().readAllBytes());
        Gson gson = new Gson();
        UserInfoRequest userInfoRequest = gson.fromJson(input, UserInfoRequest.class);
        response.setContentType("application/json");
        try {
            University university = universityService.getUniversityForUser(userInfoRequest.getUserId());
            Faculty faculty = facultyService.getFacultyOfUser(userInfoRequest.getUserId());
            UniUser user = userService.getUser(userInfoRequest.getUserId());
            Degree degree = degreeService.getDegreeForUser(userInfoRequest.getUserId(), userInfoRequest.getDegreeId());
            List<Discipline> disciplines = degreeService.getAllDisciplinesForDegree(userInfoRequest.getDegreeId());
            List<Review> reviews = userService.getAllReviewForUser(userInfoRequest.getUserId());

            UniUserInfo userInfo = new UniUserInfo();
            userInfo.setUniversity(university.getName());
            userInfo.setFaculty(faculty.getFacultyName());
            userInfo.setUsername(user.getUsername());
            userInfo.setUserId(user.getId());
            userInfo.setDegree(degree.getTitle());
            userInfo.setReviews(reviews);
            userInfo.setDisciplines(disciplines);

            String json = gson.toJson(userInfo);
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.println(json);
        }
        catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
