package com.cricket;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

class DatabaseRepository extends HttpServlet {

    public boolean insertPlayerData(User user) {
        try {
            // loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");

            //creating connection with the database
            Connection con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/taskdb", "dileep", "Dileep@123");

            PreparedStatement ps = con.prepareStatement
                    ("insert into user_registration values(?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, user.getName());
            ps.setInt(2, user.getUserId());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getGender());
            ps.setInt(6, user.getMobileNumber());
            ps.setString(7, user.getRole());
            ps.setString(8, user.getSkills());
            ps.setString(9, null);
            ps.setString(10, null);
            int i = ps.executeUpdate();
            return i > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public String verifyLogin(int userId, String password) {

        User user = null;
        String role = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/taskdb", "dileep", "Dileep@123");
            PreparedStatement stmt = con.prepareStatement("select role from user_registration where userId='" + userId + "' AND password='" + password + "'");
            ResultSet resultSet = stmt.executeQuery();
            //System.out.println(resultSet);
            while (resultSet.next()) {
                role = resultSet.getString(1);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return role;
    }


    public boolean insertTeamData(Team teamDetails) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/taskdb", "dileep", "Dileep@123");
            PreparedStatement preparedStatement = con.prepareStatement
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

    public JSONObject getTeamDetails() {
        ResultSet resultSet = null;
        JSONObject jsonObject = new JSONObject();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/taskdb", "dileep", "Dileep@123");
            PreparedStatement ps = con.prepareStatement("select * from team_registration");
            resultSet = ps.executeQuery();

            JSONArray jsonArray = new JSONArray();

            while (resultSet.next()) {
                JSONObject record = new JSONObject();
                record.put("TeamName", resultSet.getString("teamName"));
                record.put("city", resultSet.getString("city"));
                record.put("UserId", resultSet.getInt("userId"));
                jsonArray.put(record);
            }

            jsonObject.put("Teams_data", jsonArray);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


    public boolean insertTournamentData(Tournament tournament) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            //creating connection with the database
            Connection con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/taskdb", "dileep", "Dileep@123");
            PreparedStatement ps = con.prepareStatement
                    ("insert into tournament values(?,?,?,?,?,?,?)");

            ps.setString(1, tournament.getTournamentName());
            ps.setString(2, tournament.getRegistrationStartDate());
            ps.setString(3, tournament.getRegistrationEndDate());
            ps.setString(4, tournament.getFormat());
            ps.setInt(5, tournament.getOvers());
            ps.setString(6, tournament.getTrailsStartDate());
            ps.setString(7, tournament.getTrailsEndDate());

            int i = ps.executeUpdate();
            return i > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public JSONObject getOwnerTeamDetails(int userId) {
        ResultSet resultSet = null;
        JSONObject jsonObject = new JSONObject();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/taskdb", "dileep", "Dileep@123");
            PreparedStatement ps = con.prepareStatement("select userId,name,mobileNumber,email,skills from user_registration where team=(select teamName from team_registration where userId=" + userId + ")");
            resultSet = ps.executeQuery();

            JSONArray jsonArray = new JSONArray();

            while (resultSet.next()) {
                JSONObject record = new JSONObject();
                record.put("UserId", resultSet.getInt("userId"));
                record.put("Name", resultSet.getString("name"));
                record.put("MobileNumber", resultSet.getInt("mobileNumber"));
                record.put("EmailId", resultSet.getString("email"));
                record.put("Skills", resultSet.getString("skills"));
                jsonArray.put(record);
            }

            jsonObject.put("OwnerTeam_data", jsonArray);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject getPlayersTeam(int userId) {
        ResultSet resultSet = null;
        JSONObject jsonObject = new JSONObject();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/taskdb", "dileep", "Dileep@123");
            PreparedStatement ps = con.prepareStatement("select userId,name,mobileNumber,email,skills,team from user_registration where team=(select team from user_registration where userId=" + userId + ") and role!='Owner'");
            resultSet = ps.executeQuery();

            JSONArray jsonArray = new JSONArray();

            while (resultSet.next()) {
                JSONObject record = new JSONObject();
                record.put("UserId", resultSet.getInt("userId"));
                record.put("Name", resultSet.getString("name"));
                record.put("MobileNumber", resultSet.getInt("mobileNumber"));
                record.put("EmailId", resultSet.getString("email"));
                record.put("Skills", resultSet.getString("skills"));
                record.put("Team", resultSet.getString("team"));
                jsonArray.put(record);
            }

            jsonObject.put("Players_Team", jsonArray);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject getPlayerDetails(int userId) {
        ResultSet resultSet = null;
        JSONObject jsonObject = new JSONObject();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/taskdb", "dileep", "Dileep@123");
            PreparedStatement ps = con.prepareStatement("select userId,name,email,skills from user_registration where userId=" + userId);
            resultSet = ps.executeQuery();
            JSONArray jsonArray = new JSONArray();
            while (resultSet.next()) {
                JSONObject record = new JSONObject();
                record.put("UserId", resultSet.getInt("userId"));
                record.put("Name", resultSet.getString("name"));
                record.put("EmailId", resultSet.getString("email"));
                record.put("Skills", resultSet.getString("skills"));
                jsonArray.put(record);
            }
            jsonObject.put("Player_Details", jsonArray);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject getAllUsers() {
        ResultSet resultSet = null;
        JSONObject jsonObject = new JSONObject();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/taskdb", "dileep", "Dileep@123");
            PreparedStatement ps = con.prepareStatement("select userId,name,skills from user_registration");
            resultSet = ps.executeQuery();
            JSONArray jsonArray = new JSONArray();
            while (resultSet.next()) {
                JSONObject record = new JSONObject();
                record.put("UserId", resultSet.getInt("userId"));
                record.put("Name", resultSet.getString("name"));
                record.put("Skills", resultSet.getString("skills"));
                jsonArray.put(record);
            }
            jsonObject.put("Players_Details", jsonArray);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public void insertSchedule(int numberOfMatchesPerDay, String date) {
        List<Integer> listOfTeamIds = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/taskdb", "dileep", "Dileep@123");
            PreparedStatement ps = con.prepareStatement("select userId from team_registration");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                listOfTeamIds.add(resultSet.getInt("userId"));
            }
            int matchId = 0;
            System.out.println(listOfTeamIds);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(simpleDateFormat.parse(date));
            if (listOfTeamIds.size() % 2 != 0) {
                for (int i = 0; i < listOfTeamIds.size() * 2; i = i + 2) {
                    String date1 = date;
                    PreparedStatement preparedStatement = con.prepareStatement("insert into Schedule values(?,?,?,?)");
                    preparedStatement.setInt(1, matchId);
                    preparedStatement.setInt(2, listOfTeamIds.get(i % listOfTeamIds.size()));
                    preparedStatement.setInt(3, listOfTeamIds.get((i + 1) % listOfTeamIds.size()));
                    preparedStatement.setDate(4, Date.valueOf(date));
                    preparedStatement.executeUpdate();
                    calendar.setTime(simpleDateFormat.parse(date));
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    date = simpleDateFormat.format(calendar.getTime());
                    matchId += 1;
                }
            } else {
                for (int i = 0; i < listOfTeamIds.size(); i = i + 2) {
                    String date1 = date;
                    PreparedStatement preparedStatement = con.prepareStatement("insert into Schedule values(?,?,?,?)");
                    preparedStatement.setInt(1, matchId);
                    System.out.println(i);
                    preparedStatement.setInt(2, listOfTeamIds.get(i % listOfTeamIds.size()));
                    preparedStatement.setInt(3, listOfTeamIds.get((i + 1) % listOfTeamIds.size()));
                    System.out.println(i);
                    preparedStatement.setDate(4, Date.valueOf(date));
                    preparedStatement.executeUpdate();
                    calendar.setTime(simpleDateFormat.parse(date));
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    date = simpleDateFormat.format(calendar.getTime());
                    matchId += 1;
                }
                int terminateLoop = 2;
                for (int i = 0; terminateLoop < listOfTeamIds.size(); i++) {
                    String date1 = date;
                    PreparedStatement preparedStatement = con.prepareStatement("insert into Schedule values(?,?,?,?)");
                    preparedStatement.setInt(1, matchId);
                    preparedStatement.setInt(2, listOfTeamIds.get(i));
                    System.out.println(i);
                    preparedStatement.setInt(3, listOfTeamIds.get(terminateLoop));
                    terminateLoop += 1;
                    System.out.println(i);
                    preparedStatement.setDate(4, Date.valueOf(date));
                    preparedStatement.executeUpdate();
                    calendar.setTime(simpleDateFormat.parse(date));
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    date = simpleDateFormat.format(calendar.getTime());
                    matchId += 1;
                }
            }
        } catch (ClassNotFoundException | SQLException | ParseException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getTodayMatchTeams(String date) {
        JSONObject jsonObject = new JSONObject();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/taskdb", "dileep", "Dileep@123");
            PreparedStatement preparedStatement = con.prepareStatement("select teamName,userId from team_registration where userId=(select team1_Id from Schedule where matchDate='" + date + "')");
            ResultSet resultSet = preparedStatement.executeQuery();
            JSONArray jsonArray = new JSONArray();
            while (resultSet.next()) {
                JSONObject record = new JSONObject();
                record.put("TeamName", resultSet.getString("teamName"));
                record.put("TeamId",resultSet.getString("userId"));
                jsonArray.put(record);
            }
            PreparedStatement preparedStatement1 = con.prepareStatement("select teamName,userId from team_registration where userId=(select team2_Id from Schedule where matchDate='" + date + "')");
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            while (resultSet1.next()) {
                JSONObject record = new JSONObject();
                record.put("TeamName", resultSet1.getString("teamName"));
                record.put("TeamId",resultSet1.getString("userId"));
                jsonArray.put(record);
            }
            jsonObject.put("Todays_Teams", jsonArray);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}