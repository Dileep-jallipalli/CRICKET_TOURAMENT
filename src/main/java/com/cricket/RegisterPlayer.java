package com.cricket;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterPlayer extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //super.doPost(req, resp);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        int userId = Integer.parseInt(request.getParameter("userId"));
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gender[] = request.getParameterValues("gender");
        String genderValue = "";
        for (int i = 0; i < gender.length; i++) {
            genderValue = genderValue + (gender[i] + "");
        }
        Integer mobileNumber = Integer.parseInt(request.getParameter("mobileNumber"));

        String role[] = request.getParameterValues("role");
        String roleValue = "";
        for (int i = 0; i < role.length; i++) {
            roleValue = roleValue + (role[i] + "");
        }
        String checkedSkills[] = request.getParameterValues("skills");
        String skills = "";
        for (int i = 0; i < checkedSkills.length; i++) {
            skills += checkedSkills[i] + " ";
        }
        DatabaseRepository databaseRepository = new DatabaseRepository();
        boolean insertions = databaseRepository.insertPlayerData(new User(name,userId,email,password,genderValue,mobileNumber,roleValue,skills));
        if(insertions){
            response.getWriter().write("Data Inserted successfully");
        }
        else{
            response.getWriter().write("Data not inserted properly");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie ck[]=req.getCookies();
        int userId= Integer.parseInt(ck[0].getValue());
        System.out.println(userId);
        DatabaseRepository databaseRepository = new DatabaseRepository();
        JSONObject jsonObject = databaseRepository.getOwnerTeamDetails(userId);
        resp.getWriter().write(String.valueOf(jsonObject));
    }
}