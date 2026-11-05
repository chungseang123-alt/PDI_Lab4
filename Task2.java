import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of files: ");
        int n = input.nextInt();
        if (n < 1) {
            System.out.println("Enter at least 1.");
            input.close();
            return;
        }
        File folder = new File("Ex2LuckyNumber");
        folder.mkdir();
        Random random = new Random();
        for (int i = 1; i <= n; i++) {
            File file = new File(folder, "file" + i + ".csv");
            try (PrintWriter writer = new PrintWriter(file)) {
                for (int j = 0; j < 5; j++) {
                    int number = random.nextInt(100) + 1;
                    writer.println(number);
                }
            }
        }
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            File file = new File(folder, "file" + i + ".csv");
            try (Scanner reader = new Scanner(file)) {
                while (reader.hasNextInt()) {
                    numbers.add(reader.nextInt());
                }
            }
        }
        input.close();
        System.out.println("Minimum: " + Collections.min(numbers));
        System.out.println("Maximum: " + Collections.max(numbers));
    }
}