// Main FlipVertical filter implementation
public class FlipVertical implements Filter {

    /**
     * Flips the image vertically by reversing the order of rows
     *
     * @param theImage the image to be transformed
     */
    public void filter(PixelImage theImage) {
        if (theImage == null) {
            System.err.println("Error: Cannot flip null image");
            return;
        }

        Pixel[][] data = theImage.getData();
        if (data == null) {
            System.err.println("Error: Image data is null");
            return;
        }

        int height = theImage.getHeight();
        int width = theImage.getWidth();

        if (height <= 0 || width <= 0) {
            System.err.println("Error: Invalid image dimensions");
            return;
        }

        // Swap rows from top and bottom working toward the center
        for (int row = 0; row < height / 2; row++) {
            for (int col = 0; col < width; col++) {
                // Swap pixel at (row, col) with pixel at (height - 1 - row, col)
                Pixel temp = data[row][col];
                data[row][col] = data[height - 1 - row][col];
                data[height - 1 - row][col] = temp;
            }
        }

        // No need to call setData() since we're modifying the original array
    }
}
