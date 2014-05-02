<%@ page import="java.util.*" %> <!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	var basePath = '${pageContext.request.contextPath}';
</script> 
<link type="text/css" rel="stylesheet" media="all" href="${pageContext.request.contextPath}/assets/datatable/css/demo_table_jui.css" >
<link type="text/css" rel="stylesheet" media="all" href="${pageContext.request.contextPath}/assets/jquery-ui/css/redmond/jquery-ui-1.8.11.custom.css" >
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.5.2.min.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-ajax-form-plugin/jquery.form.js" ></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/datatable/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/jquery-ui/js/jquery-ui-1.8.11.custom.min.js" ></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/datatable-demo.js"></script>	

<title>Student Form</title>
</head>
<body>
<form>
	<div class="titleDiv">Reports List</div>
	<div class="clearfix"></div>
	
	<div class="formDiv">
		<table width="100%" 
			class="dataTables_wrapper" id="studentListTable">
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Address</th>
					<th>City</th>
					<th>State</th>
					<th>Zip</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</form>
</body>
</html>
