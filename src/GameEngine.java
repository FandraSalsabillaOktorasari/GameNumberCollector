public abstract class GameEngine {
    protected int targetNumber; // Protected agar bisa diakses anak class

    public GameEngine() {
        // Constructor
    }

    // Method ini wajib ada, tapi cara kerjanya ditentukan class anak (Polymorphism)
    public abstract void generateNumber();

    // Method konkrit (biasa)
    public int getTargetNumber() {
        return targetNumber;
    }

    // Method logika dasar
    public GuessResult checkGuess(int guess) {
        if (guess == targetNumber) return GuessResult.CORRECT;
        if (guess < targetNumber) return GuessResult.TOO_LOW;
        return GuessResult.TOO_HIGH;
    }
}
