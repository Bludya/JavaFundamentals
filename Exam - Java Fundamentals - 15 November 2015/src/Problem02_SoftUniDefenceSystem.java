import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem02_SoftUniDefenceSystem {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("([A-Z][a-z]+)(?:(?![A-Z][a-zA-Z]*[A-Z]).)*([A-Z][a-zA-Z]*[A-Z])(?:(?!(\\d*)L).)*(\\d*)L");
        Scanner sc = new Scanner(System.in);
        long alcohol = 0;

        String line = sc.nextLine();
        while(!line.contains("OK KoftiShans")){
            Matcher matcher = pattern.matcher(line);
            while(matcher.find()){
                String name = matcher.group(1);
                String alcoholName = matcher.group(2).toLowerCase();
                int amount = Integer.parseInt(matcher.group(3));
                alcohol+=amount;
                System.out.printf("%s brought %d liters of %s!%n",name,amount,alcoholName);
            }

            line = sc.nextLine();
        }
        System.out.printf("%.3f softuni liters",(double)alcohol/1000);
    }
}
