import Classes.Account;
import Classes.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.sql.*;
import java.util.Random;

@WebServlet(name = "CreateAccount")
public class CreateAccount extends HttpServlet {
    private Connection conn;
    private Statement stmt;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        String USER = "user";
        String PASS = "password";

        // URLs to connect to database depending on your development approach
        // (NOTE: please change to option 1 when submitting)

        // 1. use this when running everything in Docker using docker-compose
        //String DB_URL = "jdbc:mysql://db:3306/lottery";

        // 2. use this when running tomcat server locally on your machine and mysql database server in Docker
        String DB_URL = "jdbc:mysql://localhost:33333/stubank";

        // 3. use this when running tomcat and mysql database servers on your machine
        //String DB_URL = "jdbc:mysql://localhost:3306/lottery";
        HttpSession session = request.getSession();
        try {
            // create database connection and statement
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String type = request.getParameter("type");
            String name = request.getParameter("name");
            String balance = request.getParameter("balance");
            String currency = request.getParameter("currrency");
            String username = ((User) session.getAttribute("user")).username;
            String accNumber = GenerateAccNumber(stmt);
            String createAccountQuery = String.format("INSERT INTO Account (Name,Type,Balance, Currency,Username,AccNumber) " +
                    "VALUES(\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\")",name,type,balance,currency,username,accNumber);
            stmt.execute(createAccountQuery);

            ((ArrayList<Account>) session.getAttribute("accounts")).add(
                    new Account(name,type,new BigDecimal(balance),currency,accNumber));
            RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
            dispatcher.forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
            // display error.jsp page with given message if unsuccessful
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            session.setAttribute("message", "Error, Please try again");
            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected String GenerateAccNumber(Statement stmt){
        try {
            String number = "";
            String checkAccNumberQuery;
            while(true){
                number = "";
                for (int x = 0; x < 8; x++) {
                    number += (int) (Math.random() * 9) + 1;
                }
                checkAccNumberQuery = String.format("SELECT AccNumber FROM Account WHERE AccNumber = " +
                        "\"%s\"", number);
                ResultSet results = stmt.executeQuery(checkAccNumberQuery);
                if (!results.next()){
                    break;
                }
            }
            return number;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }


    }
}
