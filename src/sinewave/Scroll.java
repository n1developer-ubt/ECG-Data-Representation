package sinewave;

public interface Scroll {
    public abstract void Increase();
    public abstract void Decrease();
    public abstract void ChangeXRange(int x, int y);
    public abstract void ChangeYRange(int x, int y);
    public abstract void Up();
    public abstract void Down();
}
