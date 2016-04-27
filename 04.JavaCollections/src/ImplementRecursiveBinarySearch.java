import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ImplementRecursiveBinarySearch {
    public static void main(String[] args) {

        //this is the basic binary search algoithm and it will not return
        // index 0 in:
        // 4
        // 4 4 4 4 4 8 8 8
        //for example.
        //it returns the first found match.



        Scanner sc = new Scanner(System.in);

        int searchedNumber = Integer.parseInt(sc.nextLine());

        String[] temp = sc.nextLine().split(" ");
        int[] numbers = new int[temp.length];

        for(int i=0;i<temp.length;i++){
           numbers[i] = Integer.parseInt(temp[i]);
        }

        Arrays.sort(numbers); //sort the numbers in case they were not sorted on input

        System.out.println(search(0,numbers.length,numbers,searchedNumber)); //start recursion

    }

    private static int search(int min, int max,int[] array,int number) {

        int mid = (max+min)/2; //find the middle index

        if(min==max){ //bottom of recursion, number not found
            return -1;
        }
        if(array[mid]==number){ // number found
            return mid;
        }
        else if(array[mid]>number){ //number is smaller than number at current mid
            return search(min,mid-1,array,number);
        }
        else{
            return search(mid,max,array,number); //number is bigger than the number at current mid
        }

    }
}
