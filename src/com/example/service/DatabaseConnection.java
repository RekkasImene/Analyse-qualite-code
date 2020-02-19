package com.example.service;

import com.example.main.Home;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {
    private String user;
    private String pass;
    private String jdbcDriver;
    private String dbUrl;
    private static final Logger logger = Logger.getLogger(Home.class.getName());
    public DatabaseConnection(String user, String pass, String jdbcDriver, String dbUrl) {
        this.user = user;
        this.pass = pass;
        this.jdbcDriver = jdbcDriver;
        this.dbUrl = dbUrl; }
    public  Connection connect() { Connection conn = null;
        try { conn = DriverManager.getConnection(dbUrl, user, pass);
            Class.forName(jdbcDriver); }
        catch (SQLException|ClassNotFoundException e) { logger.log(Level.ALL, e.getMessage(), e); }
        return conn; }
    public  void disconnect(Connection conn ) {
        try { conn.close(); }
        catch (SQLException e) { logger.log(Level.ALL, e.getMessage(), e); } }
    public void createDb(Connection conn) {
        Statement stmt = null;
        try { stmt = conn.createStatement();
            String sql =  "CREATE TABLE   City " +
                    "(idCity INTEGER not NULL, " +
                    " name VARCHAR(255), " +
                    " touristNumber INTEGER, " +
                    " description VARCHAR(255), " +
                    " PRIMARY KEY ( idCity ))";
            stmt.executeUpdate(sql);
            stmt.close(); }
        catch(SQLException se) { logger.log(Level.ALL, se.getMessage(), se); }
        catch(Exception e) { logger.log(Level.ALL, e.getMessage(), e); }
         finally {try{ if(stmt!=null) stmt.close(); }
         catch(SQLException se2) { logger.log(Level.ALL, se2.getMessage(), se2); } }
    }
}