package com.cricket;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Rating extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DatabaseRepository databaseRepository = new DatabaseRepository();
        JSONObject jsonObject = databaseRepository.getAllUsers();
        resp.getWriter().write(String.valueOf(jsonObject));
    }
}
