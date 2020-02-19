package com.test;

import com.example.model.City;
import com.example.service.DatabaseConnection;
import com.example.service.DatabaseService;
import org.junit.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DatabaseServiceTest {
    Connection conn,conn2;
    DatabaseConnection db,db2;
    static final String USER = "sa";
    static final String PASS = "";
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:mem:test";
    static final String DB_URL2 = "jdbc:h2:mem:test2";

    @Before
    public  void setUp() throws Exception {
        db = new DatabaseConnection(USER,PASS,JDBC_DRIVER,DB_URL);
        db2=new DatabaseConnection(USER,PASS,JDBC_DRIVER,DB_URL2);
        conn = db.connect();
        conn2=db2.connect();
        db.createDb(conn);
        db2.createDb(conn2);
    }

    @Test
    public void testAddCity() {
        City city = new City(1,"Alger",300000000,"Belle ville");
        int i = DatabaseService.addCity(conn,city);
        Assert.assertEquals(i,1);
    }

   @Test
    public void testGetCity() throws SQLException {
        City city = new City(1,"Alger",300000000,"Belle ville");
        DatabaseService.addCity(conn,city);
        City city2 = DatabaseService.getCity(conn,1);
        Assert.assertEquals(city2,city);
    }


    @Test
    public void testGetCities() throws SQLException {
        City city1 = new City(1,"Alger",300000000,"Belle ville");
        City city2 = new City(2,"Oran",300000000,"Belle ville");
        City city3 = new City(3,"Annaba",300000000,"Belle ville");
        City[] expectedCities = {city1,city2,city3};
        DatabaseService.addCity(conn,city1);
        DatabaseService.addCity(conn,city2);
        DatabaseService.addCity(conn,city3);
        List cities =  DatabaseService.getCities(conn);
        Assert.assertArrayEquals(cities.toArray(),expectedCities);
    }

    @Test
    public void testGetCityByName()  {
        City city = new City(1,"Alger",300000000,"Belle ville");
        DatabaseService.addCity(conn,city);
        City city2 = DatabaseService.getCityByName(conn,"Alger");
        Assert.assertEquals(city2,city);
    }

    @After
    public  void tearDown() throws Exception {
        db.disconnect(conn);
        db2.disconnect(conn2);
    }

}