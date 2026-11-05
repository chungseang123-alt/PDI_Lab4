import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Task6{
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String[]> candidates = readCandidates("data.csv");
        System.out.println("1. All candidates");
        showGenderStats(candidates);
        System.out.println("\n2. Average score of at least 90");
        showHighAverages(candidates);
        System.out.println("\n3. Scores of at least 85");
        showSubjectScores(candidates);
        System.out.println("\n4. Candidates in each group");
        showGroups(candidates);
    }
    static ArrayList<String[]> readCandidates(String fileName)
            throws FileNotFoundException {
        ArrayList<String[]> candidates = new ArrayList<>();
        try (Scanner reader = new Scanner(new File(fileName))) {
            boolean foundHeader = false;
            while (reader.hasNextLine()) {
                String line = reader.nextLine().trim();
                if (line.startsWith("ID,")) {
                    foundHeader = true;
                } else if (foundHeader && !line.isEmpty()) {
                    candidates.add(line.split(","));
                }
            }
        }
        return candidates;
    }
    static void showGenderStats(ArrayList<String[]> candidates) {
        int male = 0;
        int female = 0;
        for (String[] candidate : candidates) {
            String gender = candidate[1].trim();
            if (gender.equalsIgnoreCase("male")) {
                male++;
            } else if (gender.equalsIgnoreCase("female")) {
                female++;
            }
        }
        int total = candidates.size();
        System.out.println("Total: " + total);
        System.out.printf("Male: %.2f%% | Female: %.2f%%%n",
                percent(male, total), percent(female, total));
    }
    static double percent(int count, int total) {
        if (total == 0) {
            return 0;
        }
        return count * 100.0 / total;
    }
    static void showHighAverages(ArrayList<String[]> candidates) {
        ArrayList<String[]> highAverages = new ArrayList<>();
        for (String[] candidate : candidates) {
            double average = Double.parseDouble(candidate[9].trim());
            if (average >= 90) {
                highAverages.add(candidate);
            }
        }
        showGenderStats(highAverages);
    }
    static void showSubjectScores(ArrayList<String[]> candidates) {
        int mathCount = 0;
        int englishCount = 0;
        int computerCount = 0;
        int allThreeCount = 0;
        for (String[] candidate : candidates) {
            int math = Integer.parseInt(candidate[5].trim());
            int english = Integer.parseInt(candidate[6].trim());
            int computer = Integer.parseInt(candidate[7].trim());

            if (math >= 85) {
                mathCount++;
            }
            if (english >= 85) {
                englishCount++;
            }
            if (computer >= 85) {
                computerCount++;
            }
            if (math >= 85 && english >= 85 && computer >= 85) {
                allThreeCount++;
            }
        }
        System.out.println("Math: " + mathCount);
        System.out.println("English: " + englishCount);
        System.out.println("Computer: " + computerCount);
        System.out.println("All three subjects: " + allThreeCount);
    }
    static void showGroups(ArrayList<String[]> candidates) {
        TreeSet<String> groups = new TreeSet<>();
        for (String[] candidate : candidates) {
            groups.add(candidate[2].trim());
        }
        for (String group : groups) {
            ArrayList<String[]> members = new ArrayList<>();
            for (String[] candidate : candidates) {
                if (candidate[2].trim().equals(group)) {
                    members.add(candidate);
                }
            }
            System.out.println(group);
            showGenderStats(members);
        }
    }
}