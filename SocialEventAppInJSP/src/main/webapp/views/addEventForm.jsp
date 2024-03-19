<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Event</title>
    <style>
        label {
            display: inline-block;
            width: 100px;
            margin-bottom: 10px;
        }
        input[type="text"], input[type="number"], input[type="password"] {
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
    <h2>Add Event</h2>

    <form:form action="addEventCheck" modelAttribute="event">
        <label for="eventName">Event Name:</label>
        <form:input path="eventName" /><br>
        <form:errors path="eventName" cssClass="error" /><br>

       <label for="eventCategory" style="display: inline-block; width: 150px;">Event Category:</label>
       <form:select path="eventCategory" style="width: 200px; height: 20px;">
           <form:options items="${eventCategories}" itemLabel="name" />
       </form:select><br><br>

        <label for="description">Description:</label>
        <form:textarea path="description" rows="4" cols="25" /><br>
        <form:errors path="description" cssClass="error" /><br>

        <label for="budget">Budget:</label>
        <form:input path="budget" type="number"  /><br>
        <form:errors path="budget" cssClass="error" /><br>

        <input type="submit" value="Add Event">
    </form:form>
</center>
</body>
</html>
