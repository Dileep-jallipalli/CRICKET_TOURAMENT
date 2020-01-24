package com.cricket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TournamentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String tournamentName = request.getParameter("tournamentName");
        String registerStartDate = request.getParameter("startDate");
        String registrationEndDate = request.getParameter("closeDate");
        String[] formatValues = request.getParameterValues("format");
        String format = "";
        for (int i = 0; i <formatValues.length; i++) {

            format = format + (formatValues[i]+ " ");
        }
        int  overs = Integer.parseInt(request.getParameter("overs"));
        String trailStart = request.getParameter("trailStart");
        String trailEnd = request.getParameter("trailEnd");

         DatabaseRepository databaseRepository = new DatabaseRepository();
         boolean insertionResult = databaseRepository.insertTournamentData(new Tournament(tournamentName,registerStartDate,registrationEndDate,format,overs,trailStart,trailEnd));

         if(insertionResult)
             response.getWriter().write("Success");
         else
             response.getWriter().write("Data not Inserted properly");
    }
}
