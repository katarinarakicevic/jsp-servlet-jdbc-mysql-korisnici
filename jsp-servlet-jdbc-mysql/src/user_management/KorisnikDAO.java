package user_management;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class KorisnikDAO {
    
    private  static String INSERT_KORISNICI_SQL = "INSERT INTO korisnici" + "  (ime, email, zemlja) VALUES " +
        " (?, ?, ?);";

    private   static String SELECT_KORISNICI_BY_ID = "select id,ime,email,zemlja from korisnici where id =?";
    private  static String SELECT_ALL_KORISNICI = "select * from korisnici";
    private static String DELETE_KORISNICI_SQL = "delete from korisnici where id = ?;";
    private  static String UPDATE_KORISNICI_SQL = "update korisnici set ime = ?,email= ?, zemlja =? where id = ?;";

    public KorisnikDAO() {}

    protected static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/primer?useSSL=false","root",null);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public static void insertKorisnik(Korisnik korisnik) throws SQLException {
        System.out.println(INSERT_KORISNICI_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_KORISNICI_SQL)) {
            preparedStatement.setString(1, korisnik.getIme());
            preparedStatement.setString(2, korisnik.getEmail());
            preparedStatement.setString(3, korisnik.getZemlja());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public static  Korisnik selectKorisnik(int id) {
        Korisnik korisnik = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_KORISNICI_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String ime = rs.getString("ime");
                String email = rs.getString("email");
                String zemlja = rs.getString("zemlja");
                korisnik = new Korisnik(id, ime, email, zemlja);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return korisnik;
    }

    public static List < Korisnik > selectAllKorisnici() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Korisnik > korisnik = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_KORISNICI);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String ime = rs.getString("ime");
                String email = rs.getString("email");
                String zemlja = rs.getString("zemlja");
                korisnik.add(new Korisnik(id, ime, email, zemlja));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return korisnik;
    }

    public static  boolean deleteKorisnik(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_KORISNICI_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public static  boolean updateKorisnik(Korisnik korisnik) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_KORISNICI_SQL);) {
            statement.setString(1, korisnik.getIme());
            statement.setString(2, korisnik.getEmail());
            statement.setString(3, korisnik.getZemlja());
            statement.setInt(4, korisnik.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}