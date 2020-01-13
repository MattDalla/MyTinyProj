package it.teozDa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connector {

    private final String host; //"jdbc:mysql://127.0.0.1:3306/personaldb"
    private final String user; //"root"
    private final String password; //"0000"

    public Connector(String host, String user, String password) {
        this.host = host;
        this.user = user;
        this.password = password;
    }

    //tipo di ritorno: Connection.class obj
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(host, user, password);
//        if (connection != null) {
//            System.out.println("Connected to the database!");
//        } else {
//            System.out.println("Failed to make connection!");
//        }
    }





    public void Connection() {
        // https://docs.oracle.com/javase/8/docs/api/java/sql/package-summary.html#package.description
        // auto java.sql.Driver discovery -- no longer need to load a java.sql.Driver class via Class.forName

        // register JDBC driver, optional since java 1.6
        /*try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

        // auto close connection
//        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/personaldb", "root", "0000")) {
//
//            if (conn != null) {
//                System.out.println("Connected to the database!");
//            } else {
//                System.out.println("Failed to make connection!");
//            }
//
//        } catch (SQLException e) {
//            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
}
