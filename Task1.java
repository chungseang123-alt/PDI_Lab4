import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) throws IOException {
        File file = new File("Task1.csv");
        Random random = new Random();

        try (FileWriter writer = new FileWriter(file)) {
            for (int i = 0; i < 500; i++) {
                int number = random.nextInt(1000) + 1;
                writer.write(number + "\n");
            }
        }

        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                System.out.println(reader.nextLine());
            }
        }
    }
}