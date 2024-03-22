<%@ page import="com.example.SocialEventAppSecurity.Model.EventCategory" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="com.example.SocialEventAppSecurity.Entity.Location" %>
<!DOCTYPE html>
<html>
<head>
    <title>Location Details</title>
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
      .location-details {
                  font-family: Arial, sans-serif;
                  margin-bottom: 20px;
              }

              .location-details p {
                  margin: 5px 0;
              }

              .location-details p strong {
                  font-weight: bold;
              }

        </style>
</head>
<body>
<center>
    <h1>Location Details</h1>
    <%
        // Assuming you have already set the location object as a request attribute
        Location location = (Location) request.getAttribute("location");

        if (location != null) {
    %>
            <div class="location-details">
                   <p><strong>Venue Name:</strong> <%= location.getVenueName() %></p>
                   <p><strong>Address:</strong> <%= location.getAddress() %></p>
                   <p><strong>City:</strong> <%= location.getCity() %></p>
                   <p><strong>State:</strong> <%= location.getState() %></p>
                   <p><strong>Country:</strong> <%= location.getCountry() %></p>
                   <p><strong>Pincode:</strong> <%= location.getPincode() %></p>
               </div>
    <%
        } else {
    %>
            <p>No location details found.</p>
    <%
        }
    %>
            <br>    <a href="CustomerHomePage" class="nav-link">DASHBOARD</a><br><br><br>
</center>
</body>
</html>
