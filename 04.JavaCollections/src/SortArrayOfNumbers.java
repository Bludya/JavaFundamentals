import java.util.Arrays;
import java.util.Scanner;

public class SortArrayOfNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i]=sc.nextInt(); //get all the integers into an array
        }
        Arrays.sort(numbers);       // sort the array

        for(int number:numbers){    //print the array
            System.out.print(number+" ");
        }
    }
}
