import java.sql.SQLException;

import javax.sql.RowSet;

import com.sun.rowset.CachedRowSetImpl;

class LongTermEmps {
    public static void main(String args[]) {
        String url = "jdbc:mysql://localhost:3306/java";
        String sqlStmt = "SELECT id, lastname, hire_date, salary, title FROM employee WHERE hire_date <= '1995-01-01'";

        try (RowSet rs = new CachedRowSetImpl()) {
            rs.setUrl(url);
            rs.setUsername("root");
            rs.setPassword("root");
            rs.setCommand(sqlStmt);
            rs.execute();

            while (rs.next()) {
                System.out.println(rs.getString("id") + "  "
                        + rs.getString("lastname") + " "
                        + rs.getString("hire_date") + " "
                        + rs.getFloat("salary"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}