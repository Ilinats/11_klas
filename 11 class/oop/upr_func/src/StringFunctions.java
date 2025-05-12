import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class StringFunctions {
    public static boolean isIsogram(String str) {
        if (str == null) return true;

        str = str.toLowerCase();

        Set<Character> letterSet = new HashSet<>();

        for (char c : str.toCharArray()) {
            if (!Character.isLetter(c))
                continue;

            if (letterSet.contains(c))
                return false;

            letterSet.add(c);
        }

        return true;
    }

    public static void pascalsTriangle(int i) {
        if (i <= 0) return;

        for (int row = 0; row < i; row++) {
            for (int space = 0; space < i - row - 1; space++) {
                System.out.print(" ");
            }

            int number = 1;
            for (int col = 0; col <= row; col++) {
                System.out.print(number + " ");
                number = number * (row - col) / (col + 1);
            }

            System.out.println();
        }
    }

    public static void pythagoreanTriplets(int n) {
        for (int a = 1; a <= n/3; a++) {
            for (int b = a + 1; b <= (n-a)/2; b++) {
                int c = n - a - b;

                if (a*a + b*b == c*c) {
                    System.out.println(a + " " + b + " " + c);
                }
            }
        }
    }

    public static void wc(String filename) {
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lineCount++;

                String[] words = line.trim().split("\\s+");
                if (!line.trim().isEmpty()) {
                    wordCount += words.length;
                }

                charCount += line.length() + 1;
            }

            System.out.println(lineCount + " " + wordCount + " " + charCount + " " + filename);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("Isogram test:");
        System.out.println(isIsogram("background"));
        System.out.println(isIsogram("python"));

        System.out.println("\nPascal's Triangle (4 rows):");
        pascalsTriangle(4);

        System.out.println("\nPythagorean Triplets for N=12:");
        pythagoreanTriplets(12);

        // wc("example.txt");
    }
}