import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by Bludya on 18.3.2016 г..
 */
public class RandomizeNumbersFromNtoM {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        ArrayList<Integer> usedNumbers = new ArrayList<>();

        int n = sc.nextInt();
        int m = sc.nextInt();

        if(n>m){
            int temp = m;
            m = n;
            n = temp;
        }
        for(int i=n;i<=m;i++){
            usedNumbers.add(i);
        }

        Collections.shuffle(usedNumbers);

        for (Integer i:
             usedNumbers) {
            System.out.print(i+" ");
        }
    }
}