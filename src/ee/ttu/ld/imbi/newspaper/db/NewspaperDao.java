package ee.ttu.ld.imbi.newspaper.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;

import ee.ttu.ld.imbi.newspaper.model.Newspaper;

public class NewspaperDao {
    private static final Logger logger = Logger.getLogger(NewspaperDao.class);

    private final String connectionString;
    private final String username;
    private final String password;

    public NewspaperDao() {
        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("Database.properties"));
        } catch (Exception exception) {
            logger.error("NewspaperDao()", exception);
        }
        connectionString = properties.getProperty("connectionString");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }

    public void update(Newspaper newspaper) {
        if (newspaper == null) {
            return;
        }

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = createConnection();
            if (connection != null) {
                statement = connection.prepareStatement("UPDATE newspaper SET name = ?, founded_at = ?, description = ? WHERE newspaper_id = ?");
                statement.setString(1, newspaper.getName());
                statement.setDate(2, new java.sql.Date(newspaper.getFoundedAt().getTime()));
                statement.setString(3, newspaper.getDescription());
                statement.setInt(4, newspaper.getId());
                statement.executeUpdate();
            }
        } catch (Exception exception) {
            logger.error("NewspaperDao.update(" + newspaper.getId() + ")", exception);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    public Newspaper findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = createConnection();
            if (connection != null) {
                statement = connection.prepareStatement("SELECT newspaper_id, name, founded_at, description FROM newspaper WHERE newspaper_id = ?");
                statement.setInt(1, id);
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    return readNewspaper(resultSet);
                }
            }
        } catch (Exception exception) {
            logger.error("NewspaperDao.findById(" + id + ")", exception);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            closeConnection(connection);
        }

        return null;
    }

    public Newspaper[] findAll() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = createConnection();
            if (connection != null) {
                statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT newspaper_id, name, founded_at, description FROM newspaper ORDER BY newspaper_id");

                ArrayList<Newspaper> newspapers = new ArrayList<Newspaper>();
                while (resultSet.next()) {
                    newspapers.add(readNewspaper(resultSet));
                }

                return newspapers.toArray(new Newspaper[newspapers.size()]);
            }
        } catch (Exception exception) {
            logger.error("NewspaperDao.findAll()", exception);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            closeConnection(connection);
        }

        return new Newspaper[0];
    }

    private static Newspaper readNewspaper(ResultSet resultSet) throws SQLException {
        Newspaper newspaper = new Newspaper();
        newspaper.setId(resultSet.getInt("newspaper_id"));
        newspaper.setName(resultSet.getString("name"));
        newspaper.setFoundedAt(resultSet.getDate("founded_at"));
        newspaper.setDescription(resultSet.getString("description"));
        return newspaper;
    }

    private Connection createConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(connectionString, username, password);
        } catch (Exception exception) {
            logger.error("NewspaperDao.createConnection()", exception);
            return null;
        }
    }

    private static void closeConnection(final Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception exception) {
                logger.error("NewspaperDao.closeConnection()", exception);
            }
        }
    }

    private static void closeStatement(final Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (Exception exception) {
                logger.error("NewspaperDao.closeStatement()", exception);
            }
        }
    }

    private static void closeResultSet(final ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception exception) {
                logger.error("NewspaperDao.closeResultSet()", exception);
            }
        }
    }
}
