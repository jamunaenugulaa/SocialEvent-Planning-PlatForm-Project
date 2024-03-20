<%@ page import=" com.example.SocialEventAppSecurity.Model.LocationModel" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="com.example.SocialEventAppSecurity.Model.EventCategory" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List of Locations</title>
    <Style>
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
 <h1 style="color: #DC143C;"> ${msg}</h1>

    <h1>List of Locations</h1>

    <table border="1">

        <thead>
            <tr>
                <th>Location ID</th>
                <th>Venue Name</th>
                <th>Address</th>
                <th>City</th>
                <th>State</th>
                <th>Country</th>
                <th>Pincode</th>
                <th>Description</th>
                <th>Capacity</th>
                <th>Advance</th>
                <th>Total Budget</th>
                <th>Budget Per Guest</th>
                <th>Event Categories</th>
                <th>Book The HALL</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<LocationModel> locationList = (List<LocationModel>) request.getAttribute("locationList");
                for (LocationModel location : locationList) {
            %>
                <tr>
                    <td><%= location.getLocationId() %></td>
                    <td><%= location.getVenueName() %></td>
                    <td><%= location.getAddress() %></td>
                    <td><%= location.getCity() %></td>
                    <td><%= location.getState() %></td>
                    <td><%= location.getCountry() %></td>
                    <td><%= location.getPincode() %></td>
                    <td><%= location.getDescription() %></td>
                    <td><%= location.getCapacity() %></td>
                    <td><%= location.getAdvance() %></td>
                    <td><%= location.getTotalBudget() %></td>
                    <td><%= location.getBudgetPerGuest() %></td>
                    <td>
                        <ul>
                            <% for (EventCategory category : location.getCategoryList()) { %>
                                <li><%= category.name() %></li>
                            <% } %>
                        </ul>
                    </td>

                    <td>
                    <form action="BookingTheHall" >
                  <input type="hidden" path="location" name="location" value="<%= location.getLocationId() %>" readonly/><br>
                    <input type="submit" value="BookHere" class="submit-button">  </form>
                    </td>
                </tr>
            <% } %>
        </tbody>
    </table>
    <br> <a href="CustomerHomePage" class="nav-link">DASHBOARD</a><br><br><br>
</center>
</body>
</html>
