// PixelImage class to represent the image
public class PixelImage {
    private Pixel[][] data;
    private int width;
    private int height;

    public PixelImage(int width, int height) {
        this.width = width;
        this.height = height;
        this.data = new Pixel[height][width];

        // Initialize with black pixels
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                data[row][col] = new Pixel(0, 0, 0);
            }
        }
    }

    public PixelImage(Pixel[][] pixelData) {
        if (pixelData == null || pixelData.length == 0 || pixelData[0] == null) {
            throw new IllegalArgumentException("Invalid pixel data");
        }

        this.height = pixelData.length;
        this.width = pixelData[0].length;
        this.data = new Pixel[height][width];

        // Deep copy the pixel data
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (pixelData[row][col] != null) {
                    this.data[row][col] = new Pixel(
                            pixelData[row][col].getRed(),
                            pixelData[row][col].getGreen(),
                            pixelData[row][col].getBlue()
                    );
                } else {
                    this.data[row][col] = new Pixel(0, 0, 0);
                }
            }
        }
    }

    // Getters
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public Pixel[][] getData() {
        return data; // Returns reference to actual data array
    }

    public Pixel getPixel(int row, int col) {
        if (row >= 0 && row < height && col >= 0 && col < width) {
            return data[row][col];
        }
        return null;
    }

    // Setters
    public void setData(Pixel[][] newData) {
        if (newData != null && newData.length == height &&
                newData.length > 0 && newData[0].length == width) {
            this.data = newData;
        }
    }

    public void setPixel(int row, int col, Pixel pixel) {
        if (row >= 0 && row < height && col >= 0 && col < width && pixel != null) {
            data[row][col] = pixel;
        }
    }

    // Create a copy of the image
    public PixelImage copy() {
        return new PixelImage(this.data);
    }

    // Display image as text (for testing)
    public void display() {
        System.out.println("Image " + width + "x" + height + ":");
        for (int row = 0; row < Math.min(height, 10); row++) {
            for (int col = 0; col < Math.min(width, 10); col++) {
                System.out.printf("(%3d,%3d,%3d) ",
                        data[row][col].getRed(),
                        data[row][col].getGreen(),
                        data[row][col].getBlue());
            }
            if (width > 10) System.out.print("...");
            System.out.println();
        }
        if (height > 10) System.out.println("...");
    }
}
