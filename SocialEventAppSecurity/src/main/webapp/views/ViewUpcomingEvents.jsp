<%@ page import="java.util.List" %>
<%@ page import="com.example.SocialEventAppSecurity.Model.EventCategory" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="com.example.SocialEventAppSecurity.Entity.Location" %>
<%@ page import="com.example.SocialEventAppSecurity.Model.BookingEventModel" %>
<%@ page import="com.example.SocialEventAppSecurity.Entity.Customer" %>


<!DOCTYPE html>
<html>
<head>
    <title>List Booking Events</title>
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

    <table border="1">
        <thead>
            <tr>
               <th>ID</th>
                           <th>Advance Paid</th>
                           <th>Category</th>
                           <th>Event Name</th>
                           <th>Capacity</th>
                           <th>Local Date</th>
                           <th>Total Budget</th>
                           <th>Status</th>
                           <th>Advance Amount</th>
                           <th>Location Venue</th>
                            <th>Address</th>
                              <th>Pincode</th>
                           <th>Amount Per Person</th>
                                <th>Customer Name</th>

                                                <th>Phone</th>
            </tr>
        </thead>
        <tbody>
            <%
            List<BookingEventModel> events = (List<BookingEventModel>) request.getAttribute("bookingEvents");
            if (events != null && !events.isEmpty()) {
                for (BookingEventModel event : events) {
                                    Location location = event.getLocation();
                                    Customer user=event.getCustomer(); 
                    %>
                    <tr>
                         <td><%= event.getId() %></td>
                                                <td><%= event.isAdvancePaid() %></td>
                                                <td><%= event.getCategory() != null ? event.getCategory().toString() : "" %></td>
                                                <td><%= event.getEventName() %></td>
                                                <td><%= event.getCapacity() %></td>
                                                <td><%= event.getLocalDate() != null ? event.getLocalDate().toString() : "" %></td>
                                                <td><%= event.getTotalBudget() %></td>
                                                <td><%= event.getStatus() != null ? event.getStatus().toString() : "" %></td>
                                                <td><%= event.getAdvanceAmount() %></td>
                                                <td><%= location != null ? location.getVenueName() : "" %></td>
                                                <td><%= location != null ? location.getAddress() : "" %></td>
                                                <td><%= location != null ? location.getPincode() : "" %></td>
                                                <td><%= location.getBudgetPerGuest() %></td>
                        <td><%= user != null ? user.getName() : "" %></td>
                              <td><%= user != null ? user.getPhone() : "" %></td>

                    </tr>
                    <%
                }
            } else {
                %>
                <tr>
                    <td colspan="6">No booking events found.</td>
                </tr>
                <%
            }  %>
        </tbody>
    </table>
        <br>    <a href="OrganizerHomePage" class="nav-link">DASHBOARD</a><br><br><br>

    </center>
</body>
</html>
