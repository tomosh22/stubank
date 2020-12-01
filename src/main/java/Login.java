import Classes.Account;
import Classes.User;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


@WebServlet(name = "Login")
public class Login extends HttpServlet {

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
        boolean creating = false;
        try {
            if(!request.getParameter("email").equals("")){
                creating = true;
            }
            Iterator oldAttr = session.getAttributeNames().asIterator();
            while (oldAttr.hasNext()){
                String attr = (String) oldAttr.next();
                session.removeAttribute(attr);
            }

            // create database connection and statement
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String username;
            String password;
            String email;
            String firstName;
            String secondName;
            String number;
            String street;
            String town;
            String county;
            String postcode;
            ResultSet results;
            if(creating){
                username = request.getParameter("username");
                password = request.getParameter("password");
                email = request.getParameter("email");
                firstName = request.getParameter("firstName");
                secondName = request.getParameter("secondName");
                number = request.getParameter("addressNumber");
                street = request.getParameter("addressStreet");
                town = request.getParameter("addressTown");
                county = request.getParameter("addressCounty");
                postcode = request.getParameter("addressPostcode");

                Random random = new SecureRandom();
                byte[] salt = new byte[16];
                random.nextBytes(salt);

                KeySpec spec = new PBEKeySpec(password.toCharArray(),salt,65536,128);
                SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
                password = new String(factory.generateSecret(spec).getEncoded());
                String selectAddressQuery = String.format("SELECT AddressId FROM Address WHERE Number = \"%s\" AND Street = \"%s\"" +
                        "AND TownOrCity = \"%s\" AND  County = \"%s\" AND Postcode = \"%s\"",number,street,town,county,postcode);

                results = stmt.executeQuery(selectAddressQuery);
                int addressId = -1;
                while (results.next()){
                    addressId = results.getInt("AddressId");
                }
                if(addressId == -1){
                    String insertAddressQuery = String.format("INSERT INTO Address (Number,Street,TownOrCity,County,Postcode) " +
                            "VALUES(\"%s\",\"%s\",\"%s\",\"%s\",\"%s\");",number,street,town,county,postcode);
                    stmt.execute(insertAddressQuery);
                    results = stmt.executeQuery(selectAddressQuery);
                    while(results.next()){
                        addressId = results.getInt("addressId");
                    }
                }
                String insertUserQuery = String.format("INSERT INTO User (Username,Password,Salt,Firstname,SecondName,Email,AddressId) " +
                        "VALUES(\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\");",username,password,new String(salt),firstName,secondName,email,addressId);
                stmt.execute(insertUserQuery);
                session.setAttribute("user", new User(username,firstName,secondName,email));


            }
            else{
                //logging into an existing account
                username = request.getParameter(("username"));
                String selectLoginUser = String.format("SELECT Username,FirstName,SecondName,Email FROM User WHERE Username = \"%s\"",username);
                results = stmt.executeQuery(selectLoginUser);
                while (results.next()){
                    session.setAttribute("user", new User(results.getString("Username"),
                            results.getString("FirstName"),results.getString("SecondName"),
                            results.getString("Email")));
                }
            }
            String getUserAccountsQuery = String.format("SELECT Name,Type,Balance,Currency,AccNumber FROM Account WHERE Username = \"%s\"",username);
            results = stmt.executeQuery(getUserAccountsQuery);
            session.setAttribute("accounts",new ArrayList<Account>());
            while(results.next()){
                ((ArrayList<Account>) session.getAttribute("accounts")).add(new Account(
                        results.getString("Name"),
                        results.getString("Type"),
                        results.getBigDecimal("Balance"),
                        results.getString("Currency"),
                        results.getString("AccNumber")));
            }
            stmt.close();
            conn.close();
            if(!session.getAttribute("user").equals("")){

                RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
                dispatcher.forward(request,response);
            }
            else{
                RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
                dispatcher.forward(request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // display error.jsp page with given message if unsuccessful
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            session.setAttribute("message", "Database Error, Please try again");
            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
