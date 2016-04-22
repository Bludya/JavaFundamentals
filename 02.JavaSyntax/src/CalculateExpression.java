import java.util.Locale;
import java.util.Scanner;

/**
 * Created by Bludya on 18.3.2016 г..
 */
public class CalculateExpression {
    public static void main(String[] args) {

        Locale.setDefault(Locale.ROOT);
        Scanner sc = new Scanner(System.in);

        double a = sc.nextDouble();
        double b = sc.nextDouble();
        double c = sc.nextDouble();

        double f1 = Math.pow(((a*a + b*b)/(a*a - b*b)),(a+b+c)/Math.sqrt(c));
        double f2 = Math.pow((a*a + b*b - c*c*c),a-b);
        double numbersAverage = (a+b+c)/3;
        double funcAverage = (f1+f2)/2;

        System.out.printf("F1 result: %.2f; F2 result: %.2f; Difference: %.2f",f1,f2,Math.abs(numbersAverage-funcAverage));
    }
}
