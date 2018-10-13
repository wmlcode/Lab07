import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

class ImageFromFile {
    public static void main(String args[]) {
        DbConnection dbConn = new DbConnection("jdbc:mysql://localhost:3306/java", "root", "root");

        String sqltxt = "UPDATE employee SET mugshot = ? WHERE id = ?";

        try (Connection conn = dbConn.initConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqltxt);) {

            String filepath[] = { "emp1.jpg", "emp2.jpg", "emp3.jpg" };
            int idArray[] = { 9883, 6881, 6644 };

            for (int i = 0; i < filepath.length; i++) {
                FileInputStream istream = new FileInputStream(
                        filepath[i]);
                int length = (int) (new File(filepath[i])).length();
                byte[] image = new byte[length];
                istream.read(image, 0, length);

                pstmt.setBytes(1, image);
                pstmt.setInt(2, idArray[i]);
                pstmt.executeUpdate();

                istream.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
