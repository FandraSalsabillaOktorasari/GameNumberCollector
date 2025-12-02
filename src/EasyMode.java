public class EasyMode extends GameMode {
    /**
     * Defines the upper bound for the target number generation.
     *
     * @return The integer 10, meaning the target number will be between 0 and 10.
     */
    @Override
    public int getMaxRange() { return 10; }

    /**
     * Specifies the number of lives/attempts allowed in this mode.
     *
     * @return The integer 3, giving the player a small but fair number of tries
     * relative to the small number range.
     */
    @Override
    public int getInitialLives() { return 3; }

    /**
     * Provides a user-friendly display name for this difficulty level.
     * This string is typically used in UI components (like a JComboBox).
     *
     * @return The String "Easy (1-10)".
     */
    @Override
    public String getDifficultyName() { return "Easy (1-10)"; }
}
