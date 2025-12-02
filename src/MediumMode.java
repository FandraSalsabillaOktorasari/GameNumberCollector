public class MediumMode extends GameMode {
    /**
     * Defines the upper bound for the target number generation.
     *
     * @return The integer 100, meaning the target number will be between 1 and 100.
     */
    @Override
    public int getMaxRange() { return 100; }

    /**
     * Specifies the number of lives/attempts allowed in this mode.
     * <p>
     * Returns 7. This is mathematically derived from the binary search formula:
     * log2(100) is approximately 6.64. Therefore, 7 guesses are sufficient
     * to find any number in this range if the player uses the optimal strategy.
     * </p>
     *
     * @return The integer 7.
     */
    @Override
    public int getInitialLives() { return 7; }

    /**
     * Provides a user-friendly display name for this difficulty level.
     *
     * @return The String "Medium (1-100)".
     */
    @Override
    public String getDifficultyName() { return "Medium (1-100)"; }
}
