package com.cricket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class RegisterPlayer extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //super.doPost(req, resp);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String teamName = "";
        String name = request.getParameter("name");
        Integer employeeId = Integer.parseInt(request.getParameter("employeeid"));
        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        String gender[] = request.getParameterValues("gender");
        String genderValue = "";
        for (int i = 0; i < gender.length; i++) {
            genderValue = genderValue + (gender[i] + "");
        }
        Integer mobileNumber = Integer.parseInt(request.getParameter("mobileno"));
        String checkedSkills[] = request.getParameterValues("skills");
        String skills = "";
        for (int i = 0; i < checkedSkills.length; i++) {
            skills += checkedSkills[i] + " ";
        }
        Integer rating = Integer.parseInt("0");
        DatabaseRepository databaseRepository = new DatabaseRepository();
        boolean insertionresult = databaseRepository.insertPlayerData(new Register(teamName, name, employeeId, email, password, genderValue, mobileNumber, skills, rating));

        if(insertionresult){
            out.println("Succesfully inserted");
        }
        else{
            out.println("Data is not Inserted");
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DatabaseRepository databaseRepository = new DatabaseRepository();

        String email = req.getParameter("emailId");
        String password = req.getParameter("passwordId");

        ResultSet resultSet = databaseRepository.verifyLogin(email,password);

        try {
            if(resultSet.next()) {
                resp.getWriter().write("1");
            }
            else{
                resp.getWriter().write("Failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}