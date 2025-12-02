public class Player {
    /**
     * The current number of lives/attempts remaining.
     * This field is {@code private} to enforce encapsulation.
     */
    private int lives; // Atribut private (Encapsulation)

    /**
     * Constructs a new Player object with a specific starting number of lives.
     *
     * @param maxLives The initial number of lives the player starts with.
     */
    public Player(int maxLives) {
        this.lives = maxLives;
    }

    /**
     * Retrieves the current number of lives.
     *
     * @return The integer value of remaining lives.
     */
    public int getLives() {
        return lives;
    }

    /**
     * Decreases the player's life count by 1.
     * This method includes a safety check to ensure lives do not drop below zero.
     */
    public void decreaseLife() {
        if (lives > 0) {
            lives--;
        }
    }

    /**
     * Checks if the player is still active (has remaining lives).
     *
     * @return {@code true} if lives > 0, otherwise {@code false} (Game Over).
     */
    public boolean isAlive() {
        return lives > 0;
    }

    /**
     * Resets the player's state for a new game session.
     *
     * @param maxLives The number of lives to restore.
     */
    public void reset(int maxLives) {
        this.lives = maxLives;
    }
}
