import javafx.collections.transformation.SortedList;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Project{
    private String name;
    private int totalErrorsCount;
    private int criticalErrorsCount;
    private int warningsCount;
    private List<String> criticalMessages;
    private List<String> warnings;

    public Project(String name){
        this.name = name;
        this.totalErrorsCount = 0;
        this.criticalErrorsCount = 0;
        this.warningsCount = 0;
        this.criticalMessages = new ArrayList<>();
        this.warnings = new ArrayList<>();
    }

    public void addError(String type, String error){
        if(type.equals("Critical")){
            this.totalErrorsCount++;
            this.criticalErrorsCount++;
            this.criticalMessages.add(error);
        }
        else if(type.equals("Warning")){
            this.totalErrorsCount++;
            this.warningsCount++;
            this.warnings.add(error);
        }
    }

    public int getTotalErrorsCount(){
        return this.totalErrorsCount;
    }

    public int getCriticalErrorsCount(){
        return this.criticalErrorsCount;
    }

    public int getWarningsCount(){
        return this.warningsCount;
    }

    public String getName(){
        return this.name;
    }

    public List<String> getCriticalMessages(){
        return this.criticalMessages;
    }

    public  List<String> getWarnings(){
        return this.warnings;
    }
}

public class Problem04_LogParser{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String,Project> projects = new HashMap<>();

        String line = sc.nextLine();
        while(!line.equals("END")){
            Pattern pattern = Pattern.compile("\\{\"Project\": \\[\"(.+)\"\\], \"Type\": \\[\"(Critical|Warning)\"\\], \"Message\": \\[\"(.+)\"\\]\\}");
            Matcher matcher = pattern.matcher(line);
            if(matcher.find()){
                String name = matcher.group(1);
                String type = matcher.group(2);
                String error = matcher.group(3);

                if(!projects.containsKey(name)){
                    projects.put(name,new Project(name));
                }

                projects.get(name).addError(type,error);
            }
            line = sc.nextLine();
        }

        List<Project> projectsList = new ArrayList<>(projects.values());

        Comparator<Project> comparator = (o1, o2) -> {
            if(o1.getTotalErrorsCount()>o2.getTotalErrorsCount()){
                return -1;
            }
            else if(o1.getTotalErrorsCount()<o2.getTotalErrorsCount()){
                return 1;
            }
            else if(o1.getTotalErrorsCount()==o2.getTotalErrorsCount()){
                return o1.getName().compareTo(o2.getName());
            }
            return 0;
        };
        Collections.sort(projectsList, comparator);

        print(projectsList.remove(0));
        projectsList.forEach(Problem04_LogParser::print);
    }
    private static void print(Project project){
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length()>o2.length()){
                    return 1;
                }
                else if(o1.length()<o2.length()){
                    return -11;
                }
                else{
                    return o1.compareTo(o2);
                }
            }
        };
        List<String> criticalErrors = project.getCriticalMessages();
        Collections.sort(criticalErrors,comparator);
        List<String> warnings = project.getWarnings();
        Collections.sort(warnings,comparator);

        System.out.println();
        System.out.println(project.getName() + ":");
        System.out.println("Total Errors: " + project.getTotalErrorsCount());
        System.out.println("Critical: " + project.getCriticalErrorsCount());
        System.out.println("Warnings: " + project.getWarningsCount());
        System.out.println("Critical Messages:");
        if(criticalErrors.isEmpty()){
            System.out.println("--->None");
        }
        else{
            for(String criticalMessage : criticalErrors){
                System.out.println("--->" + criticalMessage);
            }
        }
        System.out.println("Warning Messages:");
        if(warnings.isEmpty()){
            System.out.println("--->None");
        }
        else{
            for(String warningMessage : warnings){
                System.out.println("--->" + warningMessage);
            }
        }
    }
}