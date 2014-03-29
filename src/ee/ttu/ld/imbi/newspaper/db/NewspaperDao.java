package ee.ttu.ld.imbi.newspaper.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import ee.ttu.ld.imbi.newspaper.model.Newspaper;

public class NewspaperDao {
    public void update(Newspaper newspaper) {
        if (newspaper == null) {
            return;
        }

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = createConnection();
            if (connection == null) {
                return;
            }

            statement = connection.prepareStatement("UPDATE newspaper SET name = ?, founded_at = ?, description = ? WHERE newspaper_id = ?");
            statement.setString(1, newspaper.getName());
            statement.setDate(2, new java.sql.Date(newspaper.getFoundedAt().getTime()));
            statement.setString(3, newspaper.getDescription());
            statement.setInt(4, newspaper.getId());
            statement.executeUpdate();
        } catch (Exception exception) {
            System.err.println("NewspaperDao.update(" + newspaper.getId() + "): " + exception.getMessage());
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    private static Connection createConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost/newspaper", "x", "x");
        } catch (Exception exception) {
            System.err.println("NewspaperDao.createConnection(): " + exception.getMessage());
            return null;
        }
    }

    private static void closeConnection(final Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception exception) {
                System.err.println("NewspaperDao.closeConnection(): " + exception.getMessage());
            }
        }
    }

    private static void closeStatement(final Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (Exception exception) {
                System.err.println("NewspaperDao.closeStatement(): " + exception.getMessage());
            }
        }
    }
}
