package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class TableDemoAction extends HttpServlet {

	private String STUDENT_ID_LIST = "studentList";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<LocationModel> studentList = null;
		try {
			studentList = selectStudentInfo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("======studentList :"+studentList);
		String destination = "/views/com/table-demo.jsp";
		request.setAttribute(STUDENT_ID_LIST, studentList);

		RequestDispatcher rd = request.getRequestDispatcher(destination);
		rd.forward(request, response);

	}

	public List<LocationModel> selectStudentInfo() throws SQLException {

		List<LocationModel> al = new ArrayList<LocationModel>();

		// @formatter:off

		String query =

		" SELECT  ID,name,address,city,state,zip " +

		" FROM " +

		" locationroot " +

		" order by ID asc ";

		// @formatter:on
			String USERNAME = "Weber";
	String PASSWORD = "weber";
	String CONN_STRING =
			"jdbc:sqlserver://petunia.arvixe.com;databaseName=WeberInventory3;selectMethod=cursor";

		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);

		while (resultSet.next()) {

			LocationModel student = new LocationModel();
			student.setId(resultSet.getInt("ID"));
			student.setName(resultSet.getString("name"));
			student.setAddress(resultSet.getString("address"));
			student.setCity(resultSet.getString("city"));
			student.setState(resultSet.getString("state"));
			student.setZip(resultSet.getString("zip"));

			al.add(student);
		}

		resultSet.close();
		statement.close();

		return al;

	}

}
