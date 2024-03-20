<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.SocialEventAppSecurity.Model.EventOrganizerModel" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <title>Events Organizers</title>
</head>
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
    </style>
</head>
<body>
<center>

    <h1>${msg}</h1>
    <h2>List of Events Organizers</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Age</th>
            <th>Phone Number</th>
            <th>Email</th>
            <th>Role</th>
            <th>Username</th>
            <th>Delete</th>
        </tr>
        <%
            List<EventOrganizerModel> eventsOrganizers = (List<EventOrganizerModel>)request.getAttribute("eventsOrganizers");
            if (eventsOrganizers != null) {
                for (EventOrganizerModel organizer : eventsOrganizers) {
        %>
                   <tr>
                        <td><%= organizer.getId() %></td>
                        <td><%= organizer.getName() %></td>
                        <td><%= organizer.getAge() %></td>
                        <td><%= organizer.getPhnNum() %></td>
                        <td><%= organizer.getEmail() %></td>
                        <td><%= organizer.getRole() %></td>
                        <td><%= organizer.getUsername() %></td>
                        <td><form action="DeleteOrganizer">
                        <input type="hidden"  name="organizer" value="<%= organizer.getId() %>">
                        <input type="submit" value="Delete"></form></td>
                    </tr>
        <%
                }
            }
        %>
    </table>
      <br>    <a href="AdminHomePage" class="nav-link">DASHBOARD</a><br><br><br>

        </center>
</body>
</html>
