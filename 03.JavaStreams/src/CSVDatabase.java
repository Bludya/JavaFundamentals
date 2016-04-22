import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

class Student{
    String id, age, firstName, lastName, homeTown;

    public Student(String fileLine){
        String[] studentData = fileLine.split(",");
        this.id = studentData[0];
        this.firstName = studentData[1];
        this.lastName = studentData[2];
        this.age = studentData[3];
        this.homeTown="";
        for (int i = 4; i < studentData.length; i++) {
            this.homeTown += studentData[i]+" ";
        }
        this.homeTown=this.homeTown.substring(0,this.homeTown.length()-1);

    }

    @Override
    public String toString() {
        return String.format("%s %s (age: %s, town: %s)",firstName,lastName,age,homeTown);
    }

    public String toCSV(){
        return String.format("%s,%s,%s,%s,%s",id,firstName,lastName,age,homeTown);
    }
}

class Grades{
    String id="";
    String courseOne="";
    String courseTwo="";
    String courseOneGrades="";
    String courseTwoGrades="";

    public Grades(String fileLine){
        try {
            String[] gradesData = fileLine.split(",");
            this.id = gradesData[0];
            String[] courseOneData = gradesData[1].split(" ");
            this.courseOne = courseOneData[0];
            this.courseOneGrades+=courseOneData[1];
            for (int i = 2; i < courseOneData.length; i++) {
                courseOneGrades += (", "+courseOneData[i]);
            }

            if(gradesData.length>2) {
                String[] courseTwoData = gradesData[2].split(" ");
                if(courseTwoData.length>1) {
                    this.courseTwo = courseTwoData[0];
                    courseTwoGrades += courseTwoData[1];
                    for (int i = 2; i < courseTwoData.length; i++) {
                        courseTwoGrades += (" "+courseTwoData[i]);
                    }
                }
            }

        }catch(NullPointerException ex){
        }
    }
    public Grades(String id, String course, String courseOneGrades){
        this.id = id;
        this.courseOne=course;
        this.courseOneGrades=courseOneGrades;
    }

    public String addGrade(String course,String grade){
        if(course.equals(courseOne) || courseOne.equals("")){
            courseOne=course;
            courseOneGrades+=(" "+grade);
            return "Grade added.";
        }
        else if(course.equals(courseTwo) || courseTwo.equals("")){
            courseTwo=course;
            courseTwoGrades+=(" "+grade);
            return "Grade added.";
        }
        else{
            return "No such course";
        }
    }

    @Override
    public String toString(){
        String output = "";
        if(courseOne!=null && courseOneGrades!=null){
            output+=String.format("# %s: %s%n",courseOne,courseOneGrades);
        }
        if(!courseTwo.equals("") && !courseTwoGrades.equals("")){
            output+=String.format("# %s:%s",courseTwo,courseTwoGrades);
        }
        return output;
    }
    public String toCSV() {
        String output = id+",";
        if(courseOne!=null && courseOneGrades!=null){
            output+=String.format("%s %s",courseOne,courseOneGrades);
        }
        if(courseTwo!=null && courseTwoGrades!=null){
            output+=String.format(",%s%s",courseTwo,courseTwoGrades);
        }
        return output;
    }
}


public class CSVDatabase {

    static HashMap<String,Student> students = new HashMap<>();
    static HashMap<String,String> studentsByName = new HashMap<>();
    static HashMap<String,Grades> grades = new HashMap<>();
    static int nextId=1;
    static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {


        try(BufferedReader studentsReader = new BufferedReader(
                new FileReader("Resources/CSVDatabase/students.csv"));
            BufferedReader gradesReader = new BufferedReader(
                    new FileReader("Resources/CSVDatabase/grades.csv"))
        ){
            try {
                nextId = Integer.parseInt(studentsReader.readLine()); //on the first line of the file there will be the last ID +1;
            } catch(NumberFormatException ex){
                nextId=1;
            }
            String fileLine = studentsReader.readLine();
            while(fileLine!=null){                              //getting all the students from the CSV file into the HashMap
                Student currentStudent = new Student(fileLine);
                students.put(currentStudent.id,currentStudent);
                studentsByName.put(currentStudent.firstName+" "+currentStudent.lastName,currentStudent.id);
                fileLine= studentsReader.readLine();
            }

            fileLine = gradesReader.readLine();
            while(fileLine!=null){                              //getting all the grades from the file into the HashMap
                Grades currentGrades = new Grades(fileLine);
                grades.put(currentGrades.id,currentGrades);
                fileLine= gradesReader.readLine();
            }
            studentsReader.close();
            gradesReader.close();
        }catch(Exception ex){
            System.out.println(ex.fillInStackTrace());
        }

        System.out.printf("Welcome to the CSV Database!%n%nType Help for list of all commands.%n");

        String userInput = console.nextLine();
        while(!userInput.toLowerCase().equals("exit")){
            try {
                switch (userInput.toLowerCase().split(" ")[0]) {
                    case "help":
                        help();
                        break;
                    case "save":
                        save();
                        break;
                    case "quit":
                        return;
                    case "search-by-full-name":
                        System.out.printf(searchByFullName(userInput.split(" ")[1], userInput.split(" ")[2]));
                        break;
                    case "search-by-id":
                        System.out.printf(searchById(userInput.split(" ")[1]));
                        break;
                    case "delete-by-id":
                        System.out.printf(deleteById(userInput.split(" ")[1]));
                        break;
                    case "update-by-id":
                        System.out.printf(updateById(userInput));
                        break;
                    case "insert-student":
                        System.out.printf(insertStudent(userInput.substring(15, userInput.length())));
                        break;
                    case "insert-grade-by-id":
                        System.out.printf(insertGradeById(userInput.substring(19, userInput.length())));
                        break;
                    default:
                        System.out.println("Wrong command, try again!");
                        break;
                }
            } catch(ArrayIndexOutOfBoundsException ex){
                System.out.println("Invalid command.");
            }
            userInput = console.nextLine();
        }
        save();
    }


