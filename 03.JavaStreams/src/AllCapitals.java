import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AllCapitals {
    public static void main(String[] args) {

        try(BufferedReader reader = new BufferedReader(
                new FileReader("Resources/InputFiles/AllCapitals"))){
            List<String> output = new ArrayList<>();
            String line = reader.readLine();

            while(line!=null){
                output.add(line);
                line=reader.readLine();
            }

            PrintWriter writer = new PrintWriter(
                    new FileWriter("Resources/InputFiles/AllCapitals"));
            for(String temp:output){
                writer.println(temp.toUpperCase());
            }
            writer.close();
        } catch (Exception ex){
            System.out.println(ex);
        }
    }
}
