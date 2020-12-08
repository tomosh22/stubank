<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Home</title>
      <link rel="stylesheet" href="bootstrap/bootstrap.min.css">
      <link rel="stylesheet" href="style.css">
      <script src="js/jquery.min.js"></script>
      <script src="bootstrap/bootstrap.min.js"></script>
      <script src="https://www.gstatic.com/charts/loader.js"></script>
      <script src="js/account.js"></script>
  </head>
  <body>
  <div class="topnav">

      <div class="topnav-centered">
          <a href="#home" class="active">Home</a>
      </div>

      <a href="#contact">Contact</a>

      <div class="topnav-right">
          <a href="#about">About</a>
      </div>

  </div>
  <div style="position:absolute; left:33.3%; width:33.3%;">
      <%@ page import="Classes.Account" %>
      <%@ page import="java.math.BigDecimal" %>
      <%@ page import="java.sql.Date" %>
      <%@ page import="java.util.Hashtable" %>
      <div style="border-radius:50px; background-color:lightblue; text-align: center;">
          <h1 style="background-color:darkorange;">Your Accounts</h1>
          <p>Name: <%=((Account) session.getAttribute("currentAccount")).name%></p>
          <p>Type: <%=((Account) session.getAttribute("currentAccount")).type%></p>
          <p>Balance: <%=((Account) session.getAttribute("currentAccount")).balance%></p>
          <p>Currency: <%=((Account) session.getAttribute("currentAccount")).currency%></p><br>
          <p>Account Number: <%=((Account) session.getAttribute("currentAccount")).accNumber%></p>
          <%System.out.println(session.getAttribute("expenditure"));%>
          <select id="period" onchange="drawChart('<%=session.getAttribute("expenditure")%>')">
              <option value="week">Week</option>
              <option value="month">Month</option>
              <option value="year">Year</option>
          </select><br>
          <div id="chart"></div><br>

          <a href="moveMoney.jsp">Move Money</a><br>
          <a href="dashboard.jsp">Back to Dashboard</a>

      </div>
<%--      <form id="signinform" action="UserLogin" method="post" <%=disabled%>>--%>

      <img style="display:block; margin-left:auto; margin-right:auto; width:50%;"src="IMG-20201125-WA0000.jpg">
  </div>
  </body>
</html>
