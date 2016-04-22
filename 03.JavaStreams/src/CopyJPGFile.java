import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CopyJPGFile {
    public static void main(String[] args) {

        try (FileInputStream inputStream = new FileInputStream("Resources/InputFiles/jpg_ro_copy.jpg");
             FileOutputStream outputStream = new FileOutputStream("Resources/OutputFiles/my-copied-picture.jpg")) {

            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            outputStream.write(buffer);
            outputStream.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
