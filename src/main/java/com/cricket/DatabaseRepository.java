package com.cricket;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import java.sql.*;

class DatabaseRepository extends HttpServlet {

    public boolean insertPlayerData(UserRegistration userRegistration) {
        try {

            // loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");

            //creating connection with the database
            Connection con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/taskdb", "dileep", "Dileep@123");

            PreparedStatement ps = con.prepareStatement
                    ("insert into user_registration values(?,?,?,?,?,?,?,?)");
            ps.setString(1, userRegistration.getName());
            ps.setInt(2, userRegistration.getUserId());
            ps.setString(3, userRegistration.getEmail());
            ps.setString(4, userRegistration.getPassword());
            ps.setString(5, userRegistration.getGender());
            ps.setInt(6, userRegistration.getMobileNumber());
            ps.setString(7,userRegistration.getRole());
            ps.setString(8, userRegistration.getSkills());
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
            Connection con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/taskdb", "dileep", "Dileep@123");

            PreparedStatement stmt = con.prepareStatement("select * from user_registration where email='" + email + "' AND password='" + password + "'");
            resultSet = stmt.executeQuery();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public boolean insertTeamData(TeamRegistration teamDetails){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/taskdb", "dileep", "Dileep@123");
            PreparedStatement preparedStatement=connection.prepareStatement
                    ("insert into team_registration values(?,?,?)");
            preparedStatement.setString(1, teamDetails.getTeamName());
            preparedStatement.setString(2, teamDetails.getCity());
            preparedStatement.setInt(3, teamDetails.getUserId());
            int i = preparedStatement.executeUpdate();
            return i > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public JSONObject getTeamDetails(){
        ResultSet resultSet=null;
        JSONObject jsonObject = new JSONObject();
        try{
         Class.forName("com.mysql.jdbc.Driver");
         Connection con = DriverManager.getConnection
                     ("jdbc:mysql://localhost:3306/taskdb", "dileep", "Dileep@123");

         PreparedStatement ps = con.prepareStatement("select * from team_registration");
          resultSet=ps.executeQuery();

            JSONArray jsonArray = new JSONArray();

            while(resultSet.next()){
                JSONObject record  =new JSONObject();
                record.put("TeamName",resultSet.getString("teamName"));
                record.put("city",resultSet.getString("city"));
                record.put("UserId",resultSet.getInt("userId"));
                jsonArray.put(record);
            }

            jsonObject.put("Teams_data",jsonArray);
         }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return jsonObject;
     }
}
