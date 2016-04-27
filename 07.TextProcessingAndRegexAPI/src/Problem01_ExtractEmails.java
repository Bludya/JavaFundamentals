import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem01_ExtractEmails {
    public static void main(String[] args) {
        Pattern ptr = Pattern.compile("[\\da-zA-Z]+([._-][\\da-zA-Z]+)*@[\\da-zA-Z]+([._-][\\da-zA-Z]+)*");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Matcher matcher = ptr.matcher(input);

        while(matcher.find()){
            System.out.println(matcher.group().replaceAll(" ",""));
        }
    }
}
