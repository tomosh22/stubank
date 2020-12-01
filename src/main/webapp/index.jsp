<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Home</title>
      <link rel="stylesheet" href="bootstrap/bootstrap.min.css">
      <link rel="stylesheet" href="style.css">
      <script src="js/jquery.min.js"></script>
      <script src="bootstrap/bootstrap.min.js"></script>
      <script src="js/index.js"></script>
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

      <%

          String disabled = "";
          if(session.getAttribute("remainingattempts")!=null){
              if(session.getAttribute("remainingattempts").equals(0)){
                  disabled = "hidden";

              }
          }
      %>
<%--      <form id="signinform" action="UserLogin" method="post" <%=disabled%>>--%>

      <form id="signinform" action="Login" method="post" style="border-radius:50px; background-color:lightblue; text-align: center;">
          <h1 style="background-color:darkorange;">Sign In</h1>
          <label for="username">Username:</label><br>
          <input style="box-sizing: border-box; min-width: 80%; max-width: 80%;" type="text" id="username" name="username"><br>
          <label for="password">Password:</label><br>
          <input style="box-sizing: border-box; min-width: 80%; max-width: 80%;" type="text" id="password" name="password"><br>
          <p id="existPrompt">Don't have an account?</p>
          <button type="button" id="switchButton" onclick="EnableCreate()" formaction="">Sign Up</button>
          <container id="create" style="display:none;">
              <label for="email">Email:</label><br>
              <input style="box-sizing: border-box; min-width: 80%; max-width: 80%;" type="text" id="email" name="email"><br>
              <label for="firstName">First Name:</label><br>
              <input style="box-sizing: border-box; min-width: 80%; max-width: 80%;" type="text" id="firstName" name="firstName"><br>
              <label for="secondName">Second Name:</label><br>
              <input style="box-sizing: border-box; min-width: 80%; max-width: 80%;" type="text" id="secondName" name="secondName"><br>
              <label for="addressNumber">House Number:</label><br>
              <input style="box-sizing: border-box; min-width: 80%; max-width: 80%;" type="text" id="addressNumber" name="addressNumber"><br>
              <label for="addressStreet">Street:</label><br>
              <input style="box-sizing: border-box; min-width: 80%; max-width: 80%;" type="text" id="addressStreet" name="addressStreet"><br>
              <label for="addressTown">Town/City:</label><br>
              <input style="box-sizing: border-box; min-width: 80%; max-width: 80%;" type="text" id="addressTown" name="addressNTown"><br>
              <label for="addressCounty">County:</label><br>
              <input style="box-sizing: border-box; min-width: 80%; max-width: 80%;" type="text" id="addressCounty" name="addressCounty"><br>
              <label for="addressPostcode">Postcode:</label><br>
              <input style="box-sizing: border-box; min-width: 80%; max-width: 80%;" type="text" id="addressPostcode" name="addressPostcode"><br>
          </container>


          <button type="button" onclick="Login()" formaction="">Submit</button>

      </form>
      <img style="display:block; margin-left:auto; margin-right:auto; width:50%;"src="IMG-20201125-WA0000.jpg">
  </div>
  </body>
</html>
