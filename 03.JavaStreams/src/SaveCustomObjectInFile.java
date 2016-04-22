import java.io.*;

class Course implements Serializable{
    String name;
    int studentCount;

    public Course(String name,int studentCount){
        this.name = name;
        this.studentCount = studentCount;
    }

    @Override
    public String toString() {
        return this.name+" "+this.studentCount;
    }
}

public class SaveCustomObjectInFile {
    public static void main(String[] args) {

        Course javaFundamentals; // = new Course("JavaFundamentals",1245);

        try(ObjectInputStream inputStream = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream("Resources/InputFiles/course.save")));
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream("Resources/OutputFiles/course.save")))){

            javaFundamentals=(Course)inputStream.readObject();
            outputStream.writeObject(javaFundamentals);

            //System.out.println(javaFundamentals);
        } catch(Exception ex){
            System.out.println(ex);
        }


    }
}