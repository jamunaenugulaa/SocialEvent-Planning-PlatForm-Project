<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page  import="com.example.SocialEventAppSecurity.Model.EventsModel" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Events</title>
    <style>
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
<center>
    <h2>All Events</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Event ID</th>
                <th>Event Name</th>
                <th>Event Category</th>
                <th>Description</th>
                <th>Budget</th>
                <th>Update</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<EventsModel> events = (List<EventsModel>) request.getAttribute("eventList");
                if (events != null) {
                    for (EventsModel event : events) {
            %>
            <tr>
                <td><%= event.getEventId() %></td>
                <td><%= event.getEventName() %></td>
                <td><%= event.getEventCategory() %></td>
                <td><%= event.getDescription() %></td>
                <td><%= event.getBudget() %></td>
               <td> <form action="updateEvents" >
                <input type="hidden" name="eventId" value="<%= event.getEventId() %>" >
                <input type="submit" value="Update">
                </form></td>
            </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>

    <br>    <a href="OrganizerHomePage" class="nav-link">DASHBOARD</a><br><br><br>

    </center>
</body>
</html>
