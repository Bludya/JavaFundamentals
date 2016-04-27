import java.util.Scanner;
import java.util.TreeMap;

public class LargestSequenceOfEqualStrings {
    public static void main(String[] args) {
        TreeMap<String,Integer> wordsCount = new TreeMap<>();

        Scanner sc = new Scanner(System.in);

        String[] words = sc.nextLine().split(" ");//separate all the words
        String wordToPrint = "";
        int countWordToPrint = 0;

        for(String word:words){
            int count;
            try{
                count=wordsCount.get(word)+1; //if it is in the  Map, get it's count and add 1 more.
            }catch (NullPointerException ex){//if not, it will return NullPointerException, then make the count to 1 and then add the word with that count.
                count=1;
            }
            if(count>countWordToPrint){
                countWordToPrint=count;
                wordToPrint=word;
            }
            wordsCount.put(word,count);
        }

        System.out.println((new String(new char[countWordToPrint])).replaceAll("\0",wordToPrint+" "));
    }
}
