import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> students = new ArrayList<>();

        try (Scanner reader = new Scanner(new File("Ex3StudentData.csv"))) {
            reader.nextLine(); 

            while (reader.hasNextLine()) {
                students.add(reader.nextLine());
            }
        }

        System.out.println("Name,ID,Gender");
        for (String student : students) {
            System.out.println(student);
        }
    }
}