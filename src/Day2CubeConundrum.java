import java.nio.file.*;
import java.io.IOException;
import java.util.*;
/**
* Solution for part one in Day2 (https://adventofcode.com/2023)
* */

public class Day2CubeConundrum {
    static final int redCubes = 12;
    static final int greenCubes = 13;
    static final int blueCubes = 14;

    public static void main(String[] args) {
        //Input files: Test1.txt and input.txt
        String inputFile = "Input/Day2CubeConundrum/input.txt";
        try {
            List<String> fileLines = Files.readAllLines(Paths.get(inputFile));
            int sum = totalIDsOfPossibleGames(fileLines);
            System.out.println("Sum of possible game IDs: " + sum); //correct answer is 1734
        } catch (IOException e) {
            System.err.println("Error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Get the sum of the IDs of possible games.
     *
     * @param lines lines in the input file
     * @return sum of IDs
     */
    private static int totalIDsOfPossibleGames(List<String> lines) {
        int sumOfGameIDs = 0;
        for (String line : lines) {
            line = line.trim();
            Integer gameID = checkGamePossibility(line);
            if (gameID != null) {
                sumOfGameIDs += gameID;
            }
        }
        return sumOfGameIDs;
    }

    /**
     * Check if the game is possible. The game is possible if each set has a maximum of 12 red cubes, 13 green cubes, and 14 blue cubes.
     *
     * @param line a line in the file ex: Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
     * @return possible game ID otherwise return null.
     */
    private static Integer checkGamePossibility(String line) {
        String[] lineParts = line.split(": ");
        int gameID = Integer.parseInt(lineParts[0].split(" ")[1]);

        // Extract cube sets, separated by ";"
        String[] cubeSets = lineParts[1].split(";");
        boolean isPossible = true;

        for (String set : cubeSets) {
            Map<String, Integer> setColors = getSetColors(set);
            // Check if the set is possible
            if (setColors.get("red") > redCubes || setColors.get("green") > greenCubes || setColors.get("blue") > blueCubes) {
                isPossible = false;
                break; // Stop checking further sets
            }
        }

        if (isPossible) {
            return gameID;
        } else {
            return null;
        }
    }

    /**
     * Get colors in a set with their numbers of occurrence. ex: 1 blue, 2 green.
     *
     * @param set a set in a game.
     * @return a hash map with all colors with their number of occurrence.
     */
    private static Map<String, Integer> getSetColors(String set) {
        Map<String, Integer> setColors = new HashMap<>();
        setColors.put("red", 0);
        setColors.put("green", 0);
        setColors.put("blue", 0);

        String[] cubes = set.split(",");
        for (String cube : cubes) {
            String[] cubeColors = cube.trim().split(" ");
            int colorCount = Integer.parseInt(cubeColors[0]);
            String color = cubeColors[1].toLowerCase();
            //Sum number of occurrence for each color
            setColors.put(color, setColors.getOrDefault(color, 0) + colorCount);
        }
        return setColors;
    }

}

