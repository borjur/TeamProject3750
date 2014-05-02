<%-- 
    Document   : Assets page
    Created on : Apr 22, 2013, 6:52:16 PM
    Author     : Alex

--%>

<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="org.fancygiraffe.global.Connections"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.sql.rowset.CachedRowSet"%>
<jsp:useBean id="location_names" class="org.fancygiraffe.assets.AssetModel" scope="session"/>

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
        <link href="css/site.css" rel="stylesheet">
        <link href="css/bootstrap-responsive.css" rel="stylesheet">

    </head>

    <body>

        <div class="container">

            <div class="masthead">
                <h3 class="muted">Asset Management Tool</h3>

                <!-- navbar -->
                <jsp:include page="navbar.jsp" >
                    <jsp:param name="pageName" value="assets" />
                </jsp:include>
                <!-- /.navbar -->
            </div>

            <div class="container" style="margin-top: 30px; padding: 20px;">
                <h3>Manage Assets</h3>
                <div class="tabbable">
                    <!-- Tab Headings-->
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#tab1" data-toggle="tab">Add</a></li>
                        <li ><a href="#tab2" data-toggle="tab">Edit</a></li>
                        <li><a href="#tab3" data-toggle="tab">Delete</a></li>
                    </ul> <!-- /Tab Headings-->
                    <!-- Tab Contents-->
                    <div class="tab-content">

                        <!-- Tab1 -->
                        <div class="tab-pane active" id="tab1">
                            <form class="form-horizontal" id="addForm" action="assets" method="POST">
                                                               <input type="hidden" name="action" value="add">
                                                               
                                <div class="span7" style="margin-left: -30px;">
                                    <div class="control-group">
                                        <label class="control-label" for="description">Description</label>
                                        <div class="controls">
                                            <input type="text" name="description" id="description">
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for="model">Model</label>
                                        <div class="controls">
                                            <input type="text" name="model" id="model">
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for="sn">Serial Number</label>
                                        <div class="controls">
                                            <input type="text" name="sn" id="sn">
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for="type">Asset Type</label>
                                        <div class="controls">
                                            <select id="typeSelect" style="width: 112px;">                                                
                                                <option>Printer</option>
                                                <option>Scanner</option>
                                                <option>Party Pencil</option>
                                                <option>Other</option>
                                            </select>
                                            <input class="input-small typePopover" type="text" id="type" placeholder="Select other" disabled>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for="value">Value</label>
                                        <div class="controls">
                                            <div class="input-prepend input-append">
                                                <span class="add-on">$</span>
                                                <input class="span1" type="text" name="value" id="value">'
                                                <span class="add-on">.00</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label"  for="location">Location</label>
                                        <div class="controls">
                                            <select name="locationName">
                                                <%
                                                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                                                    String sql = "exec getUniqueLocations";
                                                    Connection conn = DriverManager.getConnection(Connections.connectionUrl(), Connections.user(), Connections.pwd());
                                                    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                                    ResultSet rs = stmt.executeQuery(sql);

                                                while(rs.next()) { %>
						<option><%= rs.getString("location_name") %></option>
                                                <% } %>
                                                
                                                                                           
                                            </select>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for="condition">Condition</label>
                                        <div class="controls">
                                            <select id="conditionSelect" style="width: 112px;">                                                
                                                <option>Good</option>
                                                <option>Needs Maintenance</option>
                                                <option>Do not move</option>
                                                <option>Other</option>
                                            </select>
                                            <input class="input-small conditionPopover" type="text" name="condition" id="condition" placeholder="Select other" disabled>
                                        </div>                                        
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for="assetTag">Asset Tag</label>
                                        <div class="controls">
                                            <!-- TODO <input type="text" name="inputAssetTag" id="assetTag"> -->
                                            <input type="text" name="inputAssetTag" id="inputAssetTag">
                                            <!-- change id to inputAssetTag later, to test stuff -->
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <div class="controls">
                                            <button type="submit" class="btn btn-success">Add Asset</button>
                                        </div>
                                    </div>
                                </div>
                                <div class="span3">
                                    <textarea rows="10" placeholder="Notes go here..." name="notes" style="max-height: 300px; max-width: 300px;"></textarea>
                                </div>
                            </form>
                        </div>

                        <!-- Tab 2-->
                        <div class="tab-pane" id="tab2">
                            <form class="form-horizontal"> <!-- id="editForm" action="assets" method="POST"> -->
                                <input type="hidden" name="action" value="edit">
                                <div class="span7" style="margin-left: -30px;">
                                    <div class="control-group">
                                        <label class="control-label">Asset Tag</label>
                                        <div class="controls">
                                            <input type="text" id="searchAssetTag" name="searchAssetTag">
                                            <button type="submit" class="btn btn-success">Look Up</button>
                                        </div>
                                        <br />
                                        <div class="control-group">
                                            <label class="control-label" for="description">Description</label>
                                            <div class="controls">
                                                <input type="text" id="description">
                                            </div>


                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="model">Model</label>
                                            <div class="controls">
                                                <input type="text" id="model">
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="sn">Serial Number</label>
                                            <div class="controls">
                                                <input type="text" id="sn">
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="type">Asset Type</label>
                                            <div class="controls">
                                                <input type="text" id="type">
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="value">Value</label>
                                            <div class="controls">
                                                <div class="input-prepend input-append">
                                                    <span class="add-on">$</span>
                                                    <input class="span1" type="text" id="value">'
                                                    <span class="add-on">.00</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="location">Location</label>
                                            <div class="controls">
                                                <select class="input-middle">
                                                    <%
                                                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                                                        sql = "exec getUniqueLocations";
                                                        conn = DriverManager.getConnection(Connections.connectionUrl(), Connections.user(), Connections.pwd());
                                                        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                                        rs = stmt.executeQuery(sql);

                            while (rs.next()) {%>
                                                    <option><%= rs.getString("location_name")%></option>
                                                    <% }%>


                                                </select>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="condition">Condition</label>
                                            <div class="controls">
                                                <input type="text" id="condition">
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <div class="controls">
                                                <button type="submit" class="btn btn-success">Edit Asset</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="span3">
                                    <textarea rows="10" placeholder="Notes go here..."></textarea>
                                </div>
                            </form>
                        </div> <!-- End Tab 2 -->

                        <!-- Tab 3 -->
                        <div class="tab-pane" id="tab3">
                            <form class="form-horizontal"> <!-- id="deleteForm" action="assets" method="POST"> -->
                                <input type="hidden" name="action" value="delete">
                                <div class="span7" style="margin-left: -30px;">
                                    <div class="control-group">
                                        <label class="control-label">Asset Tag</label>
                                        <div class="controls">
                                            <input type="text" name="action" id="searchAssetTag">
                                            <button type="submit" class="btn btn-success">Look Up</button>
                                        </div>

                                    </div>
                                    
                                    <div class="control-group">
                                        <label class="control-label">Description</label>
                                        <div class="controls">
                                            <span class="input uneditable-input"></span>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">Model</label>
                                        <div class="controls">
                                            <span class="input uneditable-input"></span>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">Serial Number</label>
                                        <div class="controls">
                                            <span class="input uneditable-input"></span>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">Asset Type</label>
                                        <div class="controls">
                                            <span class="input uneditable-input"></span>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">Value</label>
                                        <div class="controls">
                                            <div class="input-prepend input-append">
                                                <span class="add-on">$</span>
                                                <!--<span class="input span1 uneditable-input">400</span> -->
                                                <span class="add-on">.00</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">Location</label>
                                        <div class="controls">
                                            <!--<span class="input uneditable-input">Warehouse</span> -->
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">Condition</label>
                                        <div class="controls">
                                            <!--<span class="input uneditable-input">Good</span> -->
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <div class="controls">
                                            <button type="submit" class="btn btn-danger">Delete Asset</button>
                                        </div>
                                    </div>
                                </div>
                                <div class="span3">
                                    <textarea rows="10" placeholder="Notes go here..."></textarea>
                                </div>
                            </form>
                        </div>  <!-- end Tab 3-->
                    </div> <!-- /Tab Contents-->
                </div>
            </div>

            <hr>

            <div class="footer">
                <p>&copy; Company 2013</p>
            </div>

        </div> <!-- /container -->
        
        <!-- Le javascript -->

        <!-- JQuery -->
        <script src="http://code.jquery.com/jquery.js"></script>
            <!-- JQuery Validate Plugin -->
            <script src="js/jquery.validate.min.js"></script>
            
        <!-- Bootstrap -->
        <script src="js/bootstrap.min.js"></script>
        
        <script type="text/javascript">
             // Validation
                $(document).ready(function() {
                    $('#addForm').validate(
                    {
                        rules: {
                            description: {
                                minlength: 3,
                                required: true
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
                    
                     $('#conditionSelect').on("change", function() {
                        if($(this).val() === 'Other')  //TODO  changed from == to ===
                        {
                            $('#condition').attr("disabled", false).focus();                            
                        }
                        else
                        {
                            $('#condition').attr("disabled", true).val("");
                        }
                    });
                    
                    $('#typeSelect').on("change", function() {
                        if($(this).val() === 'Other')  //TODO  changed from == to ===
                        {
                            $('#type').attr("disabled", false).focus();                            
                        }
                        else
                        {
                            $('#type').attr("disabled", true).val("");
                        }
                    });
                }); // /document.ready
                
                $('.typePopover').popover({
                    title: 'New Asset Type',
                    content: 'If the type you want is not in the dropdown list, enter a new type here.',
                    trigger: 'focus',
                    placement: 'right'
                  });
                  
                  $('.conditionPopover').popover({
                    title: 'New Asset Condition',
                    content: 'If the condition you want is not in the dropdown list, enter a new condition here.',
                    trigger: 'focus',
                    placement: 'right'
                  });
			
            </script>

    </body>
</html>
