import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem06_StartsAndEndsWithCapitalLetter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Matcher matcher = pattern.matcher(text);
        while(matcher.find()){
            String word = matcher.group();
            if(word.charAt(0)>='A' && word.charAt(0)<='Z' && word.charAt(word.length()-1)>='A' && word.charAt(word.length()-1)<='Z'){
                System.out.println(word);
            }
        }


    }
}
