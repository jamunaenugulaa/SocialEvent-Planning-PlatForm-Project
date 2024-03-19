<%@ page language="java" %>
<%@ page import="com.example.SocialEventAppSecurity.Model.EventCategory" %>
<%@ page import="java.time.LocalDate" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <!-- Your CSS styling -->
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
             table {
                        margin: 0 auto; /* Center the table */
                        border-collapse: collapse; /* Collapse table borders */
                    }

                    td {
                        padding: 50px; /* Add padding to every table cell */
                    }

                    form {
                        display: inline-block; /* Ensure forms are displayed inline */
                        text-align: left; /* Align form elements to the left */
                    }
   .submit-button {
            margin-top: 10px;
             margin-left: 50px;
        }

    </style>

</head>
<body>
<center>
    <div class="container">
        <center>
            <div class="header">
                <h3>Search Locations Here</h3>
            </div>
                </center>
       <table>
         <tr>
           <td>
           <div>
            <form action="getLocationsBasedOnCategory" >
               <label for="eventCategory">Event Category:</label>
               <select name="eventCategory">
                 <%-- Iterate over the EventCategory enum values --%>
                 <% for (EventCategory category : EventCategory.values()) { %>
                   <option value="<%= category %>"><%= category %></option>
                      <% } %>
                          </select><br>
                 <input type="submit" value="ENTER" class="submit-button">  </form>
             </div>
           </td>
           <td>
           <div>
            <form action="getLocationsBasedOnCapacity" >
             <label for="capacity">Capacity:</label>
              <input path="capacity" name="capacity" type="number" /><br>
                 <input type="submit" value="ENTER" class="submit-button">  </form>
             </div>
           </td>
            <td>
            <div>
        <form action="getLocationsBasedOnDate" >
        <label for="dateInput">Enter Date:</label>
        <input type="date" id="dateInput" name="dateInput"  min="<%=java.time.LocalDate.now()%>" required>
        <br>
         <input type="submit" value="ENTER" class="submit-button">  </form>

            <div>
            </td>
           <td>
           <div>
          <form action="getLocationsBasedOnForm" >
                <label for="eventCategory">Event Category:</label>
               <select name="eventCategory">
               <% for (EventCategory category : EventCategory.values()) { %>
               <option value="<%= category %>"><%= category %></option>
                 <% } %></select><br>

                 <label for="capacity">Capacity:</label>
              <input path="capacity" name="capacity" type="number" /><br>

                          <label for="dateInput">Enter Date:</label>
                          <input type="date" id="dateInput" name="dateInput" min="<%=java.time.LocalDate.now()%>" >
                          <br>
                 <input type="submit" value="ENTER" class="submit-button">
          </form></div>
                 </td>

         </tr>
       </table>

        <br>
        <a href="CustomerHomePage" class="nav-link">DASHBOARD</a><br><br><br>
    </div>
</body>
</html>
