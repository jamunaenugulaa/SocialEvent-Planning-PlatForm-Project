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
     <div class="container">

            <div class="header">
                <h3>Welcome, ${name}!</h3>
                <!-- Add summary or statistics here -->
            </div>
    <br><br>

        <a href="addLocations" class="nav-link">ADD LOCATIONS</a><br><br><br>
            <a href="viewLocations" class="nav-link">VIEW LOCATIONS</a><br><br><br>

        <a href="addEvents" class="nav-link">ADD EVENT</a><br><br><br>
         <a href="viewEvents" class="nav-link">VIEW ALL EVENTS</a><br><br><br>
         <a href="upcomingEvents" class="nav-link">UPCOMING EVENTS</a><br><br><br>
       <a href="PastEvents" class="nav-link">PAST EVENTS</a><br><br><br>
    <a href="viewProjects" class="nav-link">PROFILE SETTING</a><br><br><br>

    <a href="home" class="nav-link">LOGOUT</a><br>    </center>
</div>
</body>
</html>
