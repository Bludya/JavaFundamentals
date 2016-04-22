import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Bludya on 18.3.2016 г..
 */
public class OddAndEvenPairs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> numbers = new ArrayList<>();

        for (String number : sc.nextLine().split(" ")) {
            numbers.add(Integer.parseInt(number));
        }

        if(numbers.size()%2==1){
            System.out.println("Invalid length.");
            return;
        }

        for(int i = 0;i<numbers.size();i+=2){
            if(numbers.get(i)%2==0 && numbers.get(i+1)%2==0){
                System.out.printf("%d, %d -> both are even%n", numbers.get(i), numbers.get(i+1));
            }
            else if(numbers.get(i)%2!=0 && numbers.get(i+1)%2!=0){
                System.out.printf("%d, %d -> both are odd%n", numbers.get(i), numbers.get(i+1));
            }
            else{
                System.out.printf("%d, %d -> different%n", numbers.get(i), numbers.get(i+1));
            }
        }

    }
}
