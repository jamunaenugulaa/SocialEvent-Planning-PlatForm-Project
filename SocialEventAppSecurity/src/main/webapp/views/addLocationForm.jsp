<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.example.SocialEventAppSecurity.Model.EventCategory" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Location</title>
    <style>
        label {
            display: inline-block;
            width: 150px;
            margin-bottom: 10px;
        }
        input[type="text"], input[type="number"] {
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
        <h2>Add Location</h2>
        <form:form action="addLocationCheck" modelAttribute="location">
         <label for="categoryList">Select EventCategory:</label><br>

         <% for (EventCategory category : EventCategory.values()) { %>
             <div class="checkbox-container">
                 <input type="checkbox" id="<%= category.name() %>" name="categoryList" value="<%= category.name() %>">
                 <label for="<%= category.name() %>"><%= category.name() %></label>
             </div>
         <% } %><br>
          <form:errors path="categoryList" cssClass="error" /><br>


            <label for="venueName">Venue Name:</label>
            <form:input path="venueName" /><br>
            <form:errors path="venueName" cssClass="error" /><br>

            <label for="address">Address:</label>
            <form:input path="address" /><br>
            <form:errors path="address" cssClass="error" /><br>

            <label for="city">City:</label>
            <form:input path="city" /><br>
            <form:errors path="city" cssClass="error" /><br>

            <label for="state">State:</label>
            <form:input path="state" /><br>
            <form:errors path="state" cssClass="error" /><br>

            <label for="country">Country:</label>
            <form:input path="country" /><br>
            <form:errors path="country" cssClass="error" /><br>

            <label for="pincode">Pincode:</label>
            <form:input path="pincode" type="number" /><br>
            <form:errors path="pincode" cssClass="error" /><br>

            <label for="description">Description:</label>
            <form:input path="description" /><br>
            <form:errors path="description" cssClass="error" /><br>

            <label for="capacity">Capacity:</label>
            <form:input path="capacity" type="number" /><br>
            <form:errors path="capacity" cssClass="error" /><br>

            <label for="advance">Advance:</label>
            <form:input path="advance" type="number" step="0.01" /><br>
            <form:errors path="advance" cssClass="error" /><br>

            <label for="totalBudget">Total Budget:</label>
            <form:input path="totalBudget" type="number"  /><br>
            <form:errors path="totalBudget" cssClass="error" /><br>

            <label for="budgetPerGuest">Budget Per Guest:</label>
            <form:input path="budgetPerGuest" type="number" /><br>
            <form:errors path="budgetPerGuest" cssClass="error" /><br>


            <input type="submit" value="Add Location">
        </form:form>
    </center>
</body>
</html>
