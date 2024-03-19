<%@page language="java" %>

<html>
<head>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
        }

        /* Center the content horizontally */
        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }

        .nav-link {
            text-decoration: none;
            padding: 10px 20px; /* Increase padding for better touch/clickability */
            margin-right: 10px;
            color: #333;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
            transition: background-color 0.3s ease; /* Add smooth transition on hover */
        }

        .nav-link:hover {
            background-color: #eaeaea;
        }
    </style>
</head>
<body>
<div class="container">
    <center>
     <% Boolean msg = (Boolean)request.getAttribute("msg"); %>
            <% if(msg != null && msg) { %>
       <a href="OrganizerLogin" class="nav-link">Organizer Login</a>
   <% } else { %>
       <a href="CustomerRegister" class="nav-link">Customer Register</a>
       <a href="CustomerLogin" class="nav-link">Customer Login</a>
   <% } %>

    </center>
</div>
</body>
</html>
