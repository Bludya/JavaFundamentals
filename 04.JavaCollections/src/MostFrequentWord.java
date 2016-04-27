import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class MostFrequentWord {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeMap<String,Integer> countWords = new TreeMap<>();

        String[] words = sc.nextLine().toLowerCase().replaceAll("[^a-zA-Z1-9]"," ").split(" "); //replacing all non characters or numbers with space and then splitting it.
        int count=0; //holding the count of the most repeated word

        for(String word:words){
            if(!word.equals("")){  //if it is an actual word add it to the Map
                int tempCount;
                try{
                    tempCount = countWords.get(word)+1; //if the word is in the map get its count+1
                }catch (NullPointerException ex){
                    tempCount = 1;  // if not - NullPointerException and then set the count to 1 to add it later
                }

                if(tempCount>count){
                    count = tempCount; //if the current count is bigger than the previous biggest, make it the new biggest count
                }
                countWords.put(word,tempCount); //add the word with its count
            }
        }

        for(Map.Entry<String,Integer> entry:countWords.entrySet()){ //printing the results
            if(entry.getValue()==count){  //this is to print all the words with maximum appearences
                System.out.printf("%s -> %d times%n", entry.getKey(),entry.getValue());
            }
        }

    }
}
