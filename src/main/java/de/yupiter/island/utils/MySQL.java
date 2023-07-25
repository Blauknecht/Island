package de.yupiter.island.utils;

import java.sql.*;

public class MySQL {

    public Connection con;

    String host;
    String name;
    String password;
    String database;

    public MySQL(String host, String user, String pw, String db) {
        this.host = host;
        this.name = user;
        this.password = pw;
        this.database = db;
    }

    public void connect() {
        if(!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database + "?autoReconnect=true", name, password);
                System.out.println("[MySQL] Verbindung zur MySQL erfolgreich!");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("[MySQL] §4Fehler: §c" + e.getMessage());
            }
        }
    }

    public void close() {
        if(isConnected()) {
            try {
                con.close();
                System.out.println("[MySQL] Verbindung zur MySQL beendet!");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("[MySQL] §4Fehler: §c" + e.getMessage());
            }
        }
    }

    public ResultSet query(String qry) {
        ResultSet rs = null;

        try {
            if (con.isClosed()) {
                connect();
            }
            Statement st = con.createStatement();
            st.executeQuery(qry);
            rs = st.getResultSet();
            return rs;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return rs;
    }


    public void update(String qry) {
        try {
            if (con.isClosed()) {
                connect();
            }
            Statement st = con.createStatement();
            st.executeUpdate(qry);
            st.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public boolean isConnected() {
        return con != null;

    }

    public Connection getConnection() {
        return con;
    }

}