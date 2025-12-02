public class HardMode extends GameMode {
    /**
     * Defines the upper bound for the target number generation.
     *
     * @return The integer 1000, meaning the target number will be between 1 and 1000.
     */
    @Override
    public int getMaxRange() { return 1000; }

    /**
     * Specifies the number of lives/attempts allowed in this mode.
     * <p>
     * Returns 10, which is mathematically calculated based on the binary search formula.
     * Since 2^10 = 1024, 10 guesses are just enough to find any number
     * within 1000 if the player plays optimally.
     * </p>
     *
     * @return The integer 10.
     */
    @Override
    public int getInitialLives() { return 10; }

    /**
     * Provides a user-friendly display name for this difficulty level.
     *
     * @return The String "Hard (1-1000)".
     */
    @Override
    public String getDifficultyName() { return "Hard (1-1000)"; }
}
