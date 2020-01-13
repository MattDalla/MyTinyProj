package it.teozDa;

import java.sql.*;
import java.util.*;

public class ExpenseRepository {

    private final Connector connector; //variabile di tipo Connector
    //CRUD create read update delete


    public ExpenseRepository(Connector connector) {
        this.connector = connector;
    }

    public void updateExpense(){}

    public void insertExpense(int ID, Expense exp) {

        String typeOfExp = exp.getTypeOfExp();
        String dateOfPay = exp.getDateOfPaym();
        String cvgedPeriod = exp.getCvedPeriod();
        Double amount = exp.getAmount();


        String sql = "INSERT INTO spese(IDUTENTE, TipoSpesa, DataPagamento, PeriodoCoperto, Somma) VALUES(?, ?, ?, ?, ?)";

        try (Connection conn = connector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, ID);
            pstmt.setString(2, typeOfExp);
            pstmt.setString(3, dateOfPay);
            pstmt.setString(4, cvgedPeriod);
            pstmt.setDouble(5, amount);

            int rowAffected = pstmt.executeUpdate();
            if (rowAffected == 1) {
                System.out.println("\t ***** Inserimento avvenuto con successo! *****");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Expense> getExpenseList() {
        List<Expense> expenses = new ArrayList<>();
        String sql = "SELECT * FROM spese";

        try (Connection conn = connector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                String tipoSpesa = rs.getString("TipoSpesa");
                String dataSpesa = rs.getString("DataPagamento");
                String periodoCoperto = rs.getString("PeriodoCoperto");
                Double somma = rs.getDouble("somma");

                Expense exp = new Expense(tipoSpesa, dataSpesa, periodoCoperto, somma);
                expenses.add(exp);
            }
            return expenses;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new IllegalStateException("error fetching expenses", ex);
        }
    }

    public void deleteExpense(int ID, String dataPag, String tipo){
        String sql = "DELETE FROM SPESE WHERE IDUtente = ? AND DataPagamento = ? AND TipoSpesa = ?";
        int deletedRec;

        try (Connection conn = connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ID);
            stmt.setString(2, dataPag);
            stmt.setString(3, tipo);
            deletedRec = stmt.executeUpdate();

            if (deletedRec == 1) System.out.println("\t ***** Voce spesa ID: " + ID + ", Tipo: " + tipo + ", Data Pagamento: " + dataPag + ", rimossa con successo! *****");

            else System.out.println("\t ***** Voce non trovata, operazione non riuscita. *****");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void deleteAllExps(){
        String sql = "DELETE FROM spese";
        int delRecs;

        try (Connection conn = connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            delRecs = stmt.executeUpdate();
            if (delRecs >= 1) System.out.println("\t ***** Voci di spesa rimosse con successo! *****");


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


}

