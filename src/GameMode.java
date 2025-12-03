public abstract class GameMode implements GameMechanics {
    /**
     * The hidden target number that the player is trying to guess.
     * Marked as {@code protected} to allow direct access by subclasses if necessary.
     */
    protected int targetNumber;

    /**
     * Abstract method to get the maximum possible number for this difficulty.
     * @return The upper bound integer (e.g., 100 for a range of 1-100).
     */
    public abstract int getMaxRange();

    /**
     * Abstract method to determine how many lives the player starts with.
     * @return The integer number of lives.
     */
    public abstract int getInitialLives();

    /**
     * Abstract method to get the display name of the difficulty.
     * @return A String representing the mode name (e.g., "Easy Mode").
     */
    public abstract String getDifficultyName();

    /**
     * Generates a random secret number based on the specific range of the subclass.
     * <p>
     * Logic: Generates a number between <b>1</b> and <b>{@code getMaxRange()}</b> (inclusive).
     * </p>
     */
    @Override
    public void generateNumber() {
        this.targetNumber = (int) (Math.random() * getMaxRange()) + 1;
    }

    /**
     * Retrieves the current hidden target number.
     * @return The target integer.
     */
    public int getTargetNumber() {
        return targetNumber;
    }

    /**
     * Compares the user's guess against the target number.
     *
     * @param guess The integer value guessed by the player.
     * @return A {@link GuessResult} enum indicating if the guess was
     * {@code CORRECT}, {@code TOO_LOW}, or {@code TOO_HIGH}.
     */
    @Override
    public GuessResult checkGuess(int guess) {
        if (guess == targetNumber) return GuessResult.CORRECT;
        if (guess < targetNumber) return GuessResult.TOO_LOW;
        return GuessResult.TOO_HIGH;
    }

    /**
     * Checks if the target number is greater than or equal to the input.
     * Useful for binary search logic or hints.
     *
     * @param n The number to compare against.
     * @return {@code true} if targetNumber >= n, otherwise {@code false}.
     */
    public boolean isGreaterThanOrEqual(int n) {
        return targetNumber >= n;
    }

    /**
     * Checks if the target number is less than or equal to the input.
     * Useful for binary search logic or hints.
     *
     * @param n The number to compare against.
     * @return {@code true} if targetNumber <= n, otherwise {@code false}.
     */
    public boolean isLessThanOrEqual(int n) {
        return targetNumber <= n;
    }
}
