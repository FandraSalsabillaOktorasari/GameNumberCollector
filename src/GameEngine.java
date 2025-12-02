public abstract class GameEngine {
    /**
     * The secret number that the player is trying to guess.
     * Marked as {@code protected} so it can be accessed directly by child classes.
     */
    protected int targetNumber; // Protected agar bisa diakses anak class

    /**
     * Default constructor for the GameEngine.
     */
    public GameEngine() {
        // Constructor
    }

    /**
     * Generates the hidden target number.
     * <p>
     * This method is <b>abstract</b>, meaning specific game implementations (subclasses)
     * must define their own logic for how the number is generated
     * (e.g., specific ranges or patterns).
     * </p>
     */
    // Method ini wajib ada, tapi cara kerjanya ditentukan class anak (Polymorphism)
    public abstract void generateNumber();

    /**
     * Retrieves the current target number.
     *
     * @return The integer value of the secret number.
     */
    // Method konkrit (biasa)
    public int getTargetNumber() {
        return targetNumber;
    }

    /**
     * Compares the user's guess against the target number.
     *
     * @param guess The integer guessed by the player.
     * @return A {@code GuessResult} enum indicating if the guess was
     * {@code CORRECT}, {@code TOO_LOW}, or {@code TOO_HIGH}.
     */
    // Method logika dasar
    public GuessResult checkGuess(int guess) {
        if (guess == targetNumber) return GuessResult.CORRECT;
        if (guess < targetNumber) return GuessResult.TOO_LOW;
        return GuessResult.TOO_HIGH;
    }
}