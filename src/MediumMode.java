public class MediumMode extends GameMode {
    @Override
    public int getMaxRange() { return 100; }
    
    @Override
    public int getInitialLives() { return 7; }
    
    @Override
    public String getDifficultyName() { return "Medium (1-100)"; }
}
