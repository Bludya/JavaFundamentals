import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CreateZipArchive {
    public static void main(String[] args) {
        try(ZipOutputStream outputStream= new ZipOutputStream(
                        new FileOutputStream("Resources/OutputFiles/text-files.zip"));

            FileInputStream words =  new FileInputStream("Resources/InputFiles/words.txt");
            FileInputStream lines = new FileInputStream("Resources/InputFiles/SumLine");
            FileInputStream countChars = new FileInputStream("Resources/OutputFiles/count-chars.txt")){

            int count;

            byte[] buffer = new byte[words.available()];
            outputStream.putNextEntry(new ZipEntry("words.txt"));
            count=words.read(buffer);
            outputStream.write(buffer,0,count);

            buffer = new byte[lines.available()];
            outputStream.putNextEntry(new ZipEntry("lines.txt"));
            count = lines.read(buffer);
            outputStream.write(buffer,0,count);

            buffer = new byte[countChars.available()];
            outputStream.putNextEntry(new ZipEntry("count-chars.txt"));
            count = countChars.read(buffer);
            outputStream.write(buffer,0,count);

            outputStream.close();

        } catch (Exception ex){
            System.out.println(ex);
        }


    }
}
