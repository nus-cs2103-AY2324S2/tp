package seedu.address.logic.parser.fuzzy;

/**
 * Distance function implemented using Levenshtein distance algorithm
 * Calculates minimum number of single-character edits required to change one string into another
 * @param <T> type of items distance is calculated
 */
public class LevenshteinDistance<T> implements DistanceFunction<T> {
    @Override
    public int calculateDistance(T item1, T item2) {
        assert item1 != null && item2 != null : "Items must not be null";
        assert item1 instanceof String && item2 instanceof String : "Items must be of type String";

        String s1 = (String) item1;
        String s2 = (String) item2;

        // Dynamic Programming
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= s2.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(dp[i - 1][j] + 1, Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + cost));
            }
        }

        return dp[s1.length()][s2.length()];
    }
}
