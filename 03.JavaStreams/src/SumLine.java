import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class SumLine {
    public static void main(String[] args) {
        try(BufferedReader reader = new BufferedReader(
                new FileReader(
                        new File("Resources/InputFiles/SumLine")))){
            String line = reader.readLine();

            while(line!=null){
                int sum = 0;
                for(char c:line.toCharArray()){
                    sum+=c;
                }
                System.out.println(sum);
                line = reader.readLine();
            }
        } catch(Exception ex){
            System.out.println(ex);
        }

    }

}
