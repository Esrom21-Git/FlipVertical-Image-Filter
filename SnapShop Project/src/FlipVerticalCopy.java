// Alternative FlipVertical implementation using array creation
public class FlipVerticalCopy implements Filter {

    public void filter(PixelImage theImage) {
        if (theImage == null) return;

        Pixel[][] originalData = theImage.getData();
        if (originalData == null) return;

        int height = theImage.getHeight();
        int width = theImage.getWidth();

        if (height <= 0 || width <= 0) return;

        // Create a new flipped array
        Pixel[][] flippedData = new Pixel[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                flippedData[row][col] = originalData[height - 1 - row][col];
            }
        }

        theImage.setData(flippedData);
    }
}
