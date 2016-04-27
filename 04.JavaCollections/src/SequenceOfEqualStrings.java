import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

//it's not printing them in the same order ans shown examples, but there is
// no condition in te problem text for that either, so i've done it like that.
public class SequenceOfEqualStrings {
    public static void main(String[] args) {
        Map<String,Integer> wordsList = new TreeMap<>();

        Scanner sc = new Scanner(System.in);
        String[] words = sc.nextLine().split(" ");

        for(String word:words){
            Integer count;
            try {
                count = wordsList.get(word) + 1;  //get the counter for the word and aaa 1 more to it.
            } catch(NullPointerException ex){     //if it is not in the list it returns NullPointerexception
                count=1;                          //then make the counter 1 and then add it to the Map with the word.
            }
            wordsList.put(word,count);
        }


        for(Map.Entry<String,Integer> word:wordsList.entrySet()){
            System.out.println(new String(new char[word.getValue()]).replaceAll("\0",word.getKey()+" "));
        }

    }
}
