import java.util.Scanner;

public class Problem03_CountSpecifiedWord {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] words = sc.nextLine().toLowerCase().split("[^a-zA-Z]+");
        String searchedWord = sc.nextLine().toLowerCase();
        int count=0;

        for(String word:words){
            if(word.equals(searchedWord)){
                count++;
            }
        }

        System.out.println(count);

    }
}
