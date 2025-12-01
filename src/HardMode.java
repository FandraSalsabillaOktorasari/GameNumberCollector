public class HardMode extends GameMode {
    @Override
    public int getMaxRange() { return 1000; }
    
    @Override
    public int getInitialLives() { return 10; }
    
    @Override
    public String getDifficultyName() { return "Hard (1-1000)"; }
}
