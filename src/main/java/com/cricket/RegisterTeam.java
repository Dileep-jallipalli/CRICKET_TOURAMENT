package com.cricket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

public class RegisterTeam extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String teamName = req.getParameter("teamName");
        Integer userId = Integer.parseInt(req.getParameter("userId"));
        String city = req.getParameter("city");
        DatabaseRepository databaseRepository = new DatabaseRepository();
        boolean insertionResult = databaseRepository.insertTeamData(new TeamRegistration(teamName, userId, city));

        if(insertionResult){
            resp.getWriter().write("Data Inserted successfully");
        }
        else{
            resp.getWriter().write("Data not inserted properly");
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DatabaseRepository databaseRepository = new DatabaseRepository();
        ResultSet resultSet = databaseRepository.getTeamDetails();
        resp.getWriter().write(String.valueOf(resultSet));
    }
}