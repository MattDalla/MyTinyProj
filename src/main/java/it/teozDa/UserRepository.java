package it.teozDa;

import java.sql.*;
import java.util.*;

public class UserRepository {

    private final Connector connector; //variabile di tipo Connector

    private static final String TABLE_NAME = "utenti";


    public UserRepository(Connector connector) {  //Costruttore: inizializza la variabile connector
        this.connector = connector;
    }


    //CRUD create read update delete


    public List<User> getAllUsers() {
        //List<String> strings = Arrays.asList("1", "2", "3");
        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_NAME;

        try (Connection conn = connector.getConnection(); //il metodo getConnection della classe Connector, ritorna obj Connection
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");

                User user = new User(nome, cognome);
                user.setID(id);

                users.add(user);
            }
            return users;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new IllegalStateException("error fetching users", ex);
        }
    }

    public int insertUser(User user) {
        //Inserisce un nuovo utente nel DB, tab "utenti", input Nome e Cognome.

        ResultSet rs = null;
        int userID = 0;

        String sql = "INSERT INTO utenti(Nome,Cognome) VALUES(?,?)";

        try (Connection conn = connector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // set parameters for statement
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());


            int rowAffected = pstmt.executeUpdate();
            if (rowAffected == 1) {
                // get user id
                rs = pstmt.getGeneratedKeys();
                if (rs.next())
                    userID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return userID;
    }


    public void updateFirstNameUser(int ID, String firstName) {
        String sql = "UPDATE utenti SET nome = ? WHERE id = ?";
        int rowAffected;
        try (Connection conn = connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, firstName);
            stmt.setInt(2, ID);
            rowAffected = stmt.executeUpdate();

            if (rowAffected == 1) {
                System.out.println("Utente modificato:");
                getUserByID(ID);
            }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateLastNameUser(int ID, String lastName) {
        String sql = "UPDATE utenti SET cognome = ? WHERE id = ?";
        int rowAffected;
        try (Connection conn = connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, lastName);
            stmt.setInt(2, ID);
            rowAffected = stmt.executeUpdate();

            if (rowAffected == 1) {
                System.out.println("Utente modificato:");
                getUserByID(ID);
            }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public void getUserByID(int ID) {
        String sql = "SELECT id, nome, cognome FROM UTENTI WHERE id=?";
        ResultSet rs = null;

        try (Connection conn = connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                System.out.println("Utente ID " + ID + ": " + nome + " " + cognome);
            }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public void deleteUser(int ID) {
        String sql = "DELETE FROM UTENTI where id=?";
        int deletedRec;

        try (Connection conn = connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ID);
            deletedRec = stmt.executeUpdate();

            if (deletedRec == 1) System.out.println("Utente ID " + ID + ", rimosso con successo!");

            else System.out.println("ID non trovato, non è stato rimosso alcun utente.");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public void deleteAll() {
        String sql = "DELETE FROM UTENTI";
        int delRecs;
//ALTER TABLE utenti AUTO_INCREMENT = 1;
        try (Connection conn = connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            delRecs = stmt.executeUpdate();
            if (delRecs >= 1) System.out.println("\t ***** Utenti rimossi con successo! *****");
            resetID();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void resetID() { //FAFO: Metodo private?
        String sql = "ALTER TABLE utenti AUTO_INCREMENT = 1";

        try (Connection conn = connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.executeUpdate();
            System.out.println("\t ***** ID Values resetted successfully! *****");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}






