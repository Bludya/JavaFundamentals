import java.util.Scanner;

/**
 * Created by Bludya on 15.3.2016 г..
 */
public class PrintAChracterTriangle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                char c = (char) ('a'+j);
                System.out.print(c+" ");
            }
            System.out.println();
        }
        for (int i = n-1; i >=0; i--) {
            for (int j = 0; j < i; j++) {
                char c = (char) ('a' + j);
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
