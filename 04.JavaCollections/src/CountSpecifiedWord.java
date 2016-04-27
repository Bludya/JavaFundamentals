import java.util.*;

public class CountSpecifiedWord {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] words = sc.nextLine().toLowerCase().replaceAll("[^a-zA-Z0-9]"," ").split(" "); //replacing all non characters or numbers with space and then splitting it.
        String wordToFind = sc.nextLine();
        int count=0;
        for(String word:words){
            if(word.equals(wordToFind)){ //if it is equal of the word we seek count it.
                count++;
            }
        }
        System.out.println(count);
    }
}
