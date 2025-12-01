public abstract class GameMode {
    protected int targetNumber;
    
    public abstract int getMaxRange();    
    public abstract int getInitialLives();
    public abstract String getDifficultyName(); 

    public void generateNumber() {
        this.targetNumber = (int) (Math.random() * getMaxRange()) + 1;
    }

    public int getTargetNumber() {
        return targetNumber;
    }
    
    public GuessResult checkGuess(int guess) {
        if (guess == targetNumber) return GuessResult.CORRECT;
        if (guess < targetNumber) return GuessResult.TOO_LOW;
        return GuessResult.TOO_HIGH;
    }

    public boolean isGreaterThanOrEqual(int n) {
        return targetNumber >= n;
    }

    public boolean isLessThanOrEqual(int n) {
        return targetNumber <= n;
    }
}
