import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        try (PrintWriter writer = new PrintWriter("Ex3StudentData.csv")) {
            writer.println("Name,ID,Gender");
            for (int i = 1; i <= 3; i++) {
                System.out.println("Student " + i);
                System.out.print("Name: ");
                String name = input.nextLine();
                System.out.print("ID: ");
                String id = input.nextLine();
                System.out.print("Gender: ");
                String gender = input.nextLine();
                writer.println(name + "," + id + "," + gender);
            }
        }
        input.close();
        System.out.println("Data saved to Ex3StudentData.csv.");
    }
}