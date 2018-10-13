import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbConnection {
    private final String defaultUrl = "jdbc:mysql://localhost:3306/java";
    public String conUrl;
    public Properties connectionProps = new Properties();
    public Connection conn;

    public DbConnection(String url, String user, String password) {
        this.conUrl = url;
        this.connectionProps.setProperty("user", user);
        this.connectionProps.setProperty("password", password);
    }

    public DbConnection() {
        this.conUrl = this.defaultUrl;
        this.connectionProps.setProperty("user", "root");
        this.connectionProps.setProperty("password", "root");
    }

    public Connection initConnection() throws SQLException {
        this.conn = DriverManager.getConnection(this.conUrl, this.connectionProps);
        return conn;
    }

    public Statement createStmt() throws  SQLException {
        Statement stmt = this.conn.createStatement();
        return stmt;
    }
}
