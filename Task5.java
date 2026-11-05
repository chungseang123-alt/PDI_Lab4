import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Task5 {
    public static void main(String[] args) throws IOException {
        File file = new File("StudentData.csv");
        ArrayList<String> students = new ArrayList<>();
        try (Scanner reader = new Scanner(file)) {
            reader.nextLine();
            while (reader.hasNextLine()) {
                students.add(reader.nextLine());
            }
        }
        Scanner input = new Scanner(System.in);
        for (int i = 1; i <= 3; i++) {
            System.out.println("New student " + i);
            System.out.print("Name: ");
            String name = input.nextLine();
            System.out.print("ID: ");
            String id = input.nextLine();
            System.out.print("Gender: ");
            String gender = input.nextLine();
            students.add(name + "," + id + "," + gender);
        }
        students.sort((first, second) -> {
            String firstName = first.split(",", 2)[0];
            String secondName = second.split(",", 2)[0];
            return firstName.compareToIgnoreCase(secondName);
        });
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println("Name,ID,Gender");
            for (String student : students) {
                writer.println(student);
            }
        }
        System.out.println("Sorted data saved to StudentData.csv.");
    }
}