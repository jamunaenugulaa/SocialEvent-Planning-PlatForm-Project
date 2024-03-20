<%@ page import="java.util.List" %>
<%@ page import="com.example.SocialEventAppSecurity.Model.EventsModel" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="com.example.SocialEventAppSecurity.Model.*" %>
<%@ page import="com.example.SocialEventAppSecurity.Model.BookingEventModel" %>
<%@ page import="com.example.SocialEventAppSecurity.Model.OnlinePaymentType" %>

<html>
<head>
<style>
 <Style>
    input[type="submit"] {
             padding: 8px 15px;
             background-color: #4CAF50;
             color: white;
             border: none;
             border-radius: 4px;
             cursor: pointer;
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


        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<center>
<h1>PAYMENT PAGE</h1><br>
     <% BookingEventModel events=(BookingEventModel)request.getAttribute("bookingEvent"); %>
      <form action="processPayment" method="post">
                <label>Venue Name:   </label>
                <input value="<%= events.getLocation().getVenueName() %>" readonly><br><br>

              <label for="advanceAmount">Advance Amount: </label>
              <input id="advanceAmount" name="advanceAmount" value="<%= events.getAdvanceAmount() %>" readonly><br><br>

        <label for="paymentType">Payment Type: </label>
        <select name="paymentType" style="width: 200px; height: 20px;">
            <%
                for (OnlinePaymentType type : OnlinePaymentType.values()) {
            %>
            <option value="<%= type.name() %>"><%= type.name() %></option>
            <% } %>
        </select><br><br>

              <input type="hidden" id="bookingEvent" name="bookingEvent" value="<%= events.getId()%>" readonly><br><br>
              <input type="submit" value="Submit">
          </form>
              <br> <a href="CustomerHomePage" class="nav-link">DASHBOARD</a><br><br><br>
 </center>
</body>
</html>