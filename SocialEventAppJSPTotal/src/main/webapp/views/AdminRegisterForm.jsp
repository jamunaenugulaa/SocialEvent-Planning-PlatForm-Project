<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Registration</title>
    <style>
        label {
            display: inline-block;
            width: 100px;
            margin-bottom: 10px;
        }
        input[type="text"], input[type="password"], input[type="email"] {
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
        .error {
            color: red;
        }
    </style>
</head>
<body>
<center>
    <h2>Admin Registration</h2>
    <h5>${msg}</h5>

    <form:form action="adminRegistrationCheck" modelAttribute="adminModel">

        <label for="adminName">Name:</label>
        <form:input path="adminName" /><br>
        <form:errors path="adminName" class="error" /><br>

        <label for="email">Email:</label>
        <form:input path="email" type="email" /><br>
        <form:errors path="email" class="error" /><br>



        <label for="username">Username:</label>
        <form:input path="username" /><br>
        <form:errors path="username" class="error" /><br>

        <label for="password">Password:</label>
        <form:input path="password"  /><br>
        <form:errors path="password" class="error" /><br>

        <input type="submit" value="Register">
    </form:form>
</center>
</body>
</html>
