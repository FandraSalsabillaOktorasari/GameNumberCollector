public class Player {
    private int lives; // Atribut private (Encapsulation)

    public Player(int maxLives) {
        this.lives = maxLives;
    }

    public int getLives() {
        return lives;
    }

    public void decreaseLife() {
        if (lives > 0) {
            lives--;
        }
    }

    public boolean isAlive() {
        return lives > 0;
    }

    public void reset(int maxLives) {
        this.lives = maxLives;
    }
}
