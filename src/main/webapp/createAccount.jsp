<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Home</title>
      <link rel="stylesheet" href="bootstrap/bootstrap.min.css">
      <link rel="stylesheet" href="style.css">
      <script src="js/jquery.min.js"></script>
      <script src="bootstrap/bootstrap.min.js"></script>
      <script src="js/createAccount.js"></script>
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


<%--      <form id="signinform" action="UserLogin" method="post" <%=disabled%>>--%>
      <form id="createaccountform" action="CreateAccount" method="post" style="border-radius:50px; background-color:lightblue; text-align: center;">
          <h1 style="background-color:darkorange;">Create New Account</h1>
          <label for="type">Type:</label><br>
          <select id="type" name="type">
              <option value="current">Current</option>
              <option value="savings">Savings</option>
          </select><br>
          <label for="currency">Currency:</label><br>
          <select id="currency" name="currency">
              <option value="£">£</option>
              <option value="$">$</option>
              <option value="€">€</option>
          </select><br>
          <label for="name">Name:</label><br>
          <input style="box-sizing: border-box; min-width: 80%; max-width: 80%;" type="text" id="name" name="name"><br>
          <label for="balance">Balance:</label><br>
          <input style="box-sizing: border-box; min-width: 80%; max-width: 80%;" type="number" step="0.01" id="balance" name="balance" value = 123.45 onchange="to2dps()"><br>
          <button type="button" onclick="Create()" formaction="">Submit</button>
      </form>
      <img style="display:block; margin-left:auto; margin-right:auto; width:50%;"src="IMG-20201125-WA0000.jpg">
  </div>
  </body>
</html>
