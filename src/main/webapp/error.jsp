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
    <p>error</p>
      <%

          String disabled = "";
          if(session.getAttribute("remainingattempts")!=null){
              if(session.getAttribute("remainingattempts").equals(0)){
                  disabled = "hidden";

              }
          }
      %>
<%--      <form id="signinform" action="UserLogin" method="post" <%=disabled%>>--%>

      <img style="display:block; margin-left:auto; margin-right:auto; width:50%;"src="IMG-20201125-WA0000.jpg">
  </div>
  </body>
</html>
