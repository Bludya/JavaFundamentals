package ExamJune1st2014;

import java.util.*;

public class Problem4_LogsAgregator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeMap<String,TreeSet<String>> ipHolder = new TreeMap<>();
        TreeMap<String,Integer> durationHolder = new TreeMap<>();

        int n=Integer.parseInt(sc.nextLine());

        for(int i=0;i<n;i++){
            String[] line = sc.nextLine().split(" ");
            TreeSet<String> tempIpHolder = new TreeSet<>();
            int tempDuration=0;

            if(ipHolder.containsKey(line[1])){
                tempIpHolder=ipHolder.get(line[1]);
                tempIpHolder.add(line[0]);

                tempDuration=durationHolder.get(line[1]);
                tempDuration+=Integer.parseInt(line[2]);
            }
            else{
                tempIpHolder.add(line[0]);
                tempDuration+=Integer.parseInt(line[2]);
            }
            ipHolder.put(line[1],tempIpHolder);
            durationHolder.put(line[1],tempDuration);
        }

        for(Map.Entry<String,TreeSet<String>> entry:ipHolder.entrySet()){
            System.out.printf("%s: %d ",entry.getKey(),durationHolder.get(entry.getKey()));
            System.out.println(entry.getValue());
        }
    }
}
