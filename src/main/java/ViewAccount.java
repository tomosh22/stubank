import Classes.Account;
import Classes.User;
import com.mysql.cj.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "ViewAccount")
public class ViewAccount extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        String USER = "user";
        String PASS = "password";
        String DB_URL = "jdbc:mysql://localhost:33333/stubank";
        try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String accName = request.getParameter("accName");
            String username = ((User) session.getAttribute("user")).username;
            String getAccountDataQuery = String.format("SELECT Name, Type, Balance, Currency,AccNumber FROM Account WHERE " +
                    "Username = \"%s\" AND Name = \"%s\"", username, accName);
            ResultSet results = stmt.executeQuery(getAccountDataQuery);
            while(results.next()){
                session.setAttribute("currentAccount",new Account(
                        results.getString("Name"),
                        results.getString("Type"),
                        results.getBigDecimal("Balance"),
                        results.getString("Currency"),
                        results.getString("AccNumber")
                ));
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("account.jsp");
            dispatcher.forward(request,response);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
