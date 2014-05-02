<%-- 
    Document   : Relocate page
    Created on : Apr 18, 2013, 3:09:47 PM
    Author     : Jimmy Cleveland
--%>

<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="org.fancygiraffe.global.Connections"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="javax.sql.rowset.CachedRowSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Relocate Assets</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="Jimmy Cleveland" content="">

        <!-- Le styles -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/site.css" rel="stylesheet">
        <link href="css/bootstrap-responsive.css" rel="stylesheet">
    </head>

    <body>

        <div class="container">

            <div class="masthead">
                <h3 class="muted">Asset Management Tool</h3>
                <!-- navbar -->
                <jsp:include page="navbar.jsp" >
                    <jsp:param name="pageName" value="relocate" />
                </jsp:include>
                <!-- /.navbar -->
            </div>

            <div class="container">
                <div style="margin:  0 auto;">
                    <div class="form-inline" style="padding: 30px;">
                        <form action="QueryServlet" method="POST">
                            <input type="text" name="assetID" class="input-medium assetPopover" placeholder="Asset Tag">
                            <input type="submit" style="display: none;">
                            </form>
                            <% 
                                 Map<String, Integer> locations = (Map)session.getAttribute("sessionLocations");
                                 if (locations != null) { 
                             %>
                        <form action="ReassignServlet" method="POST" class="pull-right" style="margin-top: -54px;">
                            <select name="dropdown" style="margin-right: 24px;">
                                <%--
                                <% for (String key : locations.keySet()) { %>
                                <option value="<%=key%>"><%=key%></option>
                                <% } %>
                                --%>

                                <%
                                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                                    String sql = "exec getUniqueLocations";
                                    Connection conn = DriverManager.getConnection(Connections.connectionUrl(), Connections.user(), Connections.pwd());
                                    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                                                            ResultSet rs = stmt.executeQuery(sql);

                                                                            while (rs.next()) {%>
                                <option><%= rs.getString("location_name")%></option>
                                <% } %>
                            </select>
                            <button type="submit" class="btn btn-success" style="height: 35px; line-height: 25px; font-size: 20px;">Relocate</button>
                        </form>
                        <% } %>
                                                
                    </div>
                </div>
                        
                 <!-- Reassign Success/Fail logic: returns how many asset's location were changed -->
                 <% if (request.getAttribute("count") != null) { 
                        Integer count = (Integer)request.getAttribute("count");
                        if (count == 0) { %>
                            <div id="errorMessage" class="alert alert-error">
                                <strong>Error!</strong> An asset had a problem during relocation.
                            </div>
                        <% } else { session.setAttribute("sessionCrs", null); %>                 
                            <div id="successMessage" class="alert alert-success">
                                <strong>Success!</strong> <%=count%> items were moved.
                            </div>
                        <% } } %>
                
                <hr style="margin-top: -10px;">
                <% 
                    CachedRowSet data = (CachedRowSet)session.getAttribute("sessionCrs");
                    if (data != null) { 
                %>
                <table class="table table-striped table-bordered" id="assetQueueTable" style="margin-top: -15px; background-color: white;">
                    
                    <!-- Used to count the rows in the table -->
                    <% data.last();
                        int count = data.getRow(); %>
                    <caption style="margin-bottom: 5px;"><h4>Asset Queue</h4>
                        <span class="badge badge-success pull-right">x<%=count%></span></caption>
                    <thead>
                        <tr>
                            <th>Asset ID</th>
                            <th>Name</th>
                            <th>Type</th>
                            <th>Model</th>
                            <th>Condition</th>
                            <th>Location</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%
                        data.afterLast();
                        while (data.previous()) {
                    %>
                    <tr>
                        <td><%= data.getInt("id") %></td>
                        <td><%= data.getString("asset_name") %></td>
                        <td><%= data.getString("asset_type") %></td>
                        <td><%= data.getString("model") %></td>
                        <td><%= data.getString("asset_condition") %></td>
                        <td><%= data.getString("location_name") %></td>
                    </tr>
                    <% } } %>
                    </tbody>
                </table>
                <form action="ClearSessionServlet" method="POST">
                    <input type="submit" value="Clear" />
                </form>                
            </div>
            <hr>

        </div> <!-- /container -->
        <!-- javascript -->
        <script src="http://code.jquery.com/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script type="text/javascript">
            //function showStuff(id) {
            //    document.getElementById(id).style.display = 'table';
            //}
            //function hideStuff(id) {
            //    document.getElementById(id).style.display = 'none';
            //}
            
            $('.assetPopover').popover({
              title: 'Asset Tag',
              content: 'Type or scan your asset tag here then press Enter.',
              trigger: 'focus',
              placement: 'right'
            });
            
            
            
        </script>

    </body>
</html>
