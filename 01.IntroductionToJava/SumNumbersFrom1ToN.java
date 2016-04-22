import java.util.Scanner;

/**
 * Created by Bludya on 15.3.2016 г..
 */
public class SumNumbersFrom1ToN {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sum = 0;
        int n = sc.nextInt();
        for (int i = 0; i <= n; i++) {
            sum+=i;
        }
        System.out.println(sum);
    }
}
