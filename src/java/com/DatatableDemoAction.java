package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class DatatableDemoAction extends HttpServlet {

	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String[] cols = { "ID", "name", "address", "city", "state",
				"zip" };

		JSONObject result = new JSONObject();
		int amount = 10;
		int start = 0;
		int col = 0;
		String dir = "asc";
		String sStart = request.getParameter("iDisplayStart");
		String sAmount = request.getParameter("iDisplayLength");
		String sCol = request.getParameter("iSortCol_0");
		String sdir = request.getParameter("sSortDir_0");

		if (sStart != null) {
			start = Integer.parseInt(sStart);
			if (start < 0) {
				start = 0;
			}
		}
		if (sAmount != null) {
			amount = Integer.parseInt(sAmount);
			if (amount < 10 || amount > 50) {
				amount = 10;
			}
		}
		if (sCol != null) {
			col = Integer.parseInt(sCol);
			if (col < 0 || col > 5)
				col = 0;
		}
		if (sdir != null) {
			if (!sdir.equals("asc"))
				dir = "desc";
		}

		String colName = cols[col];
		int total = -1;
		try {
			total = getTotalRecordCount();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		AMOUNT = amount;
		SEARCH_TERM = request.getParameter("sSearch");
		COL_NAME = colName;
		DIR = dir;
		START = start;

		try {
			result = getStudentData(total, request);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();

		out.print(result);

	}

	public JSONObject getStudentData(int total, HttpServletRequest request)
			throws SQLException, ClassNotFoundException {

		int totalAfterFilter = total;
		JSONObject result = new JSONObject();
		JSONArray array = new JSONArray();
		String searchSQL = "";

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String USERNAME = "Weber";
                String PASSWORD = "weber";
                String CONN_STRING = "jdbc:sqlserver://petunia.arvixe.com;databaseName=WeberInventory3;selectMethod=cursor";

		Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
		// @formatter:off
		String sql = "SELECT " + "ID, name, address, city, state, "
				+ "zip " + "FROM " + "locationroot " + "WHERE "
				+ "ID = ? ";

		// @formatter:on

		String globeSearch = " AND (name like '%" + SEARCH_TERM + "%'"
				+ "or address like '%" + SEARCH_TERM + "%'"
				+ "or city like '%" + SEARCH_TERM + "%'"
				+ "or state like  '%" + SEARCH_TERM + "%'"
				+ "or zip like '%" + SEARCH_TERM + "%')";

		if (SEARCH_TERM != "") {
			searchSQL = globeSearch;
		}

		sql += searchSQL;
		sql += " order by " + COL_NAME + " " + DIR;

		sql += " limit " + START + ", " + AMOUNT;

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setBoolean(1, false);

		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			JSONArray ja = new JSONArray();

			ja.put(rs.getString("ID"));
			ja.put(rs.getString("name"));
			ja.put(rs.getString("address"));
			ja.put(rs.getString("city"));
			ja.put(rs.getString("state"));
			ja.put(rs.getString("zip"));

			array.put(ja);
		}
		statement.close();
		rs.close();

		// @formatter:off
		String query = "SELECT " + "COUNT(*) as count " + "FROM " + "locationroot "
				+ "WHERE " + "ID = ? ";
		// @formatter:on

		if (SEARCH_TERM != "") {
			query += searchSQL;

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setBoolean(1, false);

			ResultSet resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				totalAfterFilter = resultSet.getInt("count");
			}
			stmt.close();
			resultSet.close();
			connection.close();
		}
		try {
			result.put("iTotalRecords", total);
			result.put("iTotalDisplayRecords", totalAfterFilter);
			result.put("aaData", array);
		} catch (Exception e) {

		}

		return result;
	}

	public int getTotalRecordCount() throws SQLException {

		int total = -1;
		// @formatter:off
		String sql = "SELECT " + "COUNT(*) as count " + "FROM " + "locationroot "
				+ "WHERE " + "ID = ? ";

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

		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setBoolean(1, false);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			total = resultSet.getInt("count");
		}
		resultSet.close();
		statement.close();
		connection.close();

		return total;
	}

}
