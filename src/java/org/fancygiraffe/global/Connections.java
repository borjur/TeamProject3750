package org.fancygiraffe.global;

public class Connections {
	
	public static String connectionUrl() {
		return "jdbc:sqlserver://petunia.arvixe.com;databaseName=WeberInventory3;selectMethod=cursor";
	}
	public static String user() {
		return "Weber";
	}
	public static String pwd() {
		return "weber";
	}
}
