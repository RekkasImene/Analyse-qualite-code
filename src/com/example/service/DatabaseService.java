package com.example.service;
import com.example.main.Home;
import com.example.model.City;
import javax.annotation.Resource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DatabaseService {
    @Resource(lookup = "java:/MySQLDS")
    private static final String IDCITY = "idCity";
    private static final String NAME = "name";
    private static final String TOURISTNUMBER = "touristNumber";
    private static final String DESCRIPTION = "description";
    private DatabaseService() { throw new IllegalStateException("Utility class"); }
    private static final Logger logger = Logger.getLogger(Home.class.getName());
    public static int addCity(Connection conn, City city) {
        PreparedStatement pstmt = null;
        int i = -1;
        try { String sql = "INSERT INTO City " + "VALUES (?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, city.getIdCity());
            pstmt.setString(2, city.getName());
            pstmt.setInt(3, city.getTouristNumber());
            pstmt.setString(4, city.getDescription());
            i = pstmt.executeUpdate();
            pstmt.close(); }
        catch (SQLException se) { logger.log(Level.ALL, se.getMessage(), se); }
        catch (Exception e) { logger.log(Level.ALL, e.getMessage(), e); }
        finally { try { if (pstmt != null) pstmt.close(); }
        catch (SQLException se2) { logger.log(Level.ALL, se2.getMessage(), se2); } }
        return i; }
   public static City getCity(Connection conn,int idCity) {
        City city = new City();
       String sql = "SELECT * FROM City where idCity=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);){ pstmt.setInt(1,idCity);
            try(ResultSet rs = pstmt.executeQuery();) {
                while (rs.next()) {
                    city.setIdCity(rs.getInt(IDCITY));
                    city.setName(rs.getString(NAME));
                    city.setTouristNumber(rs.getInt(TOURISTNUMBER));
                    city.setDescription(rs.getString(DESCRIPTION)); } } }
        catch (SQLException se) { logger.log(Level.ALL, se.getMessage(), se); }
        return  city; }
    public static List<City> getCities(Connection conn) {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT * FROM City";
        try(Statement stmt = conn.createStatement();) {
            try(ResultSet rs = stmt.executeQuery(sql);){
            while (rs.next()) {
                City city = new City();
                city.setIdCity(rs.getInt(IDCITY ));
                city.setName(rs.getString(NAME));
                city.setTouristNumber(rs.getInt(TOURISTNUMBER));
                city.setDescription(rs.getString(DESCRIPTION));
                cities.add(city); } } }
        catch (SQLException se) { logger.log(Level.ALL, se.getMessage(), se); }
        return  cities; }
    public static City getCityByName(Connection conn,String name) {
        City city = new City();
        String sql = "SELECT * FROM City where name=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setString(1, name);
            try(ResultSet rs = pstmt.executeQuery();) { while (rs.next()) {
                    city.setIdCity(rs.getInt(IDCITY));
                    city.setName(rs.getString(NAME));
                    city.setTouristNumber(rs.getInt(TOURISTNUMBER));
                    city.setDescription(rs.getString(DESCRIPTION)); } } }
        catch (SQLException se) { logger.log(Level.ALL, se.getMessage(), se); }
        return  city; }
}
