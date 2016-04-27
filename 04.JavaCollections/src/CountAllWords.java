import java.util.Scanner;

public class CountAllWords {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] words = sc.nextLine().replaceAll("[^a-zA-Z0-9]"," ").split(" "); //replacing all non characters or numbers with space and then splitting it.
        int count=0;
        for(String word:words){
            if(!word.equals("")){ //if it is not an empty string, count is as a word
                count++;
            }
        }
        System.out.println(count);
    }
}
