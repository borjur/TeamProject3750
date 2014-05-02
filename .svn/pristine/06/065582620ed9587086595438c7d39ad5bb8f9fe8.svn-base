<%-- 
    Document            : Locations page
    Created on          : Apr 18, 2013, 3:09:47 PM
    Java/JSON Author    : Erik Hall
    HTML/CSS/Javascript : Jimmy Cleveland
--%>

<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="org.fancygiraffe.global.Connections"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="javax.sql.rowset.CachedRowSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Template &middot; Bootstrap</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- Le styles -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/bootstrap-responsive.css" rel="stylesheet">
        <link href="css/site.css" rel="stylesheet">


    </head>

    <body>


        <div class="container">

            <div class="masthead">
                <h3 class="muted">Asset Management Tool</h3>
                <!-- navbar -->
                <jsp:include page="navbar.jsp" >
                    <jsp:param name="pageName" value="locations" />
                </jsp:include>
                <!-- /.navbar -->
            </div>

            <div class="container" style="padding: 20px;">
                <h3>Manage Locations</h3>
                <div class="tabbable">
                    <!-- Tab Headings-->
                    <ul class="nav nav-tabs">
                        <li ><a href="#tab1" data-toggle="tab">Add</a></li>
                        <li><a href="#tab2" data-toggle="tab">Edit</a></li>
                        <li><a href="#tab3" data-toggle="tab">Delete</a></li>
                        <li><a href="#tab4" data-toggle="tab">Manage Districts</a></li>
                        <li><a href="#tab5" data-toggle="tab">Manage Location Types</a></li>
                    </ul> <!-- /Tab Headings-->
                    <!-- Tab Contents-->
                    <div class="tab-content">

                        <!-------------------------------------- Tab1 Add form---------------------------------------------- ------------------------->
                        <div class="tab-pane active" id="tab1">

                            <div style ="float:left; width:500px;"> 
                                <form class="form-horizontal" id="addForm" action="locations" method="POST">


                                    <input type="hidden" name="action" value="add">
                                    <div class="control-group">
                                        <label class="control-label" for="location">Location</label>
                                        <div class="controls">
                                            <input type="text" name="location" id="location" required pattern=".{4,}" title="Minimun of 4 characters"> 
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label"  for="address">Address</label>
                                        <div class="controls">
                                            <input type="text"  name="address" id="address">
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for="city">City</label>
                                        <div class="controls">
                                            <input type="text" name="city" id="city">
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for="state">State</label>
                                        <div class="controls">
                                            <input class="input-mini" type="text"  pattern="[A-Z]{2}" title="Two Capital letter State Abbreviation ex UT" name="state" id="state" placeholder="UT">
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for="zip">Zip</label>
                                        <div class="controls">
                                            <input class="input-mini" type="text" name="zip" id="zip">
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for="phone">Phone</label>
                                        <div class="controls">
                                            <input class="input-medium" type="text"  pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" title="XXX-XXX-XXXX" name="phone" id="phone">
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for="district">District</label>
                                        <div class="controls">
                                            <input type="text" name="district" id="district">
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <div class="controls">
                                            <button type="submit" class="btn btn-success">Add Location</button>                                        
                                        </div><br />
                                        <%
                                            Object newLocationId = request.getAttribute("newLocationId");
                                            if (newLocationId != null && !newLocationId.toString().equals("-1")) { %>
                                        <div id="locationResult" class="alert alert-info">
                                            <strong>Success!</strong> Location added.
                                        </div> <% } else if (newLocationId != null) { %>
                                        <div id="locationResult" class="alert alert-info">
                                            <strong>Error!</strong> Couldn't add location.
                                        </div>
                                        <% } %>
                                    </div>
                                </form>
                            </div>

                            <div  style="max-width: 300px; max-height: 400px; overflow: scroll; float:left;" >  <table class="table table-striped table-bordered" id="assetQueueTable" style="font-size: 12px; background-color: white; width: 200px;">
                                    <caption style="margin-bottom: 20px;" ><h4 > Current Locations</h4></caption>
                                    <tbody>
                                        <%
                                            CachedRowSet locations = (CachedRowSet) request.getAttribute("locations"); // request locations from the servlet
                                            if (locations != null) // makes sure recieved something from servlet before trying to display it.
                                            {
                                                while (locations.next()) //displays each location in the table.
                                                {
                                                    String loc = locations.getString("location_name");%>
                                        <tr><td><%= loc%></td></tr>
                                        <% }
                                            } %>

                                    </tbody>

                                </table>
                            </div>
                        </div>

                        <!-------------------------------------- Tab2 edit form---------------------------------------------- ------------------------->
                        <div class="tab-pane" id="tab2">
                            <form class="form-horizontal">
                                <div class="control-group">
                                    <label class="control-label" for="location">Location</label>
                                    <div class="controls">
                                        <select id="editSelect">

                                            <%
                                                //   locations = (CachedRowSet) request.getAttribute("locations");
                                                if (locations != null) {
                                                    locations.beforeFirst();
                                                    while (locations.next()) {
                                                        String test = locations.getString("location_name");%>
                                            <option><%= test%></option>
                                            <% }
                                                } %>



                                            <%-- <%
                                                         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                                                         sql = "exec getUniqueLocations";
                                                         conn = DriverManager.getConnection(Connections.connectionUrl(), Connections.user(), Connections.pwd());
                                                         //stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                                         ResultSet rs = stmt.executeQuery(sql);

                                                while(rs.next()) { %>
                                                <option><%= rs.getString("location_name") %></option>
                                                <% } %>--%>
                                        </select>
                                    </div>
                                </div>
                            </form>
                            <br />
                            <form class="form-horizontal" action="locations" id="editF" method="post"> 
                                <input type="hidden" name="action" value="edit">
                                <input type="hidden" name="location" id="editlocation">
                                <div class="control-group">
                                    <label class="control-label" for="address">Address</label>
                                    <div class="controls" id="editForm" >
                                        <input type="text" name="address" id="editaddress">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="city">City</label>
                                    <div class="controls">
                                        <input type="text" name="city" id="editcity">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="state">State</label>
                                    <div class="controls">
                                        <input class="input-mini" type="text" name="state" pattern="[A-Z]{2}" title="Two Capital letter State Abbreviation ex UT" id="editstate" placeholder="UT">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="zip">Zip</label>
                                    <div class="controls">
                                        <input class="input-mini" type="text" name="zip" id="editzip">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="phone">Phone</label>
                                    <div class="controls">
                                        <input class="input-medium" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" title="XXX-XXX-XXXX" name="phone" id="editphone">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="district">District</label>
                                    <div class="controls">
                                        <input type="text" name="district" id="editdistrict">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <div class="controls">
                                        <button type="submit" class="btn btn-success">Submit Changes</button>
                                    </div>
                                </div>
                                <%
                                    Object editRowsAffected = request.getAttribute("editRowsAffected");
                                    if (editRowsAffected != null && !editRowsAffected.toString().equals("-1")) { %>
                                <div id="locationResult" class="alert alert-info">
                                    <strong>Success!</strong> Updated location.
                                </div> <% } else if (editRowsAffected != null) { %>
                                <div id="locationResult" class="alert alert-info">
                                    <strong>Error!</strong> Couldn't update location.
                                </div>
                                <% } %>
                            </form>
                        </div>
                        <!------------------------------------------------------- Tab 3 Delete ------------------------------------------------------------------------->
                        <div class="tab-pane" id="tab3">
                            <form class="form-horizontal"> 

                                ${result_div} <!-- display results in a div - accesses attribute result_div in servlet to displays results...-->

                                <div class="control-group">
                                    <label class="control-label" for="location">Location</label>
                                    <div class="controls">

                                        <select id="delSelect">

                                            <%
                                                //  locations = (CachedRowSet) request.getAttribute("locations"); // request locations from the servlet
                                                if (locations != null) {
                                                    locations.beforeFirst();
                                                    while (locations.next()) {
                                                        String loc = locations.getString("location_name");%>
                                            <option><%= loc%></option>
                                            <% }
                                                }%>

                                            <%--  <%
                                                      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                                                      sql = "exec getUniqueLocations";
                                                      //conn = DriverManager.getConnection(Connections.connectionUrl(), Connections.user(), Connections.pwd());
                                                      //stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                                      rs = stmt.executeQuery(sql);

                                                while(rs.next()) { %>
                                                <option><%= rs.getString("location_name") %></option>
                                                <% } %> --%>
                                        </select>
                                    </div>
                                </div>
                            </form>
                            <br />
                            <form class="form-horizontal" action="locations" method="post">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="location" id="dellocation">
                                <div class="control-group">
                                    <label class="control-label" for="address">Address</label>
                                    <div class="controls">
                                        <span class="input uneditable-input" id="deladdress">3927 S. 200 W.</span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="city">City</label>
                                    <div class="controls">
                                        <span class="input uneditable-input" id="delcity">Ogden</span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="state">State</label>
                                    <div class="controls">
                                        <span class="input-mini uneditable-input" id="delstate">UT</span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="zip">Zip</label>
                                    <div class="controls">
                                        <span class="input-mini uneditable-input" id="delzip">81234</span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="phone">Phone</label>
                                    <div class="controls">
                                        <span class="input-medium uneditable-input" id="delphone">8011234567</span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="district">District</label>
                                    <div class="controls">
                                        <span class="input uneditable-input" id="deldistrict">Precinct 12 Ogden</span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <div class="controls">
                                        <button type="submit" class="btn btn-danger">Delete Location</button>
                                    </div>
                                </div>

                            </form>
                        </div>
  <!-------------------------------------------------- Tab 4  Manage Districts ------------------------------------------------------------------>
                        <div class="tab-pane" id="tab4">
                            <div style ="float:left; width:500px;">                 
                                <form class="form-horizontal">  
                                    <div class="control-group">
                                        <label class="control-label" for="distLabel">Districts Label</label>
                                        <div class="controls">
                                            <input class="input-medium" type="text" id="distLabel" placeholder="i.e. Legislative District"></span>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for="distCount">District Count</label>
                                        <div class="controls">
                                            <input class="input-small" type="number" min="1" id="distLabel" >
                                        </div>
                                    </div>

   <div class="control-group">
                                        <label class="control-label" for="distCount">Current District Count</label>
                                        <div class="controls">
                                            <p>10</p>
                                        </div>
                                    </div>

                                </form>   
                            </div>            
                           
                             
                           
                        </div>

                        <!------------------------------ Tab 4  Manage Location Types ----------------------------->
                        <div class="tab-pane" id="tab5">
                            <form class="form-horizontal">  
                            </form>      
                        </div>



                    </div> <!-- /Tab Contents-->
                </div>
            </div>

            <hr>

            <div class="footer">
                <p>&copy; Company 2013</p>
            </div>

        </div> <!-- /container -->
        <!-- Le javascript -->
        <!-- Placed at the end of the document so the pages load faster -->

        <!-- JQuery -->
        <script src="http://code.jquery.com/jquery.js"></script>
        <!-- JQuery Validate Plugin -->
        <script src="js/jquery.validate.min.js"></script>
        <!-- JQuery Additional Validation Plugin (Used for PhoneUS and others) -->
        <script src="js/additional-methods.js"></script>

        <!-- Bootstrap -->
        <script src="js/bootstrap.min.js"></script>

        <script type="text/javascript">
            // Validation
            $(document).ready(function() {
                $('#addForm').validate(
                        {
                            rules: {
                                location: {
                                    minlength: 4,
                                    required: true
                                },
                                district: {
                                    minlength: 2,
                                    required: false
                                },
                                state: {
                                    minlength: 2,
                                    required: false
                                },
                                phone: {
                                    phoneUS: true,
                                    required: false
                                }
                            },
                            highlight: function(element) {
                                $(element).closest('.control-group').removeClass('success').addClass('error');
                            },
                            success: function(element) {
                                element
                                        .closest('.control-group').removeClass('error').addClass('success');
                            }

                        });
            }); // /document.ready

        </script>
        <script type="text/javascript">
            //<![CDATA[


            $(function() {

                //Set active tab
                var tab = <%= request.getAttribute("activeTab")%>;
                tab = (tab === null) ? 1 : tab;
                switch (tab) {

                    case 1:
                        $('a[href=#tab1]').tab('show');

                        break;

                    case 2:
                        $('a[href=#tab2]').tab('show');

                        break;
                    case 3:
                        $('a[href=#tab3]').tab('show');
                        break;
                }


                //Fill form in delete tab
                function fill_del_form() {

                    var selected = $('#delSelect').find(":selected").text();

                    $.ajax({
                        data: "",
                        dataType: 'json',
                        contentType: 'application/json',
                        type: "POST",
                        url: "locationJSONServlet?name=" + selected,
                        success: function(data, textStatus, jqXHR)
                        {
                            $('#dellocation').val(checkNull(data.location_name));
                            $('#deladdress').html(checkNull(data.address));
                            $('#delcity').html(checkNull(data.city));
                            $('#delstate').html(checkNull(data.state));
                            $('#delzip').html(checkNull(data.zip));
                            $('#delphone').html(checkNull(data.phone));
                            $('#deldistrict').html(checkNull(data.district));

                        },
                        error: function(xhr, ajaxOptions, thrownError) {
                            //alert(xhr.statusText);
                            //alert(thrownError);
                        }
                    });
                }

                //Fill form in edit tab
                function fill_edit_form() {

                    var selected = $('#editSelect').find(":selected").text();

                    $.ajax({
                        data: "",
                        dataType: 'json',
                        contentType: 'application/json',
                        type: "POST",
                        url: "locationJSONServlet?name=" + selected,
                        success: function(data, textStatus, jqXHR)
                        {

                            $('#editlocation').val(checkNull(data.location_name));
                            $('#editaddress').val(checkNull(data.address));
                            $('#editcity').val(checkNull(data.city));
                            $('#editstate').val(checkNull(data.state));
                            $('#editzip').val(checkNull(data.zip));
                            $('#editphone').val(checkNull(data.phone));
                            $('#editdistrict').val(checkNull(data.district));

                        },
                        error: function(xhr, ajaxOptions, thrownError) {
                            //alert(xhr.statusText);
                            //alert(thrownError);
                        }
                    });
                }



                function checkNull(s) {
                    return (s === "null") ? "" : s;
                }

                //run when page loads
                fill_del_form();
                fill_edit_form();


                //run on change
                $("#delSelect").change(fill_del_form);
                $("#editSelect").change(fill_edit_form);

            });
            //]]>


        </script>		
    </body>
</html>

