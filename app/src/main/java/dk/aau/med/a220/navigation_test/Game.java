package dk.aau.med.a220.navigation_test;

import java.util.Random;


class Game {

    static final int LEVEL_ONE_CAP = 25;
    static final int LEVEL_TWO_CAP = 100;
    static final int LEVEL_THREE_CAP = 250;
    static final int LEVEL_FOUR_CAP = 1000;

    static int teamNumber;
    static boolean started = false;

    private static long scoreUpdateTimeStamp = 0;
    private static long gameStartedTimeStamp = 0;
    private static long standingTimeStamp = 0;

    private static float[] scores = new float[] {0, 0, 0};
    private static int userTeamStanding = 3;


    /**
     * Starts the game, adding points to team scores
     */
    static void start() {
        scoreUpdateTimeStamp = System.currentTimeMillis();
        gameStartedTimeStamp = System.currentTimeMillis();
        started = true;
    }

    /**
     * Stops the game, preventing any more points from being earned
     */
    static void stop() {
        updateScores();
        started = false;
    }

    /**
     * Retrieves the group scores, updating them first if game is started
     * @return A list of group scores as floating-point numbers, e.g. 123.5212...
     */
    static float[] getScores() {
        updateScores();
        return scores;
    }

    /**
     * Retrieves the user's group score, updating it if game is started
     * @return The score of group to which the user belongs as a floating-point number, e.g. 123.5212...
     */
    static float getUserTeamScores() {
        return getScores()[teamNumber - 1];
    }

    /**
     * Retrieves the levels of each group building, updating them if game is started
     * @return A list of levels denoted as integers, e.g. 1
     */
    static int[] getLevels() {
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
    static int getUserTeamLevel() {
        return getLevels()[teamNumber - 1];
    }

    /**
     * Retrieves the levels of each group building, updating them if game is started
     * @return A list of levels denoted as String Objects, e.g. "LEVEL ONE"
     */
    static String[] getLevelsString() {
        updateScores();
        String[] result = new String[3];
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] < LEVEL_ONE_CAP) result[i] = "LEVEL ONE";
            else if (scores[i] < LEVEL_TWO_CAP) result[i] = "LEVEL TWO";
            else if (scores[i] < LEVEL_THREE_CAP) result[i] = "LEVEL THREE";
            else if (scores[i] < LEVEL_FOUR_CAP) result[i] = "LEVEL FOUR";
            else result[i] = "LEVEL FIVE";
        }
        return result;
    }

    /**
     * Retrieves the user's group level, updating it if game is started
     * @return The level of the group to which the user belongs as a String Object, e.g. "LEVEL ONE"
     */
    static String getUserTeamLevelString() {
        return getLevelsString()[teamNumber - 1];
    }

    /**
     * Retrieves the amount of people standing in the user's team, ranging from one to three, where four is considered the max amount of people in a team.
     * @return The integer amount of the people standing in the user's team, e.g. 1
     */
    static int getUserTeamStanding() {
        if (standingTimeStamp != 0 && System.currentTimeMillis() - standingTimeStamp < 5 * 60 * 1000) // reevaluate value after five minutes
            return userTeamStanding;
        standingTimeStamp = System.currentTimeMillis();
        Random rng = new Random();
        if (rng.nextFloat() > 0.8) userTeamStanding = rng.nextInt(3) + 1; // 20% chance that a new number will be rolled, with a 33% chance it will be the same number
        return userTeamStanding;
    }

    /**
     * Retrieves the points earned pr hour, to be used as "points earned the last hour"
     * @return A list of rates as floating-point numbers, e.g. 33.333...
     */
    static float[] getRates() {
        updateScores();
        float[] result = new float[3];
        double hoursSinceGameStarted = (System.currentTimeMillis() - gameStartedTimeStamp) / (60.0 * 60.0 * 1000.0);
        for (int i = 0; i < scores.length; i++)
            result[i] = (float) (scores[i] / hoursSinceGameStarted);
        System.out.println("getRates: [" + result[0] + ", " + result[1] + ", " + result[2] + "]");
        return result;
    }

    /**
     * Retrieves the points earned pr hour of the user's team, to be used as "points earned the last hour"
     * @return A floating-point number, e.g. 33.333...
     */
    static float getUserTeamRate() {
        return getRates()[teamNumber - 1];
    }

    /**
     * Retrieves a list of the required to level up for each team
     * @return A list of integers, e.g. [23, 6, 195]
     */
    static int[] getPointsUntilNextLevel() {
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
    static int getUserTeamPointsUntilNextLevel() {
        return getPointsUntilNextLevel()[teamNumber - 1];
    }

    /**
     * Retrieves a list containing whether or not each team has earned a new level in the last hour (estimate)
     * @return A list of boolean values, e.g. [true, false, true]
     */
    static boolean[] hasEarnedNewLevel() {
        boolean[] result = new boolean[3];
        for (int i = 0; i < scores.length; i++)
            result[i] = getLevel(getScores()[i] - getRates()[i]) != getLevels()[i];
        return result;
    }

    /**
     * Retrieves whether or not the user's team has earned a new level in the last hour (estimate)
     * @return A boolean value, e.g. "false"
     */
    static boolean userTeamHasEarnedNewLevel() {
        return hasEarnedNewLevel()[teamNumber - 1];
    }

    private static int getLevel(float score) {
        if (score < LEVEL_ONE_CAP) return 1;
        else if (score < LEVEL_TWO_CAP) return 2;
        else if (score < LEVEL_THREE_CAP) return 3;
        else if (score < LEVEL_FOUR_CAP) return 4;
        else return 5;
    }

    private static void updateScores() {

        long deltaTime = System.currentTimeMillis() - scoreUpdateTimeStamp;
        if (!started || deltaTime < 5000) // wait minimum five seconds between score updates
            return;
        scoreUpdateTimeStamp = System.currentTimeMillis();

        Random rng = new Random();
        for (int i = 0; i < scores.length; i++) {
            float diminisher = 1000; // value is for demonstrational purposes
            if (i == teamNumber - 1) diminisher = diminisher * 5; // the user's group grows ten times slower than the others
            scores[i] += (deltaTime / diminisher) * rng.nextFloat();
        }
        System.out.println("Updated scores: ( " + scores[0] + ", " + scores[1] + ", " + scores[2] + " )!");

    }

}
