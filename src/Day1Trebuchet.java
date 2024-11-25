import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
/**
 * Solution for part one and two in Day1 (https://adventofcode.com/2023)
 * */

public class Day1Trebuchet {
    public static final Map<String, String> wordToDigit = Map.of(
            "one", "1",
            "two", "2",
            "three", "3",
            "four", "4",
            "five", "5",
            "six", "6",
            "seven", "7",
            "eight", "8",
            "nine", "9"
    );
    static String firstDigit;
    static String lastDigit;

    public static void main(String[] args) {
        //Input files: Test1.txt, Test2.txt, and input.txt
        String inputFile = "Input/Day1Trebuchet/input.txt";
        List<String> fileLines = null;
        try {
            fileLines = Files.lines(Paths.get(inputFile)).toList();
        } catch (IOException e) {
            System.err.println("Error occurred.");
            e.printStackTrace();
        }
        int total = 0;
        firstDigit = null;
        lastDigit = null;
        for (String line : fileLines) {
//           getFirstAndLastDigitPartOne(line); //Solution of part one - correct answer is 54561
           getFirstAndLastDigitPartTwo(line); //Solution of part two - correct answer is 54076

            total += Integer.parseInt(firstDigit + lastDigit);
            firstDigit = null;
            lastDigit = null;
        }
        System.out.println("Total = " + total);

    }

    /**
     * Get the solution for part one. Dealing only with digits
     *
     * @param inputLine a line in the input file
     */
    public static void getFirstAndLastDigitPartOne(String inputLine) {
        String digits = inputLine.replaceAll("[^0-9]", ""); // Remove all non-digit characters
        if (!digits.isEmpty()) {
            firstDigit = digits.split("")[0];
            lastDigit = digits.split("")[digits.length() - 1];
            //System.out.println(firstDigit + lastDigit);
        } else System.out.println("This line has no digits");
    }

    /**
     * Get the solution for part two. Dealing with digits that spelled out with letters
     *
     * @param inputLine a line in the input file
     */
    public static void getFirstAndLastDigitPartTwo(String inputLine) {
        for (int i = 0; i < inputLine.length(); i++) {
            char c = inputLine.charAt(i);
            if (Character.isDigit(c)) {
                if (firstDigit == null)
                    firstDigit = String.valueOf(c);
                lastDigit = String.valueOf(c);
            }

            for (String word : wordToDigit.keySet()) {
                if (inputLine.toLowerCase().startsWith(word, i)) {
                    String digit = wordToDigit.get(word);
                    if (firstDigit == null)
                        firstDigit = digit;
                    lastDigit = digit;
                    i += word.length() - 1; // Skip the length of the matched word
                    break; // Once a match found, break
                }
            }
        }
        //System.out.println(firstDigit + lastDigit);
    }
}