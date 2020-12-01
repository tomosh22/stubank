<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Home</title>
      <link rel="stylesheet" href="bootstrap/bootstrap.min.css">
      <link rel="stylesheet" href="style.css">
      <script src="js/jquery.min.js"></script>
      <script src="bootstrap/bootstrap.min.js"></script>
      <script src="js/dashboard.js"></script>
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

      <div style="border-radius:50px; background-color:lightblue; text-align: center;">
          <h1 style="background-color:darkorange;">Your Accounts</h1>

          <%@ page import="Classes.Account" %>
          <%
              if(!(session.getAttribute("accounts") == null)){
              for(Account acc : (ArrayList<Account>)session.getAttribute("accounts")){%>
                <%="<a onclick=\"goToAccount(this)\">"+acc.name+"</p>"%>
              <%}
          }
          %>
          <a href="createAccount.jsp">Create new account</a>
          <form action="ViewAccount" id="viewAccountForm" method="post">
              <input type="hidden" id="accName" name="accName">
          </form>
      </div>
<%--      <form id="signinform" action="UserLogin" method="post" <%=disabled%>>--%>

      <img style="display:block; margin-left:auto; margin-right:auto; width:50%;"src="IMG-20201125-WA0000.jpg">
  </div>
  </body>
</html>
