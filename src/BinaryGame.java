public class BinaryGame extends GameEngine {

    @Override
    public void generateNumber() {
        this.targetNumber = (int) (Math.random() * 101);
    }

    // Method lama (Boleh dihapus jika tidak dipakai, tapi biarkan saja tidak apa-apa)
    public boolean isGreaterThan(int n) { return targetNumber > n; }
    public boolean isLessThan(int n) { return targetNumber < n; }

    // --- METHOD BARU (INCLUSIVE) ---
    public boolean isGreaterThanOrEqual(int n) {
        return targetNumber >= n;
    }

    public boolean isLessThanOrEqual(int n) {
        return targetNumber <= n;
    }
}