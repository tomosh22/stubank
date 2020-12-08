import Classes.Account;
import Classes.Transaction;
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
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

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
                session.setAttribute("AccNumber",results.getString("AccNumber"));
            }
            String getTransactionsQuery = String.format("SELECT Amount,DateTime,AccNumberTo FROM Transaction WHERE " +
                    "AccNumberFrom = \"%s\"",session.getAttribute("AccNumber"));
            System.out.println(session.getAttribute("AccNumber"));
            results = stmt.executeQuery(getTransactionsQuery);
            session.setAttribute("transactions", new ArrayList<Transaction>());
            while(results.next()){
                ((ArrayList<Transaction>)session.getAttribute("transactions")).add(new Transaction(
                        results.getBigDecimal("Amount"),
                        results.getDate("DateTime"),
                        results.getString("AccNumberTo")
                ));
            }
            Hashtable<Date, BigDecimal> dict = new Hashtable<Date, BigDecimal>();
            for (Transaction trans : (ArrayList<Transaction>)session.getAttribute("transactions")){
                if(dict.containsKey(trans.dateTime)){
                    dict.put(trans.dateTime,(dict.get(trans.dateTime).add(trans.amount)));
                }
                else{
                    dict.put(trans.dateTime,trans.amount);
                }
            }
            session.setAttribute("expenditure",dict);
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
