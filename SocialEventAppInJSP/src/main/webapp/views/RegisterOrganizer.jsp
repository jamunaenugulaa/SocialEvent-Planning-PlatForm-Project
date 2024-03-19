<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Registration</title>
    <style>
        label {
            display: inline-block;
            width: 100px;
            margin-bottom: 10px;
        }
        input[type="text"] {
            width: 200px;
            padding: 5px;
            margin-bottom: 10px;
        }
        input[type="submit"] {
            padding: 8px 15px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
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

    </style>
</head>
<body>
<center>
    <h2>Event Organizer Registration</h2>
    <h3>${msg}</h3>

    <form:form action="registerOrganizerCheck" modelAttribute="eventOrganizer">
        <label for="id">ID:</label>
        <form:input type="number" path="id"/><br>
         <form:errors path="id" style="color: red;"/><br>

        <label for="name">Name:</label>
        <form:input path="name" /><br>
        <form:errors path="name" style="color: red;"/><br>

        <label for="age">Age</label>
         <form:input type="number"  path="age" /><br>
        <form:errors path="age"  style="color: red;"/><br>

        <label for="email">Email:</label>
            <form:input  path="email" /><br>
            <form:errors path="email"  style="color: red;"/><br>

        <label for="phnNum">Phone Number:</label>
          <form:input  path="phnNum" /><br>
          <form:errors path="phnNum"  style="color: red;"/><br>




        <input type="submit" value="Register">
    </form:form>
          <br>    <a href="AdminHomePage" class="nav-link">DASHBOARD</a><br><br><br>

</center>
</body>
</html>
