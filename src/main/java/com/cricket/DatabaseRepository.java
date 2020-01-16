package com.cricket;

import javax.servlet.http.HttpServlet;
import java.sql.*;

class DatabaseRepository extends HttpServlet {

    public boolean insertPlayerData(Register playerDetails) {
        try {

            // loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");

            //creating connection with the database
            //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/new", "testuser", "password");
            Connection con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/taskdb", "dileep", "Dileep@123");
            PreparedStatement ps = con.prepareStatement
                    ("insert into employee_info values(?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, playerDetails.getTeamName());
            ps.setString(2, playerDetails.getName());
            ps.setInt(3, playerDetails.getEmployeeId());
            ps.setString(4, playerDetails.getEmail());
            ps.setString(5, playerDetails.getPassword());
            ps.setString(6, playerDetails.getGender());
            ps.setInt(7, playerDetails.getMobileNumber());
            ps.setString(8, playerDetails.getSkills());
            ps.setInt(9, playerDetails.getRating());
            ps.setString(10, "Player");
            int i = ps.executeUpdate();
            return i > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public ResultSet verifyLogin(String email, String password) {

        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
           // Connection con = DriverManager.getConnection ("jdbc:mysql://localhost:3306/new", "testuser", "password");
            Connection con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/taskdb", "dileep", "Dileep@123");

            PreparedStatement stmt = con.prepareStatement("select * from employee_info where email='" + email + "' AND pass='" + password + "'");
            resultSet = stmt.executeQuery();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public boolean insertCaptainData(Register captainDetails) {

        try {
            // loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");

            //creating connection with the database
          //  Connection con = DriverManager.getConnection ("jdbc:mysql://localhost:3306/new", "testuser", "password");

            Connection con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/taskdb", "dileep", "Dileep@123");

            PreparedStatement ps = con.prepareStatement
                    ("insert into employee_info values(?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, captainDetails.getTeamName());
            ps.setString(2, captainDetails.getName());
            ps.setInt(3, captainDetails.getEmployeeId());
            ps.setString(4, captainDetails.getEmail());
            ps.setString(5, captainDetails.getPassword());

            ps.setString(6, captainDetails.getGender());
            ps.setInt(7, captainDetails.getMobileNumber());

            ps.setString(8, captainDetails.getSkills());
            ps.setInt(9, captainDetails.getRating());
            ps.setString(10, "Captain");
            int i = ps.executeUpdate();

            return i > 0;
        } catch (Exception se) {
            se.printStackTrace();
        }
        return false;
    }
}
