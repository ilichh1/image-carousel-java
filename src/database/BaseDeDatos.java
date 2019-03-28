/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ilichh1
 */
public class BaseDeDatos {
    private static final String DATABASE_URL = "localhost";
    private static final String DATABASE_PORT = "8889"; // 3306
    private static final String DATABASE_NAME = "imagenes";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "root";
    
    private static Connection CONEXION_DB = null;
    
    public static Connection initConnection() {
        
        if (CONEXION_DB != null) {
            return CONEXION_DB;
        }
        
        try {
            // Intento de conexión
            String stringConnection = "jdbc:mysql://"
                                    + DATABASE_URL + ":" + DATABASE_PORT
                                    + "/" + DATABASE_NAME
                                    + "?user=" + DATABASE_USERNAME
                                    + "&password=" + DATABASE_PASSWORD;

            CONEXION_DB = DriverManager.getConnection(stringConnection);
            return CONEXION_DB;

        } catch (SQLException ex) {
            // Manejar errores
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("NO SE PUDO CONECTAR A LA BASE DE DATOS.");
            return null;
        }
    }
    
    public static boolean executeSQL(String sql) {
        Statement stmt;
        
        try {
            stmt = initConnection().createStatement();
            stmt.execute(sql);
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("NO SE PUEDO EJECUTAR SQL");
            System.out.println(ex.getLocalizedMessage());
            return false;
        }
        
        return true;
    }
    
    public static ResultSet executeSqlAndGetResultSet(String sql) {
        try {
            Statement stmt = initConnection().createStatement();
            stmt.execute(sql);
            return stmt.getResultSet();
        } catch (SQLException ex) {
            System.out.println("NO SE PUEDO CREAR ResultSet AL EJECUTAR SQL.");
            System.out.println(ex.getLocalizedMessage());
            return null;
        }
    }
    
}
