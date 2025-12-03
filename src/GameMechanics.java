public interface GameMechanics { 
    // each game must generate random number
    void generateNumber();

    // each game must validate player guess 
    GuessResult checkGuess(int guess);
}
