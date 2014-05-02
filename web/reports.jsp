<%@page import="java.sql.CallableStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="entities.Assets"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="org.fancygiraffe.global.Connections"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="javax.sql.rowset.CachedRowSet"%>

<%@page import="javax.sql.rowset.CachedRowSet"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Template &middot; Bootstrap</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">


        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/site.css" rel="stylesheet">
        <link href="css/bootstrap-responsive.css" rel="stylesheet">


        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
        <link rel="shortcut icon" href="../assets/ico/favicon.png">

        <link href="media/dataTables/demo_page.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table_jui.css" rel="stylesheet" type="text/css" />
        <link href="media/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
        <link href="media/themes/smoothness/jquery-ui-1.7.2.custom.css" rel="stylesheet" type="text/css" media="all" />
        <script src="scripts/jquery.js" type="text/javascript"></script>
        <script src="scripts/jquery.dataTables.min.js" type="text/javascript"></script>
        <script type="text/javascript">

                    var tableToExcel = (function() {
                            var uri = 'data:application/vnd.ms-excel;base64,'
                                    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>'
                                    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
                            , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
  return function(table, name) {
    if (!table.nodeType) table = document.getElementById(table)
    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
    window.location.href = uri + base64(format(template, ctx))
  }
})()   
            
            
        $(document).ready(function () {
            $("#companies").dataTable({
                "sPaginationType": "full_numbers",
                "bJQueryUI": true
            });
        });
        </script>
        <style type="text/css" title="currentStyle">
            @import "resources/css/demo_table.css";
        </style>
        <script type="text/javascript" language="javascript"
        src="resources/js/jquery.js"></script>
        <script type="text/javascript" language="javascript"
        src="resources/js/jquery.dataTables.js"></script>
        <script type="text/javascript" >
                                            /* Define two custom functions (asc and desc) for string sorting */
                                            jQuery.fn.dataTableExt.oSort['string-case-asc'] = function(x, y) {
                                    return ((x < y) ? - 1 : ((x > y) ?  1 : 0));
                                            };
                                            jQuery.fn.dataTableExt.oSort['string-case-desc'] = function(x, y) {
                                    return ((x < y) ?  1 : ((x > y) ? - 1 : 0));
                                            };
                                            $(document).ready(function() {
                                    /* Build the DataTable with third column using our custom sort functions */
                                    $('#example').dataTable({


                                    "aaSorting": [ [0, 'asc'], [1, 'asc'] ],
                                            "aoColumns": [
                                            { "sTitle": "ID" },
                                            { "sTitle": "NAME" },
                                            { "sTitle": "ADDRESS" },
                                            { "sTitle": "CITY" },
                                            { "sTitle": "STATE" },
                                            { "sTitle": "ZIP" },
                                            { "sTitle": "PHONE" },
                                            { "sTitle": "ACTIVE" }

                                            ],
                                            "sPaginationType": "full_numbers",
                                            "bJQueryUI": true,
                                            "bStateSave": true,
                                    });
                                            });
                                            $(document).ready(function() {
                                    /* Build the DataTable with third column using our custom sort functions */
                                    $('#examplee').dataTable({


                                    "aaSorting": [ [0, 'asc'], [1, 'asc'] ],
                                            "aoColumns": [
                                          
                                            { "sTitle": "TAG" },
                                            { "sTitle": "NAME" },
                                            { "sTitle": "MODEL" },
                                            { "sTitle": "SERIAL NUMBER" },
                                               { "sTitle": "VALUE" },
                                           { "sTitle": "TYPE" },
                                           { "sTitle": "CONDITION" },
                                            

                                          

                                            ],
                                            "sPaginationType": "full_numbers",
                                            "bJQueryUI": true,
                                            "bStateSave": true,
                                    });
                                            });</script>
    </head>

    <body id="dt_example" >




        <div class="container" id="dynamic">

            <div class="masthead">
                <h3 class="muted">Asset Management Tool</h3>


                <jsp:include page="navbar.jsp" >
                    <jsp:param name="pageName" value="reports" />
                </jsp:include> 

            </div >
            
            
            <div id="demo_jui"> 
                <form method="post">




                    <table id="example" class="display" border="2">
                        <tr>
                            <td>ID</td>
                            <td>NAME</td>
                            <td>ADDRESS</td>
                            <td>CITY</td>
                            <td>STATE</td>
                            <td>ZIP</td>
                            <td>PHONE</td>
                            <td>ACTIVE</td>
                        </tr>
                        <%
                            try {
                                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                                String url = "jdbc:sqlserver://petunia.arvixe.com;databaseName=WeberInventory3;selectMethod=cursor";
                                String username = "Weber";
                                String password = "weber";
                                String query = "select * from locationroot";
                                Connection conn = DriverManager.getConnection(url, username, password);
                                Statement stmt = conn.createStatement();
                                ResultSet rs = stmt.executeQuery(query);
                                while (rs.next()) {

                        %>
                        <tr>
                            <td><%=rs.getInt("ID")%></td>
                            <td><%=rs.getString("name")%></td>
                            <td><%=rs.getString("address")%></td>
                            <td><%=rs.getString("city")%></td>
                            <td><%=rs.getString("state")%></td>
                            <td><%=rs.getString("zip")%></td>
                            <td><%=rs.getString("phone")%></td>
                            <td><%=rs.getBoolean("active")%></td>
                        </tr>
                        <%

                            }
                        %>
                    </table>

                    <%
                            rs.close();
                            stmt.close();
                            conn.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    %>

                    <br>
                   
                            

        <input type="button" onclick="tableToExcel('example', 'W3C Example Table')" value="Export to Excel">
        <br> <br>

<table id="examplee" class="display" border="2">
    

<!--                        <tr>
                            
                            <td>TAG</td>
                            <td>NAME</td>
                            <td>MODEL</td>
                            <td>SERIAL NUMBER</td>
                           <td>VALUE</td>
                          <td>TYPE</td>
                             <td>CONDITION</td>
                             <td>NOTE</td>
                            <td>GROUP</td>
                            <td>LOCATION</td>
                            <td>DISTRICT</td>
                        </tr>-->
                        <%
                            String query = "exec rptGetAssetbyLocation ?";
                            try {
                                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                                String url = "jdbc:sqlserver://petunia.arvixe.com;databaseName=WeberInventory3;selectMethod=cursor";
                                String username = "Weber";
                                String password = "weber";
                               
                                
                                Connection conn = DriverManager.getConnection(url, username, password);
                                CallableStatement stmt = conn.prepareCall(query);
                                stmt.setString(1, "2122");
                                ResultSet rs = stmt.executeQuery();
                                while (rs.next()) {

                        %>
                        <tr>
                            
                            <td><%=rs.getString("tag")%></td>
                            <td><%=rs.getString("name")%></td>
                            <td><%=rs.getString("model")%></td>
                            <td><%=rs.getString("serialNumber")%></td>
                           <td><%=rs.getFloat("value")%></td>
                             <td><%=rs.getString("type")%></td>
                           <td><%=rs.getString("condition")%></td>
                             
                       


                        </tr>
                        <%

                            }
                        %>
                    </table>

                    <%
                            rs.close();
                            stmt.close();
                            conn.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    %>

                    <br>
                   
                            

        <input type="button" onclick="tableToExcel('examplee', 'W3C Example Table')" value="Export to Excel">
        <br> <br>

                </form>





            </div >


                   


        </div>
        









    </body>


</html>




