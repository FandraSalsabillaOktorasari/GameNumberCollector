public class BinaryGame extends GameEngine {

    /**
     * Generates a random target number for the game.
     * <p>
     * This method calculates a random integer between 0 and 100 (inclusive)
     * and assigns it to the {@code targetNumber} field.
     * </p>
     */
    @Override
    public void generateNumber() {
        this.targetNumber = (int) (Math.random() * 101);
    }

    /**
     * Checks if the target number is greater than or equal to the input value.
     * This is useful for inclusive binary search logic.
     *
     * @param n The integer input (lower bound) to compare against.
     * @return {@code true} if targetNumber >= n, otherwise {@code false}.
     */
    // --- METHOD BARU (INCLUSIVE) ---
    public boolean isGreaterThanOrEqual(int n) {
        return targetNumber >= n;
    }

    /**
     * Checks if the target number is less than or equal to the input value.
     * This is useful for inclusive binary search logic.
     *
     * @param n The integer input (upper bound) to compare against.
     * @return {@code true} if targetNumber <= n, otherwise {@code false}.
     */
    public boolean isLessThanOrEqual(int n) {
        return targetNumber <= n;
    }
}