// Pixel class to represent individual pixels
public class Pixel {
    private int red;
    private int green;
    private int blue;

    public Pixel(int red, int green, int blue) {
        this.red = clamp(red);
        this.green = clamp(green);
        this.blue = clamp(blue);
    }

    // Clamp values to valid RGB range (0-255)
    private int clamp(int value) {
        return Math.max(0, Math.min(255, value));
    }

    // Getters
    public int getRed() { return red; }
    public int getGreen() { return green; }
    public int getBlue() { return blue; }

    // Setters
    public void setRed(int red) { this.red = clamp(red); }
    public void setGreen(int green) { this.green = clamp(green); }
    public void setBlue(int blue) { this.blue = clamp(blue); }

    @Override
    public String toString() {
        return String.format("RGB(%d,%d,%d)", red, green, blue);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pixel pixel = (Pixel) obj;
        return red == pixel.red && green == pixel.green && blue == pixel.blue;
    }
}
