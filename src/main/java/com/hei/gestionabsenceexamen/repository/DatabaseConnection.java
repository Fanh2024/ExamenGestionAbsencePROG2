package com.hei.gestionabsenceexamen.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/gestion_absence_hei";
    private static final String USER = "postgres";
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    // Méthode pour obtenir une connexion réutilisable
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {  // Utilisation de la méthode getConnection ici
            if (conn != null) {
                System.out.println("Connexion établie !");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
