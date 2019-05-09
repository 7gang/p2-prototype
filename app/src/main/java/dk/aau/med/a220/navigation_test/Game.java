package dk.aau.med.a220.navigation_test;

import java.util.Random;


public class Game {

    public static final int LEVEL_ONE_CAP = 100;
    public static final int LEVEL_TWO_CAP = 500;
    public static final int LEVEL_THREE_CAP = 1000;

    public static int teamNumber;
    public static boolean started = false;

    private static long timeStamp = 0;
    private static float[] scores = new float[] {0, 0, 0};


    /**
     * Starts the game, adding points to team scores
     */
    public static void start() {
        timeStamp = System.currentTimeMillis();
        started = true;
    }

    /**
     * Stops the game, preventing any more points from being earned
     */
    public static void stop() {
        updateScores();
        started = false;
    }

    /**
     * Retrieves the group scores, updating them first if game is started
     * @return A list of group scores as floating-point numbers, e.g. 123.5212...
     */
    public static float[] getScores() {
        updateScores();
        return scores;
    }

    /**
     * Retrieves the user's group score, updating it if game is started
     * @return The score of group to which the user belongs as a floating-point number, e.g. 123.5212...
     */
    public static float getUserTeamScores() {
        return getScores()[teamNumber - 1];
    }

    /**
     * Retrieves the levels of each group building, updating them if game is started
     * @return A list of levels denoted as integers, e.g. 1
     */
    public static int[] getLevels() {
        updateScores();
        int[] result = new int[3];
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] < LEVEL_ONE_CAP) result[i] = 1;
            else if (scores[i] < LEVEL_TWO_CAP) result[i] = 2;
            else if (scores[i] < LEVEL_THREE_CAP) result[i] = 3;
        }
        return result;
    }
    /**
     *
     * Retrieves the user's group level, updating it if game is started
     * @return The level of the group to which the user belongs as an integer, e.g. 1
     */
    public static int getUserTeamLevel() {
        return getLevels()[teamNumber - 1];
    }

    /**
     * Retrieves the levels of each group building, updating them if game is started
     * @return A list of levels denoted as String Objects, e.g. "LEVEL ONE"
     */
    public static String[] getLevelsString() {
        updateScores();
        String[] result = new String[3];
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] < LEVEL_ONE_CAP) result[i] = "LEVEL ONE";
            else if (scores[i] < LEVEL_TWO_CAP) result[i] = "LEVEL TWO";
            else if (scores[i] < LEVEL_THREE_CAP) result[i] = "LEVEL THREE";
        }
        return result;
    }

    /**
     * Retrieves the user's group level, updating it if game is started
     * @return The level of the group to which the user belongs as a String Object, e.g. "LEVEL ONE"
     */
    public static String getUserTeamLevelString() {
        return getLevelsString()[teamNumber - 1];
    }

    private static void updateScores() {

        long deltaTime = System.currentTimeMillis() - timeStamp;
        if (!started && deltaTime > 5000) return; // wait minimum five seconds between score updates
        timeStamp = System.currentTimeMillis();

        Random rng = new Random();
        for (int i = 0; i < scores.length; i++) {
            float diminisher = 1000; // value is for demonstrational purposes
            if (i == teamNumber - 1) diminisher = diminisher * 5; // have the user's group grows ten times slower than the others
            scores[i] += (deltaTime / diminisher) * rng.nextFloat();
        }
        System.out.println("Updated scores: ( " + scores[0] + ", " + scores[1] + ", " + scores[2] + " )!");

    }

}
