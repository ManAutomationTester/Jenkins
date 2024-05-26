package com.ostrum.database.connection;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SSHDatabaseConnector {

    public static void main(String[] args) {
        String sshHost = "3.8.111.213";
        int sshPort = 5532;
        String sshUser = "ubuntu";
        String privateKeyPath = "D:/OstrumTech/ostrumTech_WorkSpace/OT-TEST-SCRIPT/src/main/resources/sql_data/ot-bastion_test.pem";
        int localPort = 3310;  // Local port to forward
        String remoteHost = "ot-proxy.proxy-caehrpeiakcr.eu-west-2.rds.amazonaws.com";  // Database server
        int remotePort = 3306;  // MySQL port on the database server
        String dbUser = "ot_read";
        String dbPassword = "e$eVG#]#dnok#lFq";
        String dbName = "db_ostrum_lgw";

        try {
            Connection connection = connectToDatabaseWithSSH(sshHost, sshPort, sshUser, privateKeyPath, localPort, remoteHost, remotePort, dbUser, dbPassword, dbName);
            System.out.println("Successfully connected to the database throug h SSH tunnel.");
            // Do something with the connection
            // For example:
            // Statement stmt = connection.createStatement();
            // ResultSet rs = stmt.executeQuery("SELECT * FROM your_table");
            // ...
            // Close the connection when done:
            
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    		ResultSet resultSet = statement.executeQuery("select * from db_ostrum_lgw.airport_code;");

    		// to get the total number of column
    		int columnCount = resultSet.getMetaData().getColumnCount();
    		System.out.println("the total column count = " + columnCount);

    		// this will move the result set to last row
    		resultSet.last();

    		// to get the total number of row
    		int rowCount = resultSet.getRow();
    		System.out.println("the total row count = " + rowCount);

    		// this will bring it back to the first row
    		resultSet.beforeFirst();

    		while (resultSet.next()) {
    	                String data = resultSet.getString("airport_code");
//    			String data = resultSet.getString("code");

    			System.out.println(data);
    		}

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection connectToDatabaseWithSSH(String sshHost, int sshPort, String sshUser, String privateKeyPath,
                                                      int localPort, String remoteHost, int remotePort,
                                                      String dbUser, String dbPassword, String dbName) throws SQLException {

        try {
            // SSH Tunnel
            JSch jsch = new JSch();
            jsch.addIdentity(privateKeyPath);
            Session session = jsch.getSession(sshUser, sshHost, sshPort);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            session.setPortForwardingL(localPort, remoteHost, remotePort);

            // JDBC URL for connecting through the local SSH tunnel
            String connectionUrl = "jdbc:mysql://localhost:" + localPort + "/" + dbName + "?useSSL=false";

            // Database Connection
            return DriverManager.getConnection(connectionUrl, dbUser, dbPassword);
        } catch (Exception e) {
            throw new SQLException("Failed to connect to the database through SSH tunnel", e);
        }
    }
}
