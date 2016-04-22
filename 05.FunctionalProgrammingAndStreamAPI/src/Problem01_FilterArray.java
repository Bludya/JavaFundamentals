
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Problem01_FilterArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

       List<String> stringArray = Arrays.asList(sc.nextLine().split(" ")); //will spilt strings by space only

        stringArray.stream().filter(p -> p.length()>3).forEach(p -> System.out.printf("%s ",p));
    }
}
