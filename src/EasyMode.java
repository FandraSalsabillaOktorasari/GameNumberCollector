public class EasyMode extends GameMode {
    @Override
    public int getMaxRange() { return 10; }

    @Override
    public int getInitialLives() { return 3; }

    @Override
    public String getDifficultyName() { return "Easy (1-10)"; }
}
