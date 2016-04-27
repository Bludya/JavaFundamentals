import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CardsFrequences {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeMap<String,Integer> countCards = new TreeMap<>();

        String[] cards = sc.nextLine().toLowerCase().replaceAll("[^a-zA-Z0-9]"," ").split(" "); //replacing all non characters or numbers with space and then splitting it.
        int count=0;

        for(String card:cards){
            if(!card.equals("")){  //if it is an actual card add it to the Map
                int tempCount;
                try{
                    tempCount = countCards.get(card)+1; //if the card is in the map get its count+1
                }catch (NullPointerException ex){
                    tempCount = 1;  // if not - NullPointerException and then set the count to 1 to add it later
                }
                count++; //new card
                countCards.put(card,tempCount); //add the card with its count
            }
        }
        for(String card:cards){ //read the cards from initial string so i print them in same order
            try{
                double occurance = (double)countCards.get(card)/(double)count*100;
                System.out.printf("%s -> %.2f%%%n",card,occurance);
                countCards.remove(card); //remove printed card from map so i dont print it more than once

            }catch(Exception ex){
                //do nothing
            }

        }
        
    }
}
