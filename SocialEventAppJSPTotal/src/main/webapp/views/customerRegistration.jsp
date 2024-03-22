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
    </style>
</head>
<body>
<center>
    <h2>Event Organizer Registration</h2>
    <h5>${msg}</h5>

    <form:form action="customerOrganizerCheck" modelAttribute="customer">


        <label for="name">Name:</label>
        <form:input path="name" /><br>
        <form:errors path="name" style="color: red;"/><br>

        <label for="age">Age</label>
         <form:input type="number"  path="age" /><br>
        <form:errors path="age"  style="color: red;"/><br>

        <label for="email">Email:</label>
            <form:input  path="email" /><br>
            <form:errors path="email"  style="color: red;"/><br>

        <label for="phone">Phone Number:</label>
          <form:input  path="phone" /><br>
          <form:errors path="phone"  style="color: red;"/><br>



        <label for="username">Username:</label>
        <form:input  path="username" /><br>
        <form:errors path="username" style="color: red;"/><br>

        <label for="password">Password:</label>
        <form:input  path="password" /><br>
        <form:errors path="password" style="color: red;"/><br>

        <input type="submit" value="Register">
    </form:form>
</center>
</body>
</html>
