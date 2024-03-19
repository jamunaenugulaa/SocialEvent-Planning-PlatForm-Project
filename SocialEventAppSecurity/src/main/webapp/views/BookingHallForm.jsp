<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.SocialEventAppSecurity.Model.EventCategory" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="com.example.SocialEventAppSecurity.Entity.Location" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page  import="com.example.SocialEventAppSecurity.Model.EventsModel" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Event Booking Form</title>
    <style>
        label {
            display: inline-block;
            width: 100px;
            margin-bottom: 10px;
        }
        input[type="text"], input[type="number"], input[type="date"] {
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
    <h2>Event Booking Form</h2>
    <h3>${msg}</h3>



   <form:form action="bookingHallDone"  modelAttribute="bookingForm">
          <label for="EventName">EventName</label>

<select name="eventName">
    <% for (EventsModel event : (List<EventsModel>)request.getAttribute("eventModel")) { %>
                <option value="<%= event.getEventName() %>"><%= event.getEventName() %></option>
            <% } %>
        </select><br>
       <label for="capacity">Capacity:</label>
<form:input path="capacity" type="number" min="${location.capacity - 300}" max="${location.capacity}" /><br>
       <form:errors path="capacity" style="color: red;" /><br>

       <label for="localDate">Date:</label>
       <form:input path="localDate" type="date" min="<%=java.time.LocalDate.now().plusDays(3)%>" /><br>
       <form:errors path="localDate" style="color: red;" /><br>

       <input type="hidden" name="location" value="${location.locationId}" readonly/>

       <input type="submit" value="Submit">
   </form:form>
    <br> <a href="CustomerHomePage" class="nav-link">DASHBOARD</a><br><br><br>

</center>
</body>
</html>
