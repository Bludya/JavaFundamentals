import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class CountCharacterTypes {
    public static void main(String[] args) {
        final String vowelsGroup = "AEIOUaeiou";
        final String punctMarks = "!,.?";
        int vowels,consonants,punctuations;

        vowels = 0;
        consonants = 0;
        punctuations = 0;

        try(BufferedReader reader = new BufferedReader(
                new FileReader("Resources/InputFiles/words.txt"));
            PrintWriter writer = new PrintWriter(
                    new FileWriter("Resources/OutputFiles/count-chars.txt"))){

            String line = reader.readLine();
            while (line!=null){
                for(String c:line.replaceAll(" ","").split("")){
                    if(vowelsGroup.contains(c)){
                        vowels++;
                    }
                    else if(punctMarks.contains(c)){
                        punctuations++;
                    }
                    else{
                        consonants++;
                    }
                }
                line=reader.readLine();
            }
            writer.printf("Vowels: %s\nConsonants: %s\nPunctuation: %s\n",vowels,consonants,punctuations);
            writer.close();
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }
}
