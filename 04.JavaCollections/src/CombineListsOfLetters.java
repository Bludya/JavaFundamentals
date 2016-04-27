import java.util.ArrayList;
import java.util.Scanner;

public class CombineListsOfLetters {
    public static void main(String[] args) {

        ArrayList<Character> firstLine = new ArrayList<>();
        ArrayList<Character> secondLine = new ArrayList<>(); //useless for only printing purposes
        Scanner sc = new Scanner(System.in);

        for(char c:sc.nextLine().replaceAll(" ","").toCharArray()){ //put all letters from first line in ArrayList and print them
            firstLine.add(c);
            System.out.print((char)c+" ");
        }

        for(char c:sc.nextLine().replaceAll(" ","").toCharArray()){ //put all letters from second line in second Arraylist and print if not in first ArrayList
            secondLine.add(c);
            if(!firstLine.contains(c)){
                System.out.print((char)c+" ");
            }
        }

    }
}
