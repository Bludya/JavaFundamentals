import java.util.Scanner;
import java.util.TreeSet;

public class ExtractAllUniqueWords {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] words = sc.nextLine().toLowerCase().replaceAll("[^a-zA-Z0-9]"," ").split(" "); //replacing all non characters or numbers with space and then splitting it.

        TreeSet<String> wordsToPrint = new TreeSet<>(); //Using tree set because it automatically sorts the strings

        for(String word:words){
            if(!word.equals("")){  //if it is an actual word add it to the set
                wordsToPrint.add(word); // if it is already in the set it wont add it, so we print 1 word only once
            }
        }

        String output = wordsToPrint.toString().replaceAll("[,\\[\\]]",""); //removing the braces and commas from the print
        System.out.println(output);
    }
}
