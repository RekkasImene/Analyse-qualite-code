package com.example.main;

import com.example.model.City;
import com.example.service.DatabaseConnection;
import com.example.service.DatabaseService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Home {
    private static final Logger logger = Logger.getLogger(Home.class.getName());
    private static String password;
    public static final String USER="root";

    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/citiesdb";

    public static void main(String[] args) {
        try(BufferedReader buff= new BufferedReader(new FileReader("config.properties"))){
          password = buff.readLine();
        } catch (Exception e) {
            logger.log(Level.ALL, e.getMessage(), e);
        }
        DatabaseConnection db = new DatabaseConnection(USER, password, JDBC_DRIVER, DB_URL);
        Connection conn = db.connect();
        try {
            if (args.length > 0) {
                int id = Integer.parseInt(args[0]);
                String name = args[1];
                int numTourist = Integer.parseInt(args[2]);
                String desc = args[3];
                City city1 = new City(id, name, numTourist, desc);
                DatabaseService.addCity(conn, city1);
            }
        }
        catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Arguments" + args[0] + args[2] + " must be an integer.", e);
            System.exit(1);
        }
    }
}
