//package com.cricket;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class ProfileServlet extends HttpServlet {
//
////    @Override
////    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////        doPost(req,resp);
////    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        System.out.println("sdhfbsdjhf");
//        //        response.setContentType("text/html");
////        PrintWriter out=response.getWriter();
//
////        request.getRequestDispatcher("playerLogin.html").forward(request, response);
//        response.sendRedirect("/Cricketapp/playerLogin.html");
////return;
////        ServletContext sc = getServletContext();
////        sc.getRequestDispatcher("/playerLogin.html").forward(request, response);
//        //        Cookie ck[]=request.getCookies();
////        if(ck!=null){
////            int userId=Integer.parseInt(ck[0].getValue());
////            DatabaseRepository databaseRepository  = new DatabaseRepository();
////            if(!userId("")||userId!=){
////                out.print("<b>Welcome to Profile</b>");
////                out.print("<br>Welcome, "+userId);
////            }
////        }else{
////            out.print("Please login first");
////            request.getRequestDispatcher("login.html").include(request, response);
////        }
////        out.close();
//    }
//}
