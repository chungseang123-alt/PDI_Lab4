import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Task7 {
    public static void main(String[] args) throws IOException {
        ArrayList<String> students = new ArrayList<>();
        String header;
        try (Scanner reader = new Scanner(new File("data.csv"))) {
            header = reader.nextLine();
            System.out.println(header);
            while (reader.hasNextLine()) {
                String student = reader.nextLine();
                if (!student.isBlank()) {
                    students.add(student);
                    System.out.println(student);
                }
            }
        }
        System.out.println();
        for (char group = 'A'; group <= 'D'; group++) {
            writeGroupFile(students, header, group);
        }
    }
    static void writeGroupFile(ArrayList<String> students, String header, char group)
            throws IOException {
        String fileName = "group" + group + ".csv";
        int total = 0;
        int male = 0;
        int female = 0;
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println(header);
            for (String student : students) {
                String[] fields = student.split(",");
                String studentGroup = fields[2].trim();
                if (studentGroup.equalsIgnoreCase("group " + group)) {
                    writer.println(student);
                    total++;
                    String gender = fields[1].trim();
                    if (gender.equalsIgnoreCase("male")) {
                        male++;
                    } else if (gender.equalsIgnoreCase("female")) {
                        female++;
                    }
                }
            }
            double malePercent = percent(male, total);
            double femalePercent = percent(female, total);
            writer.printf(Locale.US, "Total students,%d,Male,%.2f%%,Female,%.2f%%,,,%n",
                    total, malePercent, femalePercent);
            if (writer.checkError()) {
                throw new IOException("Could not save " + fileName);
            }
            System.out.printf(Locale.US, "Group %c: %d students, %.2f%% male, %.2f%% female%n",
                    group, total, malePercent, femalePercent);
        }
    }
    static double percent(int count, int total) {
        if (total == 0) {
            return 0;
        }
        return count * 100.0 / total;
    }
}
