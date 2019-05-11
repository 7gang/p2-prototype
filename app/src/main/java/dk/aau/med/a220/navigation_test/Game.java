package dk.aau.med.a220.navigation_test;

import java.util.Random;


public class Game {

    public static final int LEVEL_ONE_CAP = 100;
    public static final int LEVEL_TWO_CAP = 500;
    public static final int LEVEL_THREE_CAP = 1000;

    public static int teamNumber;
    public static boolean started = false;

    private static long timeStamp = 0;
    private static long gameStartedTimeStamp = 0;
    private static float[] scores = new float[] {0, 0, 0};
    private static int userTeamStanding = 3;


    /**
     * Starts the game, adding points to team scores
     */
    public static void start() {
        timeStamp = System.currentTimeMillis();
        gameStartedTimeStamp = System.currentTimeMillis();
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
        for (int i = 0; i < scores.length; i++)
            result[i] = getLevel(scores[i]);
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
            if (scores[i] < LEVEL_ONE_CAP)
                result[i] = "LEVEL ONE";
            else if (scores[i] < LEVEL_TWO_CAP)
                result[i] = "LEVEL TWO";
            else if (scores[i] < LEVEL_THREE_CAP)
                result[i] = "LEVEL THREE";
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

    /**
     * Retrieves the amount of people standing in the user's team, ranging from one to three, where four is considered the max amount of people in a team.
     * @return The integer amount of the people standing in the user's team, e.g. 1
     */
    public static int getUserTeamStanding() {
        Random rng = new Random();
        if (rng.nextFloat() > 0.9) userTeamStanding = rng.nextInt(3) + 1; // 1% chance that a new number will be rolled, with a 33% chance it will be the same number
        return userTeamStanding;
    }

    /**
     * Retrieves the points earned pr hour, to be used as "points earned the last hour"
     * @return A list of rates as floating-point numbers, e.g. 33.333...
     */
    public static float[] getRates() {
        updateScores();
        float[] result = new float[3];
        long hoursSinceGameStarted = (System.currentTimeMillis() - gameStartedTimeStamp) / (60 * 60 * 1000);
        for (int i = 0; i < scores.length; i++) result[i] = scores[i] / hoursSinceGameStarted;
        return result;
    }

    /**
     * Retrieves the points earned pr hour of the user's team, to be used as "points earned the last hour"
     * @return A floating-point number, e.g. 33.333...
     */
    public static float getUserTeamRate() {
        return getRates()[teamNumber - 1];
    }

    /**
     * Retrieves a list of the required to level up for each team
     * @return A list of integers, e.g. [23, 6, 195]
     */
    public static int[] getPointsUntilNextLevel() {
        updateScores();
        int[] result = new int[3];
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] < LEVEL_ONE_CAP) result[i] = Math.round(LEVEL_ONE_CAP - scores[i]);
            else if (scores[i] < LEVEL_TWO_CAP) result[i] = Math.round(LEVEL_TWO_CAP - scores[i]);
            else if (scores[i] < LEVEL_THREE_CAP) result[i] = Math.round(LEVEL_THREE_CAP - scores[i]);
            else result[i] = 0;
        }
        return result;
    }

    /**
     * Retrieves the points required for the user's team to level up
     * @return An integer, e.g. 25
     */
    public static int getUserTeamPointsUntilNextLevel() {
        return getPointsUntilNextLevel()[teamNumber - 1];
    }

    /**
     * Retrieves a list containing whether or not each team has earned a new level in the last hour (estimate)
     * @return A list of boolean values, e.g. [true, false, true]
     */
    public static boolean[] hasEarnedNewLevel() {
        boolean[] result = new boolean[3];
        for (int i = 0; i < scores.length; i++) result[i] = getLevel(getScores()[i] - getRates()[i]) != getLevels()[i];
        return result;
    }

    /**
     * Retrieves whether or not the user's team has earned a new level in the last hour (estimate)
     * @return A boolean value, e.g. "false"
     */
    public static boolean userTeamHasEarnedNewLevel() {
        return hasEarnedNewLevel()[teamNumber - 1];
    }

    private static int getLevel(float score) {
        updateScores();
        if (score < LEVEL_ONE_CAP) return 1;
        else if (score < LEVEL_TWO_CAP) return 2;
        else if (score < LEVEL_THREE_CAP) return 3;
        return 0;
    }

    private static void updateScores() {

        long deltaTime = System.currentTimeMillis() - timeStamp;
        if (!started && deltaTime > 5000) return; // wait minimum five seconds between score updates
        timeStamp = System.currentTimeMillis();

        Random rng = new Random();
        for (int i = 0; i < scores.length; i++) {
            float diminisher = 1000; // value is for demonstrational purposes
            if (i == teamNumber - 1) diminisher = diminisher * 5; // the user's group grows ten times slower than the others
            scores[i] += (deltaTime / diminisher) * rng.nextFloat();
        }
        System.out.println("Updated scores: ( " + scores[0] + ", " + scores[1] + ", " + scores[2] + " )!");

    }

}