    public static void help(){
        System.out.println("Help  ---> Brings out all the commands.");
        System.out.println("Quit  ---> Exit and save the data.");
        System.out.println("Save  ---> Save the data.");
        System.out.println("Search-by-full-name  <firstName secondName> ---> Bring out data about the student if he exists.");
        System.out.println("Search-by-id  <id> ---> Bring out data about the student with that ID if he exists.");
        System.out.println("Delete-by-id  <id>---> Delete student with that ID from the database.");
        System.out.println("Update-by-id  <id> <grades/info> <course grades/firstName secondName age homeTown> ---> Update data on student or his grades with that ID.");
        System.out.println("Insert-student <firstName secondName age homeTown> ---> Inserts new student with the new ID.");
        System.out.println("Insert-grade-by-id <id course grade> ---> Adds a new grade to a students(ID) course.");
    }

    public static void save(){
        try(BufferedWriter studentWriter = new BufferedWriter(
                new FileWriter("Resources/CSVDatabase/students.csv"));
            BufferedWriter gradesWriter = new BufferedWriter(
                    new FileWriter("Resources/CSVDatabase/grades.csv"))
        ){
            studentWriter.write(String.format("%d",nextId));
            studentWriter.newLine();
            for(Student student:students.values()){
                studentWriter.write(student.toCSV());
                studentWriter.newLine();
            }

            for(Grades grade:grades.values()){
                gradesWriter.write(grade.toCSV());
                gradesWriter.newLine();
            }
            studentWriter.close();
            gradesWriter.close();
        } catch(Exception ex){
            System.out.println(ex);
        }
    }

    public static String searchByFullName(String firstName, String lastName){
        if(!studentsByName.containsKey(firstName+" "+lastName)){
            return "Student does not exist.";
        }

        String id = studentsByName.get(firstName+" "+lastName);
        String currentGrades;
        if(!grades.containsKey(id)){
            currentGrades = "No current grades.";
        }
        else{
            currentGrades = grades.get(id).toString();
        }

        return String.format("%s%n%s",students.get(id).toString(),currentGrades);

    }

    public static String searchById (String id){
        if(!students.containsKey(id)){
            return "Student does not exist.";
        }

        String currentGrades;
        if(!grades.containsKey(id)){
            currentGrades = "No current grades.";
        }
        else{
            currentGrades = grades.get(id).toString();
        }

        return String.format("%s%n%s",students.get(id).toString(),currentGrades);
    }

    public static String deleteById(String id){
        if(!students.containsKey(id)){
            return "Student does not exist";
        }
        studentsByName.remove(students.get(id).firstName+" "+students.get(id).lastName);
        students.remove(id);
        grades.remove(id);

        save();
        return String.format("Student with ID: %s is removed from the database.",id);
    }

    public static String updateById(String command) {
        command=command.substring(13, command.length());
        String id = command.split(" ")[0];
        String choice = command.split(" ")[1];              //student or grade database
        String textToUpdate = command.replaceAll((choice + " "), "");

        switch (choice) {
            case "info":
                students.remove(id);
                students.put(id, new Student(textToUpdate.replaceAll(" ",",")));
                break;
            case "grades":
                grades.remove(id);
                grades.put(id, new Grades(textToUpdate.replaceAll(" ",",")));
                break;
            default:
                return "Invalid choice of data to update.";
        }
        save();
        return String.format("Student with ID:%s updated.",id);
    }

    public static String insertStudent(String line){
        line = String.format("%d,%s",nextId,line.replaceAll(" ",","));
        students.put(String.format("%d",nextId),new Student(line));

        String id = String.format("%d",nextId);
        String name = students.get(id).firstName+" "+students.get(id).lastName;
        studentsByName.put(name,id);
        nextId++;
        save();
        return String.format("Student added with ID:%d",nextId-1);
    }

    public static String insertGradeById(String line){
        String[] commands = line.split(" ");
        if(!grades.containsKey(commands[0])){
            grades.put(commands[0],new Grades(commands[0],commands[1],commands[2]));
        }
        else{
            grades.get(commands[0]).addGrade(commands[1],commands[2]);
        }
        save();
        return String.format("Grade on %s added on student with ID:%s.",commands[1],commands[0]);
    }


}