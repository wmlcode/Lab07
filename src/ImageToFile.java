import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;

class ImageToFile {
    public static void main(String args[]) {
        DbConnection dbConn = new DbConnection("jdbc:mysql://localhost:3306/java", "root", "root");

        String sqltxt = "SELECT id, mugshot FROM employee WHERE length(mugshot) > 0";

        try (Connection conn = dbConn.initConnection();
             Statement stmt = conn.createStatement();) {

            ResultSet rs = stmt.executeQuery(sqltxt);

            while (rs.next()) {
                File image = new File(String.valueOf(rs.getInt(1)) + "_.jpg");
                FileOutputStream fos = new FileOutputStream(image);
                byte[] buffer = new byte[1];
                InputStream inputStream = rs.getBinaryStream(2);
                while (inputStream.read(buffer) > 0) {
                    fos.write(buffer);
                }
                fos.close();
            }
        }
        catch (SQLException e) {
            System.err.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
