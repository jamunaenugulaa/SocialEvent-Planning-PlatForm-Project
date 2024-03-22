<%@ page import="java.util.List" %>
<%@ page import="com.example.SocialEventAppSecurity.Model.EventsModel" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="com.example.SocialEventAppSecurity.Model.Status" %>
<%@ page import="com.example.SocialEventAppSecurity.Model.*" %>
<%@ page import=" com.example.SocialEventAppSecurity.Entity.Location" %>
<%@ page import="java.time.LocalDate" %>

<!DOCTYPE html>
<html>
<head>
    <title>List of Events</title>
    <style>
        .nav-link {
            text-decoration: none;
            padding: 10px 20px;
            margin-right: 10px;
            color: #333;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
            transition: background-color 0.3s ease;
        }

        table {
            margin: 0 auto;
            border-collapse: collapse;
        }

        th, td {
            padding: 8px;
            border: 1px solid #ddd;
            text-align: left;
        }
         input[type="submit"]:hover {
        background-color: #7ebf73;
     }

        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<center>
<h1 style="color: #DC143C;">${msg}</h1>
<h2 style="color: #DC143C;">${message}</h2>
    <h2>List of Events</h2>
    <%
        LocalDate currentDate = LocalDate.now();
        LocalDate twoDaysAfter = currentDate.plusDays(2);
    %>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Advance Paid</th>
            <th>Availability</th>
            <th>Event Name</th>
            <th>Guest Count</th>
            <th>Local Date</th>
            <th>Total Budget</th>
            <th>Status</th>
            <th>Advance Amount</th>
            <th>Location</th>
            <th>Booking Venue Status</th>
            <th>Cancel</th>
        </tr>
        </thead>
        <tbody>
        <%

            List<BookingEventModel> events = (List<BookingEventModel>) request.getAttribute("bookingList");
            if (events != null && !events.isEmpty()) {
                for (BookingEventModel event : events) {
                         Location location = event.getLocation();
        %>
                    <tr>
                        <td><%= event.getId() %></td>
                        <td>
                       <%
                           if (event.getCancelled()) {
                       %>
                               Paid
                       <%
                           } else {
                       %>
                           <%= event.isAdvancePaid() ? "Paid" : "Unpaid" %>
                       <%
                           }
                       %></td>
                       <td><%= event.isLocationAvailable()? "Available" : "Not Available" %></td>
                        <td><%= event.getEventName() %></td>
                        <td><%= event.getCapacity() %></td>
                        <td><%= event.getLocalDate() != null ? event.getLocalDate().toString() : "" %></td>
                        <td><%= event.getTotalBudget() %></td>
                        <td><%= event.getStatus() != null ? event.getStatus().toString() : "" %></td>
                        <td><%= event.getAdvanceAmount() %></td>
                         <td><form action="viewLocationOfBookingVenueOfCustomer">
                                <input type="hidden" name="location" value="<%= location.getLocationId() %>" >
                                            <input type="submit" value="View Venue">
                                </form>
                                 </td>

 <td>
        <%
            boolean isWithinTwoDays = event.getLocalDate() != null && event.getLocalDate().isAfter(twoDaysAfter);
                      LocalDate twoDaysBeforeEventDate = event.getLocalDate().minusDays(2);
             boolean isBeforeTwoDays = currentDate.isBefore(twoDaysBeforeEventDate);
            boolean isAvailableOfLocation = event.isLocationAvailable();

            boolean isAdvancePaid = event.isAdvancePaid();
        %>
       <%
           if (isAdvancePaid||event.getCancelled()) {
       %>
           <button type="button" disabled style="width: 130px;">Booked</button>
       <%
           } else if (!isAdvancePaid && !isWithinTwoDays && isAvailableOfLocation&&!event.getCancelled()) {
       %>
            <form action="advancePayment" method="post">
               <input type="hidden" name="eventId" value="<%= event.getId() %>">
               <input type="submit" value="Book On Emergency">
           </form>       <%
           } else if (!isAdvancePaid && !isWithinTwoDays && !isAvailableOfLocation) {
       %>
           <button type="button" disabled style="width: 130px;">Cannot Book Now</button>
       <%
           } else {

           if (!isAvailableOfLocation) {
           %>
               <button type="button" disabled style="width: 130px;">Venue not Available</button>
           <%
           }else{
          %>
           <form action="advancePayment" method="post">
               <input type="hidden" name="eventId" value="<%= event.getId() %>">
               <input type="submit" value="Proceed to Payment">
           </form>
       <%
       }
           }
       %>

    </td>
    <td>
            <form action="cancelBookingEventByCustomer" method="post">
                      <input type="hidden" name="eventId" value="<%= event.getId() %>">

                     <%
                         if (event.getCancelled()) {
                     %>
                         <input type="submit" value="Cancelled Refunded" disabled>
                     <%
                         } else if (isAdvancePaid && !event.getCancelled()) {
                     %>
                         <input type="submit" value="Cancel Booking" <%= isBeforeTwoDays ? "" : "disabled" %>>
                     <%
                         } else {
                     %>
                         <input type="submit" value="Delete Booking">
                     <%
                         }
                     %>
             </form>

    </td>

      </tr>
        <%
                }
            } else {
        %>
                <tr>
                    <td colspan="9">No events found.</td>
                </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <br> <a href="CustomerHomePage" class="nav-link">DASHBOARD</a><br><br><br>
</center>
</body>
</html>
