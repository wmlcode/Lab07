import java.sql.*;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;

import com.sun.rowset.CachedRowSetImpl;

class LongTermEmps2 {
    public static void main(String args[]) {
        String url = "jdbc:mysql://localhost:3306/java";
        String sqlStmt = "SELECT id, lastname, hire_date, salary, title FROM employee WHERE hire_date <= '1995-01-01'";

        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlStmt);
             CachedRowSet crs = new CachedRowSetImpl();) {

            crs.populate(rs);
            stmt.close();

            conn.setAutoCommit(false);

            while (crs.next()) {
                crs.updateFloat("salary", 50_100f);
                crs.updateRow();
            }
            crs.setTableName("employee");
            crs.acceptChanges(conn);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}