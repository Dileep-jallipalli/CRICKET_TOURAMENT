package com.cricket;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ScheduleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int numberOfMatchesPerDay = Integer.parseInt(req.getParameter("matchesPerDay"));
        String tournamentStartDate = req.getParameter("matchStartDate");
        DatabaseRepository databaseRepository = new DatabaseRepository();
        databaseRepository.insertSchedule(numberOfMatchesPerDay,tournamentStartDate);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(today);
        System.out.println(date);
        DatabaseRepository databaseRepository =new DatabaseRepository();
        JSONObject jsonObject = databaseRepository.getTodayMatchTeams(date);
        resp.getWriter().write(String.valueOf(jsonObject));
    }
}
