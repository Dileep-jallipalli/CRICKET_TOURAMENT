package com.cricket;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterTeam extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String teamName = req.getParameter("teamName");
        String city = req.getParameter("city");
        DatabaseRepository databaseRepository = new DatabaseRepository();
        Cookie ck[]=req.getCookies();
        int userId= Integer.parseInt(ck[0].getValue());
        boolean insertionResult = databaseRepository.insertTeamData(new Team(teamName, userId, city));

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
        JSONObject jsonObject = databaseRepository.getTeamDetails();
        resp.getWriter().write(String.valueOf(jsonObject));
    }
}
