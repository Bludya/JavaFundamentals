import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem02_CountSubstringOccurrences {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        Pattern search = Pattern.compile(sc.nextLine().toLowerCase());
        int count=0;

        Matcher matcher = search.matcher(text.toLowerCase());
        while(matcher.find()){
            matcher.group();
            count++;
        }

        System.out.println(count);
    }
}
